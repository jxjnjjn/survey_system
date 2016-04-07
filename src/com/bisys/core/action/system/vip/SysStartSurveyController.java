package com.bisys.core.action.system.vip;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bisys.core.entity.survey.SurveyInfoEntity;
import com.bisys.core.service.impl.StartSurveyServiceImpl;
import com.google.gson.Gson;
/**
 * 主页
 * @author noviachan
 *
 */
@Controller
@RequestMapping("/system/survey")
public class SysStartSurveyController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private StartSurveyServiceImpl surveyService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String survey(HttpServletRequest request){
		logger.info("开始答题");
		return "system/vip/StartSurveyList";
	}
	
	@RequestMapping(value = "getlist", method = RequestMethod.GET)
	@ResponseBody 
	public String getlist(@RequestParam int status, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<SurveyInfoEntity> surveyinfoList= surveyService.getSurveyInfo(status);
		logger.info(new Gson().toJson(surveyinfoList));
		return new Gson().toJson(surveyinfoList);
	}
}
