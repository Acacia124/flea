/**
 * 
 */
package site.acacia.flea.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import site.acacia.flea.mapper.TbUserMapper;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.TbUserExample;
import site.acacia.flea.pojo.TbUserExample.Criteria;
import site.acacia.flea.pojo.UserClaims;
import site.acacia.flea.pojo.WeChatSession;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.LoginService;
import site.acacia.flea.util.JwtTokenProvider;

/**
 * @author 张胤
 *
 *         2018年8月28日-下午8:41:35
 */
@Service
@Component
public class LoginServiceImpl implements LoginService {

	protected static Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private TbUserMapper userMapper;

	@Value("${jwt.secret}")
	private String SECRET;

	@Value("${jwt.expiration}")
	private String EXPIRATION;

	@Override
	public WeResult userLogin(TbUser user, WeChatSession chatSession) {
		logger.info("==> Request parameter: user(" + user + "),chatSession(" + chatSession + ")");
		// 1. 根据openId查询数据库是否有该用户
		TbUserExample userExample = new TbUserExample();
		Criteria createCriteria = userExample.createCriteria();
		createCriteria.andOpenidEqualTo(user.getOpenid());
		// 2 . 执行查询
		List<TbUser> list = userMapper.selectByExample(userExample);
		// 3. 没有添加
		if (list == null || list.size() == 0) {
			// 此处增加数据库用户信息
			user.setUserCreated(new Date());
			user.setUserStatus((byte) 1);
			user.setScore(0);
			userMapper.insertSelective(user);
		} else {
			user.setUserStatus(list.get(0).getUserStatus());
			user.setSdasd(list.get(0).getSdasd());
			user.setUserCreated(list.get(0).getUserCreated());
		}

		JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(SECRET);
		UserClaims claims = new UserClaims();
		claims.setOpenid(user.getOpenid());
		claims.setAvatarUrl(user.getAvatarUrl());
		claims.setNickName(user.getNickName());
		claims.setRole(1);
		// 调用JWT发放令牌
		String token = jwtTokenProvider.createToken(claims,
				new Date(System.currentTimeMillis() + Integer.parseInt(EXPIRATION)));
		System.out.println("生成的token：" + token);
		// // 4. 生成Token
		// String token = UUID.randomUUID().toString();
		// // 5. 将用户信息写入redis
		// ValueOperations<String, String> ops = redisTemplate.opsForValue();
		// user.setOpenid(user.getOpenid());
		// user.setSessionKey(chatSession.getSession_key());
		// String json = JSON.toJSONString(user);
		// // Redis过期时间设置，此处设置1天。
		// ops.set(SESSION + token, json, 1, TimeUnit.DAYS);
		// // 6. 返回token
		return WeResult.ok(token);
	}

	public WeResult getUserByToken(String key) {
		logger.info("==> Request parameter: key(" + key + ")");
		// // 准备redis数据操作对象（String类型）
		// ValueOperations<String, String> ops = redisTemplate.opsForValue();
		// // 准备user对象封装数据
		// TbUser user = new TbUser();
		// // 从redis数据库中获取json字符串
		// String str = ops.get(SESSION + key);
		// if (StringUtils.isBlank(str)) {
		// return WeResult.build(201, "用户登录信息已经过期");
		// }
		// // 重置用户信息过期时间
		// redisTemplate.expire(SESSION + key, 1, TimeUnit.DAYS);
		// user = JSON.parseObject(str, TbUser.class);
		// return WeResult.ok(user);

		// 生成Token
		JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(SECRET);
		Claims userClaims = jwtTokenProvider.parseToken(key);
		UserClaims user = new UserClaims();
		user.setNickName((String) userClaims.get("nickName"));
		user.setAvatarUrl((String) userClaims.get("avatarUrl"));
		user.setId((String) userClaims.get("openid"));
		user.setRole((Integer) userClaims.get("role"));
		return WeResult.ok(user);
	}

