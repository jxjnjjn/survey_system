package com.bisys.core.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.SurveyDao;
import com.bisys.core.entity.survey.VipSurveyFriendInfoEntity;

@Service("MyFriendServiceImpl")
public class MyFriendServiceImpl{
	
	private static Logger logger = Logger.getLogger(MyFriendServiceImpl.class);
	
	@Autowired
	private SurveyDao surveyDao;

	public List<VipSurveyFriendInfoEntity> getSurveyInfo(String user_name) throws Exception
	{
		return surveyDao.getVipFriendSurveyInfo(user_name);
	}
}
