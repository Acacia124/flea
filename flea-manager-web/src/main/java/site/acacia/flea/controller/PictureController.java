package site.acacia.flea.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import site.acacia.flea.exception.Unauthorized;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.util.FastDFSClient;
import site.acacia.flea.util.QiniuClient;

/**
 * 上传图片Controller
 * 
 * @author 张胤
 *
 *         2018年8月5日-上午11:52:00
 */
@Controller
@RequestMapping("/api")
@CrossOrigin
public class PictureController {

	protected static Logger logger = LoggerFactory.getLogger(PictureController.class);
	@Value("${qiniu.accessKey}")
	private String accessKey;

	@Value("${qiniu.secretKey}")
	private String secretKey;

	@Value("${qiniu.dns}")
	private String dns;

	@Value("${qiniu.bucket}")
	private String bucket;

	@RequestMapping("/user/pic/upload")
	@ResponseBody
	public WeResult uploadFile(MultipartFile uploadFile, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: uploadFile");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			try {
				FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
				// 取文件扩展名
				String originalFilename = uploadFile.getOriginalFilename();
				String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
				// 获得图片地址和文件名
				String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
				// 补充为完整URL
				url = "https://upload.flea.store/" + url;
				return WeResult.ok(url);
			} catch (Exception e) {
				System.out.println(e.toString());
				logger.error("<== 图片上传出错  " + e.getMessage());
				return WeResult.build(500, "服务端正忙...");
			}
		}
	}

	@RequestMapping("/user/pic/delete")
	@ResponseBody
	public WeResult deleteFile(String fileName, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: deleteFile");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			try {
				FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
				String substring = fileName.substring(26);
				Integer delete_file = fastDFSClient.delete_file(substring);
				return WeResult.ok(delete_file);
			} catch (Exception e) {
				logger.error("<== 图片删除出错");
				return WeResult.build(500, "服务端正忙...");
			}
		}
	}

	@RequestMapping("/user/qiniu/upload")
	@ResponseBody
	public WeResult uploadFileByqiniu(MultipartFile uploadFile, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: uploadFile");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			QiniuClient client = new QiniuClient(accessKey, secretKey, bucket);
			String originalFilename = uploadFile.getOriginalFilename();
			String extName = System.currentTimeMillis() / 1000 + "/" + originalFilename;
			client.uploadFile(uploadFile.getBytes(), extName);
			// 补充为完整URL
			String url = dns + extName;
			return WeResult.ok(url);
		}
	}
}
