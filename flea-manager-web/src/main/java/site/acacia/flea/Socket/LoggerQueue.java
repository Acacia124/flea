package site.acacia.flea.Socket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import site.acacia.flea.pojo.LoggerMessage;

/**
 * 日志队列，保存最近20条日志信息
 * 
 * @author 张胤 2018年12月21日-下午2:53:45
 */
public class LoggerQueue {
	// 队列大小
	public static final int QUEUE_MAX_SIZE = 1000;
	private static LoggerQueue alarmMessageQueue = new LoggerQueue();
	// 阻塞队列
	private BlockingQueue<Object> blockingQueue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

	private LoggerQueue() {
	}

	public static LoggerQueue getInstance() {
		return alarmMessageQueue;
	}

	public int getSize() {
		return this.blockingQueue.size();
	}

	/**
	 * 消息入队
	 *
	 * @param log
	 * @return
	 */
	public boolean push(LoggerMessage log) {
		return this.blockingQueue.add(log);// 队列满了就抛出异常，不阻塞
	}

	/**
	 * 消息出队
	 *
	 * @return
	 */
	public LoggerMessage poll() {
		LoggerMessage result = null;
		try {
			result = (LoggerMessage) this.blockingQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setNullQueue() {
		blockingQueue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);
	}
}
