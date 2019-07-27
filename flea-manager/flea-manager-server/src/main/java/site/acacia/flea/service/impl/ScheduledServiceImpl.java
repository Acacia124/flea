package site.acacia.flea.service.impl;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import site.acacia.flea.mapper.TbItemMapper;
import site.acacia.flea.pojo.ItemDetail;
import site.acacia.flea.pojo.TbItem;
import site.acacia.flea.service.ItemService;

/**
 * 定时任务类
 * 
 * @author 张胤
 *
 *         2018年9月18日-上午11:10:03
 */
@Component
public class ScheduledServiceImpl {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private StringRedisTemplate redisTemplate;

	protected static Logger logger = LoggerFactory.getLogger(ItemService.class);

	/*
	 * 凌晨2点触发 对缓存中商品收藏人数进行更新到数据库操作
	 */
	@Scheduled(cron = "0 0 2 * * ?")
	public void timingWriteTbItem() {
		logger.info("==> 02:00定时任务触发");
		String key = "ITEMINFO*";
		// 从缓存中读取所有的商品信息
		Set<String> keys = redisTemplate.keys(key);
		System.out.println("触发定时任务" + new Date());
		System.out.println("keys" + keys);
		// 将每个商品对应的收藏信息更新到数据库
		for (String string : keys) {
			ValueOperations<String, String> ops = redisTemplate.opsForValue();
			String detail = ops.get(string);
			if (StringUtils.isNotBlank(detail)) {
				ItemDetail parseObject = JSON.parseObject(detail, ItemDetail.class);
				TbItem item = parseObject.getItem();
				itemMapper.updateByPrimaryKeySelective(item);
			}
		}
	}
}
