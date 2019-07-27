package site.acacia.flea.service.impl;

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

import com.alibaba.fastjson.JSON;

import site.acacia.flea.mapper.TbCollectMapper;
import site.acacia.flea.pojo.ItemDetail;
import site.acacia.flea.pojo.TbCollect;
import site.acacia.flea.pojo.TbCollectExample;
import site.acacia.flea.pojo.TbCollectExample.Criteria;
import site.acacia.flea.pojo.TbItem;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.ItemCollectService;

/**
 * @author 张胤
 *
 *         2018年9月15日-下午4:25:34
 */
@Service
public class ItemCollectServiceImpl implements ItemCollectService {

	protected static Logger logger = LoggerFactory.getLogger(ItemCollectService.class);

	@Autowired
	private TbCollectMapper collectMapper;

	@Autowired
	private StringRedisTemplate redisTemplate;

	private static final String ITEMINFO_PREFIX = "ITEMINFO:";

	private static final String ITEMINFO_SUFFIX = ":DESC";

	private static final int ITEMINFO_TIME = 24;

	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	@Override
	public WeResult updateCollect(String userId, String itemId) {
		logger.info("==> Request parameter: userId(" + userId + "),itemId(" + itemId + ")");
		if (this.selectCollectByItemUser(userId, itemId).getStatus() == 200) {
			// 缓存更新
			this.updateItemCollectInRedis(itemId, 1);

			// 异步更新数据库
			Destination destination = new ActiveMQQueue("deleteItem");
			try {
				jmsTemplate.convertAndSend(destination, userId + "#" + itemId);
			} catch (Exception e) {
				logger.error("<== ActiveMq 中间件出错: " + e.getMessage());
			}
		} else {
			// 缓存更新
			this.updateItemCollectInRedis(itemId, 2);

			// 异步更新数据库
			Destination destination = new ActiveMQQueue("addItem");
			try {
				jmsTemplate.convertAndSend(destination, userId + "#" + itemId);
			} catch (Exception e) {
				logger.error("<== ActiveMq 中间件出错: " + e.getMessage());
			}
		}
		return WeResult.ok();
	}

	@Override
	public List<TbCollect> allCollectByUserId(String userId) {
		logger.info("==> Request parameter: userId(" + userId + ")");
		TbCollectExample example = new TbCollectExample();
		example.createCriteria().andOpenidEqualTo(userId);
		return collectMapper.selectByExample(example);
	}

	/*
	 * 查询是否收藏该商品
	 */
	@Override
	public WeResult selectCollectByItemUser(String userId, String itemId) {
		logger.info("==> Request parameter: userId(" + userId + "),itemId(" + itemId + ")");
		TbCollectExample example = new TbCollectExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andOpenidEqualTo(userId);
		createCriteria.andItemIdEqualTo(itemId);
		List<TbCollect> collectList = collectMapper.selectByExample(example);
		if (collectList.size() > 0) {
			return WeResult.ok();
		} else {
			return WeResult.build(201, "未收藏");
		}
	}

	/*
	 * 更改Redis中商品信息的收藏数(status 1: 减 2 增加)
	 */
	public WeResult updateItemCollectInRedis(String itemId, int status) {
		logger.info("==> Request parameter: itemId(" + itemId + "),status(" + status + ")");
		ValueOperations<String, String> ops = null;
		boolean flg = false;
		String detail = null;
		synchronized ("加锁") {
			// 1.根据Id查询缓存
			try {
				ops = redisTemplate.opsForValue();
				detail = ops.get(ITEMINFO_PREFIX + itemId + ITEMINFO_SUFFIX);
			} catch (Exception e) {
				logger.error("<== Redis 缓存库出错: " + e.getMessage());
				flg = true;
			}
			if (flg) {
				return WeResult.ok();
			}
			// 2.存在更新
			if (StringUtils.isNotBlank(detail)) {
				ItemDetail parseObject = JSON.parseObject(detail, ItemDetail.class);
				int num = parseObject.getItem().getCollectNum();
				TbItem item = parseObject.getItem();
				if (status == 1) {
					if (num != 0) {
						item.setCollectNum(num - 1);
					}
				} else if (status == 2) {
					item.setCollectNum(num + 1);
				}
				parseObject.setItem(item);
				ops.set(ITEMINFO_PREFIX + itemId + ITEMINFO_SUFFIX, JSON.toJSONString(parseObject), ITEMINFO_TIME,
						TimeUnit.HOURS);
				return WeResult.ok();
			}
		}
		return WeResult.ok();
	}

	@Override
	public WeResult clearCartItem(String userId) {
		logger.info("==> Request parameter: userId(" + userId + ")");
		TbCollectExample example = new TbCollectExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andOpenidEqualTo(userId);
		collectMapper.deleteByExample(example);
		// 省略同步更新商品收藏数操作。
		return WeResult.ok();
	}

	@Override
	public WeResult delateCartByid(String userId, String itemid) {
		logger.info("==> Request parameter: userId(" + userId + "),itemId(" + itemid + ")");
		this.updateItemCollectInRedis(itemid, 1);

		// 异步更新数据库
		Destination destination = new ActiveMQQueue("deleteItem");
		try {
			jmsTemplate.convertAndSend(destination, userId + "#" + itemid);
		} catch (Exception e) {
			logger.error("<== ActiveMq 中间件出错: " + e.getMessage());
		}
		return WeResult.ok();
	}

}
