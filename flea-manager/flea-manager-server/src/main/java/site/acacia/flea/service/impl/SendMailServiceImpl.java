package site.acacia.flea.service.impl;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.SendMailService;
import site.acacia.flea.util.SendMail;

/**
 * @author 张胤
 *
 *         2018年11月16日-下午12:58:46
 */
@Service
public class SendMailServiceImpl implements SendMailService {

	protected static Logger logger = LoggerFactory.getLogger(SendMailServiceImpl.class);

	@Autowired
	private SendMail sendMail;

	@Autowired
	private StringRedisTemplate redisTemplate;

	private static final String VERIFICATION_CODE = "VERIFICATION:CODE";

	private static final int VERIFICATIONCODE_TIME = 10;

	@Override
	public WeResult sendMialOfCode(String to, String title, String userName, String type) throws MessagingException {
		logger.info("==> Request parameter: to(" + to + "),title(" + title + "),userName(" + userName + "),type(" + type
				+ ")");
		String sources = "0123456789";
		Random rand = new Random();
		StringBuffer flag = new StringBuffer();
		for (int j = 0; j < 6; j++) {
			flag.append(sources.charAt(rand.nextInt(9)) + "");
		}
		if (sendMail.sendSimpleCode(to, title, userName, type, flag.toString())) {
			// 将二维码存入缓存（10分钟过期）
			try {
				ValueOperations<String, String> ops = redisTemplate.opsForValue();
				ops.set(VERIFICATION_CODE + ":" + to, flag.toString(), VERIFICATIONCODE_TIME, TimeUnit.MINUTES);
			} catch (Exception e) {
				logger.error("===============Redis 缓存库出错==============");
				logger.error(e.getStackTrace()[0] + "-" + e.getMessage());
				return WeResult.build(505, "该功能正在维护");
			}
			return WeResult.ok();
		}
		return WeResult.build(554, "发送的邮件内容包含了未被许可的信息，或被系统识别为垃圾邮件。请检查是否有用户发送病毒或者垃圾邮件");
	}

	@Override
	public WeResult sendMailOfLogger(String Message) {
		return null;
	}

	@Override
	public WeResult getMailOfCode(String mail, String code) {
		logger.info("==> Request parameter: mail(" + mail + "),code(" + code + ")");
		if (code == null || code.length() != 6) {
			return WeResult.build(400, "请求参数错误");
		}
		try {
			ValueOperations<String, String> ops = redisTemplate.opsForValue();
			String code2 = ops.get(VERIFICATION_CODE + ":" + mail);
			if (code.equals(code2)) {
				return WeResult.ok();
			} else {
				return WeResult.build(401, "验证码验证错误");
			}
		} catch (Exception e) {
			logger.error("===============Redis 缓存库出错==============");
			logger.error(e.getStackTrace()[0] + "-" + e.getMessage());
			return WeResult.build(505, "该功能正在维护");
		}
	}

}
