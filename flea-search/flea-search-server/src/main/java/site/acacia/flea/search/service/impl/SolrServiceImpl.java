package site.acacia.flea.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.acacia.flea.pojo.SearchItem;
import site.acacia.flea.pojo.SearchResult;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.search.dao.SolrDao;
import site.acacia.flea.search.mapper.SolrMapper;
import site.acacia.flea.search.service.SolrService;

/**
 * @author 张胤
 *
 *         2018年9月6日-上午9:42:30
 */
@Service
public class SolrServiceImpl implements SolrService {

	@Autowired
	private SolrClient solrClient;

	@Autowired
	private SolrMapper solrMapper;

	@Autowired
	private SolrDao solrDao;

	protected static Logger logger = LoggerFactory.getLogger(SolrService.class);

	// 导入所有商品信息到索引库
	@Override
	public WeResult importSolrList() {
		List<SearchItem> searchItemList = solrMapper.getSearchItemList();
		logger.info("=============>进入Solr添加索引库服务<==============");
		for (SearchItem searchItem : searchItemList) {
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSellPoint());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_originalPrice", searchItem.getOriginalPrice());
			document.addField("item_newnessRate", searchItem.getNewnessRate());
			String image = "";
			if (searchItem.getImage() != null && !"".equals(searchItem.getImage())) {
				String[] strings = searchItem.getImage().split(",");
				image = strings[0];
			} else {
				image = null;
			}
			document.addField("item_image", image);
			document.addField("item_category_name", searchItem.getCategoryName());
			document.addField("item_father_category_name", searchItem.getFatherCategoryName());
			document.addField("item_consignment_address", searchItem.getConsignmentAddress());
			document.addField("item_status", searchItem.getStatus());
			try {
				solrClient.add(document);
				solrClient.commit();
			} catch (SolrServerException e) {
				logger.error(e.getStackTrace()[0] + "===" + e.getMessage() + "===");
				return WeResult.build(500, "Solr导入失败");
			} catch (IOException e) {
				logger.error(e.getStackTrace()[0] + "===" + e.getMessage() + "===");
				return WeResult.build(500, "Solr导入失败");
			}
		}
		return WeResult.ok();
	}

	// 根据查询条件查询商品信息
	@Override
	public SearchResult searchSolrList(String keywords, int page, int rows, String price, String sort, String cat)
			throws Exception {
		// 设置查询条件
		SolrQuery query = new SolrQuery();
		query.setQuery(keywords);
		if (page <= 0) {
			page = 1;
		}
		// 显示第几页数
		query.setStart((page - 1) * rows);
		// 一页显示数据
		query.setRows(rows);
		// 设置默认搜索域
		query.set("df", "item_keywords");

		// 设置过滤查询添加 free: 0 全部搜索，1搜索不免费，2搜索免费
		if (StringUtils.isNotBlank(cat)) {
			if (!"分类".equals(cat)) {
				query.addFilterQuery("item_father_category_name:" + cat);
			}
		}
		if (StringUtils.isNotBlank(price)) {
			if (!"价格".equals(price)) {
				if ("免费".equals(price)) {
					query.addFilterQuery("item_status:2");
				} else {
					String[] split = price.split("-");
					if (split.length == 2) {
						if (NumberUtils.isNumber(split[0])) {
							split[0] = Integer.toString(Integer.parseInt(split[0]) * 100);
						}
						if (NumberUtils.isNumber(split[1])) {
							split[1] = Integer.toString(Integer.parseInt(split[1]) * 100);
						}
						query.addFilterQuery("item_price: [" + split[0] + " TO " + split[1] + "]");
					}
				}
			}
		}
		if (StringUtils.isNotBlank(sort)) {
			if (!"综合排序".equals(sort)) {
				if ("价格 低-高".equals(sort)) {
					query.setSort("item_price", ORDER.asc);
				} else if ("价格 高-低".equals(sort)) {
					query.setSort("item_price", ORDER.desc);
				}
			}
		}
		// 开启高亮
		// query.setHighlight(true);
		// query.setHighlightSimplePre("<text style=\"color:red\">");
		// query.setHighlightSimplePost("</text>");
		// 调用dao执行查询
		SearchResult searchResult = solrDao.search(query);
		// 计算总页数
		long recourdCount = searchResult.getRecourdCount();
		int totalPage = (int) (recourdCount / rows);
		if (recourdCount % rows > 0) {
			totalPage++;
		}
		// 返回结果
		searchResult.setTotalPages(totalPage);
		searchResult.setKeyword(keywords);
		searchResult.setPage(page);
		return searchResult;
	}

	// 清除所有
	@Override
	public WeResult deleteAllSolr() {
		logger.info("=============>进入Solr清楚索引库服务<==============");
		// 清空所有数据
		try {
			solrClient.deleteByQuery("*:*");
			solrClient.commit();
		} catch (Exception e) {
			logger.error(e.getStackTrace()[0] + "===" + e.getMessage() + "===");
			WeResult.build(500, "清空索引库错误");
		}
		return WeResult.ok();
	}

}
