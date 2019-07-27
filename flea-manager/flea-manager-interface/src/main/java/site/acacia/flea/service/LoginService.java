/**
 * 
 */
package site.acacia.flea.service;

import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.WeChatSession;
import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2018年8月28日-下午8:37:23
 */
public interface LoginService {

	public WeResult userLogin(TbUser user, WeChatSession chatSession);

	public WeResult getUserByToken(String key);

	public WeResult userLogin(String mail);

}
