/**
 * 
 */
package site.acacia.flea.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.exception.NoPermission;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.service.ItemHisService;
import site.acacia.flea.service.ItemService;
import site.acacia.flea.service.UserService;
import site.acacia.flea.vo.ChatVo;
import site.acacia.flea.vo.G2Vo;

/**
 * @author 张胤
 *
 *         2019年5月27日-上午9:22:03
 */
@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class AnalysisController {
	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemHisService hisService;

	@RequestMapping("/his/g2")
	public Set<G2Vo> hisG2(HttpServletRequest request, long start, long end, Integer status)
			throws ParseException, NoPermission {
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null || user.getUserStatus() != 3) {
			throw new NoPermission("403");
		} else {
			Set<G2Vo> hisByDate = hisService.getHisByDate(new Date(start), new Date(end), status);
			return hisByDate;
		}
	}

	@RequestMapping("/his/top")
	public List<ChatVo> HisTopt(HttpServletRequest request, long start, long end) throws ParseException, NoPermission {
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null || user.getUserStatus() != 3) {
			throw new NoPermission("403");
		} else {
			List<ChatVo> hisTop = hisService.getHisTop(new Date(start), new Date(end));
			return hisTop;
		}
	}

	@RequestMapping("/analysis")
	public Map<String, Object> getAnalysis(HttpServletRequest request, long start, long end) throws NoPermission {
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null || user.getUserStatus() != 3) {
			throw new NoPermission("403");
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("hisToday", hisService.getHisToday());
			map.put("hisTop", hisService.getHisTop(new Date(start), new Date(end)));
			map.put("hisG2", hisService.getHisByDate(new Date(start), new Date(end), 3));
			map.put("hisSum", hisService.getHisSum());
			map.put("itemData", itemService.getItemData());
			map.put("userData", userService.getUserData());
			map.put("itemG2", itemService.getItemG2());
			return map;
		}
	}

}
