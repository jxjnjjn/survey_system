package com.bisys.core.action.system;

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

import com.bisys.core.entity.survey.VipListEntity;
import com.bisys.core.service.impl.VIPServiceImpl;
import com.google.gson.Gson;
/**
 * 主页
 * @author noviachan
 *
 */
@Controller
@RequestMapping("/system/vip")
public class SysVIPController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private VIPServiceImpl surveyService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String vip(HttpServletRequest request){
		logger.info("开始答题");
		return "system/survey/VIPList";
	}
	
	@RequestMapping(value = "getlist", method = RequestMethod.GET)
	@ResponseBody 
	public String getlist(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<VipListEntity> surveyinfoList= surveyService.getSurveyInfo();
		logger.info(new Gson().toJson(surveyinfoList));
		return new Gson().toJson(surveyinfoList);
	}
}
