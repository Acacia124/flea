package site.acacia.flea.controller;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import site.acacia.flea.Socket.LoggerQueue;
import site.acacia.flea.content.service.LogContentService;
import site.acacia.flea.pojo.FileVo;
import site.acacia.flea.pojo.LoggerMessage;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.LogService;
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
	private LogService logService;

	@Autowired
	private LogContentService logConService;

	private static final String downloadaddress = "http://101.200.56.187:8080";
	private static final String downloadaddress2 = "http://47.106.181.166:8082";

	protected static Logger logger = LoggerFactory.getLogger(LogController.class);

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Value("${jwt.tokenHead}")
	private String TOKENHEAD;

	@ResponseBody
	@RequestMapping(value = "/user/log/{server}/list/{host}", method = RequestMethod.GET)
	public WeResult getLogInfoList(@PathVariable String server, @PathVariable Integer host, HttpServletRequest request)
			throws Exception {
		logger.info("==> Request parameter: host(" + host + ")" + ", server(" + server + ")");
		if ("manager".equals(server)) {
			if (host == 1) {
				return WeResult.ok(logService.getLogList());
			} else if (host == 2) {
				return WeResult.ok(LogUtil.getFile("log", 0));
			}
		} else if ("content".equals(server)) {
			return WeResult.ok(logConService.getLogList());
		}
		return WeResult.build(404, "未找到");
	}

	@RequestMapping(value = "/user/log/{server}/download/{host}", method = RequestMethod.GET)
	public String downLoadLogFile(@PathVariable String server, @PathVariable Integer host, String fileName,
			HttpServletRequest request, HttpServletResponse res) throws IOException {
		logger.info(
				"==> Request parameter: host(" + host + "),fileName(" + fileName + ")" + ", server(" + server + ")");
		if ("manager".equals(server)) {
			if (host == 1) {
				String token = request.getHeader(TOKENHEAD);
				return "redirect:" + downloadaddress + "/api/user/log/manager/download/1?fileName=" + fileName
						+ "&token=" + token + (new Random().nextInt(10)) + (new Random().nextInt(10))
						+ (new Random().nextInt(10)) + (new Random().nextInt(10));
			} else if (host == 2) {
				res.setHeader("content-type", "application/octet-stream");
				res.setContentType("application/octet-stream");
				res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
				FileVo fileVo = new FileVo();
				fileVo.setFileName(fileName);
				fileVo.setOs(res.getOutputStream());
				LogUtil.download(fileVo);
			}
		} else if ("content".equals(server)) {
			String token = request.getHeader(TOKENHEAD);
			return "redirect:" + downloadaddress2 + "/api/user/log/content/download?fileName=" + fileName + "&token="
					+ token + (new Random().nextInt(10)) + (new Random().nextInt(10)) + (new Random().nextInt(10))
					+ (new Random().nextInt(10));
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
								messagingTemplate.convertAndSend("/topic/pullLogger2", log);
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
