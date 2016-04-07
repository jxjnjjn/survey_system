package com.bisys.core.action.system.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bisys.core.service.impl.VIPServiceImpl;
/**
 * 主页
 * @author noviachan
 *
 */
@Controller
@RequestMapping("/system/createsurvey")
public class SysCreateSurveyController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private VIPServiceImpl surveyService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String vip(HttpServletRequest request){
		logger.info("创建问卷");
		return "system/admin/CreateSurvey";
	}

}
