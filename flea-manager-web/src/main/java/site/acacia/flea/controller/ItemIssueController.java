/**
 * 
 */
package site.acacia.flea.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.pojo.TbItem;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.service.ItemIssueService;

/**
 * @author 张胤
 *
 *         2018年9月26日-下午5:52:31
 */
@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class ItemIssueController {

	protected static Logger logger = LoggerFactory.getLogger(ItemIssueController.class);

	@Autowired
	private ItemIssueService itemIssueService;

	/**
	 * 根据userId获取用户发布商品
	 * 
	 * @param userId
	 * @param status
	 *            0 在售(包括免费和付费和下架),1收费，2 免费，3下架
	 * @param request
	 * @return
	 */
	@RequestMapping("/item/issue")
	public List<TbItem> getItemByUserId(String userId, int status, HttpServletRequest request) {
		logger.info("==> Request parameter: userId(" + userId + "), status(" + status + ")");
		if (StringUtils.isNotBlank(userId)) {
			List<TbItem> itemList = itemIssueService.getListItemByUserId(userId, status);
			return itemList;
		}
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			return null;
		}
		List<TbItem> itemList = itemIssueService.getListItemByUserId(user.getOpenid(), status);
		return itemList;

	}

}
