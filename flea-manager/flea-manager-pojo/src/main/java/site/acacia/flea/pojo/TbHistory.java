package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbHistory implements Serializable {

	private static final long serialVersionUID = 5767214850487192283L;

	private Integer hisId;

	private String openid;

	private String itemId;

	private Date hisCreate;

	public Integer getHisId() {
		return hisId;
	}

	public void setHisId(Integer hisId) {
		this.hisId = hisId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId == null ? null : itemId.trim();
	}

	public Date getHisCreate() {
		return hisCreate;
	}

	public void setHisCreate(Date hisCreate) {
		this.hisCreate = hisCreate;
	}
}