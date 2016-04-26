package com.bisys.core.action.system.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bisys.core.entity.JsonResult;
import com.bisys.core.entity.survey.PhoneZoneAnalysisEntity;
import com.bisys.core.entity.survey.SurveyAnalysisEntity;
import com.bisys.core.service.impl.SurveyAnalysisServiceImpl;
import com.google.gson.Gson;
/**
 * 主页
 * @author noviachan
 *
 */
@Controller
@RequestMapping("/system/surveyanalysis")
public class SysSurveyAnalysisController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private SurveyAnalysisServiceImpl surveyService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String vip(HttpServletRequest request){
		logger.info("问卷分析");
		return "system/admin/SurveyAnalysisList";
	}
	
	@RequestMapping(value = "getlist", method = RequestMethod.GET)
	@ResponseBody 
	public String getlist(HttpServletRequest request, HttpServletResponse response, 
			String surveyname) throws Exception{

		logger.info("获取问卷分析"); 
		
		boolean flag = false;
		String errorMessage = "查询失败";
		List<SurveyAnalysisEntity> surveyinfoList = null;
		//JsonPageInfo pageInfo = null;
		
		try {
			surveyinfoList = surveyService.getSurveyAnalysisByName(surveyname);
			flag = true;
		}catch (Exception e) {
			logger.error("sys admin search failed! ", e);
		}
		
		//返回信息
		JsonResult<SurveyAnalysisEntity> jsonResult = new JsonResult<SurveyAnalysisEntity>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "查询成功" : errorMessage);
		jsonResult.setData(surveyinfoList);
		//jsonResult.setPageInfo(pageInfo);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
	
	@RequestMapping(value = "getlistphone", method = RequestMethod.GET)
	@ResponseBody 
	public String getlistzone(HttpServletRequest request, HttpServletResponse response,String surveyname) throws Exception{

		logger.info("获取问卷:【"+surveyname+"】-手机号码分布"); 
		
		boolean flag = false;
		String errorMessage = "查询失败";
		List<PhoneZoneAnalysisEntity> surveyinfoList = null;
		
		try {
			surveyinfoList = surveyService.getListPhone(surveyname);
			flag = true;
		}catch (Exception e) {
			logger.error("sys admin search failed! ", e);
		}
		
		//返回信息
		JsonResult<PhoneZoneAnalysisEntity> jsonResult = new JsonResult<PhoneZoneAnalysisEntity>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "查询成功" : errorMessage);
		jsonResult.setData(surveyinfoList);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
}
