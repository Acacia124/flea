/**
 * 
 */
package site.acacia.flea.pojo;

import java.io.Serializable;

/**
 * Solr对应查询字段
 * 
 * @author 张胤
 *
 *         2018年9月6日-上午9:22:09
 */
public class SearchItem implements Serializable {

	private static final long serialVersionUID = -8326762709759497577L;
	private String id;
	private String title;
	private String sellPoint;
	private String categoryName;
	private String fatherCategoryName;
	private long price;
	private long originalPrice;
	private float newnessRate;
	private String image;
	private String consignmentAddress;
	private int status;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(long originalPrice) {
		this.originalPrice = originalPrice;
	}

	public float getNewnessRate() {
		return newnessRate;
	}

	public void setNewnessRate(float newnessRate) {
		this.newnessRate = newnessRate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getConsignmentAddress() {
		return consignmentAddress;
	}

	public void setConsignmentAddress(String consignmentAddress) {
		this.consignmentAddress = consignmentAddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFatherCategoryName() {
		return fatherCategoryName;
	}

	public void setFatherCategoryName(String fatherCategoryName) {
		this.fatherCategoryName = fatherCategoryName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SearchItem [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", categoryName="
				+ categoryName + ", fatherCategoryName=" + fatherCategoryName + ", price=" + price + ", originalPrice="
				+ originalPrice + ", newnessRate=" + newnessRate + ", image=" + image + ", consignmentAddress="
				+ consignmentAddress + ", status=" + status + "]";
	}

}
