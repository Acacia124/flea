package site.acacia.flea.service;

import java.util.List;

import site.acacia.flea.pojo.TbItem;
import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 * 
 *         2018年9月26日-下午4:40:00
 */
public interface ItemIssueService {

	List<TbItem> getListItemByUserId(String userId, int status);

	WeResult updateItemStatus(String itemId);
}
