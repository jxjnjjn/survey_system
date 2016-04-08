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
				List<VipSurveyFriendInfoEntity> result_temp = this.getFriendName(bean.getFriends());
				
				for(VipSurveyFriendInfoEntity bean1 : result_temp)
				{
					result.add(bean1);
				}
			}
		}
		
		//logger.info(new Gson().toJson(result));
		return this.getSurveyInfoGroupByUserName(result);
	}
	
	private List<VipSurveyFriendInfoEntity> getSurveyInfoGroupByUserName(List<VipSurveyFriendInfoEntity> result) throws Exception
	{
		List<VipSurveyFriendInfoEntity> p_result = new ArrayList<VipSurveyFriendInfoEntity>();
		
		if(result != null)
		{
			for(VipSurveyFriendInfoEntity bean : result)
			{
				VipSurveyFriendInfoEntity item = this.findByUserName(p_result, bean);
				
				if(item == null)
				{
					item = new VipSurveyFriendInfoEntity();
					item.setUser_name(bean.getUser_name());
					p_result.add(item);
				}
				
				item.addSurveyToList(bean.getSurvey_name());
			}
		}
		
		return p_result;
	}
	
	private VipSurveyFriendInfoEntity findByUserName(List<VipSurveyFriendInfoEntity> list , VipSurveyFriendInfoEntity item)
	{
		if(list != null)
		{
			for(VipSurveyFriendInfoEntity bean :list )
			{
				if(bean != null && bean.getUser_name() != null 
						&& item != null && item.getUser_name() != null)
				{
					if(bean.getUser_name().compareTo(item.getUser_name()) == 0)
					{
						return bean;
					}
				}
			}
		}
		
		return null;
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
					result_temp.add(item);				
				}
			} 
		}
		
		return result_temp;
	}
}
