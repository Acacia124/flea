/**
 * 
 */
package site.acacia.flea.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import site.acacia.flea.mapper.TbItemSchoolMapper;
import site.acacia.flea.pojo.TbItemSchool;
import site.acacia.flea.pojo.TbItemSchoolExample;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.ItemSchoolService;

/**
 * @author 张胤
 *
 *         2018年11月9日-上午10:53:29
 */
@Service
public class ItemSchoolServiceImpl implements ItemSchoolService {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private TbItemSchoolMapper schoolMapper;

	private static final String ITEM_SCHOOL = "ITEM:SCHOOL";

	protected static Logger logger = LoggerFactory.getLogger(ItemSchoolService.class);

	@Override
	public List<TbItemSchool> getSchoolList() {
		logger.info("==> Request parameter Empty");
		ValueOperations<String, String> ops = null;
		String str = null;
		boolean flg = false;
		try {
			ops = redisTemplate.opsForValue();
			str = ops.get(ITEM_SCHOOL);
		} catch (Exception e) {
			logger.error("<== Redis 缓存库出错: " + e.getMessage());
			flg = true;
		}
		if (!flg) {
			if (StringUtils.isNotBlank(str)) {
				return JSON.parseArray(str, TbItemSchool.class);
			}
		}
		TbItemSchoolExample example = new TbItemSchoolExample();
		List<TbItemSchool> list = schoolMapper.selectByExample(example);
		if (!flg) {
			ops.set(ITEM_SCHOOL, JSON.toJSONString(list));
		}
		return list;
	}

	@Override
	public WeResult delateSchoolById(Integer id) {
		logger.info("==> Request parameter: id(" + id + ")");
		if (schoolMapper.deleteByPrimaryKey(id) > 0) {
			try {
				redisTemplate.delete(ITEM_SCHOOL);
			} catch (Exception e) {
				logger.error("<== Redis 缓存库出错: " + e.getMessage());
			}
			return WeResult.ok();
		} else {
			return WeResult.build(402, "该地址不存在");
		}
	}

	@Override
	public WeResult updateSchoolNameById(TbItemSchool itemSchool) {
		logger.info("==> Request parameter: itemSchool(" + itemSchool + ")");
		if (schoolMapper.updateByPrimaryKeySelective(itemSchool) > 0) {
			try {
				redisTemplate.delete(ITEM_SCHOOL);
			} catch (Exception e) {
				logger.error("<== Redis 缓存库出错: " + e.getMessage());
			}
			return WeResult.ok();
		} else {
			return WeResult.build(402, "修改地址失败");
		}
	}

	@Override
	public WeResult addSchool(TbItemSchool itemSchool) {
		logger.info("==> Request parameter: itemSchool(" + itemSchool + ")");
		itemSchool.setCreated(new Date());
		if (schoolMapper.insertSelective(itemSchool) > 0) {
			try {
				redisTemplate.delete(ITEM_SCHOOL);
			} catch (Exception e) {
				logger.error("<== Redis 缓存库出错: " + e.getMessage());
			}
			return WeResult.ok();
		} else {
			return WeResult.build(402, "地址已存在，请勿重复添加");
		}
	}

}
