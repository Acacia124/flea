package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class MessageInfo implements Serializable {
	private static final long serialVersionUID = -678977692478189623L;

	private Integer messageInfoId;

	private String messageTitle;

	private String messageTxt;

	private String messageUrl;

	@JSONField(format = "yyyy-MM-dd HH:mm")
	private Date messagePushTime;

	private String messageOperator;

	private String messageOperatorNick;

	private String messageOperatorAvater;

	private String messageType;

	public Integer getMessageInfoId() {
		return messageInfoId;
	}

	public void setMessageInfoId(Integer messageInfoId) {
		this.messageInfoId = messageInfoId;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle == null ? null : messageTitle.trim();
	}

	public String getMessageTxt() {
		return messageTxt;
	}

	public void setMessageTxt(String messageTxt) {
		this.messageTxt = messageTxt == null ? null : messageTxt.trim();
	}

	public String getMessageUrl() {
		return messageUrl;
	}

	public void setMessageUrl(String messageUrl) {
		this.messageUrl = messageUrl == null ? null : messageUrl.trim();
	}

	public Date getMessagePushTime() {
		return messagePushTime;
	}

	public void setMessagePushTime(Date messagePushTime) {
		this.messagePushTime = messagePushTime;
	}

	public String getMessageOperator() {
		return messageOperator;
	}

	public void setMessageOperator(String messageOperator) {
		this.messageOperator = messageOperator == null ? null : messageOperator.trim();
	}

	public String getMessageOperatorNick() {
		return messageOperatorNick;
	}

	public void setMessageOperatorNick(String messageOperatorNick) {
		this.messageOperatorNick = messageOperatorNick == null ? null : messageOperatorNick.trim();
	}

	public String getMessageOperatorAvater() {
		return messageOperatorAvater;
	}

	public void setMessageOperatorAvater(String messageOperatorAvater) {
		this.messageOperatorAvater = messageOperatorAvater == null ? null : messageOperatorAvater.trim();
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType == null ? null : messageType.trim();
	}
}