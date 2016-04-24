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
import com.bisys.core.entity.survey.VipListEntity;
import com.bisys.core.service.impl.VIPServiceImpl;
import com.bisys.core.util.JsonPageInfo;
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
		logger.info("会员列表");
		return "system/admin/VIPList";
	}
	
	@RequestMapping(value = "getlist", method = RequestMethod.GET)
	@ResponseBody 
	public String getlist(HttpServletRequest request, HttpServletResponse response, int pageNo) throws Exception{

		logger.info("获取会员列表"); 
		
		boolean flag = false;
		String errorMessage = "查询失败";
		List<VipListEntity> surveyinfoList = null;
		JsonPageInfo pageInfo = null;
		
		try {
			List<VipListEntity> result = surveyService.getList();
			
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
		JsonResult<VipListEntity> jsonResult = new JsonResult<VipListEntity>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "查询成功" : errorMessage);
		jsonResult.setData(surveyinfoList);
		jsonResult.setPageInfo(pageInfo);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
	
	@RequestMapping(value = "getlistbyname", method = RequestMethod.GET)
	@ResponseBody 
	public String getlistbyname(HttpServletRequest request, HttpServletResponse response, String username) throws Exception{

		logger.info("获取会员信息，会员ID：【"+username+"】"); 
		
		boolean flag = false;
		String errorMessage = "查询失败";
		List<VipListEntity> surveyinfoList = null;
		
		try {
			surveyinfoList = surveyService.getByName(username);
			flag = true;
		}catch (Exception e) {
			logger.error("sys admin search failed! ", e);
		}
		
		//返回信息
		JsonResult<VipListEntity> jsonResult = new JsonResult<VipListEntity>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "查询成功" : errorMessage);
		jsonResult.setData(surveyinfoList);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
}
