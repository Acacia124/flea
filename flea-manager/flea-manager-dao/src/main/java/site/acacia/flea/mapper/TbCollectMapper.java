package site.acacia.flea.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import site.acacia.flea.pojo.TbCollect;
import site.acacia.flea.pojo.TbCollectExample;

public interface TbCollectMapper {
    int countByExample(TbCollectExample example);

    int deleteByExample(TbCollectExample example);

    int deleteByPrimaryKey(Integer collectId);

    int insert(TbCollect record);

    int insertSelective(TbCollect record);

    List<TbCollect> selectByExample(TbCollectExample example);

    TbCollect selectByPrimaryKey(Integer collectId);

    int updateByExampleSelective(@Param("record") TbCollect record, @Param("example") TbCollectExample example);

    int updateByExample(@Param("record") TbCollect record, @Param("example") TbCollectExample example);

    int updateByPrimaryKeySelective(TbCollect record);

    int updateByPrimaryKey(TbCollect record);
}