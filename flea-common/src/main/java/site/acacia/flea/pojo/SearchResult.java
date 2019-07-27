/**
 * 
 */
package site.acacia.flea.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Solr查询PoJo类
 * 
 * @author 张胤
 *
 *         2018年9月6日-上午9:17:40
 */
public class SearchResult implements Serializable {

	private static final long serialVersionUID = -6589692991257003405L;
	private String keyword; // 查询字段
	private int page; // 查询页
	private long recourdCount; // 总查询数
	private int totalPages; // 查询总页数
	private List<SearchItem> itemList;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getRecourdCount() {
		return recourdCount;
	}

	public void setRecourdCount(long recourdCount) {
		this.recourdCount = recourdCount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<SearchItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}

}
