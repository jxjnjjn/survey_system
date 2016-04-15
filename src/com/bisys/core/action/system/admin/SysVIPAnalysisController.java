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
import com.bisys.core.entity.survey.VipAnalysisEntity;
import com.bisys.core.entity.survey.VipTrendAnalysisEntity;
import com.bisys.core.entity.survey.VipZoneAnalysisEntity;
import com.bisys.core.service.impl.VIPAnalysisServiceImpl;
import com.google.gson.Gson;
/**
 * 主页
 * @author noviachan
 *
 */
@Controller
@RequestMapping("/system/vipanalysis")
public class SysVIPAnalysisController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private VIPAnalysisServiceImpl surveyService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String vip(HttpServletRequest request){
		logger.info("会员分析");
		return "system/admin/VIPAnalysis";
	}
	
	@RequestMapping(value = "getlist", method = RequestMethod.GET)
	@ResponseBody 
	public String getlist(HttpServletRequest request, HttpServletResponse response, 
			String today) throws Exception{
		logger.info("获取会员分析-人数统计"); 
		
		boolean flag = false;
		String errorMessage = "查询失败";
		List<VipAnalysisEntity> surveyinfoList = null;
		
		try {
			surveyinfoList = surveyService.getList(today);
			flag = true;
		}catch (Exception e) {
			logger.error("sys admin search failed! ", e);
		}
		
		//返回信息
		JsonResult<VipAnalysisEntity> jsonResult = new JsonResult<VipAnalysisEntity>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "查询成功" : errorMessage);
		jsonResult.setData(surveyinfoList);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
	
	@RequestMapping(value = "getlisttrend", method = RequestMethod.GET)
	@ResponseBody 
	public String getlisttrend(HttpServletRequest request, HttpServletResponse response, 
			String start , String end) throws Exception{

		logger.info("获取会员分析-人数趋势"); 
		
		boolean flag = false;
		String errorMessage = "查询失败";
		List<VipTrendAnalysisEntity> surveyinfoList = null;
		
		try {
			surveyinfoList = surveyService.getListTrend(start , end);
			flag = true;
		}catch (Exception e) {
			logger.error("sys admin search failed! ", e);
		}
		
		//返回信息
		JsonResult<VipTrendAnalysisEntity> jsonResult = new JsonResult<VipTrendAnalysisEntity>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "查询成功" : errorMessage);
		jsonResult.setData(surveyinfoList);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
	
	@RequestMapping(value = "getlistzone", method = RequestMethod.GET)
	@ResponseBody 
	public String getlistzone(HttpServletRequest request, HttpServletResponse response) throws Exception{

		logger.info("获取会员分析-地区分布"); 
		
		boolean flag = false;
		String errorMessage = "查询失败";
		List<VipZoneAnalysisEntity> surveyinfoList = null;
		
		try {
			surveyinfoList = surveyService.getListZone();
			flag = true;
		}catch (Exception e) {
			logger.error("sys admin search failed! ", e);
		}
		
		//返回信息
		JsonResult<VipZoneAnalysisEntity> jsonResult = new JsonResult<VipZoneAnalysisEntity>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "查询成功" : errorMessage);
		jsonResult.setData(surveyinfoList);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
}
