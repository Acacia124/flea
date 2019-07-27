package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TbItem implements Serializable {
	private static final long serialVersionUID = -951496842681407401L;

	private String itemId;

	private Integer catId;

	private String openid;

	private String itemTitle;

	private String sellPoint;

	private Long price;

	private Long originalPrice;

	private Double newnessRate;

	private String itemImage;

	private String sellAddress;

	private Integer collectNum;

	private String sellQq;

	private String sellPhone;

	private String sellWechat;

	@JSONField(format = "yyyy-MM-dd")
	private Date buyTime;

	private Byte status;

	@JSONField(format = "yyyy-MM-dd")
	private Date itemCreated;

	@JSONField(format = "yyyy-MM-dd")
	private Date itemUpdate;

	@JSONField(format = "yyyy-MM-dd")
	private Date itemDown;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId == null ? null : itemId.trim();
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle == null ? null : itemTitle.trim();
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint == null ? null : sellPoint.trim();
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Long originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getNewnessRate() {
		return newnessRate;
	}

	public void setNewnessRate(Double newnessRate) {
		this.newnessRate = newnessRate;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage == null ? null : itemImage.trim();
	}

	public String getSellAddress() {
		return sellAddress;
	}

	public void setSellAddress(String sellAddress) {
		this.sellAddress = sellAddress == null ? null : sellAddress.trim();
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public String getSellQq() {
		return sellQq;
	}

	public void setSellQq(String sellQq) {
		this.sellQq = sellQq == null ? null : sellQq.trim();
	}

	public String getSellPhone() {
		return sellPhone;
	}

	public void setSellPhone(String sellPhone) {
		this.sellPhone = sellPhone == null ? null : sellPhone.trim();
	}

	public String getSellWechat() {
		return sellWechat;
	}

	public void setSellWechat(String sellWechat) {
		this.sellWechat = sellWechat == null ? null : sellWechat.trim();
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Date getItemCreated() {
		return itemCreated;
	}

	public void setItemCreated(Date itemCreated) {
		this.itemCreated = itemCreated;
	}

	public Date getItemUpdate() {
		return itemUpdate;
	}

	public void setItemUpdate(Date itemUpdate) {
		this.itemUpdate = itemUpdate;
	}

	public Date getItemDown() {
		return itemDown;
	}

	public void setItemDown(Date itemDown) {
		this.itemDown = itemDown;
	}
}