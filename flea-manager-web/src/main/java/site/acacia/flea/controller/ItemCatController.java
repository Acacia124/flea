package site.acacia.flea.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.exception.NoPermission;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.TreeNode;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.ItemCatService;

/**
 * @author 张胤
 *
 *         2018年9月2日-下午12:31:38
 */
@RestController
@CrossOrigin
@RequestMapping("/api/")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	protected static Logger logger = LoggerFactory.getLogger(ItemCatController.class);

	@RequestMapping("/item/cat/list")
	public List<TreeNode> getItemCatTree(@RequestParam(name = "id", defaultValue = "0") int parentId) {
		logger.info("==> Request parameter: parentId(" + parentId + ")");
		return itemCatService.getItemCatlist(parentId);
	}

	@RequestMapping("/item/cat/list/all")
	public List<TreeNode> getItemCatTree() {
		logger.info("==> Request parameter Empty");
		return itemCatService.getItemCatlist();
	}

	@RequestMapping("/user/item/cat/add")
	public WeResult addItemCat(int parentId, String catName, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: parentId(" + parentId + "), catName(" + catName + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null || user.getUserStatus() != 3) {
			throw new NoPermission("403");
		} else {
			return itemCatService.addContentCategory(parentId, catName);
		}
	}

	@RequestMapping("/user/item/cat/delete")
	public WeResult deleteCatById(int catId, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: catId(" + catId + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null || user.getUserStatus() != 3) {
			throw new NoPermission("403");
		} else {
			return itemCatService.deleteContentCategory(catId);
		}
	}

	@RequestMapping("/user/item/cat/update")
	public WeResult updateItemCat(int id, String catName, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: id(" + id + "), catName(" + catName + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null || user.getUserStatus() != 3) {
			throw new NoPermission("403");
		} else {
			if (StringUtils.isBlank(catName)) {
				return WeResult.build(400, "请求参数错误");
			}
			return itemCatService.updateContentCategory(id, catName);
		}
	}

}