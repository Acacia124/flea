package site.acacia.flea.service;

import javax.mail.MessagingException;

import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2018年11月16日-下午12:54:15
 */
public interface SendMailService {

	WeResult sendMialOfCode(String to, String title, String userName, String type) throws MessagingException;

	WeResult sendMailOfLogger(String Message) throws MessagingException;

	WeResult getMailOfCode(String mail, String code);
}
