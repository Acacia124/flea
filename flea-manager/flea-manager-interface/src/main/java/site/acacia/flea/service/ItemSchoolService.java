/**
 * 
 */
package site.acacia.flea.service;

import java.util.List;

import site.acacia.flea.pojo.TbItemSchool;
import site.acacia.flea.pojo.WeResult;

/**
 * @author 张胤
 *
 *         2018年11月9日-上午10:51:31
 */
public interface ItemSchoolService {

	List<TbItemSchool> getSchoolList();

	WeResult delateSchoolById(Integer id);

	WeResult updateSchoolNameById(TbItemSchool itemSchool);

	WeResult addSchool(TbItemSchool itemSchool);
}
