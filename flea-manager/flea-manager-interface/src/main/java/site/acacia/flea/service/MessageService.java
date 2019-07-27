package site.acacia.flea.service;

import java.util.List;

import site.acacia.flea.pojo.MessageInfo;
import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2018年12月8日-上午10:21:34
 */
public interface MessageService {

	WeResult addMessageInfo(MessageInfo info);

	WeResult addMessageUser(MessageInfo info, List<String> openIdList);

	WeResult selectMessageUser(String openId);

	WeResult updateMessageStatus(int messageInfoId, String openId);

	WeResult selectMessageNoRead(String openId, Byte status);

	WeResult selectMessageRead(String openId, Byte byte1);

	WeResult selectMessageInfo(String openId, int messageInfoId);

	WeResult updateMessageStatus(String openId, String type);

	WeResult addMessageUserByStatus(MessageInfo info, String status);
}
