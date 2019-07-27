package site.acacia.flea.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by kl on 2017/10/9. Content :
 */
@Data
public class LoggerMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8624539277196336109L;
	private String body;
	private String timestamp;
	private String threadName;
	private String className;
	private String level;

	public LoggerMessage(String body, String timestamp, String threadName, String className, String level) {
		super();
		this.body = body;
		this.timestamp = timestamp;
		this.threadName = threadName;
		this.className = className;
		this.level = level;
	}

}