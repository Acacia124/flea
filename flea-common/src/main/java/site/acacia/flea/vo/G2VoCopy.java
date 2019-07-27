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
public class G2VoCopy implements Serializable, Comparable<G2VoCopy> {

	private static final long serialVersionUID = -7325279876589901091L;
	private String x;
	private Integer y1;
	private Integer y2;

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
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
		G2VoCopy other = (G2VoCopy) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		return true;
	}

	@Override
	public int compareTo(G2VoCopy o) {
		// TODO 自动生成的方法存根
		if (o.getX().indexOf("月") != -1) {
			return Integer.parseInt(this.x.split("月")[0]) - Integer.parseInt(o.getX().split("月")[0]);
		} else if (o.getX().indexOf(":00") != -1) {
			return Integer.parseInt(this.x.split(":00")[0]) - Integer.parseInt(o.getX().split(":00")[0]);
		} else {
			return this.x.compareTo(o.getX());
		}
	}

	public Integer getY1() {
		return y1;
	}

	public void setY1(Integer y1) {
		this.y1 = y1;
	}

	public Integer getY2() {
		return y2;
	}

	public void setY2(Integer y2) {
		this.y2 = y2;
	}
}
