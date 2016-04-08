package com.bisys.core.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.SurveyDao;
import com.bisys.core.entity.survey.SurveyInfoEntity;

@Service("SurveyServiceImpl")
public class SurveyServiceImpl{
	
	private static Logger logger = Logger.getLogger(SurveyServiceImpl.class);
	
	@Autowired
	private SurveyDao surveyDao;

	public boolean saveSurveyInfo(SurveyInfoEntity survey) throws Exception
	{
		return surveyDao.insertIntoSurveyTable(survey);
	}
	
	
}
