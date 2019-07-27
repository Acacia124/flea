package site.acacia.flea.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.acacia.flea.exception.NoPermission;
import site.acacia.flea.exception.Unauthorized;
import site.acacia.flea.pojo.TbItemSchool;
import site.acacia.flea.pojo.TbUser;
import site.acacia.flea.pojo.WeResult;
import site.acacia.flea.service.ItemSchoolService;

/**
 * @author 张胤
 *
 *         2018年11月9日-上午11:25:22
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ItemSchoolController {

	protected static Logger logger = LoggerFactory.getLogger(ItemSchoolController.class);

	@Autowired
	private ItemSchoolService schoolService;

	@RequestMapping("/school/list")
	public List<TbItemSchool> getItemSchool() {
		logger.info("==> Request parameter Empty");
		return schoolService.getSchoolList();
	}

	@RequestMapping("/user/school/delete")
	public WeResult deleteSchoolById(Integer id, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: id(" + id + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getUserStatus() != 3) {
				throw new NoPermission("403");
			} else {
				return schoolService.delateSchoolById(id);
			}
		}
	}

	@RequestMapping("/user/address/add")
	public WeResult addSchool(TbItemSchool itemSchool, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: itemSchool(" + itemSchool + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getUserStatus() != 3) {
				throw new NoPermission("403");
			} else {
				return schoolService.addSchool(itemSchool);
			}
		}
	}

	@RequestMapping("/user/address/update")
	public WeResult updateSchool(TbItemSchool itemSchool, HttpServletRequest request) throws Exception {
		logger.info("==> Request parameter: itemSchool(" + itemSchool + ")");
		TbUser user = (TbUser) request.getAttribute("user");
		if (user == null) {
			throw new Unauthorized("401");
		} else {
			if (user.getUserStatus() != 3) {
				throw new NoPermission("403");
			} else {
				return schoolService.updateSchoolNameById(itemSchool);
			}
		}
	}

}
