package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TbMessage implements Serializable {
	private static final long serialVersionUID = 8073269167122821221L;

	private String msgId;

	private String msgType;

	private String sendTo;

	private String createBy;

	@JSONField(format = "yyyy-MM-dd HH:mm")
	private Date createTime;

	private String delFlag;

	private String isOffLine;

	private String content;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId == null ? null : msgId.trim();
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType == null ? null : msgType.trim();
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo == null ? null : sendTo.trim();
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	public String getIsOffLine() {
		return isOffLine;
	}

	public void setIsOffLine(String isOffLine) {
		this.isOffLine = isOffLine == null ? null : isOffLine.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}