package com.bisys.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.SurveyDao;
import com.bisys.core.dao.UserDao;
import com.bisys.core.entity.survey.VipListEntity;
import com.bisys.core.util.JsonPageInfo;

@Service("VIPServiceImpl")
public class VIPServiceImpl{
	
	private static Logger logger = Logger.getLogger(VIPServiceImpl.class);
	
	private static int everyPageNum = 10;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SurveyDao surveyDao;
	
	public List<VipListEntity> getList() throws Exception
	{
		return surveyDao.getVipList();
	}
	
	public List<VipListEntity> getEntityInfo(List<VipListEntity> result , int pageNo) throws Exception
	{
		List<VipListEntity> tableList = new ArrayList<VipListEntity>();
		
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
