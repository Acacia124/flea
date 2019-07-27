package site.acacia.flea.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import site.acacia.flea.pojo.MessageUser;
import site.acacia.flea.pojo.MessageUserExample;

public interface MessageUserMapper {
    int countByExample(MessageUserExample example);

    int deleteByExample(MessageUserExample example);

    int deleteByPrimaryKey(Integer messageUserId);

    int insert(MessageUser record);

    int insertSelective(MessageUser record);

    List<MessageUser> selectByExample(MessageUserExample example);

    MessageUser selectByPrimaryKey(Integer messageUserId);

    int updateByExampleSelective(@Param("record") MessageUser record, @Param("example") MessageUserExample example);

    int updateByExample(@Param("record") MessageUser record, @Param("example") MessageUserExample example);

    int updateByPrimaryKeySelective(MessageUser record);

    int updateByPrimaryKey(MessageUser record);
}