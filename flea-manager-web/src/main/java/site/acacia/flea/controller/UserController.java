package site.acacia.flea.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.exception.NoPermission;
import site.acacia.flea.exception.Unauthorized;
import site.acacia.flea.pojo.EasyUIDataGridResult;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.UserService;

/**
 * @author 张胤
 *
 *         2018年10月20日-下午2:41:50
 */
@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

	protected static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param page
	 *            页码
	 * @param rows
	 *            一页显示条目
	 * @param userName
	 *            模糊查询用户名
	 * @param userStatus
	 *            用户状态
	 * @param schoolList
	 *            用户地址
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public EasyUIDataGridResult<TbUser> getUser(int page, Integer rows,
			@RequestParam(value = "nickName", defaultValue = "") String nickName,
			@RequestParam(value = "userStatus", required = false) Byte[] userStatus,
			@RequestParam(value = "schoolList", required = false) String[] schoolList, HttpServletRequest request)
			throws Exception {
		logger.info("==> Request parameter: page(" + page + "),rows(" + rows + "),nickName(" + nickName
				+ "),userStatus(" + Arrays.toString(userStatus) + "),schoolList(" + Arrays.toString(schoolList) + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getUserStatus() != 3) {
				throw new NoPermission("403");
			} else {
				return userService.getUserList(page, rows, nickName, userStatus, schoolList);
			}
		}
	}

	@RequestMapping("/upgrade")
	public WeResult upgrade(String ids, Integer status, HttpServletRequest request) {
		logger.info("==> Request parameter: ids(" + ids + "),status(" + status + ")");
		return userService.banOrRelieveUserStatus(ids, status);
	}

	@RequestMapping("/ban")
	public WeResult barUser(String ids, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: ids(" + ids + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getUserStatus() != 3) {
				throw new NoPermission("403");
			} else {
				String[] id = ids.split(",");
				for (String string : id) {
					WeResult weResult = userService.banOrRelieveUserStatus(string, 2);
					if (weResult.getStatus() != 200) {
						return weResult;
					}
				}
				return WeResult.ok();
			}
		}

	}

	@RequestMapping("/relieve")
	public WeResult relieveUser(String ids, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: ids(" + ids + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getUserStatus() != 3) {
				throw new NoPermission("403");
			} else {
				String[] id = ids.split(",");
				for (String string : id) {
					WeResult weResult = userService.banOrRelieveUserStatus(string, 1);
					if (weResult.getStatus() != 200) {
						return weResult;
					}
				}
				return WeResult.ok();
			}
		}
	}

	@RequestMapping("/my")
	public WeResult getMyInfo(HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter is Null");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return userService.selectUserByOpenId(user.getOpenid());
		}
	}

	@RequestMapping("/reset")
	public WeResult resetMyInfo(HttpServletRequest request, @RequestBody TbUser tbUser) throws Exception {
		logger.info("==> Request parameter is tbUser(" + tbUser + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return userService.updateUserByUserId(tbUser);
		}
	}

	@RequestMapping("/getUserInfo")
	public WeResult selectUserSelective(HttpServletRequest request, @RequestBody TbUser tbUser) throws Exception {
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			return userService.updateUserByUserId(tbUser);
		}
	}

	@RequestMapping("/getUserLike/{nickName}")
	public WeResult getUserListByNickName(@PathVariable(value = "nickName") String nickName) {
		return userService.selectUserByNickName(nickName);
	}

}
