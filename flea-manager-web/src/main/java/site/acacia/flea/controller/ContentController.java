package site.acacia.flea.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.content.service.ContentService;
import site.acacia.flea.exception.NoPermission;
import site.acacia.flea.pojo.EasyUIDataGridResult;
import site.acacia.flea.pojo.TbContent;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.WeResult;

/***
 * 
 * @author 张胤**2018 年8月6日-下午12:46:31
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ContentController {

	@Autowired
	private ContentService contentService;

	protected static Logger logger = LoggerFactory.getLogger(ContentController.class);

	@PostMapping("/user/content/save")
	@ResponseBody
	public WeResult addContent(HttpServletRequest request, @RequestBody TbContent content) throws NoPermission {
		logger.info("==> Request parameter: content(" + content + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null || user.getUserStatus() != 3) {
			throw new NoPermission("403");
		} else {
			return contentService.addContent(content);
		}
	}

	@RequestMapping("/user/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult<TbContent> getContentList(int categoryId, int page, int rows) {
		logger.info("==> Request parameter: categoryId(" + categoryId + "), " + "page: (" + page + ") , " + "rows: ("
				+ rows + ") ");
		return contentService.getContentList(categoryId, page, rows);
	}

	/**
	 * 根据id获取内容列表给前台
	 *
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("/user/content/weChat/list")
	@ResponseBody
	public List<TbContent> getListContent(int categoryId) {
		logger.info("==> Request parameter: categoryId(" + categoryId + ")");
		return contentService.getContentListByCid(categoryId);
	}
}
