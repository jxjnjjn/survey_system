package com.bisys.core.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.SurveyDao;
import com.bisys.core.entity.survey.VipUserSurveyInfoEntity;

@Service("MySurveyServiceImpl")
public class MySurveyServiceImpl{
	
	private static Logger logger = Logger.getLogger(MySurveyServiceImpl.class);
	
	@Autowired
	private SurveyDao surveyDao;

	public List<VipUserSurveyInfoEntity> getSurveyInfo(String user_name) throws Exception
	{
		
		List<VipUserSurveyInfoEntity> result_list= surveyDao.getVipSurveyInfo(user_name);
		
		for(VipUserSurveyInfoEntity bean : result_list)
		{
			if(bean != null)
			{
				bean.calculat_correct_rate();
			}
		}
		
		return result_list;
	}
	
	
}