	@Override
	public WeResult userLogin(String mail) {
		logger.info("==> Request parameter: mail(" + mail + ")");
		TbUserExample example = new TbUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEmailEqualTo(mail);
		List<TbUser> selectByExample = userMapper.selectByExample(example);
		if (selectByExample.size() <= 0) {
			return WeResult.build(404, "服务器未找到该用户");
		} else {
			JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(SECRET);
			UserClaims claims = new UserClaims();
			claims.setOpenid(selectByExample.get(0).getOpenid());
			claims.setAvatarUrl(selectByExample.get(0).getAvatarUrl());
			claims.setNickName(selectByExample.get(0).getNickName());
			claims.setRole((int) selectByExample.get(0).getUserStatus());
			// 调用JWT发放令牌
			String token = jwtTokenProvider.createToken(claims,
					new Date(System.currentTimeMillis() + Integer.parseInt(EXPIRATION)));
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("token", token);
			hashMap.put("nickName", selectByExample.get(0).getNickName());
			hashMap.put("avatarUrl", selectByExample.get(0).getAvatarUrl());
			hashMap.put("email", mail);
			hashMap.put("openid", selectByExample.get(0).getOpenid());
			return WeResult.ok(hashMap);
		}
	}

	// public void createUser() {
	// JSONObject httpGet = HttpClientUtils.httpGet("https://randomuser.me/api");
	// List<Map<String, Object>> object = (List<Map<String, Object>>)
	// httpGet.get("results");
	// Map<String, Object> map = object.get(0);
	// TbUser tbUser = new TbUser();
	// tbUser.setGender(map.get("gender").equals("male") ? 1 : 2);
	// Map<String, String> name = (Map<String, String>) map.get("name");
	// char[] cs = name.get("last").toCharArray();
	// cs[0] -= 32;
	// tbUser.setNickName(String.valueOf(cs));
	// tbUser.setEmail((String) map.get("email"));
	// Map<String, String> login = (Map<String, String>) map.get("login");
	// tbUser.setOpenid(login.get("uuid"));
	// Map<String, Object> register = (Map<String, Object>) map.get("registered");
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// try {
	// tbUser.setUserCreated(sdf.parse((String) register.get("date")));
	// } catch (ParseException e) {
	// // TODO 自动生成的 catch 块
	// e.printStackTrace();
	// }
	// Map<String, String> img = (Map<String, String>) map.get("picture");
	// tbUser.setAvatarUrl(img.get("large"));
	// String[] address = { "太原理工大学", "山西医科大学", "长治医学院", "山西师范大学", "山西财经大学",
	// "太原师范学院", "晋中学院", "忻州师范学院", "山西中医学院",
	// "太原科技大学", "中北大学", "太原工业学院", "山西大同大学", "长治学院", "山西大学", "山西中医大学", "山西农业大学",
	// "太原科技大学", "山西医科大学", "山西大同大学",
	// "忻州师范学院", "运城学院", "太原工业学院", "吕梁学院", "山西工程技术学院", "北京大学", "清华大学", "山西传媒学院" };
	// Random random = new Random();
	// tbUser.setUserSchool(address[random.nextInt(address.length)]);
	// tbUser.setUserStatus((byte) 1);
	// JSONObject gushi = HttpClientUtils.httpGet("https://api.gushi.ci/all.json");
	// tbUser.setSdasd((String) gushi.get("content"));
	// System.out.println(JSON.toJSONString(tbUser));
	// userMapper.insertSelective(tbUser);
	// }

	public ArrayList<String> getOpenIds() {
		TbUserExample example = new TbUserExample();
		ArrayList<String> arrayList = new ArrayList<>();
		List<TbUser> selectByExample = userMapper.selectByExample(example);
		for (TbUser tbUser : selectByExample) {
			arrayList.add(tbUser.getOpenid());
		}
		return arrayList;
	}
}
