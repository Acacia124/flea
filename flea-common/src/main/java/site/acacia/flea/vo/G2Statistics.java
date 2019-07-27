/**
 * 
 */
package site.acacia.flea.vo;

import java.io.Serializable;

/**
 * @author 张胤
 *
 *         2019年5月26日-下午2:54:35
 */
public class G2Statistics implements Serializable {

	private static final long serialVersionUID = -3400309706875645603L;

	private Integer count;

	private Integer today;

	private Integer yesterday;

	private Integer week;

	private Integer lastWeek;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getToday() {
		return today;
	}

	public void setToday(Integer today) {
		this.today = today;
	}

	public Integer getYesterday() {
		return yesterday;
	}

	public void setYesterday(Integer yesterday) {
		this.yesterday = yesterday;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getLastWeek() {
		return lastWeek;
	}

	public void setLastWeek(Integer lastWeek) {
		this.lastWeek = lastWeek;
	}
}
