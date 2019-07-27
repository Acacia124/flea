/**
 * 
 */
package site.acacia.flea.service;

import site.acacia.flea.pojo.EasyUIDataGridResult;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.vo.G2Statistics;

/**
 * @author 张胤
 *
 *         2018年10月19日-下午5:43:23
 */
public interface UserService {

	EasyUIDataGridResult<TbUser> getUserList(int page, int rows, String userName, Byte[] userStatus,
			String[] schoolList);

	WeResult banOrRelieveUserStatus(String userId, int status);

	WeResult updateUserByUserId(TbUser user);

	WeResult selectUserByMail(String mail);

	WeResult selectUserByStatusIsAdmin();

	WeResult selectUserByOpenId(String openId);

	WeResult selectUserSelective(TbUser user);

	WeResult selectUserByNickName(String nickName);

	G2Statistics getUserData();
}
