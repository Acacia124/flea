package site.acacia.flea.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2019年5月10日-下午3:55:35
 */
public class QiniuClient {

	String token;

	public QiniuClient(String accessKey, String secretKey, String bucket) {
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		token = upToken;
	}

	public WeResult uploadFile(byte[] file, String name) throws QiniuException {
		Configuration configuration = new Configuration(Zone.zone0());
		UploadManager uploadManage = new UploadManager(configuration);
		Response response = uploadManage.put(file, name, token);
		DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		return WeResult.ok(putRet.key);
	}

}
