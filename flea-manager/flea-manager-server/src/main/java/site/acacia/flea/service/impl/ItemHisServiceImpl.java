/**
 * 
 */
package site.acacia.flea.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.acacia.flea.mapper.TbHistoryMapper;
import site.acacia.flea.pojo.TbHistoryExample;
import site.acacia.flea.service.ItemHisService;
import site.acacia.flea.vo.ChatVo;
import site.acacia.flea.vo.G2Vo;

/**
 * @author 张胤
 *
 *         2019年5月25日-下午6:57:59
 */
@Service
public class ItemHisServiceImpl implements ItemHisService {

	@Autowired
	private TbHistoryMapper hisMapper;

	/**
	 * status: 1 本日 2周 3月 4 年
	 */
	@Override
	public Set<G2Vo> getHisByDate(Date start, Date end, int status) {
		List<ChatVo> val = new ArrayList<ChatVo>();
		Set<G2Vo> g2 = new TreeSet<G2Vo>();
		if (status == 1) {
			val = hisMapper.getValByTime(start, end);
			for (int j = 0; j < val.size(); j++) {
				G2Vo g2Vo = new G2Vo();
				g2Vo.setX(val.get(j).gethCreate() + ":00");
				g2Vo.setY(val.get(j).getVal());
				g2.add(g2Vo);
			}
			for (int i = 0; i < 24; i++) {
				G2Vo g2Vo = new G2Vo();
				g2Vo.setX(i + ":00");
				g2Vo.setY(0);
				g2.add(g2Vo);
			}
		} else if (status == 2) {
			val = hisMapper.getValByDay(start, end);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar tempStart = Calendar.getInstance();
			tempStart.setTime(start);
			Calendar tempEnd = Calendar.getInstance();
			tempStart.setTime(end);
			tempEnd.add(Calendar.DATE, +1);
			for (int j = 0; j < val.size(); j++) {
				G2Vo g2Vo = new G2Vo();
				g2Vo.setX(val.get(j).gethCreate());
				g2Vo.setY(val.get(j).getVal());
				g2.add(g2Vo);
			}
			while (tempStart.before(tempEnd)) {
				G2Vo g2Vo = new G2Vo();
				g2Vo.setX(df.format(tempStart.getTime()));
				g2Vo.setY(0);
				g2.add(g2Vo);
				tempStart.add(Calendar.DAY_OF_YEAR, 1);
			}
		} else if (status == 3) {
			val = hisMapper.getValByDay(start, end);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar tempStart = Calendar.getInstance();
			tempStart.setTime(start);
			Calendar tempEnd = Calendar.getInstance();
			tempEnd.setTime(end);
			tempEnd.add(Calendar.DATE, +1);
			for (int j = 0; j < val.size(); j++) {
				G2Vo g2Vo = new G2Vo();
				g2Vo.setX(val.get(j).gethCreate());
				g2Vo.setY(val.get(j).getVal());
				g2.add(g2Vo);
			}
			while (tempStart.before(tempEnd)) {
				G2Vo g2Vo = new G2Vo();
				g2Vo.setX(df.format(tempStart.getTime()));
				g2Vo.setY(0);
				g2.add(g2Vo);
				tempStart.add(Calendar.DAY_OF_YEAR, 1);
			}
		} else {
			val = hisMapper.getValByMonth(start, end);
			for (int j = 0; j < val.size(); j++) {
				G2Vo g2Vo = new G2Vo();
				g2Vo.setX(val.get(j).gethCreate() + "月");
				g2Vo.setY(val.get(j).getVal());
				g2.add(g2Vo);
			}
			for (int i = 1; i <= 12; i++) {
				G2Vo g2Vo = new G2Vo();
				g2Vo.setX(i + "月");
				g2Vo.setY(0);
				g2.add(g2Vo);
			}
		}
		return g2;
	}

	@Override
	public List<ChatVo> getHisTop(Date start, Date end) {
		List<ChatVo> topByDate = hisMapper.getTopByDate(start, end);
		return topByDate;
	}

	@Override
	public int getHisToday() {
		return hisMapper.getCountByToday();
	}

	@Override
	public int getHisSum() {
		TbHistoryExample example = new TbHistoryExample();
		return hisMapper.countByExample(example);
	}

}
