package site.acacia.flea.service.impl;

import java.util.HashMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;

import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.FaceService;

/**
 * @author 张胤
 *
 *         2018年12月29日-下午2:16:16
 */
@Service
public class FaceServiceImpl implements FaceService {

	public static final String APP_ID = "15292557";
	public static final String API_KEY = "uGIWl9XR1eVGRlVgE91q59An";
	public static final String SECRET_KEY = "UEKrGo0swWrvKFQAm0pW6yckOCgsMiWD";
	public static AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

	protected static Logger logger = LoggerFactory.getLogger(FaceService.class);

	static {
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
	}

	/**
	 * 人脸识别验证
	 */
	@Override
	public WeResult faceVerify(String base64Img) {
		HashMap<String, String> options = new HashMap<String, String>();
		// 查询人脸库中系统管理员匹配度
		options.put("quality_control", "NORMAL");
		options.put("liveness_control", "HIGH");
		options.put("user_id", "Admin");

		String imageType = "BASE64";

		JSONObject res = client.search(base64Img, imageType, "Admin", options);
		return WeResult.ok(JSON.parse(res.toString(2)));
	}

}
