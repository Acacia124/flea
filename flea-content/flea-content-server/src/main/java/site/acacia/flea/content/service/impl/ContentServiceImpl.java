package site.acacia.flea.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import site.acacia.flea.content.service.ContentService;
import site.acacia.flea.mapper.TbContentMapper;
import site.acacia.flea.pojo.EasyUIDataGridResult;
import site.acacia.flea.pojo.TbContent;
import site.acacia.flea.pojo.TbContentExample;
import site.acacia.flea.pojo.TbContentExample.Criteria;
import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2018年8月6日-下午12:40:30
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Autowired
	private StringRedisTemplate redisTemplate;

	private String CONTENT_LIST = "CONTENT_LIST";

	protected static Logger logger = LoggerFactory.getLogger(ContentService.class);

	/**
	 * 发布内容
	 */
	@Override
	public WeResult addContent(TbContent content) {
		content.setContentUpdate(new Date());
		content.setContentCreate(new Date());
		contentMapper.insert(content);

		// 缓存同步，删除对应hash的filed
		try {
			HashOperations<String, String, String> vo = redisTemplate.opsForHash();
			vo.delete(CONTENT_LIST, content.getConCatId().toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("<== Redis 缓存库出错: " + e.getMessage());
		}
		return WeResult.ok();
	}

	/**
	 * 根据分类获取内容列表
	 */
	@Override
	public EasyUIDataGridResult<TbContent> getContentList(int categoryId, int page, int rows) {
		// 设置分页信息
		PageHelper.startPage(page, rows);
		// 执行查询
		TbContentExample example = new TbContentExample();
		Criteria createCriteria = example.createCriteria();
		if (categoryId != 0) {
			createCriteria.andConCatIdEqualTo(categoryId);
		}
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		// 取分页信息，PageInfo。1、总记录数2、总页数 。当前页码
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		pageInfo.setList(list);
		EasyUIDataGridResult<TbContent> dataGridResult = new EasyUIDataGridResult<TbContent>();
		dataGridResult.setRows(list);
		dataGridResult.setTotal(pageInfo.getTotal());
		return dataGridResult;
	}

	/**
	 * 获取内容列表
	 */
	@Override
	public List<TbContent> getContentListByCid(int cid) {
		// 查询缓存，如果缓存中有，直接返回
		boolean flg = true;
		HashOperations<String, String, String> vo = null;
		try {
			vo = redisTemplate.opsForHash();
			String json = vo.get(CONTENT_LIST, cid + "");
			if (StringUtils.isNoneBlank(json)) {
				List<TbContent> list = JSON.parseArray(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("<== Redis 缓存库出错: " + e.getMessage());
			flg = false;
		}
		// 如果没有查询数据库
		TbContentExample example = new TbContentExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andConCatIdEqualTo(cid);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		// 查询到加入缓存
		if (flg) {
			vo.put(CONTENT_LIST, cid + "", JSON.toJSONString(list));
		}
		// 返回
		return list;
	}

	/**
	 * 根据分类Id删除内容
	 */
	@Override
	public WeResult delateTbContentByCatId(int cid) {
		// 1.将所属分类内容删除
		TbContentExample example = new TbContentExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andConCatIdEqualTo(cid);
		contentMapper.deleteByExample(example);
		// 2.将缓存中分类内容删除
		try {
			redisTemplate.opsForHash().delete(CONTENT_LIST, cid + "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("<== Redis 缓存库出错: " + e.getMessage());
		}
		return WeResult.ok();
	}

	/**
	 * 根据商品Id更新商品
	 */
	@Override
	@Transactional
	public WeResult updateTbContentById(TbContent content) {
		// 1.更新数据库
		contentMapper.updateByPrimaryKeySelective(content);
		// 根据数据库查询所述Id；
		content = contentMapper.selectByPrimaryKey(content.getContentId());
		// 2.删除redis对应的hash键值
		try {
			redisTemplate.opsForHash().delete(CONTENT_LIST, content.getConCatId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("<== Redis 缓存库出错: " + e.getMessage());
		}
		return WeResult.ok();
	}

	/**
	 * 根据内容2Id删除内容
	 */
	@Override
	@Transactional
	public WeResult delateTbContentById(int id) {
		contentMapper.deleteByPrimaryKey(id);
		// 根据数据库查询所述Id；
		TbContent content = contentMapper.selectByPrimaryKey(id);
		try {
			redisTemplate.opsForHash().delete(CONTENT_LIST, content.getConCatId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("<== Redis 缓存库出错: " + e.getMessage());
		}
		return null;
	}

}
