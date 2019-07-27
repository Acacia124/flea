/**
 * 
 */
package site.acacia.flea.search.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import site.acacia.flea.pojo.SearchItem;

/**
 * @author 张胤
 *
 *         2018年9月6日-上午10:15:25
 */
@Mapper
public interface SolrMapper {

	List<SearchItem> getSearchItemList();

	SearchItem getItemById(String itemId);

}
