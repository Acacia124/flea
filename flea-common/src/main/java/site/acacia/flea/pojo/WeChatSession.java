/**
 * 
 */
package site.acacia.flea.pojo;

import java.io.Serializable;

/**
 * @author 张胤
 *
 *         2018年8月29日-下午1:02:41
 */
public class WeChatSession implements Serializable {

	private static final long serialVersionUID = 2513421276230303580L;
	private String session_key;
	private String openid;
	private String unionid;

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

}
