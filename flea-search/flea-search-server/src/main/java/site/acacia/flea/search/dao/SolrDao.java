/**
 * 
 */
package site.acacia.flea.search.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import site.acacia.flea.pojo.SearchItem;
import site.acacia.flea.pojo.SearchResult;

/**
 * @author 张胤
 *
 *         2018年9月6日-下午2:14:50
 */
@Repository
public class SolrDao {

	@Autowired
	private SolrClient solrClient;

	// 根据查询条件查询索引
	public SearchResult search(SolrQuery query) throws Exception {
		QueryResponse queryResponse = solrClient.query(query);
		SolrDocumentList documentList = queryResponse.getResults();
		// 查询个数
		long numFound = documentList.getNumFound();
		SearchResult result = new SearchResult();
		result.setRecourdCount(numFound);
		List<SearchItem> items = new ArrayList<>();
		// 取高亮
		// Map<String, Map<String, List<String>>> highlighting =
		// queryResponse.getHighlighting();
		// String title = "";
		for (SolrDocument solrDocument : documentList) {
			SearchItem item = new SearchItem();
			item.setId((String) solrDocument.get("id"));
			// 判断高亮
			// List<String> list =
			// highlighting.get(solrDocument.get("id")).get("item_title");
			// if (list != null && list.size() > 0) {
			// title = list.get(0);
			// } else {
			// title = (String) solrDocument.get("item_title");
			// }
			item.setTitle((String) solrDocument.get("item_title"));
			item.setSellPoint((String) solrDocument.get("item_sell_point"));
			item.setPrice((long) solrDocument.get("item_price"));
			item.setOriginalPrice((long) solrDocument.get("item_originalPrice"));
			item.setNewnessRate((float) solrDocument.get("item_newnessRate"));
			item.setImage((String) solrDocument.get("item_image"));
			item.setCategoryName((String) solrDocument.get("item_category_name"));
			item.setFatherCategoryName((String) solrDocument.get("item_father_category_name"));
			item.setConsignmentAddress((String) solrDocument.get("item_consignment_address"));
			item.setStatus((int) solrDocument.get("item_status"));
			items.add(item);
		}
		result.setItemList(items);
		return result;
	}
}
