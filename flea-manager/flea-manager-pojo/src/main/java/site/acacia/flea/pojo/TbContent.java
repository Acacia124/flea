package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbContent implements Serializable {

	private static final long serialVersionUID = 6579896072839264977L;

	private Integer contentId;

	private Integer conCatId;

	private String contentTitle;

	private String subTitle;

	private String titleDesc;

	private String contentPic;

	private String contentUrl;

	private Date contentCreate;

	private Date contentUpdate;

	private String content;

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public Integer getConCatId() {
		return conCatId;
	}

	public void setConCatId(Integer conCatId) {
		this.conCatId = conCatId;
	}

	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle == null ? null : contentTitle.trim();
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle == null ? null : subTitle.trim();
	}

	public String getTitleDesc() {
		return titleDesc;
	}

	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc == null ? null : titleDesc.trim();
	}

	public String getContentPic() {
		return contentPic;
	}

	public void setContentPic(String contentPic) {
		this.contentPic = contentPic == null ? null : contentPic.trim();
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl == null ? null : contentUrl.trim();
	}

	public Date getContentCreate() {
		return contentCreate;
	}

	public void setContentCreate(Date contentCreate) {
		this.contentCreate = contentCreate;
	}

	public Date getContentUpdate() {
		return contentUpdate;
	}

	public void setContentUpdate(Date contentUpdate) {
		this.contentUpdate = contentUpdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}