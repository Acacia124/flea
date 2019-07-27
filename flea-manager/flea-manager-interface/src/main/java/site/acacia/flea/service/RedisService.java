package site.acacia.flea.service;

import java.util.List;
import java.util.Map;

import site.acacia.flea.pojo.Operate;
import site.acacia.flea.pojo.RedisInfoDetail;

public interface RedisService {
	List<RedisInfoDetail> getRedisInfo();

	List<Operate> getLogs(long entries);

	Long getLogLen();

	String logEmpty();

	Map<String, Object> getMemeryInfo();

	/**
	 * @return
	 */
	Map<String, Object> getKeysSize();
}
