package com.bisys.core.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.SurveyDao;
import com.bisys.core.dao.UserDao;
import com.bisys.core.entity.survey.VipAnalysisEntity;
import com.bisys.core.entity.survey.VipTrendAnalysisEntity;
import com.bisys.core.entity.survey.VipZoneAnalysisEntity;

@Service("VIPAnalysisServiceImpl")
public class VIPAnalysisServiceImpl{
	
	private static Logger logger = Logger.getLogger(VIPAnalysisServiceImpl.class);
	
	private static int everyPageNum = 10;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SurveyDao surveyDao;
	
	public List<VipAnalysisEntity> getList(String today) throws Exception
	{
		return surveyDao.getVipAnalysis(today);
	}

	public List<VipTrendAnalysisEntity> getListTrend(String start , String end) throws Exception
	{
		return surveyDao.getVipTrendAnalysis(start , end);
	}
	
	public List<VipZoneAnalysisEntity> getListZone() throws Exception
	{
		return surveyDao.getVipZoneAnalysis();
	}
	

}
