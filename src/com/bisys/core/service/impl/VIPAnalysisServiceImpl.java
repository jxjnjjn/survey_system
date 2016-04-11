package com.bisys.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.SurveyDao;
import com.bisys.core.dao.UserDao;
import com.bisys.core.entity.survey.VipAnalysisEntity;
import com.bisys.core.entity.survey.VipTrendAnalysisEntity;
import com.bisys.core.entity.survey.VipZoneAnalysisEntity;
import com.bisys.core.util.JsonPageInfo;

@Service("VIPAnalysisServiceImpl")
public class VIPAnalysisServiceImpl{
	
	private static Logger logger = Logger.getLogger(VIPAnalysisServiceImpl.class);
	
	private static int everyPageNum = 10;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SurveyDao surveyDao;
	
	public List<VipAnalysisEntity> getList(Date today) throws Exception
	{
		return surveyDao.getVipAnalysis(today);
	}

	public List<VipTrendAnalysisEntity> getListTrend(Date start , Date end) throws Exception
	{
		return surveyDao.getVipTrendAnalysis(start , end);
	}
	
	public List<VipZoneAnalysisEntity> getListZone() throws Exception
	{
		return surveyDao.getVipZoneAnalysis();
	}
	
	
	public List<VipAnalysisEntity> getEntityInfo(List<VipAnalysisEntity> result , int pageNo) throws Exception
	{
		List<VipAnalysisEntity> tableList = new ArrayList<VipAnalysisEntity>();
		
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
