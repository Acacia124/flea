package site.acacia.flea.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import site.acacia.flea.pojo.TbInformation;
import site.acacia.flea.pojo.TbInformationExample;

public interface TbInformationMapper {
    int countByExample(TbInformationExample example);

    int deleteByExample(TbInformationExample example);

    int deleteByPrimaryKey(Integer informationId);

    int insert(TbInformation record);

    int insertSelective(TbInformation record);

    List<TbInformation> selectByExample(TbInformationExample example);

    TbInformation selectByPrimaryKey(Integer informationId);

    int updateByExampleSelective(@Param("record") TbInformation record, @Param("example") TbInformationExample example);

    int updateByExample(@Param("record") TbInformation record, @Param("example") TbInformationExample example);

    int updateByPrimaryKeySelective(TbInformation record);

    int updateByPrimaryKey(TbInformation record);
}