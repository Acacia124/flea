package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TbContentCat implements Serializable {

	private static final long serialVersionUID = -2109785315964804711L;

	private Integer conCatId;

	private String conCatName;

	private Integer conCatParentId;

	private Integer conCatSortOrder;

	private Byte conCatIsParent;

	private Byte conCatStatus;

	@JSONField(format = "yyyy-MM-dd")
	private Date created;

	public Integer getConCatId() {
		return conCatId;
	}

	public void setConCatId(Integer conCatId) {
		this.conCatId = conCatId;
	}

	public String getConCatName() {
		return conCatName;
	}

	public void setConCatName(String conCatName) {
		this.conCatName = conCatName == null ? null : conCatName.trim();
	}

	public Integer getConCatParentId() {
		return conCatParentId;
	}

	public void setConCatParentId(Integer conCatParentId) {
		this.conCatParentId = conCatParentId;
	}

	public Integer getConCatSortOrder() {
		return conCatSortOrder;
	}

	public void setConCatSortOrder(Integer conCatSortOrder) {
		this.conCatSortOrder = conCatSortOrder;
	}

	public Byte getConCatIsParent() {
		return conCatIsParent;
	}

	public void setConCatIsParent(Byte conCatIsParent) {
		this.conCatIsParent = conCatIsParent;
	}

	public Byte getConCatStatus() {
		return conCatStatus;
	}

	public void setConCatStatus(Byte conCatStatus) {
		this.conCatStatus = conCatStatus;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}