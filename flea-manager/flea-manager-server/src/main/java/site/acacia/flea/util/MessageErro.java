package site.acacia.flea.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import site.acacia.flea.pojo.MessageInfo;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.MessageService;
import site.acacia.flea.service.UserService;

/**
 * @author 张胤
 *
 *         2018年12月12日-上午10:44:51
 */
@Component
public class MessageErro {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	/**
	 * 系统出错，通知管理员
	 * 
	 * @param title
	 * @param content
	 * @return
	 */
	public WeResult sendErroMessage(String title, String content) {
		MessageInfo info = new MessageInfo();
		info.setMessageOperator("001");
		info.setMessageOperatorNick("系统");
		info = (MessageInfo) messageService.addMessageInfo(info).getData();
		List<String> list = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<TbUser> tbUsers = (List<TbUser>) userService.selectUserByStatusIsAdmin();
		for (TbUser tbUser : tbUsers) {
			list.add(tbUser.getOpenid());
		}
		return messageService.addMessageUser(info, list);
	}
}
