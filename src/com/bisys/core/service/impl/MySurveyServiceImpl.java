package com.bisys.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.SurveyDao;
import com.bisys.core.entity.survey.VipListEntity;
import com.bisys.core.entity.survey.VipUserSurveyInfoEntity;
import com.bisys.core.util.JsonPageInfo;

@Service("MySurveyServiceImpl")
public class MySurveyServiceImpl{
	
	private static Logger logger = Logger.getLogger(MySurveyServiceImpl.class);
	private static int everyPageNum = 10;
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
	
	public List<VipUserSurveyInfoEntity> getEntityInfo(List<VipUserSurveyInfoEntity> result , int pageNo) throws Exception
	{
		List<VipUserSurveyInfoEntity> tableList = new ArrayList<VipUserSurveyInfoEntity>();
		
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
