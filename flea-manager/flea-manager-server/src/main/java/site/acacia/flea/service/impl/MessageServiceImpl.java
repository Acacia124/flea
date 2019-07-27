package site.acacia.flea.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import site.acacia.flea.mapper.MessageInfoMapper;
import site.acacia.flea.mapper.MessageUserMapper;
import site.acacia.flea.pojo.Message;
import site.acacia.flea.pojo.MessageInfo;
import site.acacia.flea.pojo.MessageInfoExample;
import site.acacia.flea.pojo.MessageUser;
import site.acacia.flea.pojo.MessageUserExample;
import site.acacia.flea.pojo.MessageUserExample.Criteria;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.ItemService;
import site.acacia.flea.service.MessageService;

/**
 * @author 张胤
 *
 *         2018年12月8日-上午11:15:01
 */
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageInfoMapper infoMapper;

	@Autowired
	private MessageUserMapper userMapper;

	protected static Logger logger = LoggerFactory.getLogger(ItemService.class);

	/**
	 * 增加消息详情
	 */
	@Override
	public WeResult addMessageInfo(MessageInfo info) {
		logger.info("==> Request parameter: info(" + info + ")");
		info.setMessagePushTime(new Date());
		infoMapper.insertSelective(info);
		System.out.println(JSON.toJSONString(info));
		return WeResult.ok(info);
	}

	public static void main(String[] args) {
		MessageInfo info = new MessageInfo();
		info.setMessageTxt("<p>哒哒哒我</p>");
		info.setMessageType("消息");
		info.setMessageTitle("测试鼻头爱11");
		System.out.println(JSON.toJSONString(info));
		MessageInfo parseObject = JSON.parseObject(JSON.toJSONString(info), MessageInfo.class);
		System.out.println(parseObject.getMessageTitle());
	}

	/**
	 * 增加用户消息对应表的信息
	 */
	@Override
	public WeResult addMessageUser(MessageInfo info, List<String> openIdList) {
		logger.info("==> Request parameter: info(" + info + "),openIdList(" + openIdList + ")");
		MessageUser messageUser = new MessageUser();
		messageUser.setMessageInfoId(info.getMessageInfoId());
		messageUser.setMessageUserStatus(0);
		messageUser.setMessageType(info.getMessageType());
		for (String openId : openIdList) {
			messageUser.setUserId(openId);
			userMapper.insertSelective(messageUser);
		}
		return WeResult.ok();
	}

	/**
	 * 查询用户未接受信息
	 */
	@Override
	public WeResult selectMessageUser(String openId) {
		logger.info("==> Request parameter:openId(" + openId + ")");
		MessageUserExample example = new MessageUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUserIdEqualTo(openId);
		createCriteria.andMessageUserStatusEqualTo(0);
		List<MessageUser> messageUserList = userMapper.selectByExample(example);
		return WeResult.ok(messageUserList.size());
	}

	/**
	 * 更新用户信息接收状态表为已读
	 */
	@Override
	public WeResult updateMessageStatus(int messageInfoId, String openId) {
		logger.info("==> Request parameter: messageInfoId(" + messageInfoId + "),openId(" + openId + ")");
		MessageUserExample example = new MessageUserExample();
		Criteria createCriteria = example.createCriteria();
		MessageUser messageUser = new MessageUser();
		createCriteria.andMessageInfoIdEqualTo(messageInfoId);
		createCriteria.andUserIdEqualTo(openId);
		messageUser.setMessage(new Date());
		messageUser.setMessageUserStatus(1);
		userMapper.updateByExampleSelective(messageUser, example);
		return WeResult.ok();
	}

	/**
	 * 查询未读消息列表
	 */
	@Override
	public WeResult selectMessageNoRead(String openId, Byte status) {
		logger.info("==> Request parameter:openId(" + openId + ")");
		MessageUserExample example = new MessageUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUserIdEqualTo(openId);
		createCriteria.andMessageUserStatusEqualTo(0);
		List<MessageUser> messageUserList = userMapper.selectByExample(example);
		List<Message> messageInfos = new ArrayList<Message>();
		for (MessageUser messageUser : messageUserList) {
			MessageInfo messageInfo = infoMapper.selectByPrimaryKey(messageUser.getMessageInfoId());
			Message message = new Message();
			message.setAvatar(messageInfo.getMessageOperatorAvater());
			message.setId(messageInfo.getMessageInfoId() + "");
			message.setDescription(messageInfo.getMessageTitle());
			message.setDatetime(messageInfo.getMessagePushTime());
			message.setType(messageInfo.getMessageType());
			message.setTitle(messageInfo.getMessageTitle());
			message.setNickName(messageInfo.getMessageOperatorNick());
			messageInfos.add(message);
		}
		// 加上全体消息
		for (MessageUser messageUser : getMessagesByStatus(status, 0)) {
			MessageInfoExample example2 = new MessageInfoExample();
			site.acacia.flea.pojo.MessageInfoExample.Criteria createCriteria2 = example2.createCriteria();
			createCriteria2.andMessageInfoIdEqualTo(messageUser.getMessageInfoId());
			createCriteria2.andMessageOperatorNotEqualTo(openId);
			List<MessageInfo> messageInfos2 = infoMapper.selectByExample(example2);
			for (MessageInfo messageInfo : messageInfos2) {
				Message message = new Message();
				message.setAvatar(messageInfo.getMessageOperatorAvater());
				message.setId(messageInfo.getMessageInfoId() + "");
				message.setDescription(messageInfo.getMessageTitle());
				message.setDatetime(messageInfo.getMessagePushTime());
				message.setType(messageInfo.getMessageType());
				message.setTitle(messageInfo.getMessageTitle());
				message.setNickName(messageInfo.getMessageOperatorNick());
				messageInfos.add(message);
			}
		}
		return WeResult.ok(messageInfos);
	}

	/**
	 * 查询已读消息列表
	 */
	@Override
	public WeResult selectMessageRead(String openId, Byte status) {
		logger.info("==> Request parameter:openId(" + openId + ")");
		MessageUserExample example = new MessageUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUserIdEqualTo(openId);
		createCriteria.andMessageUserStatusEqualTo(1);
		List<MessageUser> messageUserList = userMapper.selectByExample(example);
		List<Message> messageInfos = new ArrayList<Message>();
		for (MessageUser messageUser : messageUserList) {
			MessageInfo messageInfo = infoMapper.selectByPrimaryKey(messageUser.getMessageInfoId());
			Message message = new Message();
			message.setAvatar(messageInfo.getMessageOperatorAvater());
			message.setId(messageInfo.getMessageInfoId() + "");
			message.setDescription(messageInfo.getMessageTitle());
			message.setDatetime(messageInfo.getMessagePushTime());
			message.setType(messageInfo.getMessageType());
			message.setTitle(messageInfo.getMessageTitle());
			message.setNickName(messageInfo.getMessageOperatorNick());
			messageInfos.add(message);
		}
		// 加上全体消息
		for (MessageUser messageUser : getMessagesByStatus(status, 1)) {
			MessageInfoExample example2 = new MessageInfoExample();
			site.acacia.flea.pojo.MessageInfoExample.Criteria createCriteria2 = example2.createCriteria();
			createCriteria2.andMessageInfoIdEqualTo(messageUser.getMessageInfoId());
			createCriteria2.andMessageOperatorNotEqualTo(openId);
			List<MessageInfo> messageInfos2 = infoMapper.selectByExample(example2);
			for (MessageInfo messageInfo : messageInfos2) {
				Message message = new Message();
				message.setAvatar(messageInfo.getMessageOperatorAvater());
				message.setId(messageInfo.getMessageInfoId() + "");
				message.setDescription(messageInfo.getMessageTitle());
				message.setDatetime(messageInfo.getMessagePushTime());
				message.setType(messageInfo.getMessageType());
				message.setTitle(messageInfo.getMessageTitle());
				message.setNickName(messageInfo.getMessageOperatorNick());
				messageInfos.add(message);
			}
		}
		return WeResult.ok(messageInfos);
	}

	/**
	 * 查询消息详细信息
	 */
	@Override
	public WeResult selectMessageInfo(String openId, int messageInfoId) {
		logger.info("==> Request parameter: messageInfoId(" + messageInfoId + "),openId(" + openId + ")");
		this.updateMessageStatus(messageInfoId, openId);
		MessageInfo messageInfo = infoMapper.selectByPrimaryKey(messageInfoId);
		return WeResult.ok(messageInfo);
	}

	/**
	 * 根据类型对用户所属未读消息更改为已读
	 */
	@Override
	public WeResult updateMessageStatus(String openId, String type) {
		logger.info("==> Request parameter: type(" + type + "),openId(" + openId + ")");
		MessageUserExample example = new MessageUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andMessageTypeEqualTo(type);
		createCriteria.andUserIdEqualTo(openId);
		createCriteria.andMessageUserStatusEqualTo(0);
		MessageUser messageUser = new MessageUser();
		messageUser.setMessageUserStatus(1);
		userMapper.updateByExampleSelective(messageUser, example);
		return WeResult.ok();
	}

	/**
	 * 根据状态增加对应用户表 status： A： 所有用户 B：所有管理员 C: 所有普通会员
	 */
	@Override
	public WeResult addMessageUserByStatus(MessageInfo info, String status) {
		logger.info("==> Request parameter: info(" + info + "),status(" + status + ")");
		MessageUser messageUser = new MessageUser();
		messageUser.setMessageInfoId(info.getMessageInfoId());
		messageUser.setMessageUserStatus(0);
		messageUser.setMessageType(info.getMessageType());
		if ("A".equals(status)) {
			messageUser.setUserId("B");
			userMapper.insertSelective(messageUser);
			messageUser.setUserId("C");
			userMapper.insertSelective(messageUser);
		} else if ("B".equals(status)) {
			messageUser.setUserId("B");
			userMapper.insertSelective(messageUser);
		} else if ("C".equals(status)) {
			messageUser.setUserId("C");
			userMapper.insertSelective(messageUser);
		} else {
			return WeResult.build(400, "请求参数错误");
		}
		return WeResult.ok();
	}

	public List<MessageUser> getMessagesByStatus(Byte status, Integer readStatus) {
		MessageUserExample example = new MessageUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andMessageUserStatusEqualTo(readStatus);
		if (3 == status) {
			createCriteria.andUserIdEqualTo("B");
			List<MessageUser> messageUserList = userMapper.selectByExample(example);
			return messageUserList;
		}
		if (2 == status) {
			createCriteria.andUserIdEqualTo("C");
			List<MessageUser> messageUserList = userMapper.selectByExample(example);
			return messageUserList;
		}
		return new ArrayList<MessageUser>();
	}

}
