/****/
package site.acacia.flea.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.search.service.SolrService;

/*******
 * 
 * @author 张胤**2018 年12月28日-上午11:09:07
 */
@RestController
@CrossOrigin
@RequestMapping("/api/user/")
public class SearchController {

	protected static Logger logger = LoggerFactory.getLogger(ItemCatController.class);

	@Autowired
	private SolrService solrService;

	/**
	 * 导入数据库所有商品到solr
	 */
	@RequestMapping("solr/import")
	public WeResult importAllItem() {
		logger.info("==> Request parameter Empty");
		return solrService.importSolrList();
	}

	/**
	 * 清空solr库
	 */
	@RequestMapping("solr/delete")
	public WeResult emptySolr() {
		logger.info("==> Request parameter Empty");
		return solrService.deleteAllSolr();
	}
}
