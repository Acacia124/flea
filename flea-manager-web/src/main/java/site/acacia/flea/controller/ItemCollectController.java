/**
 * 
 */
package site.acacia.flea.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.exception.NoPermission;
import site.acacia.flea.exception.Unauthorized;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.ItemCollectService;

/**
 * @author 张胤
 *
 *         2018年9月15日-下午9:53:43
 */
@RestController
@CrossOrigin
@RequestMapping("/api/user/item/collect")
public class ItemCollectController {

	@Autowired
	private ItemCollectService collectService;

	protected static Logger logger = LoggerFactory.getLogger(ItemCollectController.class);

	@RequestMapping("/add")
	public WeResult addCollect(String itemId, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: itemId(" + itemId + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getUserStatus() == 2) {
				throw new NoPermission("403");
			} else {
				return collectService.updateCollect(user.getOpenid(), itemId);
			}
		}
	}

	@RequestMapping("/select")
	public WeResult selectCollectList(HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter Empty");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return WeResult.ok(collectService.allCollectByUserId(user.getOpenid()));
		}
	}

	@RequestMapping("/clean")
	public WeResult cleanCollect(HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter Empty");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return collectService.clearCartItem(user.getOpenid());
		}
	}

	@RequestMapping("/isCollect")
	public WeResult getCollectByUserIdAndItemId(String itemId, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: itemId(" + itemId + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return collectService.selectCollectByItemUser(user.getOpenid(), itemId);
		}

	}

	@RequestMapping("/delate")
	public WeResult delateCollect(String itemId, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: itemId(" + itemId + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return collectService.delateCartByid(user.getOpenid(), itemId);
		}
	}

}
