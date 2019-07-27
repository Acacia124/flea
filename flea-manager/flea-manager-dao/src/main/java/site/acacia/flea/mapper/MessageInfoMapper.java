package site.acacia.flea.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import site.acacia.flea.pojo.MessageInfo;
import site.acacia.flea.pojo.MessageInfoExample;

public interface MessageInfoMapper {
    int countByExample(MessageInfoExample example);

    int deleteByExample(MessageInfoExample example);

    int deleteByPrimaryKey(Integer messageInfoId);

    int insert(MessageInfo record);

    int insertSelective(MessageInfo record);

    List<MessageInfo> selectByExample(MessageInfoExample example);

    MessageInfo selectByPrimaryKey(Integer messageInfoId);

    int updateByExampleSelective(@Param("record") MessageInfo record, @Param("example") MessageInfoExample example);

    int updateByExample(@Param("record") MessageInfo record, @Param("example") MessageInfoExample example);

    int updateByPrimaryKeySelective(MessageInfo record);

    int updateByPrimaryKey(MessageInfo record);
}