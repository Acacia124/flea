package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class MessageUser implements Serializable {
	private static final long serialVersionUID = 7195082263973584556L;

	private Integer messageUserId;

	private String messageType;

	private String userId;

	private Integer messageInfoId;

	private Integer messageUserStatus;

	@JSONField(format = "yyyy-MM-dd HH:mm")
	private Date message;

	public Integer getMessageUserId() {
		return messageUserId;
	}

	public void setMessageUserId(Integer messageUserId) {
		this.messageUserId = messageUserId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType == null ? null : messageType.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public Integer getMessageInfoId() {
		return messageInfoId;
	}

	public void setMessageInfoId(Integer messageInfoId) {
		this.messageInfoId = messageInfoId;
	}

	public Integer getMessageUserStatus() {
		return messageUserStatus;
	}

	public void setMessageUserStatus(Integer messageUserStatus) {
		this.messageUserStatus = messageUserStatus;
	}

	public Date getMessage() {
		return message;
	}

	public void setMessage(Date message) {
		this.message = message;
	}
}