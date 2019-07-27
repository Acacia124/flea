package site.acacia.flea.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import site.acacia.flea.pojo.TbItemDesc;
import site.acacia.flea.pojo.TbItemDescExample;

public interface TbItemDescMapper {
    int countByExample(TbItemDescExample example);

    int deleteByExample(TbItemDescExample example);

    int deleteByPrimaryKey(Integer itemDescId);

    int insert(TbItemDesc record);

    int insertSelective(TbItemDesc record);

    List<TbItemDesc> selectByExampleWithBLOBs(TbItemDescExample example);

    List<TbItemDesc> selectByExample(TbItemDescExample example);

    TbItemDesc selectByPrimaryKey(Integer itemDescId);

    int updateByExampleSelective(@Param("record") TbItemDesc record, @Param("example") TbItemDescExample example);

    int updateByExampleWithBLOBs(@Param("record") TbItemDesc record, @Param("example") TbItemDescExample example);

    int updateByExample(@Param("record") TbItemDesc record, @Param("example") TbItemDescExample example);

    int updateByPrimaryKeySelective(TbItemDesc record);

    int updateByPrimaryKeyWithBLOBs(TbItemDesc record);

    int updateByPrimaryKey(TbItemDesc record);
}