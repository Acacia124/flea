/**
 * 
 */
package site.acacia.flea.vo;

import java.io.Serializable;

/**
 * @author 张胤
 *
 *         2019年5月26日-上午12:46:36
 */
public class G2Vo implements Serializable, Comparable<G2Vo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5435266108677175439L;
	private String x;
	private Integer y;

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		G2Vo other = (G2Vo) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		return true;
	}

	@Override
	public int compareTo(G2Vo o) {
		// TODO 自动生成的方法存根
		if (o.getX().indexOf("月") != -1) {
			return Integer.parseInt(this.x.split("月")[0]) - Integer.parseInt(o.getX().split("月")[0]);
		} else if (o.getX().indexOf(":00") != -1) {
			return Integer.parseInt(this.x.split(":00")[0]) - Integer.parseInt(o.getX().split(":00")[0]);
		} else {
			return this.x.compareTo(o.getX());
		}
	}
}
