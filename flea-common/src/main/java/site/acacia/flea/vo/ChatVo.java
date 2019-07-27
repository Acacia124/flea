package site.acacia.flea.vo;

import java.io.Serializable;

public class ChatVo implements Serializable {
	private static final long serialVersionUID = 5830668955231254449L;
	private Integer val;
	private String hCreate;

	public Integer getVal() {
		return val;
	}

	public void setVal(Integer val) {
		this.val = val;
	}

	public String gethCreate() {
		return hCreate;
	}

	public void sethCreate(String hCreate) {
		this.hCreate = hCreate;
	}

}