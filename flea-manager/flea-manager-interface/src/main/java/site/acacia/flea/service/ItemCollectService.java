/**
 * 
 */
package site.acacia.flea.service;

import java.util.List;

import site.acacia.flea.pojo.TbCollect;
import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2018年9月15日-下午4:03:24
 */
public interface ItemCollectService {

	WeResult updateCollect(String userId, String itemId);

	WeResult selectCollectByItemUser(String userId, String itemId);

	List<TbCollect> allCollectByUserId(String userId);

	WeResult clearCartItem(String userId);

	WeResult delateCartByid(String userId, String itemid);
}
