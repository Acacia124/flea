package site.acacia.flea.util;

import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSONObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenProvider {

	SecretKeySpec key;

	/**
	 * @param key
	 * 
	 */
	public JwtTokenProvider(String key) {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS512.getJcaName());
		this.key = secretKeySpec;
	}

	/**
	 * 生成token
	 * 
	 * @return
	 */
	public String createToken(Claims claims, Date date) {
		String compactJws = Jwts.builder()

				.setPayload(JSONObject.toJSONString(claims)).compressWith(CompressionCodecs.DEFLATE)
				.signWith(SignatureAlgorithm.HS512, key).compact();
		return compactJws;
	}

	/** token转换为 */
	public Claims parseToken(String token) {
		try {
			return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}