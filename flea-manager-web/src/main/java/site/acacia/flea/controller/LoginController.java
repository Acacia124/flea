/**
 * 
 */
package site.acacia.flea.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

import site.acacia.flea.exception.Unauthorized;
import site.acacia.flea.pojo.RawData;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.WeChatSession;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.LoginService;
import site.acacia.flea.service.SendMailService;
import site.acacia.flea.service.UserService;

/**
 * @author 张胤
 *
 *         2018年9月16日-下午8:35:44
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {

	protected static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;

	@Autowired
	private SendMailService mailService;

	// 小程序唯一标识 (在微信小程序管理后台获取)
	private static final String WXSPAPPID = "wx24fabc6202bea046";

	// 小程序的 app secret (在微信小程序管理后台获取)
	private static final String WXSPSERCRER = "f69b37306b1b12e3fffbee40d60cfdcb";

	// 授权（必填）
	private static final String GrANT_TYPE = "authorization_code";

	private static final String WEURL = "https://api.weixin.qq.com/sns/jscode2session";

	/**
	 * 小程序登录获取openid
	 * 
	 * @param code
	 * @param rawData
	 * @return
	 */
	@RequestMapping("/login")
	public WeResult loginByWechat(String code, String rawData) {
		logger.info("==> Request parameter: code(" + code + "),rawData(" + rawData + ")");
		RawData raw = JSON.parseObject(rawData, RawData.class);
		// 用户基本信息获取
		TbUser user = new TbUser();
		user.setGender(raw.getGender());
		user.setNickName(raw.getNickName());
		user.setAvatarUrl(raw.getAvatarUrl());
		// 请求参数
		String params = "appid=" + WXSPAPPID + "&secret=" + WXSPSERCRER + "&js_code=" + code + "&grant_type="
				+ GrANT_TYPE;
		// 发送请求
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<String>(params, headers);
		String strbody = restTemplate.exchange(WEURL, HttpMethod.POST, entity, String.class).getBody();
		// 获取session_key和openid
		WeChatSession chatSession = JSON.parseObject(strbody, WeChatSession.class);
		user.setOpenid(chatSession.getOpenid());
		// 与后台交互
		return loginService.userLogin(user, chatSession);
	}

	/**
	 * 邮箱登录
	 * 
	 * @param mail
	 * @param code
	 * @return
	 */
	@RequestMapping("/login/mail")
	public WeResult loginByEmail(String mail, String code) {
		logger.info("==> Request parameter: code(" + code + "),mail(" + mail + ")");
		WeResult mailOfCode = mailService.getMailOfCode(mail, code);
		if (mailOfCode.getStatus() == 200) {
			return loginService.userLogin(mail);
		} else {
			return mailOfCode;
		}
	}

	/**
	 * 校验邮箱
	 */
	@RequestMapping("/login/mail/check")
	public WeResult CheckloginByEmail(String mail) {
		logger.info("==> Request parameter: mail(" + mail + ")");
		return userService.selectUserByMail(mail);
	}

	/**
	 * 登录检查
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/user/login/check")
	public WeResult checkLogin(HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter Empty");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return WeResult.ok(user.getOpenid());
		}
	}

}
