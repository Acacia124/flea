package site.acacia.flea.controller;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.Socket.LoggerQueue;
import site.acacia.flea.pojo.FileVo;
import site.acacia.flea.pojo.LoggerMessage;
import site.acacia.flea.util.LogUtil;

/**
 * @author 张胤
 *
 *         2018年12月18日-上午9:16:54
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class LogController {

	protected static Logger logger = LoggerFactory.getLogger(LogController.class);

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	// @Autowired
	// private LoginServiceImpl loginService;
	//
	// @Autowired
	// private ItemService itemService;
	//
	// @Autowired
	// private UserService userService;
	//
	// @Autowired
	// private ItemHisService hisService;

	@RequestMapping(value = "/user/log/manager/download/1", method = RequestMethod.GET)
	public void downLoadLogFile(String fileName, HttpServletResponse res) throws IOException {
		logger.info("==> fileName(" + fileName + ")");
		res.setHeader("content-type", "application/octet-stream");
		res.setContentType("application/octet-stream");
		res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		FileVo fileVo = new FileVo();
		fileVo.setFileName(fileName);
		fileVo.setOs(res.getOutputStream());
		LogUtil.download(fileVo);
	}

	/**
	 * 推送日志到/topic/pullLogger
	 */
	@PostConstruct
	public void pushLogger() {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						LoggerMessage log = LoggerQueue.getInstance().poll();
						if (log != null) {
							if (messagingTemplate != null)
								messagingTemplate.convertAndSend("/topic/pullLogger1", log);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		executorService.submit(runnable);
	}

	// @RequestMapping("/his/g2")
	// public Set<G2Vo> tt(long start, long end, Integer status) throws
	// ParseException {
	// Set<G2Vo> hisByDate = hisService.getHisByDate(new Date(start), new Date(end),
	// status);
	// return hisByDate;
	// }
	//
	// @RequestMapping("/his/top")
	// public List<ChatVo> tt(long start, long end) throws ParseException {
	// List<ChatVo> hisTop = hisService.getHisTop(new Date(start), new Date(end));
	// return hisTop;
	// }
	//
	// @RequestMapping("/analysis")
	// public Map<String, Object> getAnalysis(long start, long end) {
	// Map<String, Object> map = new HashMap<>();
	// map.put("hisToday", hisService.getHisToday());
	// map.put("hisTop", hisService.getHisTop(new Date(start), new Date(end)));
	// map.put("hisG2", hisService.getHisByDate(new Date(start), new Date(end), 3));
	// map.put("hisSum", hisService.getHisSum());
	// map.put("itemData", itemService.getItemData());
	// map.put("userData", userService.getUserData());
	// map.put("itemG2", itemService.getItemG2());
	// return map;
	// }

	// @RequestMapping("/openIds")
	// public ArrayList<String> tt() {
	// return loginService.getOpenIds();
	// }
	//
	// @RequestMapping("/add")
	// public WeResult tt(@RequestBody TbItem item) {
	// System.out.println(JSON.toJSONString(item));
	// return itemService.addItem(item);
	// }
}
