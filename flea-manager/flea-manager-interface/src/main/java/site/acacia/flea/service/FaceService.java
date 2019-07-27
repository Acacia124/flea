/**
 * 
 */
package site.acacia.flea.service;

import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2018年12月29日-下午2:13:47
 */
public interface FaceService {

	WeResult faceVerify(String base64Img);
}
