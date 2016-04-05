package com.bisys.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.SurveyDao;
import com.bisys.core.entity.survey.VipFriendInfoEntity;
import com.bisys.core.entity.survey.VipSurveyFriendInfoEntity;

@Service("MyFriendServiceImpl")
public class MyFriendServiceImpl{
	
	private static Logger logger = Logger.getLogger(MyFriendServiceImpl.class);
	
	@Autowired
	private SurveyDao surveyDao;

	public List<VipSurveyFriendInfoEntity> getSurveyInfo(String user_name) throws Exception
	{
		List<VipSurveyFriendInfoEntity> result = new ArrayList<VipSurveyFriendInfoEntity>();
		List<VipFriendInfoEntity> vip_friends_list= surveyDao.getVipFriendInfo(user_name);
		
		for(VipFriendInfoEntity bean : vip_friends_list)
		{
			if(bean != null)
			{
				List<VipSurveyFriendInfoEntity> result_temp = this.getFriendName(bean.getFriend_list());
				for(VipSurveyFriendInfoEntity bean1 : result_temp)
				{
					result.add(bean1);
				}
			}
		}
		
		return result;
	}
	
	private List<VipSurveyFriendInfoEntity> getFriendName(String friend_name) throws Exception
	{
		List<VipSurveyFriendInfoEntity> result_temp = new ArrayList<VipSurveyFriendInfoEntity>();
		
		if(friend_name != null)
		{
			String[] sArray=friend_name.split(","); 
			for(String bean : sArray) 
			{
				List<VipSurveyFriendInfoEntity> friend_result = surveyDao.getVipFriendSurveyInfo(bean);
				for(VipSurveyFriendInfoEntity item : friend_result)
				{
					result_temp.add(item);				}
			} 
		}
		
		return result_temp;
	}
}
