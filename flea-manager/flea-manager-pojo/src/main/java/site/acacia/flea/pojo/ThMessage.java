package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 站内信消息Entity
 * 
 * @author HQ
 * @date 2018年11月20日
 */
public class ThMessage implements Serializable {

	private static final long serialVersionUID = 8557743570925759197L;
	private String msgType; // 消息类型（1文字 2图片）
	private String sendTo; // 发送给
	private String content; // 消息内容
	private String imgUrl; // 图片url
	private Date beginCreateDate; // 发送时间起
	private Date endCreateDate;// 发送时间止
	private String createBy; // 发送人
	private String updateBy; // 更新者

	private String isOffLine;// 是否在线内容

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getIsOffLine() {
		return isOffLine;
	}

	public void setIsOffLine(String isOffLine) {
		this.isOffLine = isOffLine;
	}

}
