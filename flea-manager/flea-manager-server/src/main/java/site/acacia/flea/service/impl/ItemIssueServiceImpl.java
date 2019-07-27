package site.acacia.flea.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.alibaba.fastjson.JSON;

import site.acacia.flea.mapper.TbCollectMapper;
import site.acacia.flea.mapper.TbItemMapper;
import site.acacia.flea.pojo.ItemDetail;
import site.acacia.flea.pojo.TbCollect;
import site.acacia.flea.pojo.TbCollectExample;
import site.acacia.flea.pojo.TbCollectExample.Criteria;
import site.acacia.flea.pojo.TbItem;
import site.acacia.flea.pojo.TbItemExample;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.ItemIssueService;

/**
 * @author 张胤
 *
 *         2018年9月26日-下午4:41:26
 */
@Service
public class ItemIssueServiceImpl implements ItemIssueService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	@Autowired
	private TbCollectMapper collectMapper;

	private static final String ITEMINFO_PREFIX = "ITEMINFO:";

	private static final String ITEMINFO_SUFFIX = ":DESC";

	protected static Logger logger = LoggerFactory.getLogger(ItemIssueService.class);

	/**
	 * 根据用户Id查询所发布的商品，0 在售(包括免费和付费),1收费，2 免费，3下架
	 */
	@Override
	public List<TbItem> getListItemByUserId(String userId, int status) {
		logger.info("==> Request parameter: userId(" + userId + "),status(" + status + ")");
		TbItemExample itemExample = new TbItemExample();
		site.acacia.flea.pojo.TbItemExample.Criteria createCriteria = itemExample.createCriteria();
		// 查询用户出售商品
		createCriteria.andOpenidEqualTo(userId);
		if (status == 0) {
			List<Byte> stat = new ArrayList<>();
			stat.add((byte) 2);
			stat.add((byte) 1);
			stat.add((byte) 3);
			createCriteria.andStatusIn(stat);
		} else {
			createCriteria.andStatusEqualTo((byte) status);
		}
		List<TbItem> selectByExample = itemMapper.selectByExample(itemExample);
		return selectByExample;
	}

	/**
	 * 下架产品
	 * 
	 */
	@Override
	public WeResult updateItemStatus(String itemId) {
		logger.info("==> Request parameter: itemId(" + itemId + ")");
		// 1.更新商品表
		TbItem tbItem = new TbItem();
		tbItem.setItemId(itemId);
		tbItem.setStatus((byte) 3);
		itemMapper.updateByPrimaryKeySelective(tbItem);

		// 2.更新商品详情缓存的Redis
		ValueOperations<String, String> ops = null;
		String detail = null;
		boolean flg = false;
		try {
			ops = redisTemplate.opsForValue();
			detail = ops.get(ITEMINFO_PREFIX + itemId + ITEMINFO_SUFFIX);
		} catch (Exception e) {
			logger.error("<== Redis 缓存库出错: " + e.getMessage());
			flg = true;
		}
		if (!flg) {
			if (StringUtils.isNotBlank(detail)) {
				ItemDetail parseObject = JSON.parseObject(detail, ItemDetail.class);
				tbItem = parseObject.getItem();
				tbItem.setStatus((byte) 3);
				tbItem.setItemUpdate(new Date());
				parseObject.setItem(tbItem);
				ops.set(ITEMINFO_PREFIX + itemId + ITEMINFO_SUFFIX, detail);
			}
		}

		// 3. 更新用户收藏表，
		TbCollect collect = new TbCollect();
		TbCollectExample example = new TbCollectExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andItemIdEqualTo(itemId);
		collect.setCollectStatus((byte) 3);
		collectMapper.updateByExampleSelective(collect, example);

		// 4.发送删除消息（Search模块接受然后Solr删除）
		Destination destination = new ActiveMQQueue("soldOutMeaage");
		try {
			jmsTemplate.convertAndSend(destination, itemId + "");
		} catch (Exception e) {
			logger.error("<== ActiveMq 中间件出错: " + e.getMessage());
		}
		return WeResult.ok();
	}
}
