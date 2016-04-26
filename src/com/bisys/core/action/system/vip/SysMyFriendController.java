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
import com.bisys.core.entity.survey.VipFriendCheck;
import com.bisys.core.entity.survey.VipSurveyFriendInfoEntity;
import com.bisys.core.service.impl.MyFriendServiceImpl;
import com.bisys.core.util.JsonPageInfo;
import com.google.gson.Gson;
/**
 * 主页
 * @author noviachan
 *
 */
@Controller
@RequestMapping("/system/myfriend")
public class SysMyFriendController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private MyFriendServiceImpl surveyService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String myfriend(HttpServletRequest request){
		logger.info("我的好友");
		return "system/vip/MyFriendList";
	}
	
	@RequestMapping(value = "getlist", method = RequestMethod.GET)
	@ResponseBody 
	public String getlist(@RequestParam String user_name, HttpServletRequest request, HttpServletResponse response , int pageNo) throws Exception{

		logger.info("获取我的好友列表：用户名["+user_name+"],pageNo["+pageNo+"]。"); 
		
		boolean flag = false;
		String errorMessage = "查询失败";
		List<VipSurveyFriendInfoEntity> surveyinfoList = null;
		JsonPageInfo pageInfo = null;
		
		try {
			List<VipSurveyFriendInfoEntity>  result = surveyService.getSurveyInfo(user_name);

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
		JsonResult<VipSurveyFriendInfoEntity> jsonResult = new JsonResult<VipSurveyFriendInfoEntity>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "查询成功" : errorMessage);
		jsonResult.setData(surveyinfoList);
		jsonResult.setPageInfo(pageInfo);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
	
	@RequestMapping(value = "delfriend", method = RequestMethod.GET)
	@ResponseBody 
	public String delfriend(String username, String friendname, HttpServletRequest request, HttpServletResponse response){
		logger.info("删除好友："+username+":"+friendname);
		boolean flag = false;
		String errorMessage = "删除失败";
		
		try {
			flag = surveyService.delfriend(username, friendname);
		}catch (Exception e) {
			logger.error("sys admin save failed! ", e);
		}
		
		//返回信息
		JsonResult<String> jsonResult = new JsonResult<String>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "删除成功" : errorMessage);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
	
	
	@RequestMapping(value = "friendcheck", method = RequestMethod.GET)
	@ResponseBody 
	public String friendCheck(String username, String friendname , HttpServletRequest request, HttpServletResponse response){
		logger.info("好友检测：用户名【"+username+"】，添加好友名：【"+friendname+"】。");
		boolean flag = false;
		List<VipFriendCheck> result = null;
		String errorMessage = "添加失败";
		
		try {
			result = surveyService.friendCheck(username , friendname);
			flag = true;
		}catch (Exception e) {
			logger.error("sys admin save failed! ", e);
		}
		
		//返回信息
		JsonResult<VipFriendCheck> jsonResult = new JsonResult<VipFriendCheck>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "添加成功" : errorMessage);
		jsonResult.setData(result);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
	
	@RequestMapping(value = "addfriend", method = RequestMethod.GET)
	@ResponseBody 
	public String addfriend(String username, String friendname, HttpServletRequest request, HttpServletResponse response){
		logger.info("添加好友："+username+":"+friendname);
		boolean flag = false;
		String errorMessage = "添加失败";
		
		try {
			flag = surveyService.addfriend(username, friendname);
		}catch (Exception e) {
			logger.error("sys admin save failed! ", e);
		}
		
		//返回信息
		JsonResult<String> jsonResult = new JsonResult<String>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "添加成功" : errorMessage);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
}
