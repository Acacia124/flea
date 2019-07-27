/**
 * 
 */
package site.acacia.flea.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import site.acacia.flea.vo.ChatVo;
import site.acacia.flea.vo.G2Vo;

/**
 * @author 张胤
 *
 *         2019年5月25日-下午6:58:14
 */
public interface ItemHisService {

	Set<G2Vo> getHisByDate(Date statrt, Date end, int status);

	List<ChatVo> getHisTop(Date start, Date end);

	int getHisToday();

	int getHisSum();
}
