package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TbUser implements Serializable {

	private static final long serialVersionUID = 4248806954024109780L;

	private String sessionKey; // 用户登录sessionkey

	private String openid;

	private String nickName;

	private String avatarUrl;

	private String sdasd;

	private Integer gender;

	private Integer score;

	private String email;

	private String userPhone;

	private String userSchool;

	private Byte userStatus;

	@JSONField(format = "yyyy-MM-dd")
	private Date userCreated;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
	}

	public String getSdasd() {
		return sdasd;
	}

	public void setSdasd(String sdasd) {
		this.sdasd = sdasd == null ? null : sdasd.trim();
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone == null ? null : userPhone.trim();
	}

	public String getUserSchool() {
		return userSchool;
	}

	public void setUserSchool(String userSchool) {
		this.userSchool = userSchool == null ? null : userSchool.trim();
	}

	public Byte getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Byte userStatus) {
		this.userStatus = userStatus;
	}

	public Date getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(Date userCreated) {
		this.userCreated = userCreated;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
}