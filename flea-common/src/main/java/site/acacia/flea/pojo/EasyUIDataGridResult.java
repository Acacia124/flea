package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 张胤
 *
 *         2018年8月3日-上午10:33:56
 */
public class EasyUIDataGridResult<T> implements Serializable {
	private static final long serialVersionUID = 4854003311673108204L;
	private long total;
	private List<T> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
