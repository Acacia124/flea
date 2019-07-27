/**
 * 
 */
package site.acacia.flea.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import site.acacia.flea.mapper.TbUserMapper;
import site.acacia.flea.pojo.EasyUIDataGridResult;
import site.acacia.flea.pojo.IMMessage;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.TbUserExample;
import site.acacia.flea.pojo.TbUserExample.Criteria;
import site.acacia.flea.pojo.UserClaims;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.UserService;
import site.acacia.flea.util.JwtTokenProvider;
import site.acacia.flea.vo.G2Statistics;

/**
 * @author 张胤
 *
 *         2018年10月19日-下午5:49:32
 */
@Service
public class UserServiceImpl implements UserService {

	protected static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private TbUserMapper userMapper;

	@Value("${jwt.secret}")
	private String SECRET;

	@Value("${jwt.expiration}")
	private String EXPIRATION;

	@Override
	public EasyUIDataGridResult<TbUser> getUserList(int page, int rows, String userName, Byte[] userStatus,
			String[] schoolList) {
		logger.info("==> Request parameter: page(" + page + "),rows(" + rows + "),userName(" + userName
				+ "),userStatus(" + Arrays.toString(userStatus) + "),schoolList(" + Arrays.toString(schoolList) + ")");
		// 设置分页信息
		PageHelper.startPage(page, rows);
		// 执行查询
		TbUserExample example = new TbUserExample();
		Criteria createCriteria = example.createCriteria();
		if (StringUtils.isNotBlank(userName)) {
			createCriteria.andNickNameLike("%" + userName + "%");
		}
		if (userStatus != null && userStatus.length > 0) {
			createCriteria.andUserStatusIn(Arrays.asList(userStatus));
		}
		if (schoolList != null && schoolList.length > 0) {
			createCriteria.andUserSchoolIn(Arrays.asList(schoolList));
		}
		List<TbUser> list = userMapper.selectByExample(example);
		// 取分页信息，PageInfo。1、总记录数2、总页数 。当前页码
		PageInfo<TbUser> pageInfo = new PageInfo<TbUser>(list);
		pageInfo.setList(list);
		EasyUIDataGridResult<TbUser> dataGridResult = new EasyUIDataGridResult<TbUser>();
		dataGridResult.setRows(list);
		dataGridResult.setTotal(pageInfo.getTotal());
		return dataGridResult;
	}

	/**
	 * @param status:
	 *            1: 普通会员，2：封号 ,3 管理员
	 */
	@Override
	public WeResult banOrRelieveUserStatus(String userId, int status) {
		logger.info("==> Request parameter: userId(" + userId + "),status(" + status + ")");
		TbUser tbUser = new TbUser();
		tbUser.setOpenid(userId);
		tbUser.setUserStatus((byte) status);
		userMapper.updateByPrimaryKeySelective(tbUser);
		// 删除用户缓存
		return WeResult.ok();
	}

	@Override
	public WeResult updateUserByUserId(TbUser user) {
		logger.info("==> Request parameter: user(" + user + ")");
		userMapper.updateByPrimaryKeySelective(user);

		JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(SECRET);
		UserClaims claims = new UserClaims();
		claims.setOpenid(user.getOpenid());
		claims.setAvatarUrl(user.getAvatarUrl());
		claims.setNickName(user.getNickName());
		claims.setRole((int) user.getUserStatus());
		// 调用JWT发放令牌
		String token = jwtTokenProvider.createToken(claims,
				new Date(System.currentTimeMillis() + Integer.parseInt(EXPIRATION)));
		return WeResult.ok(token);
	}

	@Override
	public WeResult selectUserByMail(String mail) {
		logger.info("==> Request parameter: mail(" + mail + ")");
		TbUserExample example = new TbUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEmailEqualTo(mail);
		List<TbUser> selectByExample = userMapper.selectByExample(example);
		if (selectByExample.size() <= 0) {
			return WeResult.build(404, "服务器未找到该用户");
		} else {
			return WeResult.ok(selectByExample.get(0).getNickName());
		}
	}

	/**
	 * 查询管理员账号
	 */
	@Override
	public WeResult selectUserByStatusIsAdmin() {
		TbUserExample example = new TbUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUserStatusEqualTo((byte) 3);
		List<TbUser> selectByExample = userMapper.selectByExample(example);
		return WeResult.ok(selectByExample);
	}

	/**
	 * 获取用户信息
	 */
	@Override
	public WeResult selectUserByOpenId(String openId) {
		TbUser tbUser = userMapper.selectByPrimaryKey(openId);
		return WeResult.ok(tbUser);
	}

	/**
	 * 根据用户Id，或用户名称查询用户
	 */
	@Override
	public WeResult selectUserSelective(TbUser user) {
		if (user == null) {
			return WeResult.build(404, "未找到该用户");
		}
		TbUserExample example = new TbUserExample();
		Criteria createCriteria = example.createCriteria();
		if (StringUtils.isNotBlank(user.getOpenid())) {
			createCriteria.andOpenidEqualTo(user.getOpenid());
		}
		if (StringUtils.isNotBlank(user.getNickName())) {
			createCriteria.andNickNameEqualTo(user.getNickName());
		}
		List<TbUser> selectByExample = userMapper.selectByExample(example);
		if (selectByExample == null) {
			return WeResult.build(404, "未找到该用户");
		}
		return WeResult.ok(selectByExample);
	}

	/**
	 * 根据nickName模糊查询姓名
	 */
	@Override
	public WeResult selectUserByNickName(String nickName) {
		TbUserExample example = new TbUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andNickNameLike("%" + nickName + "%");
		List<TbUser> selectByExample = userMapper.selectByExample(example);
		List<IMMessage> imMessages = new ArrayList<IMMessage>();
		for (TbUser tbUser : selectByExample) {
			IMMessage imMessage = new IMMessage();
			imMessage.setImgUrl(tbUser.getAvatarUrl());
			imMessage.setOpenId(tbUser.getOpenid());
			imMessage.setNickName(tbUser.getNickName());
			imMessages.add(imMessage);
		}
		return WeResult.ok(imMessages);
	}

	@Override
	public G2Statistics getUserData() {
		return userMapper.getUserData();
	}

}
