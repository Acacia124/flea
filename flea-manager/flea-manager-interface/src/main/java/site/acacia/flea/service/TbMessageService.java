package site.acacia.flea.service;

import java.util.List;

import site.acacia.flea.pojo.TbMessage;
import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2019年1月16日-下午8:32:41
 */
public interface TbMessageService {

	WeResult getOffLineMsg(String sendTo, String createMsg);

	WeResult getOffLineUser(String sendTo);

	WeResult getAllMsg(String sendTo, String createMsg);

	WeResult saveMsg(TbMessage message);

	WeResult delAllMsg(String createMsg, String sendTo);

	WeResult cleanTbMsg();

	WeResult updateLineStatus(List<String> ids);

}
