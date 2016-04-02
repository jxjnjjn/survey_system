package com.bisys.core.action.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 主页
 * @author noviachan
 *
 */
@Controller
@RequestMapping("/system")
public class SysIndexController{
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 初始页面默认到系统管理员主页
	 * @throws Exception 
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletRequest request) throws Exception{
		logger.info("管理员/会员后台");
		return "system/index";
	}
}
