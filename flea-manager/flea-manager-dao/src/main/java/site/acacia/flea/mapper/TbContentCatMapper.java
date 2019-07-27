package site.acacia.flea.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import site.acacia.flea.pojo.TbContentCat;
import site.acacia.flea.pojo.TbContentCatExample;

public interface TbContentCatMapper {
    int countByExample(TbContentCatExample example);

    int deleteByExample(TbContentCatExample example);

    int deleteByPrimaryKey(Integer conCatId);

    int insert(TbContentCat record);

    int insertSelective(TbContentCat record);

    List<TbContentCat> selectByExample(TbContentCatExample example);

    TbContentCat selectByPrimaryKey(Integer conCatId);

    int updateByExampleSelective(@Param("record") TbContentCat record, @Param("example") TbContentCatExample example);

    int updateByExample(@Param("record") TbContentCat record, @Param("example") TbContentCatExample example);

    int updateByPrimaryKeySelective(TbContentCat record);

    int updateByPrimaryKey(TbContentCat record);
}