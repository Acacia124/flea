package site.acacia.flea.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import site.acacia.flea.mapper.TbCollectMapper;
import site.acacia.flea.mapper.TbItemCatMapper;
import site.acacia.flea.mapper.TbItemMapper;
import site.acacia.flea.mapper.TbUserMapper;
import site.acacia.flea.pojo.EasyUIDataGridResult;
import site.acacia.flea.pojo.ItemDetail;
import site.acacia.flea.pojo.TbCollect;
import site.acacia.flea.pojo.TbCollectExample;
import site.acacia.flea.pojo.TbItem;
import site.acacia.flea.pojo.TbItemCat;
import site.acacia.flea.pojo.TbItemCatExample;
import site.acacia.flea.pojo.TbItemExample;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.ItemService;
import site.acacia.flea.util.IDUtils;
import site.acacia.flea.vo.G2Statistics;
import site.acacia.flea.vo.G2VoCopy;

/**
 * 商品服务
 * 
 * @author 张胤
 *
 *         2018年8月26日-下午9:55:56
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private TbCollectMapper collectMapper;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	private static final String ITEMINFO_PREFIX = "ITEMINFO:";

	private static final String ITEMINFO_SUFFIX = ":DESC";

	private static final int ITEMINFO_TIME = 24;

	protected static Logger logger = LoggerFactory.getLogger(ItemService.class);

	/**
	 * 查询商品
	 */
	@Override
	public ItemDetail getItemByItemId(String itemId) {
		logger.info("==> Request parameter: itemId(" + itemId + ")");
		// 1.根据Id查询缓存
		ValueOperations<String, String> ops = null;
		boolean flg = false;
		String detail = null;
		try {
			ops = redisTemplate.opsForValue();
			detail = ops.get(ITEMINFO_PREFIX + itemId + ITEMINFO_SUFFIX);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("<== Redis 缓存库出错: " + e.getMessage());
			flg = true;
		}
		if (!flg) {
			// 2.存在更新缓存过期时间
			if (StringUtils.isNotBlank(detail)) {
				ItemDetail parseObject = JSON.parseObject(detail, ItemDetail.class);
				ops.set(ITEMINFO_PREFIX + itemId + ITEMINFO_SUFFIX, detail, ITEMINFO_TIME, TimeUnit.HOURS);
				return parseObject;
			}
		}
		// 3.不存在或者缓存失效查询数据库
		// 3.1查询商品详情
		TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
		if (tbItem == null) {
			return null;
		}
		// 3.2查询发布商品人信息
		TbUser tbUser = userMapper.selectByPrimaryKey(tbItem.getOpenid());
		// 3.3查询商品留言
		// 4.整合数据
		ItemDetail itemDetail = new ItemDetail();
		itemDetail.setItem(tbItem);
		tbUser.setSessionKey(null);
		tbUser.setScore(0);
		itemDetail.setUser(tbUser);
		if (!flg) {
			// 将数据存入缓存过期时间设置为24个小时
			String json = JSON.toJSONString(itemDetail);
			ops.set(ITEMINFO_PREFIX + itemId + ITEMINFO_SUFFIX, json, ITEMINFO_TIME, TimeUnit.HOURS);
		}
		return itemDetail;
	}

	/**
	 * 增加商品
	 */
	@Override
	public WeResult addItem(TbItem item) {
		logger.info("==> Request parameter: item(" + item + ")");
		final long genItemId = IDUtils.genItemId();
		item.setItemId(genItemId + "");
		item.setCollectNum(0);
		// 商品状态，1-正常，2-免费，3-已售
		if (item.getStatus() == 0) {
			item.setStatus((byte) 1);
		} else {
			item.setStatus((byte) 2);
		}
		item.setItemUpdate(new Date());
		item.setItemCreated(new Date());
		itemMapper.insertSelective(item);

		Destination destination = new ActiveMQQueue("itemMeaage");
		try {
			jmsTemplate.convertAndSend(destination, item.getItemId() + "");
		} catch (Exception e) {
			logger.error("<== ActiveMq 中间件出错: " + e.getMessage());
		}
		return WeResult.ok();
	}

	/**
	 * 商品列表
	 */
	@Override
	public EasyUIDataGridResult<TbItem> getItemList(int page, int rows, Integer[] catId, String userId, String itemName,
			Long startPrice, Long endPrice, String[] schoolList, Byte[] status, Date startDate, Date endDate) {
		logger.info("==> Request parameter: page(" + page + "),rows(" + rows + "),catId(" + catId + "),userId(" + userId
				+ ")," + "itemName(" + itemName + "),startPrice(" + startPrice + ")," + "endPrice(" + endPrice
				+ "),schoolList(" + Arrays.toString(schoolList) + "),status(" + Arrays.toString(status) + "),startDate("
				+ startDate + "),endDate(" + endDate + ")");
		// 设置分页信息
		PageHelper.startPage(page, rows);
		// 执行查询
		TbItemExample example = new TbItemExample();
		site.acacia.flea.pojo.TbItemExample.Criteria createCriteria = example.createCriteria();
		List<Integer> arrayList = new ArrayList<>();
		if (catId != null && catId.length > 0) {
			// 查询Id是否为父Id，为父Id则将子Id插入list，反之直接插入
			TbItemCatExample catExample = new TbItemCatExample();
			site.acacia.flea.pojo.TbItemCatExample.Criteria createCriteria2 = catExample.createCriteria();
			for (int i = 0; i < catId.length; i++) {
				createCriteria2.andParentIdEqualTo(catId[i]);
				List<TbItemCat> selectByExample = itemCatMapper.selectByExample(catExample);
				if (selectByExample == null || selectByExample.size() <= 0) {
					arrayList.add(catId[i]);
				} else {
					for (TbItemCat tbItemCat : selectByExample) {
						arrayList.add(tbItemCat.getCatId());
					}
				}
			}
			createCriteria.andCatIdIn(arrayList);
		}
		if (StringUtils.isNotBlank(userId)) {
			createCriteria.andOpenidEqualTo(userId);
		}
		if (StringUtils.isNotBlank(itemName)) {
			createCriteria.andItemTitleLike("%" + itemName + "%");
		}
		if (startPrice >= 0) {
			if (endPrice >= 0) {
				createCriteria.andPriceBetween(startPrice, endPrice);
			} else {
				createCriteria.andPriceGreaterThanOrEqualTo(startPrice);
			}
		} else {
			if (endPrice >= 0) {
				createCriteria.andPriceLessThanOrEqualTo(endPrice);
			}
		}
		if (schoolList != null && schoolList.length > 0) {
			createCriteria.andSellAddressIn(Arrays.asList(schoolList));
		}
		if (status != null && status.length > 0) {
			createCriteria.andStatusIn(Arrays.asList(status));
		}
		if (startDate != null && endDate != null) {
			createCriteria.andItemCreatedBetween(startDate, endDate);
		}
		List<TbItem> list = itemMapper.selectByExample(example);
		// 取分页信息，PageInfo。1、总记录数2、总页数 。当前页码
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		pageInfo.setList(list);
		EasyUIDataGridResult<TbItem> dataGridResult = new EasyUIDataGridResult<TbItem>();
		dataGridResult.setRows(list);
		dataGridResult.setTotal(pageInfo.getTotal());
		return dataGridResult;
	}

	/**
	 * 更新商品信息
	 */
	@Override
	@Transactional
	public WeResult updateItemByItemId(TbItem record) {
		logger.info("==> Request parameter: record(" + record + ")");
		record.setItemUpdate(new Date());
		itemMapper.updateByPrimaryKeySelective(record);
		TbCollectExample collectExample = new TbCollectExample();
		site.acacia.flea.pojo.TbCollectExample.Criteria createCriteria = collectExample.createCriteria();
		// 更新收藏表
		createCriteria.andItemIdEqualTo(record.getItemId());
		TbCollect collect = new TbCollect();
		if (record.getStatus() != null) {
			collect.setCollectStatus(record.getStatus());
		}
		if (StringUtils.isNotBlank(record.getItemImage())) {
			collect.setCollectImage(record.getItemImage());
		}
		if (StringUtils.isNotBlank(record.getItemTitle())) {
			collect.setCollectName(record.getItemTitle());
		}
		if (StringUtils.isNotBlank(record.getSellAddress())) {
			collect.setCollectSellSchool(record.getSellAddress());
		}
		if (StringUtils.isNotBlank(record.getSellPoint())) {
			collect.setCollectSellPoint(record.getSellPoint());
		}
		if (record.getPrice() != null) {
			collect.setCollectPrice(record.getPrice());
		}
		if (record.getOriginalPrice() != null) {
			collect.setCollectOriginalPrice(record.getOriginalPrice());
		}
		if (record.getNewnessRate() != null) {
			collect.setCollectNewnessRate(record.getNewnessRate());
		}
		// 更新收藏表
		collectMapper.updateByExampleSelective(collect, collectExample);
		// 删除redis
		try {
			redisTemplate.delete(ITEMINFO_PREFIX + record.getItemId() + ITEMINFO_SUFFIX);
		} catch (Exception e) {
			logger.error("<== Redis 缓存库出错: " + e.getMessage());
		}
		// 更新solr（下架或违规下架则删除solr中产品信息，更新则更爱solr产品库信息）
		if (record.getStatus() != null && (record.getStatus() == 4 || record.getStatus() == 3)) {
			Destination destination = new ActiveMQQueue("soldOutMeaage");
			try {
				jmsTemplate.convertAndSend(destination, record.getItemId() + "");
			} catch (Exception e) {
				logger.error("<== ActiveMq 中间件出错: " + e.getMessage());
			}
		} else {
			Destination destination = new ActiveMQQueue("itemMeaage");
			try {
				jmsTemplate.convertAndSend(destination, record.getItemId() + "");
			} catch (Exception e) {
				logger.error("<== ActiveMq 中间件出错: " + e.getMessage());
			}
		}
		return WeResult.ok();
	}

	/**
	 * 根据用户Id下架其所有产品
	 */
	@Override
	@Transactional
	public WeResult banItemByUserId(String userId) {
		logger.info("==> Request parameter: userId(" + userId + ")");
		TbItemExample example = new TbItemExample();
		site.acacia.flea.pojo.TbItemExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andOpenidEqualTo(userId);
		TbItem record = new TbItem();
		record.setItemUpdate(new Date());
		record.setStatus((byte) 4);
		itemMapper.updateByExampleSelective(record, example);

		return WeResult.ok();
	}

	/**
	 * 查询商品异步
	 */
	@Override
	public ItemDetail getItemByItemId(String itemId, String openId) {
		Destination destination = new ActiveMQQueue("addItemHis");
		try {
			jmsTemplate.convertAndSend(destination, itemId + "#" + openId);
		} catch (Exception e) {
			logger.error("<== ActiveMq 中间件出错: " + e.getMessage());
		}
		return getItemByItemId(itemId);
	}

	@Override
	public G2Statistics getItemData() {
		return itemMapper.getItemData();
	}

	@Override
	public List<G2VoCopy> getItemG2() {
		return itemMapper.getItemG2();
	}

}
