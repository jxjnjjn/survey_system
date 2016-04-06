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

import com.bisys.core.entity.survey.VipSurveyFriendInfoEntity;
import com.bisys.core.service.impl.MyFriendServiceImpl;
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
	public String login(HttpServletRequest request){
		logger.info("我的好友");
		return "system/survey/MyFriendList";
	}
	
	@RequestMapping(value = "getlist", method = RequestMethod.GET)
	@ResponseBody 
	public String getlist(@RequestParam String user_name, HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<VipSurveyFriendInfoEntity> surveyinfoList= surveyService.getSurveyInfo(user_name);
		logger.info(new Gson().toJson(surveyinfoList));
		return new Gson().toJson(surveyinfoList);
	}
}
