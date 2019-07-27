package site.acacia.flea.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import site.acacia.flea.pojo.WeResult;

@CrossOrigin
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler
	public WeResult processException(Exception msg, HttpServletRequest request, HttpServletResponse response) {

		if (msg instanceof MessagingException) {
			LOGGER.error("Email exception interception: " + msg.getMessage());
			return WeResult.build(554, "发送的邮件内容包含了未被许可的信息，或被系统识别为垃圾邮件。请检查是否有用户发送病毒或者垃圾邮件");
		}
		LOGGER.error("Global exception interception: " + msg.getMessage());
		return WeResult.build(500, "系统代码异常");
	}
}
