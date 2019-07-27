/**
 * 
 */
package site.acacia.flea.search.service;

import site.acacia.flea.pojo.SearchResult;
import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2018年9月6日-上午9:38:46
 */
public interface SolrService {

	WeResult importSolrList();

	SearchResult searchSolrList(String keywords, int page, int rows, String price, String sort, String cat)
			throws Exception;

	WeResult deleteAllSolr();

}
