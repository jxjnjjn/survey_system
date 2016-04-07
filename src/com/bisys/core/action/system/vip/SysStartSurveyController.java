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

import com.bisys.core.entity.JsonResult;
import com.bisys.core.entity.survey.SurveyInfoEntity;
import com.bisys.core.service.impl.StartSurveyServiceImpl;
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
		return "system/vip/StartSurveyList";
	}
	
	@RequestMapping(value = "getlist", method = RequestMethod.GET)
	@ResponseBody 
	public String getlist(@RequestParam int status, HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("获取开始答题列表："+status); 
		
		boolean flag = false;
		String errorMessage = "查询失败";
		List<SurveyInfoEntity> surveyinfoList = null;
		
		try {
			surveyinfoList = surveyService.getSurveyInfo(status);
			flag = true;
		}catch (Exception e) {
			logger.error("sys admin search failed! ", e);
		}
		
		//返回信息
		JsonResult<SurveyInfoEntity> jsonResult = new JsonResult<SurveyInfoEntity>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "查询成功" : errorMessage);
		jsonResult.setData(surveyinfoList);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
}
