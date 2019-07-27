package site.acacia.flea.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import site.acacia.flea.pojo.TbItemSchool;
import site.acacia.flea.pojo.TbItemSchoolExample;

public interface TbItemSchoolMapper {
    int countByExample(TbItemSchoolExample example);

    int deleteByExample(TbItemSchoolExample example);

    int deleteByPrimaryKey(Integer schoolId);

    int insert(TbItemSchool record);

    int insertSelective(TbItemSchool record);

    List<TbItemSchool> selectByExample(TbItemSchoolExample example);

    TbItemSchool selectByPrimaryKey(Integer schoolId);

    int updateByExampleSelective(@Param("record") TbItemSchool record, @Param("example") TbItemSchoolExample example);

    int updateByExample(@Param("record") TbItemSchool record, @Param("example") TbItemSchoolExample example);

    int updateByPrimaryKeySelective(TbItemSchool record);

    int updateByPrimaryKey(TbItemSchool record);
}