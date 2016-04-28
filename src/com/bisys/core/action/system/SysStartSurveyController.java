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

import com.bisys.core.entity.JsonResult;
import com.bisys.core.entity.survey.SurveyInfoEntity;
import com.bisys.core.service.impl.StartSurveyServiceImpl;
import com.bisys.core.util.JsonPageInfo;
import com.google.gson.Gson;
/**
 * 主页
 * @author noviachan
 *
 */
@Controller
@RequestMapping("/system/startsurvey")
public class SysStartSurveyController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private StartSurveyServiceImpl surveyService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String startsurvey(HttpServletRequest request){
		logger.info("开始答题");
		return "system/StartSurveyList";
	}
	
	@RequestMapping(value = "surveytext", method = RequestMethod.GET)
	public String surveytext(String username, String surveyname, HttpServletRequest request){
		logger.info("surveytext页面"+username+surveyname);
		request.getSession().setAttribute("username", username);
		request.getSession().setAttribute("surveyname", surveyname);
		return "system/SurveyText";
	}
	
	
	@RequestMapping(value = "submitanswer", method = RequestMethod.GET)
	@ResponseBody 
	public String submitAnswer(@RequestParam String surveyname, String optionanswer , String username ,
			String fillinblankanswer ,HttpServletRequest request, HttpServletResponse response){
		logger.info("提交答案，问卷名称:【"+surveyname+"】");
		boolean flag = false;
		String errorMessage = "提交失败";
		try {
			surveyService.submitanswer(surveyname , optionanswer , username , fillinblankanswer);
			flag = true;
		}catch (Exception e) {
			logger.error("sys admin showsurvey failed! ", e);
		}
		
		//返回信息
		JsonResult<String> jsonResult = new JsonResult<String>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "提交成功" : errorMessage);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
	
	@RequestMapping(value = "getsurveytext", method = RequestMethod.GET)
	@ResponseBody 
	public String getsurveytext(@RequestParam String surveyname, HttpServletRequest request, HttpServletResponse response){
		logger.info("getsurveytext:"+surveyname);
		boolean flag = false;
		String errorMessage = "getsurveytext失败";
		List<SurveyInfoEntity> surveyinfoList = null;
		
		try {
			surveyinfoList = surveyService.getsurveytext(surveyname);
			flag = true;
		}catch (Exception e) {
			logger.error("sys admin showsurvey failed! ", e);
		}
		
		//返回信息
		JsonResult<SurveyInfoEntity> jsonResult = new JsonResult<SurveyInfoEntity>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "getsurveytext成功" : errorMessage);
		jsonResult.setData(surveyinfoList);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
	
	@RequestMapping(value = "getlist", method = RequestMethod.GET)
	@ResponseBody 
	public String getlist(@RequestParam int status, HttpServletRequest request, 
			HttpServletResponse response, int pageNo , String username) throws Exception{
		logger.info("获取开始答题列表：["+status+"] , username:["+username+"]."); 
		
		boolean flag = false;
		String errorMessage = "查询失败";
		List<SurveyInfoEntity> surveyinfoList = null;
		JsonPageInfo pageInfo = null;
		
		try {
			List<SurveyInfoEntity>  result = surveyService.getSurveyInfo(status , username);

			int length = 0;
			if(result != null)
			{
				length = result.size();
			}
			
			surveyinfoList = surveyService.getEntityInfo(result ,pageNo);
			pageInfo = surveyService.getPageInfo(length ,pageNo);
			flag = true;
		}catch (Exception e) {
			logger.error("sys admin search failed! ", e);
		}
		
		//返回信息
		JsonResult<SurveyInfoEntity> jsonResult = new JsonResult<SurveyInfoEntity>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "查询成功" : errorMessage);
		jsonResult.setData(surveyinfoList);
		jsonResult.setPageInfo(pageInfo);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
}
