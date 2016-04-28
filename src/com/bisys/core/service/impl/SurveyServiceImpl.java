package com.bisys.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.SurveyDao;
import com.bisys.core.entity.survey.SurveyInfoEntity;
import com.bisys.core.entity.survey.SurveyRankListEntity;
import com.bisys.core.util.JsonPageInfo;

@Service("SurveyServiceImpl")
public class SurveyServiceImpl{
	
	private static Logger logger = Logger.getLogger(SurveyServiceImpl.class);
	
	private static int everyPageNum = 10;
	
	@Autowired
	private SurveyDao surveyDao;
	
	public List<SurveyInfoEntity> getSurveyInfo(int status , String username) throws Exception
	{
		return surveyDao.getSurveyInfo(status , username);
	}
	
	public List<SurveyInfoEntity> getSurveyInfobyName(String surveyname) throws Exception
	{
		return surveyDao.getSurveyInfoByName(surveyname);
	}
	
	public List<SurveyInfoEntity> getEntityInfo(List<SurveyInfoEntity> result , int pageNo) throws Exception
	{
		List<SurveyInfoEntity> tableList = new ArrayList<SurveyInfoEntity>();
		
		if(null != result)
		{
			int srcSize = result.size();
			int starlow = everyPageNum*(pageNo-1);

			if(starlow < srcSize && starlow >= 0)
			{
				int endlow = ((starlow + everyPageNum)>srcSize)?srcSize:(starlow + everyPageNum);
						
				for(int i=starlow;i<endlow;i++)
				{
					tableList.add(result.get(i));
				}
			}
		}

		return tableList;
	}
	
	public JsonPageInfo getPageInfo(int length , int pageNo) throws Exception
	{
		JsonPageInfo result = new JsonPageInfo(pageNo , everyPageNum , length);
		return result;
	}

	public boolean saveSurveyInfo(SurveyInfoEntity survey , String old_survey_name) throws Exception
	{
		//默认未发布
		survey.setStatus("2");
		return surveyDao.replaceSurveyTable(survey , old_survey_name);
	}
	
	public List<SurveyInfoEntity> showSurveyInfo(SurveyInfoEntity survey) throws Exception
	{
		List<SurveyInfoEntity> surveyLs = new ArrayList<SurveyInfoEntity>();
		String surveytext = survey.getSurvey_text();
		StringBuffer Stemp = new StringBuffer();
		String radio_name = "";
		int radio_num = 0;
		if(null != surveytext){
			String[] questionsList = surveytext.split("<br />\r\n<br />\r\n");
			//logger.info(questionsList.length);
			for(String question :questionsList){
				if("[单选题]".equals(question.substring(0, question.indexOf("]")+1))){
					radio_num++;
					radio_name = "optionsRadios_"+radio_num;
					logger.info("[单选题]");
					String[] ls = question.split("<br />\r\n");
					//logger.info(ls.length);
					Stemp.append("<div class=\"form-group form-group-lg\">");
					Stemp.append("<h4><strong>"+ls[0].substring(ls[0].indexOf("]")+1)+"</strong></h4>");
					
					//<div class="btn-group" data-toggle="buttons">
					Stemp.append("<div class=\"btn-group-vertical\" data-toggle=\"buttons\">");
					for(int i=1;i<ls.length;i++){
						Stemp.append("<label class=\"btn btn-default btn-lg\">");
						//Stemp.append(" <input type=\"radio\" name=\"optionsRadios\" value=\""+i+"\">");
						Stemp.append("<input type=\"radio\" name=\""+ radio_name  +"\" value=\""+i+"\">");
						Stemp.append(ls[i]);
						Stemp.append(" </label>");
					}
					Stemp.append("</div>");
					
//					for(int i=1;i<ls.length;i++){
//						Stemp.append("<div class=\"radio\">");
//						Stemp.append("<label>");
//						//Stemp.append(" <input type=\"radio\" name=\"optionsRadios\" value=\""+i+"\">");
//						Stemp.append("<input type=\"radio\" name=\""+ radio_name  +"\" value=\""+i+"\">");
//						Stemp.append(ls[i]);
//						Stemp.append(" </label>");
//						Stemp.append("</div>");
//					}
					Stemp.append("</div>");
				}else if("[填空题]".equals(question.substring(0, question.indexOf("]")+1))){
					logger.info("[填空题]");
					Stemp.append("<div class=\"form-group form-group-lg\">");
					Stemp.append("<h4><strong>"+question.substring(question.indexOf("]")+1)+"</strong></h4>");
					Stemp.append("<input type=\"text\" class=\"form-control input-lg\" placeholder=\"请输入\">");
					Stemp.append("</div>");
				}else{
					logger.info("[未知]");
					Stemp.append(question);
				}
			}
		}
		survey.setSurvey_text(Stemp.toString());
		surveyLs.add(survey);
		return surveyLs;
	}
	
	public boolean updateStatus(String surveyname, int status) throws Exception
	{
		if(1 == status){
			return surveyDao.updateSurveyStatus(surveyname,3);
		}else{
			return surveyDao.updateSurveyStatus(surveyname,1);
		}
	}
	
	public boolean deleteSurvey(String surveyname) throws Exception
	{
		return surveyDao.deleteSurveyByName(surveyname);
	}
	
	public List<SurveyRankListEntity> topchart(String surveyname) throws Exception
	{
		return surveyDao.getSurveyRankListAnalysis(surveyname);
	}
	
}
