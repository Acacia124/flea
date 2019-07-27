package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.RequiredTypeException;
import io.jsonwebtoken.impl.JwtMap;

/**
 * 权限信息对象
 * 
 * @author Tony
 *
 */
public class UserClaims extends JwtMap implements Claims, Serializable {
	private static final long serialVersionUID = 4470424648603732886L;
	private String openid;
	private String nickName;
	private String avatarUrl;
	private Integer role;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
		setValue("openid", this.openid);
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
		setValue("nickName", this.nickName);
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
		setValue("avatarUrl", this.avatarUrl);
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
		setValue("role", this.role);
	}

	@Override
	public String getIssuer() {
		return getString(ISSUER);
	}

	@Override
	public Claims setIssuer(String iss) {
		setValue(ISSUER, iss);
		return this;
	}

	@Override
	public String getSubject() {
		return getString(SUBJECT);
	}

	@Override
	public Claims setSubject(String sub) {
		setValue(SUBJECT, sub);
		return this;
	}

	@Override
	public String getAudience() {
		return getString(AUDIENCE);
	}

	@Override
	public Claims setAudience(String aud) {
		setValue(AUDIENCE, aud);
		return this;
	}

	@Override
	public Date getExpiration() {
		return get(Claims.EXPIRATION, Date.class);
	}

	@Override
	public Claims setExpiration(Date exp) {
		setDate(Claims.EXPIRATION, exp);
		return this;
	}

	@Override
	public Date getNotBefore() {
		return get(Claims.NOT_BEFORE, Date.class);
	}

	@Override
	public Claims setNotBefore(Date nbf) {
		setDate(Claims.NOT_BEFORE, nbf);
		return this;
	}

	@Override
	public Date getIssuedAt() {
		return get(Claims.ISSUED_AT, Date.class);
	}

	@Override
	public Claims setIssuedAt(Date iat) {
		setDate(Claims.ISSUED_AT, iat);
		return this;
	}

	@Override
	public String getId() {
		return getString(ID);
	}

	@Override
	public Claims setId(String jti) {
		setValue(Claims.ID, jti);
		return this;
	}

	@Override
	public <T> T get(String claimName, Class<T> requiredType) {
		Object value = get(claimName);
		if (value == null) {
			return null;
		}

		if (Claims.EXPIRATION.equals(claimName) || Claims.ISSUED_AT.equals(claimName)
				|| Claims.NOT_BEFORE.equals(claimName)) {
			value = getDate(claimName);
		}

		if (requiredType == Date.class && value instanceof Long) {
			value = new Date((Long) value);
		}

		if (!requiredType.isInstance(value)) {
			throw new RequiredTypeException(
					"Expected value to be of type: " + requiredType + ", but was " + value.getClass());
		}

		return requiredType.cast(value);
	}

}
