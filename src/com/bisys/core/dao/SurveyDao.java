package com.bisys.core.dao;

import java.util.Date;
import java.util.List;

import com.bisys.core.entity.survey.SurveyAnalysisEntity;
import com.bisys.core.entity.survey.SurveyInfoEntity;
import com.bisys.core.entity.survey.VipAnalysisEntity;
import com.bisys.core.entity.survey.VipInfoEntity;
import com.bisys.core.entity.survey.VipListEntity;
import com.bisys.core.entity.survey.VipSurveyFriendInfoEntity;
import com.bisys.core.entity.survey.VipUserSurveyInfoEntity;

public interface SurveyDao {
	public boolean insertIntoVipBaseTable(String user_name,String password , String register_date , String register_ip , String register_source , String cellphone_zone , String ip_zone , String role_name , String permission_name) throws Exception;
	public boolean replaceIntoVipDynamicTable(String user_name,int login_num , int test_num , String friends) throws Exception;
	public boolean insertIntoSurveyTable(String survey_name,String survey_desc , String survey_text , String survey_anwser , String start_time , String end_time , int status) throws Exception;
	public boolean insertIntoSurveyUserTable(String user_name,String survey_name , int answer_time , String answer , int transfer_flag) throws Exception; 
	public List<VipListEntity> getVipList() throws Exception; 
	public List<VipInfoEntity> getVipInfo(String user_name) throws Exception;
	public List<VipUserSurveyInfoEntity> getVipSurveyInfo(String user_name) throws Exception;
	public List<VipSurveyFriendInfoEntity> getVipFriendSurveyInfo(String user_name) throws Exception; 
	public List<SurveyInfoEntity> getSurveyInfo(int status) throws Exception;
	public List<VipAnalysisEntity> getVipAnalysis(Date today) throws Exception;
	public List<SurveyAnalysisEntity> getSurveyAnalysis(String survey_name , Date today) throws Exception;
}
