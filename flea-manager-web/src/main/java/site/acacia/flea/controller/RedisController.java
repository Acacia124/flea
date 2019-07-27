package site.acacia.flea.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.RedisService;

/**
 * 
 * @author 张胤
 *
 *         2019年3月30日-上午11:35:26
 */
@CrossOrigin
@RestController
@RequestMapping("/api/user/")
public class RedisController {

	@Autowired
	RedisService redisService;

	@RequestMapping(value = "redisMonitor")
	public WeResult redisMonitor() {
		Map<String, Object> map = new ConcurrentHashMap<>(5);
		map.put("infoList", redisService.getRedisInfo());
		map.put("logList", redisService.getLogs(1000));
		map.put("logLen", redisService.getLogLen());
		return WeResult.ok(map);
	}

	@RequestMapping(value = "logEmpty")
	public String logEmpty() {
		return redisService.logEmpty();
	}

	@RequestMapping(value = "getKeysSize")
	public WeResult getKeysSize() {
		return WeResult.ok(redisService.getKeysSize());
	}

	@RequestMapping(value = "getMemeryInfo")
	public WeResult getMemeryInfo() {
		return WeResult.ok(redisService.getMemeryInfo());
	}

	@RequestMapping("getMemory/{number}")
	public WeResult getMemoryInfo(@PathVariable("number") Integer number) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < number; i++) {
			mapList.add(redisService.getMemeryInfo());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return WeResult.ok(mapList);
	}

}
