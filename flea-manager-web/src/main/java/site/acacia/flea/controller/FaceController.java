/**
 * 
 */
package site.acacia.flea.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.FaceService;

/**
 * @author 张胤
 *
 *         2018年12月29日-下午3:57:23
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class FaceController {

	@Autowired
	private FaceService faceService;

	protected static Logger logger = LoggerFactory.getLogger(FaceController.class);

	@RequestMapping(value = "/face/verify", method = RequestMethod.POST)
	public WeResult faceVerity(@RequestBody String base64Img) {
		return faceService.faceVerify(base64Img.split(",")[1].replace(" ", "+"));
	}

}
