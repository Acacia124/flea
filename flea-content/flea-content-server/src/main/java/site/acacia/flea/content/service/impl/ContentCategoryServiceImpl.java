package site.acacia.flea.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import site.acacia.flea.content.service.ContentCategoryService;
import site.acacia.flea.mapper.TbContentCatMapper;
import site.acacia.flea.pojo.TbContentCat;
import site.acacia.flea.pojo.TbContentCatExample;
import site.acacia.flea.pojo.TbContentCatExample.Criteria;
import site.acacia.flea.pojo.TreeNode;
import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2018年8月5日-下午10:46:08
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCatMapper categoryMapper;

	@Override
	public List<TreeNode> getContentCatList(int productId) {

		TbContentCatExample categoryExample = new TbContentCatExample();
		Criteria createCriteria = categoryExample.createCriteria();
		createCriteria.andConCatParentIdEqualTo(productId);
		List<TbContentCat> selectByExample = categoryMapper.selectByExample(categoryExample);
		List<TreeNode> list = new ArrayList<>();
		for (TbContentCat category : selectByExample) {
			TreeNode node = new TreeNode();
			node.setKey(category.getConCatId());
			node.setValue(category.getConCatId());
			node.setLeaf(category.getConCatIsParent() == 1 ? false : true);
			node.setTitle(category.getConCatName());
			list.add(node);
		}
		return list;
	}

	@Transactional
	@Override
	public WeResult addContentCategory(int parentId, String name) {
		// 创建一个tb_content_category对象
		TbContentCat category = new TbContentCat();
		// 补全属性
		category.setCreated(new Date());
		category.setConCatIsParent((byte) 0);
		category.setConCatParentId(parentId);
		// 1正常，2删除
		category.setConCatStatus((byte) 1);
		// 默认排序1
		category.setConCatSortOrder(1);
		category.setCreated(new Date());
		category.setConCatName(name);
		// 插入数据库
		categoryMapper.insert(category);
		// 更改父节点isParent属性为true
		TbContentCat category2 = categoryMapper.selectByPrimaryKey(parentId);
		if (category2.getConCatIsParent() == 0) {
			category2.setConCatIsParent((byte) 1);
			categoryMapper.updateByPrimaryKey(category2);
		}
		// 返回数据
		return WeResult.ok(category);
	}

	@Override
	public int updateContentCategory(int id, String name) {
		TbContentCat category = new TbContentCat();
		category.setConCatId(id);
		category.setCreated(new Date());
		category.setConCatName(name);
		return categoryMapper.updateByPrimaryKeySelective(category);
	}

	@Override
	public int deleteContentCategory(int id) {
		// 获得删除节点信息
		TbContentCat category = categoryMapper.selectByPrimaryKey(id);
		int parentId = category.getConCatParentId();
		// 获取同级节点信息
		TbContentCatExample categoryExample = new TbContentCatExample();
		Criteria createCriteria = categoryExample.createCriteria();
		createCriteria.andConCatParentIdEqualTo(parentId);
		if (categoryMapper.selectByExample(categoryExample).size() == 1) {
			// 存在同级节点不修改父节点isParend选项否则修改
			TbContentCat parent = categoryMapper.selectByPrimaryKey(parentId);
			parent.setConCatIsParent((byte) 0);
			categoryMapper.updateByPrimaryKey(parent);
		}
		try {
			// 删除子节点
			delete(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public void delete(int id) {
		TbContentCat category = categoryMapper.selectByPrimaryKey(id);
		if (category.getConCatIsParent() == 1) {
			// 获取下一级子节点信息
			TbContentCatExample categoryExample = new TbContentCatExample();
			Criteria createCriteria = categoryExample.createCriteria();
			createCriteria.andConCatParentIdEqualTo(id);
			List<TbContentCat> selectByExample = categoryMapper.selectByExample(categoryExample);
			for (TbContentCat TbContentCat : selectByExample) {
				delete(TbContentCat.getConCatId());
			}
			// 删除后更新本身信息
			System.out.println("删除节点" + id);
			categoryMapper.deleteByPrimaryKey(id);
		} else {
			System.out.println("删除节点" + id);
			categoryMapper.deleteByPrimaryKey(id);
		}
	}

}
