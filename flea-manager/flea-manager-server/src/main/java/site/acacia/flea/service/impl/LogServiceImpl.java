/**
 * 
 */
package site.acacia.flea.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import site.acacia.flea.service.LogService;
import site.acacia.flea.util.LogUtil;

/**
 * @author 张胤
 *
 *         2018年12月12日-下午4:28:53
 */
@Service
public class LogServiceImpl implements LogService, Serializable {

	private static final long serialVersionUID = -5969110933456131029L;

	protected static Logger logger = LoggerFactory.getLogger(LogService.class);

	@Override
	public List<String> getLogList() {
		logger.info("==> Request parameter Empty");
		return LogUtil.getFile("log", 0);
	}

}
