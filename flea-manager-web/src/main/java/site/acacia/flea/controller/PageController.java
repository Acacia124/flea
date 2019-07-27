/**
 * 
 */
package site.acacia.flea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 张胤
 *
 *         2018年11月18日-上午10:30:47
 */
@Controller
public class PageController {
	@RequestMapping("")
	public String goIndex() {
		return "index";
	}
}
