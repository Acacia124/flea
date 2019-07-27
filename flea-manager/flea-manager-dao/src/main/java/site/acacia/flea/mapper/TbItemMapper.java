package site.acacia.flea.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.acacia.flea.pojo.TbItem;
import site.acacia.flea.pojo.TbItemExample;
import site.acacia.flea.vo.G2Statistics;
import site.acacia.flea.vo.G2VoCopy;

public interface TbItemMapper {
	int countByExample(TbItemExample example);

	int deleteByExample(TbItemExample example);

	int deleteByPrimaryKey(String itemId);

	int insert(TbItem record);

	int insertSelective(TbItem record);

	List<TbItem> selectByExample(TbItemExample example);

	TbItem selectByPrimaryKey(String itemId);

	int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

	int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

	int updateByPrimaryKeySelective(TbItem record);

	int updateByPrimaryKey(TbItem record);

	List<G2VoCopy> getItemG2();

	G2Statistics getItemData();
}