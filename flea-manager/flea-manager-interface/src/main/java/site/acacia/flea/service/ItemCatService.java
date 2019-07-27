/**
 * 
 */
package site.acacia.flea.service;

import java.util.List;

import site.acacia.flea.pojo.TreeNode;
import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2018年9月2日-下午12:20:03
 */
public interface ItemCatService {

	// 根据父Id获取节点信息
	public List<TreeNode> getItemCatlist(Integer parentId);

	// 获取所有节点
	public List<TreeNode> getItemCatlist();

	WeResult addContentCategory(int parentId, String name);

	WeResult updateContentCategory(int id, String name);

	WeResult deleteContentCategory(int id);
}
