/**
 * 
 */
package site.acacia.flea.content.service;

import java.util.List;

import site.acacia.flea.pojo.TreeNode;
import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2018年8月5日-下午10:44:09
 */
public interface ContentCategoryService {

	List<TreeNode> getContentCatList(int productId);

	WeResult addContentCategory(int parentId, String name);

	int updateContentCategory(int id, String name);

	int deleteContentCategory(int id);
}
