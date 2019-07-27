package site.acacia.flea.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import site.acacia.flea.exception.Unauthorized;
import site.acacia.flea.im.SocketServer;
import site.acacia.flea.pojo.MessageInfo;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.MessageService;
import site.acacia.flea.service.TbMessageService;

/**
 * 站内信
 * 
 * @author 张胤
 *
 *         2018年12月8日-下午2:42:28
 */
@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class MessageController {

	protected static Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private MessageService messageService;

	@Autowired
	private TbMessageService msgService;

	@RequestMapping("/message")
	public WeResult selectMessageUser(HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter Empty");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return messageService.selectMessageUser(user.getOpenid());
		}

	}

	@RequestMapping("/message/noread")
	public WeResult selectMessageNoRead(HttpServletRequest request) throws Unauthorized {
		logger.info("==> Request parameter Empty");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return messageService.selectMessageNoRead(user.getOpenid(), user.getUserStatus());
		}
	}

	@RequestMapping("/message/read")
	public WeResult selectMessageRead(HttpServletRequest request) throws Unauthorized {
		logger.info("==> Request parameter Empty");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return messageService.selectMessageRead(user.getOpenid(), user.getUserStatus());
		}
	}

	@RequestMapping("/message/info")
	public WeResult selectMessageInfo(int messageInfoId, HttpServletRequest request) throws Unauthorized {
		logger.info("==> Request parameter messageInfoId(" + messageInfoId + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return messageService.selectMessageInfo(user.getOpenid(), messageInfoId);
		}
	}

	// @RequestMapping("/message/private/send")
	// public WeResult sendMessage(@RequestBody MessageInfo info, @RequestBody
	// String openId, HttpServletRequest request)
	// throws Unauthorized {
	// logger.info("==> Request parameter info(" + info + "),openId(" + openId +
	// ")");
	// TbUser user = (TbUser) request.getAttribute("user");
	// if (user == null) {
	// throw new Unauthorized("401");
	// } else {
	// info.setMessageOperator(user.getOpenid());
	// info.setMessageOperatorNick(user.getNickName());
	// info.setMessageOperatorAvater(user.getAvatarUrl());
	// info = (MessageInfo) messageService.addMessageInfo(info).getData();
	// List<String> list = new ArrayList<>();
	// list.add(openId);
	// return messageService.addMessageUser(info, list);
	// }
	// }

	@RequestMapping("/message/all/send")
	public WeResult sendMessage(@RequestBody HashMap<String, Object> map, HttpServletRequest request)
			throws Unauthorized {
		if (map == null || (!map.containsKey("info")) || (!map.containsKey("openIds"))
				|| (!map.containsKey("isSystem"))) {
			return WeResult.build(400, "参数请求不合法！");
		}
		logger.info("==> Request parameter map(" + map + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			boolean isSystem = (boolean) map.get("isSystem");
			MessageInfo info = JSON.parseObject((String) map.get("info"), MessageInfo.class);
			List<String> openIds = JSON.parseObject((String) map.get("openIds"), List.class);
			if (user.getUserStatus() != 3 && (isSystem || openIds.size() > 1)) {
				return WeResult.build(400, "权限不足！");
			}
			if (isSystem) {
				info.setMessageOperator("001");
				info.setMessageOperatorNick("系统");
				info.setMessageOperatorAvater("https://gw.alipayobjects.com/zos/rmsportal/GvqBnKhFgObvnSGkDsje.png");
			} else {
				info.setMessageOperator(user.getOpenid());
				info.setMessageOperatorNick(user.getNickName());
				info.setMessageOperatorAvater(user.getAvatarUrl());
			}
			info = (MessageInfo) messageService.addMessageInfo(info).getData();
			return messageService.addMessageUser(info, openIds);
		}
	}

	@RequestMapping("/message/all/sendByStatus")
	public WeResult sendMessageByStatus(@RequestBody HashMap<String, Object> map, HttpServletRequest request)
			throws Unauthorized {
		if (map == null || (!map.containsKey("info")) || (!map.containsKey("status"))
				|| (!map.containsKey("isSystem"))) {
			return WeResult.build(400, "参数请求不合法！");
		}
		logger.info("==> Request parameter map(" + map + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getUserStatus() != 3) {
				return WeResult.build(400, "权限不足！");
			}
			boolean isSystem = (boolean) map.get("isSystem");
			MessageInfo info = JSON.parseObject((String) map.get("info"), MessageInfo.class);
			String status = (String) map.get("status");
			if (isSystem) {
				info.setMessageOperator("001");
				info.setMessageOperatorNick("系统");
				info.setMessageOperatorAvater("https://gw.alipayobjects.com/zos/rmsportal/GvqBnKhFgObvnSGkDsje.png");
			} else {
				info.setMessageOperator(user.getOpenid());
				info.setMessageOperatorNick(user.getNickName());
				info.setMessageOperatorAvater(user.getAvatarUrl());
			}
			info = (MessageInfo) messageService.addMessageInfo(info).getData();
			return messageService.addMessageUserByStatus(info, status);
		}
	}

	@RequestMapping("/message/all/read")
	public WeResult updateMessageStatus(HttpServletRequest request, String type) throws Unauthorized {
		logger.info("==> Request parameter type(" + type + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return messageService.updateMessageStatus(user.getOpenid(), type);
		}
	}

	@PostConstruct
	public void initMsgService() {
		SocketServer.setApplicationContext(msgService);
	}

	@RequestMapping("/IM/list")
	public WeResult getOffLineUser(HttpServletRequest request) throws Unauthorized {
		logger.info("==> Request parameter NULL");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return msgService.getOffLineUser(user.getOpenid());
		}
	}

}
