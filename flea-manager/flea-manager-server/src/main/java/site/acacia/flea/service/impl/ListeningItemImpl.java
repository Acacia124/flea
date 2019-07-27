package site.acacia.flea.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import site.acacia.flea.mapper.TbCollectMapper;
import site.acacia.flea.mapper.TbHistoryMapper;
import site.acacia.flea.mapper.TbItemMapper;
import site.acacia.flea.pojo.TbCollect;
import site.acacia.flea.pojo.TbCollectExample;
import site.acacia.flea.pojo.TbCollectExample.Criteria;
import site.acacia.flea.pojo.TbHistory;
import site.acacia.flea.pojo.TbItem;

/**
 * 监听中间件消息
 * 
 * @author 张胤
 * 
 *         2019年5月4日-下午3:26:33
 */
@Service
public class ListeningItemImpl {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbCollectMapper collectMapper;

	@Autowired
	private TbHistoryMapper hisMapper;

	@JmsListener(destination = "deleteItem")
	public void deleteItem(String text) {
		if (StringUtils.isBlank(text)) {
			return;
		}
		String userId = text.split("#")[0];
		String itemId = text.split("#")[1];
		TbCollectExample collectExample = new TbCollectExample();
		Criteria createCriteria = collectExample.createCriteria();
		createCriteria.andOpenidEqualTo(userId);
		createCriteria.andItemIdEqualTo(itemId);
		collectMapper.deleteByExample(collectExample);

		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		if (item != null) {
			if (item.getCollectNum() != 0) {
				item.setCollectNum(item.getCollectNum() - 1);
				itemMapper.updateByPrimaryKeySelective(item);
			}
		}
	}

	@JmsListener(destination = "addItem")
	public void updateItem(String text) {
		if (StringUtils.isBlank(text)) {
			return;
		}
		String userId = text.split("#")[0];
		String itemId = text.split("#")[1];
		TbCollect collect = new TbCollect();
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		if (item == null) {
			return;
		}
		collect.setCollectName(item.getItemTitle());
		collect.setCollectOriginalPrice(item.getOriginalPrice());
		collect.setCollectPrice(item.getPrice());
		collect.setCollectSellPoint(item.getSellPoint());
		collect.setCollectSellSchool(item.getSellAddress());
		String image = "";
		if (item.getItemImage() != null && !"".equals(item.getItemImage())) {
			String[] strings = item.getItemImage().split(",");
			image = strings[0];
		} else {
			image = null;
		}
		collect.setCollectImage(image);
		collect.setCollectNewnessRate(item.getNewnessRate());
		collect.setCollectStatus((byte) 1);
		collect.setItemId(itemId);
		collect.setOpenid(userId);
		collectMapper.insert(collect);

		item.setCollectNum(item.getCollectNum() + 1);
		itemMapper.updateByPrimaryKeySelective(item);
	}

	@JmsListener(destination = "addItemHis")
	public void hisItem(String text) {
		String itemId = text.split("#")[0];
		String openid = text.split("#")[1];
		TbHistory history = new TbHistory();
		history.setItemId(itemId);
		history.setHisCreate(new Date());
		history.setOpenid(openid);
		hisMapper.insertSelective(history);
	}
}
