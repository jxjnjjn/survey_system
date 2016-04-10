package com.bisys.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.SurveyDao;
import com.bisys.core.entity.survey.SurveyInfoEntity;
import com.bisys.core.entity.survey.VipSurveyFriendInfoEntity;
import com.bisys.core.util.JsonPageInfo;

@Service("SurveyServiceImpl")
public class SurveyServiceImpl{
	
	private static Logger logger = Logger.getLogger(SurveyServiceImpl.class);
	
	private static int everyPageNum = 10;
	
	@Autowired
	private SurveyDao surveyDao;
	
	public List<SurveyInfoEntity> getSurveyInfo(int status) throws Exception
	{
		return surveyDao.getSurveyInfo(status);
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

	public boolean saveSurveyInfo(SurveyInfoEntity survey) throws Exception
	{
		survey.setStatus("1");
		return surveyDao.insertIntoSurveyTable(survey);
	}
	
	public List<SurveyInfoEntity> showSurveyInfo(SurveyInfoEntity survey) throws Exception
	{
		
		return null;
	}
	
	
}
