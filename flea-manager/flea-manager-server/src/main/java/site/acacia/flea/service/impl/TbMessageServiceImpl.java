package site.acacia.flea.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.acacia.flea.mapper.TbMessageMapper;
import site.acacia.flea.pojo.TbMessage;
import site.acacia.flea.pojo.TbMessageExample;
import site.acacia.flea.pojo.TbMessageExample.Criteria;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.TbMessageService;

/**
 * @author 张胤
 *
 *         2019年1月16日-下午8:40:14
 */
@Service
public class TbMessageServiceImpl implements TbMessageService {

	protected static Logger logger = LoggerFactory.getLogger(TbMessageService.class);

	@Autowired
	private TbMessageMapper msgMapper;

	/*
	 * 未读消息列表(指定用户)
	 */
	@Override
	public WeResult getOffLineMsg(String sendTo, String createMsg) {
		logger.info("==> Request parameter: sendTo(" + sendTo + "),createMsg(" + createMsg + ")");
		TbMessageExample example = new TbMessageExample();
		Criteria createCriteria = example.createCriteria();
		example.setOrderByClause("create_time");
		// 离线
		createCriteria.andIsOffLineEqualTo("2");
		// 未删除
		createCriteria.andDelFlagEqualTo("1");
		createCriteria.andSendToEqualTo(sendTo);
		createCriteria.andCreateByEqualTo(createMsg);
		List<TbMessage> selectByExampleWithBLOBs = msgMapper.selectByExampleWithBLOBs(example);
		List<String> list = new ArrayList<>();
		for (TbMessage tbMessage : selectByExampleWithBLOBs) {
			list.add(tbMessage.getMsgId());
		}
		this.updateLineStatus(list);
		return WeResult.ok(selectByExampleWithBLOBs);
	}

	/*
	 * 未读消息列表（未读消息用户）
	 */
	@Override
	public WeResult getOffLineUser(String sendTo) {
		return WeResult.ok(msgMapper.selectNoReadMsgList(sendTo));
	}

	/*
	 * 獲取所有消息
	 */
	@Override
	public WeResult getAllMsg(String sendTo, String createMsg) {
		logger.info("==> Request parameter: sendTo(" + sendTo + "),createMsg(" + createMsg + ")");
		TbMessageExample example = new TbMessageExample();
		example.setOrderByClause("create_time");
		Criteria createCriteria = example.createCriteria();
		// 未删除
		createCriteria.andDelFlagEqualTo("1");
		createCriteria.andSendToEqualTo(sendTo);
		createCriteria.andCreateByEqualTo(createMsg);
		List<TbMessage> selectByExampleWithBLOBs = msgMapper.selectByExampleWithBLOBs(example);
		return WeResult.ok(selectByExampleWithBLOBs);
	}

	/*
	 * 保存消息
	 */
	@Override
	public WeResult saveMsg(TbMessage message) {
		logger.info("==> Request parameter: message(" + message + ")");
		message.setCreateTime(new Date());
		message.setDelFlag("1");
		msgMapper.insertSelective(message);
		return WeResult.ok();
	}

	/*
	 * 删除某个聊天记录
	 */
	@Override
	public WeResult delAllMsg(String createMsg, String sendTo) {
		logger.info("==> Request parameter: sendTo(" + sendTo + "),createMsg(" + createMsg + ")");
		TbMessageExample example = new TbMessageExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andCreateByEqualTo(createMsg);
		createCriteria.andSendToEqualTo(sendTo);
		msgMapper.deleteByExample(example);
		return WeResult.ok();
	}

	/*
	 * 清空表
	 */
	@Override
	public WeResult cleanTbMsg() {
		// TODO 自动生成的方法存根
		return null;
	}

	/*
	 * 更新离线信息为在线状态
	 */
	@Override
	public WeResult updateLineStatus(List<String> ids) {
		logger.info("==> Request parameter: ids(" + ids + ")");
		if (ids != null && ids.size() > 0) {
			TbMessageExample example = new TbMessageExample();
			Criteria createCriteria = example.createCriteria();
			createCriteria.andMsgIdIn(ids);
			TbMessage record = new TbMessage();
			record.setIsOffLine("1");
			msgMapper.updateByExampleSelective(record, example);
			return WeResult.ok();
		}
		return WeResult.ok();
	}

}
