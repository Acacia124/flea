package site.acacia.flea.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.content.service.ContentCategoryService;
import site.acacia.flea.pojo.TreeNode;
import site.acacia.flea.pojo.WeResult;

/***
 * 
 * @author 张胤**2018 年8月5日-下午10:59:45
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentService;

	protected static Logger logger = LoggerFactory.getLogger(ContentCategoryController.class);

	@RequestMapping("/user/content/category/list")
	public List<TreeNode> getConCatList(@RequestParam(name = "id", defaultValue = "0") int parentId) {
		logger.info("==> Request parameter: parentId(" + parentId + ")");
		return contentService.getContentCatList(parentId);
	}

	@PostMapping("/user/content/category/create")
	public WeResult createContentCategory(int parentId, String name) {
		logger.info("==> Request parameter: parentId(" + parentId + "), name(" + name + ")");
		return contentService.addContentCategory(parentId, name);
	}

	@PostMapping("/user/content/category/update")
	public int updateContentCategory(int id, String name) {
		logger.info("==> Request parameter: id(" + id + "), name(" + name + ")");
		return contentService.updateContentCategory(id, name);
	}

	@PostMapping("/user/content/category/delete")
	public int deleteContentCategory(int id) {
		logger.info("==> Request parameter: id(" + id + ")");
		return contentService.deleteContentCategory(id);
	}
}
