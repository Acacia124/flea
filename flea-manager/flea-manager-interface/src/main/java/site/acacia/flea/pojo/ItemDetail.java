/**
 * 
 */
package site.acacia.flea.pojo;

import java.io.Serializable;

/**
 * 商品详情
 * 
 * @author 张胤
 *
 *         2018年9月10日-下午6:19:59
 */
public class ItemDetail implements Serializable {
	private static final long serialVersionUID = 1888305193201473698L;
	private TbItem item;
	private TbUser user;

	public TbItem getItem() {
		return item;
	}

	public void setItem(TbItem item) {
		this.item = item;
	}

	public TbUser getUser() {
		return user;
	}

	public void setUser(TbUser user) {
		this.user = user;
	}

}
