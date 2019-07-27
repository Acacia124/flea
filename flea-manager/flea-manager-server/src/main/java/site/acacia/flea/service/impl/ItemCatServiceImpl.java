/**
 * 
 */
package site.acacia.flea.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.acacia.flea.mapper.TbItemCatMapper;
import site.acacia.flea.pojo.TbItemCat;
import site.acacia.flea.pojo.TbItemCatExample;
import site.acacia.flea.pojo.TbItemCatExample.Criteria;
import site.acacia.flea.pojo.TreeNode;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.ItemCatService;

/**
 * @author 张胤
 *
 *         2018年9月2日-下午12:25:46
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper catMapper;

	protected static Logger logger = LoggerFactory.getLogger(ItemCatService.class);

	/**
	 * 根据父节点Id查询子节点
	 * 
	 * @return
	 */
	public List<TreeNode> getItemCatlist(Integer parentId) {
		logger.info("==> Request parameter: parentId(" + parentId + ")");
		TbItemCatExample example = new TbItemCatExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = catMapper.selectByExample(example);
		List<TreeNode> nodes = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			TreeNode node = new TreeNode();
			node.setValue(tbItemCat.getCatId());
			node.setKey(tbItemCat.getCatId());
			node.setTitle(tbItemCat.getCatName());
			node.setImageUrl(tbItemCat.getCatImage());
			node.setLeaf(tbItemCat.getCatIsParent() == 1 ? false : true);
			nodes.add(node);
		}
		return nodes;
	}

	@Override
	public List<TreeNode> getItemCatlist() {
		List<TreeNode> root = getItemCatlist(0);
		for (TreeNode treeNode : root) {
			List<TreeNode> children = getItemCatlist((int) treeNode.getValue());
			treeNode.setChildren(children);
		}
		return root;
	}

	@Override
	public WeResult addContentCategory(int parentId, String name) {
		logger.info("==> Request parameter: parentId(" + parentId + "),name(" + name + ")");
		// 创建一个tb_content_category对象
		TbItemCat itemCat = new TbItemCat();
		// 补全属性
		itemCat.setCatIsParent((byte) 0);
		itemCat.setParentId(parentId);
		// 1正常，2删除
		itemCat.setCatStatus((byte) 1);
		// 默认排序1
		itemCat.setCatSortOrder(1);
		itemCat.setCatName(name);
		// 插入数据库
		catMapper.insertSelective(itemCat);
		// 更改父节点isParent属性为true
		TbItemCat itemCatParent = catMapper.selectByPrimaryKey(parentId);
		if (itemCatParent.getCatIsParent() == 0) {
			itemCatParent.setCatIsParent((byte) 1);
			catMapper.updateByPrimaryKey(itemCatParent);
		}
		// 返回数据
		return WeResult.ok(itemCat);
	}

	@Override
	public WeResult updateContentCategory(int id, String name) {
		logger.info("==> Request parameter: id(" + id + "), name(" + name + ")");
		TbItemCat cat = new TbItemCat();
		cat.setCatId(id);
		cat.setCatName(name);
		catMapper.updateByPrimaryKeySelective(cat);
		return WeResult.ok();
	}

	@Override
	public WeResult deleteContentCategory(int id) {
		logger.info("==> Request parameter: id(" + id + ")");
		// 获得删除节点信息
		TbItemCat cat = catMapper.selectByPrimaryKey(id);
		int parentId = cat.getParentId();
		// 获取同级节点信息
		TbItemCatExample catExample = new TbItemCatExample();
		Criteria createCriteria = catExample.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		if (catMapper.selectByExample(catExample).size() == 1) {
			// 存在同级节点不修改父节点isParend选项否则修改
			TbItemCat parent = catMapper.selectByPrimaryKey(parentId);
			parent.setCatIsParent((byte) 0);
			catMapper.updateByPrimaryKey(parent);
		}
		delete(id);
		return WeResult.ok();
	}

	public void delete(int id) {
		logger.info("==> Request parameter: id(" + id + ")");
		TbItemCat cat = catMapper.selectByPrimaryKey(id);
		if (cat.getCatIsParent() == 1) {
			// 获取下一级子节点信息
			TbItemCatExample catExample = new TbItemCatExample();
			Criteria createCriteria = catExample.createCriteria();
			createCriteria.andParentIdEqualTo(id);
			List<TbItemCat> selectByExample = catMapper.selectByExample(catExample);
			for (TbItemCat TbItemCat : selectByExample) {
				delete(TbItemCat.getCatId());
			}
			catMapper.deleteByPrimaryKey(id);
		} else {
			catMapper.deleteByPrimaryKey(id);
		}
	}

}
