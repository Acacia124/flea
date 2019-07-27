package site.acacia.flea.im;

/**
 * 用户聊天模块
 */
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import site.acacia.flea.pojo.TbMessage;
import site.acacia.flea.service.TbMessageService;

@ServerEndpoint(value = "/socketServer/im/{openId}/{sendId}")
@Component
public class SocketServer {

	private String openId;
	private String sendId;
	private static Map<String, Session> clients = new ConcurrentHashMap<String, Session>();

	protected static Logger logger = LoggerFactory.getLogger(SocketServer.class);

	// 此处是解决无法注入的关键
	private static TbMessageService msgService;

	public static void setApplicationContext(TbMessageService msgService) {
		SocketServer.msgService = msgService;
	}

	/**
	 * 用户连接时触发
	 * 
	 * @param session
	 * @param openId
	 */
	@OnOpen
	public void open(Session session, @PathParam(value = "openId") String openId,
			@PathParam(value = "sendId") String sendId) {
		this.openId = openId;
		this.sendId = sendId;
		logger.info("==> " + openId + " 进入了聊天室，聊天对象： " + sendId);
		clients.put(openId + "#" + sendId, session);
		// 发送未读历史记录给用户
		sendMessage(JSON.toJSONString(msgService.getOffLineMsg(openId, sendId)), openId + "#" + sendId);
	}

	/**
	 * 收到信息时触发
	 * 
	 * @param message
	 */
	@OnMessage
	public void onMessage(String message) {
		TbMessage msgVo = JSON.parseObject(message, TbMessage.class);
		msgVo.setMsgId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
		msgVo.setCreateBy(this.openId);
		msgVo.setDelFlag("1");
		msgVo.setSendTo(this.sendId);
		msgVo.setMsgType(msgVo.getMsgType());
		Session s = clients.get(sendId + "#" + openId);
		if (s != null) {
			msgVo.setIsOffLine("1");
			try {
				// 发送消息
				s.getBasicRemote().sendText(JSON.toJSONString(msgVo));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			msgVo.setIsOffLine("2");
		}
		// 保存消息到数据库
		msgService.saveMsg(msgVo);
	}

	/**
	 * 连接关闭触发
	 */
	@OnClose
	public void onClose() {
		clients.remove(this.openId + "#" + this.sendId);
	}

	/**
	 * 发生错误时触发
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
	}

	/**
	 * 信息发送的方法
	 * 
	 * @param message
	 * @param userId
	 */
	public synchronized static void sendMessage(String message, String userId) {
		Session s = clients.get(userId);
		if (s != null) {
			try {
				s.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
