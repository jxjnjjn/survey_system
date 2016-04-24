package com.bisys.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.SurveyDao;
import com.bisys.core.dao.UserDao;
import com.bisys.core.entity.survey.PhoneZoneAnalysisEntity;
import com.bisys.core.entity.survey.SurveyAnalysisEntity;
import com.bisys.core.util.JsonPageInfo;

@Service("SurveyAnalysisServiceImpl")
public class SurveyAnalysisServiceImpl{
	
	private static Logger logger = Logger.getLogger(SurveyAnalysisServiceImpl.class);
	
	private static int everyPageNum = 10;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SurveyDao surveyDao;
	
	public List<SurveyAnalysisEntity> getSurveyAnalysisByName(String survey_name) throws Exception
	{
		return surveyDao.getSurveyAnalysis(survey_name);
	}

	public List<SurveyAnalysisEntity> getListTrend(Date start , Date end) throws Exception
	{
		//return surveyDao.getVipTrendAnalysis(start , end);
		return null;
	}
	
	public List<PhoneZoneAnalysisEntity> getListPhone(String survey_name) throws Exception
	{
		return surveyDao.getPhoneZoneAnalysis(survey_name);
	}
	
	
	public List<SurveyAnalysisEntity> getEntityInfo(List<SurveyAnalysisEntity> result , int pageNo) throws Exception
	{
		List<SurveyAnalysisEntity> tableList = new ArrayList<SurveyAnalysisEntity>();
		
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
}
