package site.acacia.flea.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

		if (msg instanceof Unauthorized) {
			LOGGER.warn("<== 未登录用户，访问拒绝");
			return WeResult.build(401, "登录状态异常");
		}
		if (msg instanceof NoPermission) {
			LOGGER.warn("<== 登录用户权限不足，访问拒绝");
			return WeResult.build(403, "权限不足");
		}
		LOGGER.error("Global exception interception: " + msg.getMessage());
		return WeResult.build(500, "系统代码异常");
	}
}
