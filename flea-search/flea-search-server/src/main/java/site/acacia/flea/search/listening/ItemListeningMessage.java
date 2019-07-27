/**
 * 
 */
package site.acacia.flea.search.listening;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import site.acacia.flea.pojo.SearchItem;
import site.acacia.flea.search.mapper.SolrMapper;

/**
 * @author 张胤
 *
 *         2018年9月6日-下午6:26:31
 */
@Service
public class ItemListeningMessage {

	@Autowired
	private SolrClient solrClient;

	@Autowired
	private SolrMapper solrMapper;

	protected static Logger logger = LoggerFactory.getLogger(ItemListeningMessage.class);

	// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
	@JmsListener(destination = "itemMeaage")
	public void onItemQueue(String text) {
		logger.info("=============>MQ队列接受到添加消息<==============");
		// 为防止事务还未提交等待1/10秒
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error(e.getStackTrace()[0] + "===" + e.getMessage() + "===");
		}
		// 数据库获取刚添加商品的信息
		SearchItem searchItem = solrMapper.getItemById(text);
		// 创建文档对象
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
		} catch (IOException e) {
			logger.error(e.getStackTrace()[0] + "===" + e.getMessage() + "===");
		}
	}

	@JmsListener(destination = "soldOutMeaage")
	public void soldOutQueue(String text) {
		logger.info("===================MQ接收到删除消息====================");
		try {
			solrClient.deleteById(text);
			solrClient.commit();
		} catch (SolrServerException e) {
			logger.error(e.getStackTrace()[0] + "===" + e.getMessage() + "===");
		} catch (IOException e) {
			logger.error(e.getStackTrace()[0] + "===" + e.getMessage() + "===");
		}
	}
}
