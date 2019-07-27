package site.acacia.flea.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.UserClaims;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.LoginService;

/**
 * @author 张胤
 *
 *         2018年8月15日-上午11:59:34
 */
@Component
public class LoginIntercepter implements HandlerInterceptor {

	protected static Logger logger = LoggerFactory.getLogger(LoginIntercepter.class);

	@Autowired
	private LoginService loginService;

	@Value("${jwt.tokenHead}")
	private String TOKENHEAD;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader(TOKENHEAD);
		if (StringUtils.isBlank(token)) {
			logger.info("USER(Null) Request address： " + " [" + request.getMethod() + "] "
					+ request.getRequestURL().toString());
			return true;
		}
		WeResult userToken = loginService.getUserByToken(token);
		if (userToken.getStatus() != 200) {
			if (userToken.getStatus() == 500) {
				logger.info("USER(Null) Request address： " + " [" + request.getMethod() + "] "
						+ request.getRequestURL().toString());
				return true;
			}
			return true;
		}
		UserClaims userClaims = (UserClaims) userToken.getData();
		TbUser user = new TbUser();
		user.setAvatarUrl((String) userClaims.get("avatarUrl"));
		user.setOpenid(userClaims.getId());
		user.setNickName((String) userClaims.get("nickName"));
		user.setUserStatus((byte) ((Integer) userClaims.get("role")).intValue());
		logger.info("USER(" + user.getOpenid() + ") Request address： " + " [" + request.getMethod() + "] "
				+ request.getRequestURL().toString());
		request.setAttribute("user", user);
		return true;
	}

}
