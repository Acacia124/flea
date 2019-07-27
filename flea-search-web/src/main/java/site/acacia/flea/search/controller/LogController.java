package site.acacia.flea.search.controller;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import site.acacia.flea.pojo.FileVo;
import site.acacia.flea.pojo.LoggerMessage;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.search.service.LogSearchService;
import site.acacia.flea.search.socket.LoggerQueue;
import site.acacia.flea.util.LogUtil;

/**
 * @author 张胤
 *
 *         2018年12月12日-下午4:34:37
 */
@Controller
@CrossOrigin
@RequestMapping("/api")
public class LogController {

	@Autowired
	private LogSearchService logService;

	private static final String downloadaddress = "http://47.106.181.166:8080";

	protected static Logger logger = LoggerFactory.getLogger(LogController.class);

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@ResponseBody
	@RequestMapping(value = "/user/log/search/list/{host}", method = RequestMethod.GET)
	public WeResult getLogInfoList(@PathVariable Integer host, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: host(" + host + ")");
		if (host == 1) {
			return WeResult.ok(logService.getLogList());
		} else if (host == 2) {
			return WeResult.ok(LogUtil.getFile("log", 0));
		}
		return WeResult.build(404, "未找到");
	}

	@RequestMapping(value = "/user/log/search/download/{host}", method = RequestMethod.GET)
	public String downLoadLogFile(@PathVariable Integer host, String fileName, HttpServletRequest request,
			HttpServletResponse res) throws IOException {
		logger.info("==> Request parameter: host(" + host + "),fileName(" + fileName + ")");
		if (host == 1) {
			String token = "";
			return "redirect:" + downloadaddress + "/api/user/log/search/download/1?fileName=" + fileName + "&token="
					+ token + (new Random().nextInt(10)) + (new Random().nextInt(10)) + (new Random().nextInt(10))
					+ (new Random().nextInt(10));
		} else if (host == 2) {
			res.setHeader("content-type", "application/octet-stream");
			res.setContentType("application/octet-stream");
			res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			FileVo fileVo = new FileVo();
			fileVo.setFileName(fileName);
			fileVo.setOs(res.getOutputStream());
			LogUtil.download(fileVo);
		}
		return null;
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
								messagingTemplate.convertAndSend("/topic/pullLoggerSearch2", log);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		executorService.submit(runnable);
	}

}
