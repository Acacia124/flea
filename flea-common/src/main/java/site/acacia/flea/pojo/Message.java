/**
 * 
 */
package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 张胤 2018年12月10日-上午9:47:29
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 1777883663736054337L;
	private String id;
	private String avatar;
	private String title;
	private String description;
	private String nickName;
	@JSONField(format = "yyyy-MM-dd HH:mm")
	private Date datetime;
	private String type;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", avatar=" + avatar + ", title=" + title + ", description=" + description
				+ ", datetime=" + datetime + ", type=" + type + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
