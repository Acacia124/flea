package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TbInformation implements Serializable {

	private static final long serialVersionUID = -3110178500078073358L;

	private Integer informationId;

	private String inSendOpenid;

	private String inAcceptOpenid;

	private String informationContent;

	private Byte informationIsReader;

	private Byte informationStatus;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date informationCreate;

	public Integer getInformationId() {
		return informationId;
	}

	public void setInformationId(Integer informationId) {
		this.informationId = informationId;
	}

	public String getInSendOpenid() {
		return inSendOpenid;
	}

	public void setInSendOpenid(String inSendOpenid) {
		this.inSendOpenid = inSendOpenid == null ? null : inSendOpenid.trim();
	}

	public String getInAcceptOpenid() {
		return inAcceptOpenid;
	}

	public void setInAcceptOpenid(String inAcceptOpenid) {
		this.inAcceptOpenid = inAcceptOpenid == null ? null : inAcceptOpenid.trim();
	}

	public String getInformationContent() {
		return informationContent;
	}

	public void setInformationContent(String informationContent) {
		this.informationContent = informationContent == null ? null : informationContent.trim();
	}

	public Byte getInformationIsReader() {
		return informationIsReader;
	}

	public void setInformationIsReader(Byte informationIsReader) {
		this.informationIsReader = informationIsReader;
	}

	public Byte getInformationStatus() {
		return informationStatus;
	}

	public void setInformationStatus(Byte informationStatus) {
		this.informationStatus = informationStatus;
	}

	public Date getInformationCreate() {
		return informationCreate;
	}

	public void setInformationCreate(Date informationCreate) {
		this.informationCreate = informationCreate;
	}
}