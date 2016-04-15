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
import com.bisys.core.entity.survey.VipUserSurveyInfoEntity;
import com.bisys.core.service.impl.MySurveyServiceImpl;
import com.bisys.core.util.JsonPageInfo;
import com.google.gson.Gson;
/**
 * 主页
 * @author noviachan
 *
 */
@Controller
@RequestMapping("/system/mysurvey")
public class SysMySurveyControlle{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private MySurveyServiceImpl surveyService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String mysurvey(HttpServletRequest request){
		logger.info("我的问卷");
		return "system/vip/MySurveyList";
	}
	
	@RequestMapping(value = "getlist", method = RequestMethod.GET)
	@ResponseBody 
	public String getlist(@RequestParam String user_name, HttpServletRequest request, HttpServletResponse response, int pageNo) throws Exception{
		logger.info("获取我的问卷列表：用户名["+user_name +"],pageNo["+pageNo+"]。"); 
		
		boolean flag = false;
		String errorMessage = "查询失败";
		List<VipUserSurveyInfoEntity> surveyinfoList = null;
		JsonPageInfo pageInfo = null;
		
		try {
			List<VipUserSurveyInfoEntity> result = surveyService.getSurveyInfo(user_name);
			
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
		JsonResult<VipUserSurveyInfoEntity> jsonResult = new JsonResult<VipUserSurveyInfoEntity>();
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "查询成功" : errorMessage);
		jsonResult.setData(surveyinfoList);
		jsonResult.setPageInfo(pageInfo);
		logger.info(new Gson().toJson(jsonResult)); 
		return new Gson().toJson(jsonResult);
	}
}
