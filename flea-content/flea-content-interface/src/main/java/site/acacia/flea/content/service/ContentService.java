package site.acacia.flea.content.service;

import java.util.List;

import site.acacia.flea.pojo.EasyUIDataGridResult;
import site.acacia.flea.pojo.TbContent;
import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2018年8月6日-下午12:38:09
 */
public interface ContentService {

	WeResult addContent(TbContent content);

	EasyUIDataGridResult<TbContent> getContentList(int categoryId, int page, int rows);

	List<TbContent> getContentListByCid(int cid);

	WeResult delateTbContentById(int id);

	WeResult delateTbContentByCatId(int cid);

	WeResult updateTbContentById(TbContent content);
}
