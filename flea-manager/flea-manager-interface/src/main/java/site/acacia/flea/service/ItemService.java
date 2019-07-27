package site.acacia.flea.service;

import java.util.Date;
import java.util.List;

import site.acacia.flea.pojo.EasyUIDataGridResult;
import site.acacia.flea.pojo.ItemDetail;
import site.acacia.flea.pojo.TbItem;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.vo.G2Statistics;
import site.acacia.flea.vo.G2VoCopy;

/**
 * @author 张胤
 *
 *         2018年8月26日-下午9:48:37
 */
public interface ItemService {

	ItemDetail getItemByItemId(String itemId);

	WeResult addItem(TbItem item);

	ItemDetail getItemByItemId(String itemId, String openId);

	EasyUIDataGridResult<TbItem> getItemList(int page, int rows, Integer[] catId, String userId, String itemName,
			Long startPrice, Long endPrice, String[] schoolList, Byte[] status, Date startDate, Date endStart);

	WeResult updateItemByItemId(TbItem item);

	WeResult banItemByUserId(String userId);

	G2Statistics getItemData();

	List<G2VoCopy> getItemG2();
}
