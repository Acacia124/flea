/**
 * 
 */
package site.acacia.flea.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * @author 张胤
 *
 *         2018年11月16日-上午10:43:31
 */
@Component
public class SendMail {
	@Autowired
	private JavaMailSender sender;
	@Value("${spring.mail.username}")
	private String from;

	/**
	 * 发送验证码
	 * 
	 * @param to
	 *            接收方
	 * @param title
	 *            接受者
	 * @param content
	 *            内容
	 * @return
	 * @throws MessagingException
	 */
	public boolean sendSimpleCode(String to, String title, String userName, String type, String content)
			throws MessagingException {
		MimeMessage mailMessage = sender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);
		messageHelper.setFrom(from); // 发送者
		messageHelper.setTo(to); // 接受者
		messageHelper.setSubject(title); // 发送标题
		String html = "<html><style type=\"text/css\">"
				+ "body { margin: 0;padding: 0;background: #fff;font-family: \"Verdana, Arial, Helvetica, sans-serif\";font-size: 14px;line-height: 24px;}"
				+ "div, p, span, img { margin: 0;padding: 0;}" + "img {border: none;}"
				+ ".contaner { margin: 0 auto; } "
				+ ".title { margin: 0 auto;background: url() #CCC repeat-x;height: 30px;text-align: center; font-weight: bold;padding-top: 12px;font-size: 16px;} "
				+ ".content {margin: 4px;}" + ".biaoti {padding: 6px;color: #000;}"
				+ ".xtop, .xbottom {display: block;font-size: 1px;} "
				+ ".xb1, .xb2, .xb3, .xb4 {display: block;overflow: hidden;}" + ".xb1, .xb2, .xb3 {height: 1px;}"
				+ ".xb2, .xb3, .xb4 {border-left: 1px solid #BCBCBC;border-right: 1px solid #BCBCBC;}"
				+ ".xb1 {margin: 0 5px;background: #BCBCBC;}" + ".xb2 {margin: 0 3px;border-width: 0 2px;}"
				+ ".xb3 {margin: 0 2px;}" + ".xb4 {height: 2px;margin: 0 1px;}"
				+ ".xboxcontent {display: block;border: 0 solid #BCBCBC;border-width: 0 1px;}"
				+ ".line {margin-top: 6px;border-top: 1px dashed #B9B9B9;padding: 4px;}"
				+ ".neirong {padding: 6px;color: #666666;}" + ".foot {padding: 6px;color: #777;}"
				+ ".font_darkblue {color: #006699;font-weight: bold;}"
				+ ".font_lightblue {color: #008BD1;font-weight: bold;}"
				+ ".font_gray {color: #888;font-size: 12px;}</style>"
				+ "<div class='qmbox qm_con_body_content qqmail_webmail_only' id='mailContentContainer'>"
				+ "<div class='contaner'><div class='title'>" + title
				+ "</div><div class='content'><p class='biaoti'><b>亲爱的用户，你好！</b></p><b class='xtop'><b class='xb1'></b><b class='xb2'></b><b class='xb3'></b><b class='xb4'></b></b>"
				+ "<div class='xboxcontent'><div class='neirong'><p><b>请核对你的用户名：</b><span id='userName' class='font_darkblue'>"
				+ userName + "</span></p><p><b>" + type
				+ "的验证码：</b><span class='font_lightblue'><span id='yzm' data='$(captcha)' t='7' style='border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;'>"
				+ content + "</span></span><br><span class='font_gray'>(请输入该验证码完成" + type
				+ "，验证码30分钟内有效！)</span></p><div class='line'>如果你未申请" + type
				+ "服务，请忽略该邮件。</div></div></div><b class='xbottom'><b class='xb4'></b><b class='xb3'></b><b class='xb2'></b><b class='xb1'></b></b><p class='foot'>如果仍有问题，请发送信息到我们的服务邮箱:"
				+ "<span data='800-820-5100' t='7' style='border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;'>acaciazy@163.com</span></p></div></div></div></html>";
		messageHelper.setText(html, true);
		sender.send(mailMessage);
		return true;
	}

	/**
	 * 发送报警邮件
	 * 
	 * @param to
	 * @param title
	 * @param content
	 * @return
	 * @throws MessagingException
	 */
	public boolean sendErroMail(String to, String title, String content) throws MessagingException {
		MimeMessage mailMessage = sender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);
		messageHelper.setFrom(from); // 发送者
		messageHelper.setTo(to); // 接受者
		messageHelper.setSubject(title); // 发送标题
		String html = "<html><style type=\"text/css\">"
				+ "body { margin: 0;padding: 0;background: #fff;font-family: \"Verdana, Arial, Helvetica, sans-serif\";font-size: 14px;line-height: 24px;}"
				+ "div, p, span, img { margin: 0;padding: 0;}" + "img {border: none;}"
				+ ".contaner { margin: 0 auto; } "
				+ ".title { margin: 0 auto;background: url() #f70303 repeat-x;height: 30px;text-align: center; font-weight: bold;padding-top: 12px;font-size: 16px;} "
				+ ".content {margin: 4px;}" + ".biaoti {padding: 6px;color: #000;}"
				+ ".xtop, .xbottom {display: block;font-size: 1px;} "
				+ ".xb1, .xb2, .xb3, .xb4 {display: block;overflow: hidden;}" + ".xb1, .xb2, .xb3 {height: 1px;}"
				+ ".xb2, .xb3, .xb4 {border-left: 1px solid #BCBCBC;border-right: 1px solid #BCBCBC;}"
				+ ".xb1 {margin: 0 5px;background: #BCBCBC;}" + ".xb2 {margin: 0 3px;border-width: 0 2px;}"
				+ ".xb3 {margin: 0 2px;}" + ".xb4 {height: 2px;margin: 0 1px;}"
				+ ".xboxcontent {display: block;border: 0 solid #BCBCBC;border-width: 0 1px;}"
				+ ".line {margin-top: 6px;border-top: 1px dashed #B9B9B9;padding: 4px;}"
				+ ".neirong {padding: 6px;color: #666666;}" + ".foot {padding: 6px;color: #777;}"
				+ ".font_darkblue {color: #006699;font-weight: bold;}"
				+ ".font_lightblue {color: #008BD1;font-weight: bold;}"
				+ ".font_gray {color: #888;font-size: 12px;}</style>"
				+ "<div class='qmbox qm_con_body_content qqmail_webmail_only' id='mailContentContainer'>"
				+ "<div class='contaner'><div class='title'>" + title
				+ "</div><div class='content'><p class='biaoti'><b>系统管理员，你好！</b></p><b class='xtop'><b class='xb1'></b><b class='xb2'></b><b class='xb3'></b><b class='xb4'></b></b>"
				+ "<div class='xboxcontent'><div class='neirong'><p>"
				+ "<span class='font_lightblue'><span id='yzm' data='$(captcha)' t='7' style='border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;'>"
				+ content + "</span></span><br>" + "<span class='font_gray'>(请尽快处理该问题)</span></p>"
				+ "</div></div><b class='xbottom'><b class='xb4'></b><b class='xb3'></b><b class='xb2'></b><b class='xb1'></b></b></div></div></div></html>";
		messageHelper.setText(html, true);
		sender.send(mailMessage);
		return true;
	}
}
