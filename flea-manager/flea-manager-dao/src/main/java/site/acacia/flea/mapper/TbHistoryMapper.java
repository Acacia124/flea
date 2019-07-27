package site.acacia.flea.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.acacia.flea.pojo.TbHistory;
import site.acacia.flea.pojo.TbHistoryExample;
import site.acacia.flea.vo.ChatVo;

public interface TbHistoryMapper {
	int countByExample(TbHistoryExample example);

	int deleteByExample(TbHistoryExample example);

	int deleteByPrimaryKey(Integer hisId);

	int insert(TbHistory record);

	int insertSelective(TbHistory record);

	List<TbHistory> selectByExample(TbHistoryExample example);

	TbHistory selectByPrimaryKey(Integer hisId);

	int updateByExampleSelective(@Param("record") TbHistory record, @Param("example") TbHistoryExample example);

	int updateByExample(@Param("record") TbHistory record, @Param("example") TbHistoryExample example);

	int updateByPrimaryKeySelective(TbHistory record);

	int updateByPrimaryKey(TbHistory record);

	List<ChatVo> getValByMonth(@Param("start") Date start, @Param("end") Date end);

	List<ChatVo> getValByDay(@Param("start") Date start, @Param("end") Date end);

	List<ChatVo> getValByTime(@Param("start") Date start, @Param("end") Date end);

	List<ChatVo> getTopByDate(@Param("start") Date start, @Param("end") Date end);

	int getCountByToday();
}