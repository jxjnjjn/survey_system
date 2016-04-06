package com.bisys.core.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.SurveyDao;
import com.bisys.core.dao.UserDao;
import com.bisys.core.entity.survey.SurveyInfoEntity;

@Service("StartSurveyServiceImpl")
public class StartSurveyServiceImpl{
	
	private static Logger logger = Logger.getLogger(StartSurveyServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SurveyDao surveyDao;

	public List<SurveyInfoEntity> getSurveyInfo(int status) throws Exception
	{
		return surveyDao.getSurveyInfo(status);
	}
}
