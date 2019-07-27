package site.acacia.flea.controller;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.SendMailService;

/**
 * @author 张胤
 *
 *         2018年11月16日-下午4:18:02
 */
@RequestMapping("/api")
@RestController
@CrossOrigin
public class SendMailController {

	protected static Logger logger = LoggerFactory.getLogger(SendMailController.class);

	@Autowired
	private SendMailService mailService;

	@RequestMapping("/login/sendMailCode")
	public WeResult sendMailOfCode(String to, String title, String type, String userName) {
		logger.debug("==> Request parameter: to(" + to + "),title(" + title + "),type(" + type + ")");
		try {
			return mailService.sendMialOfCode(to, title, userName, type);
		} catch (MessagingException e) {
			return WeResult.build(554, "发送的邮件内容包含了未被许可的信息，或被系统识别为垃圾邮件。请检查是否有用户发送病毒或者垃圾邮件");
		}
	}

}
