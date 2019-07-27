package site.acacia.flea.pojo;

import java.io.Serializable;

public class TbCollect implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8451542375460726286L;

	private Integer collectId;

	private String itemId;

	private String openid;

	private String collectName;

	private String collectImage;

	private Double collectNewnessRate;

	private String collectSellPoint;

	private Long collectPrice;

	private Long collectOriginalPrice;

	private String collectSellSchool;

	private Byte collectStatus;

	public Integer getCollectId() {
		return collectId;
	}

	public void setCollectId(Integer collectId) {
		this.collectId = collectId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId == null ? null : itemId.trim();
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	public String getCollectName() {
		return collectName;
	}

	public void setCollectName(String collectName) {
		this.collectName = collectName == null ? null : collectName.trim();
	}

	public String getCollectImage() {
		return collectImage;
	}

	public void setCollectImage(String collectImage) {
		this.collectImage = collectImage == null ? null : collectImage.trim();
	}

	public Double getCollectNewnessRate() {
		return collectNewnessRate;
	}

	public void setCollectNewnessRate(Double collectNewnessRate) {
		this.collectNewnessRate = collectNewnessRate;
	}

	public String getCollectSellPoint() {
		return collectSellPoint;
	}

	public void setCollectSellPoint(String collectSellPoint) {
		this.collectSellPoint = collectSellPoint == null ? null : collectSellPoint.trim();
	}

	public Long getCollectPrice() {
		return collectPrice;
	}

	public void setCollectPrice(Long collectPrice) {
		this.collectPrice = collectPrice;
	}

	public Long getCollectOriginalPrice() {
		return collectOriginalPrice;
	}

	public void setCollectOriginalPrice(Long collectOriginalPrice) {
		this.collectOriginalPrice = collectOriginalPrice;
	}

	public String getCollectSellSchool() {
		return collectSellSchool;
	}

	public void setCollectSellSchool(String collectSellSchool) {
		this.collectSellSchool = collectSellSchool == null ? null : collectSellSchool.trim();
	}

	public Byte getCollectStatus() {
		return collectStatus;
	}

	public void setCollectStatus(Byte collectStatus) {
		this.collectStatus = collectStatus;
	}
}