package site.acacia.flea.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import site.acacia.flea.pojo.SearchResult;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.search.service.SolrService;

/**
 * @author 张胤
 *
 *         2018年9月5日-下午7:50:26
 */
@Controller
@RequestMapping("/weChat")
public class SolrController {

	@Autowired
	private SolrService solrService;

	@ResponseBody
	@RequestMapping("/insert")
	public WeResult insert() {
		return solrService.importSolrList();
	}

	@ResponseBody
	@RequestMapping("/select")
	public SearchResult select(@RequestParam(name = "keyword") String keyword,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "rows", defaultValue = "5") int rows,
			@RequestParam(name = "price", defaultValue = "价格") String price,
			@RequestParam(name = "sort", defaultValue = "综合排序") String sort,
			@RequestParam(name = "cat", defaultValue = "分类") String cat) throws Exception {
		return solrService.searchSolrList(keyword, page, rows, price, sort, cat);
	}

	@ResponseBody
	@RequestMapping("/delete")
	public WeResult deleteAll() throws Exception {
		return solrService.deleteAllSolr();
	}

}
