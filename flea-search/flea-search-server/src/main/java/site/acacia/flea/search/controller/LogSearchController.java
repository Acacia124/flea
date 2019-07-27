package site.acacia.flea.search.controller;

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

import site.acacia.flea.pojo.FileVo;
import site.acacia.flea.pojo.LoggerMessage;
import site.acacia.flea.search.socket.LoggerQueue;
import site.acacia.flea.util.LogUtil;

/**
 * @author 张胤
 *
 *         2018年12月18日-上午9:16:54
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class LogSearchController {

	protected static Logger logger = LoggerFactory.getLogger(LogSearchController.class);

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@RequestMapping(value = "/user/log/search/download/1", method = RequestMethod.GET)
	public void downLoadLogFile(String fileName, String token, HttpServletResponse res) throws IOException {
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
	 * 推送日志
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
								messagingTemplate.convertAndSend("/topic/pullLoggerSearch1", log);
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
