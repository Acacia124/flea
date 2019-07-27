package site.acacia.flea.pojo;

import java.io.Serializable;

public class Operate implements Serializable {
	private static final long serialVersionUID = 668585616039078694L;
	private long id;// slowlog唯一编号id
	private String executeTime;// 查询的时间戳
	private String usedTime;// 查询的耗时（微妙），如表示本条命令查询耗时47微秒
	private String args;// 查询命令，完整命令为 SLOWLOG GET，slowlog最多保存前面的31个key和128字符

	public Operate() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	public String getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

}
