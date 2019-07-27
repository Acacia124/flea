package site.acacia.flea.pojo;

import java.io.Serializable;

public class TbItemCat implements Serializable {

	private static final long serialVersionUID = -1313085896206091319L;

	private Integer catId;

	private String catName;

	private Integer parentId;

	private Integer catSortOrder;

	private String catImage;

	private Byte catIsParent;

	private Byte catStatus;

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName == null ? null : catName.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getCatSortOrder() {
		return catSortOrder;
	}

	public void setCatSortOrder(Integer catSortOrder) {
		this.catSortOrder = catSortOrder;
	}

	public String getCatImage() {
		return catImage;
	}

	public void setCatImage(String catImage) {
		this.catImage = catImage == null ? null : catImage.trim();
	}

	public Byte getCatIsParent() {
		return catIsParent;
	}

	public void setCatIsParent(Byte catIsParent) {
		this.catIsParent = catIsParent;
	}

	public Byte getCatStatus() {
		return catStatus;
	}

	public void setCatStatus(Byte catStatus) {
		this.catStatus = catStatus;
	}
}