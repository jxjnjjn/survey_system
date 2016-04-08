package com.bisys.core.action.system.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bisys.core.entity.JsonResult;
import com.bisys.core.entity.survey.SurveyInfoEntity;
import com.bisys.core.service.impl.SurveyServiceImpl;
import com.google.gson.Gson;
/**
 * 主页
 * @author noviachan
 *
 */
@Controller
@RequestMapping("/system/survey")
public class SysSurveyController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private SurveyServiceImpl surveyService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletRequest request){
		logger.info("问卷管理");
		return "system/admin/SurveyManage";
	}
	
	@RequestMapping(value = "createsurvey", method = RequestMethod.GET)
	public String createsurvey(HttpServletRequest request){
		logger.info("创建问卷");
		return "system/admin/CreateSurvey";
	}
	
	@RequestMapping(value = "savesurvey", method = RequestMethod.GET)
	@ResponseBody 
	public String savesurvey( @RequestBody SurveyInfoEntity survey, HttpServletRequest request, HttpServletResponse response){
		logger.info("保存问卷");
		boolean flag = false;
		String errorMessage = "保存失败";
		
		try {
			flag = surveyService.saveSurveyInfo(survey);
		}catch (Exception e) {
			logger.error("sys admin save failed! ", e);
		}
		
		//返回信息
		JsonResult<SurveyInfoEntity> jsonResult = new JsonResult<SurveyInfoEntity>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "保存成功" : errorMessage);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
	
	@RequestMapping(value = "getlist", method = RequestMethod.GET)
	@ResponseBody 
	public String getlist(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		logger.info("获取问卷管理列表"); 
		return "";
//		boolean flag = false;
//		String errorMessage = "查询失败";
//		List<VipListEntity> surveyinfoList = null;
//		
//		try {
//			surveyinfoList = surveyService.getSurveyInfo();
//			flag = true;
//		}catch (Exception e) {
//			logger.error("sys admin search failed! ", e);
//		}
//		
//		//返回信息
//		JsonResult<VipListEntity> jsonResult = new JsonResult<VipListEntity>();
//		jsonResult.setResultCode(flag ? 0 : 1);
//		jsonResult.setResultMessage(flag ? "查询成功" : errorMessage);
//		jsonResult.setData(surveyinfoList);
//		logger.info(new Gson().toJson(jsonResult)); 
//		return new Gson().toJson(jsonResult);
	}

}
