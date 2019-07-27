package site.acacia.flea.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.acacia.flea.pojo.IMMessage;
import site.acacia.flea.pojo.TbMessage;
import site.acacia.flea.pojo.TbMessageExample;

public interface TbMessageMapper {
	int countByExample(TbMessageExample example);

	int deleteByExample(TbMessageExample example);

	int deleteByPrimaryKey(String msgId);

	int insert(TbMessage record);

	int insertSelective(TbMessage record);

	List<TbMessage> selectByExampleWithBLOBs(TbMessageExample example);

	List<TbMessage> selectByExample(TbMessageExample example);

	TbMessage selectByPrimaryKey(String msgId);

	int updateByExampleSelective(@Param("record") TbMessage record, @Param("example") TbMessageExample example);

	int updateByExampleWithBLOBs(@Param("record") TbMessage record, @Param("example") TbMessageExample example);

	int updateByExample(@Param("record") TbMessage record, @Param("example") TbMessageExample example);

	int updateByPrimaryKeySelective(TbMessage record);

	int updateByPrimaryKeyWithBLOBs(TbMessage record);

	int updateByPrimaryKey(TbMessage record);

	List<IMMessage> selectNoReadMsgList(String openId);
}