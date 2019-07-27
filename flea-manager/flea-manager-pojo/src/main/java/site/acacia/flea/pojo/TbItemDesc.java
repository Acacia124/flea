package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbItemDesc implements Serializable {

	private static final long serialVersionUID = 1726743009998115345L;

	private Integer itemDescId;

	private String itemId;

	private Date descCreate;

	private String itemDesc;

	public Integer getItemDescId() {
		return itemDescId;
	}

	public void setItemDescId(Integer itemDescId) {
		this.itemDescId = itemDescId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId == null ? null : itemId.trim();
	}

	public Date getDescCreate() {
		return descCreate;
	}

	public void setDescCreate(Date descCreate) {
		this.descCreate = descCreate;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc == null ? null : itemDesc.trim();
	}
}