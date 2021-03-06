package com.bisys.core.dao;

import java.util.List;

import com.bisys.core.entity.survey.PhoneZoneAnalysisEntity;
import com.bisys.core.entity.survey.SurveyAnalysisEntity;
import com.bisys.core.entity.survey.SurveyInfoEntity;
import com.bisys.core.entity.survey.SurveyRankListEntity;
import com.bisys.core.entity.survey.VipAnalysisEntity;
import com.bisys.core.entity.survey.VipFriendInfoEntity;
import com.bisys.core.entity.survey.VipInfoEntity;
import com.bisys.core.entity.survey.VipListEntity;
import com.bisys.core.entity.survey.VipSurveyFriendInfoEntity;
import com.bisys.core.entity.survey.VipTrendAnalysisEntity;
import com.bisys.core.entity.survey.VipUserSurveyInfoEntity;
import com.bisys.core.entity.survey.VipZoneAnalysisEntity;

public interface SurveyDao {
	/*
	 * VIP 用户相关的操作
	 * */
	public List<VipAnalysisEntity> getVipAnalysis(String today) throws Exception;
	public List<VipTrendAnalysisEntity> getVipTrendAnalysis(String start, String end) throws Exception;
	public List<VipZoneAnalysisEntity> getVipZoneAnalysis() throws Exception;
	public boolean insertIntoSurveyUserTable(String user_name,String survey_name , int answer_time , String answer , int transfer_flag) throws Exception; 
	public List<VipUserSurveyInfoEntity> getVipSurveyInfo(String user_name) throws Exception;
	public List<VipSurveyFriendInfoEntity> getVipFriendSurveyInfo(String user_name) throws Exception; 
	public List<VipListEntity> getVipList() throws Exception; 
	public List<VipInfoEntity> getVipInfo(String user_name) throws Exception;
	boolean deleteVip(String user_name) throws Exception;
	boolean insertUserFriend(String user_name, String friend_name) throws Exception;
	boolean deleteUserFriend(String user_name, String friend_name) throws Exception;
	public List<VipFriendInfoEntity> getVipFriendInfo(String user_name) throws Exception;
	public boolean insertIntoVipBaseTable(String user_name,String password , String register_date , String register_ip , String register_source , String cellphone_zone , String ip_zone , String role_name , String permission_name) throws Exception;
	public boolean replaceIntoVipDynamicTable(String user_name,int login_num , int test_num , String friends) throws Exception;
	/*
	 * 问卷 相关的操作
	 * */
	public List<PhoneZoneAnalysisEntity> getPhoneZoneAnalysis(String survey_name) throws Exception;
	boolean updateSurveyStatus(String survey_name, int status) throws Exception;
	public boolean replaceSurveyTable(SurveyInfoEntity survey , String old_survey_name) throws Exception;
	boolean deleteSurveyByName(String survey_name) throws Exception;
	boolean setSurveyAnswer(String survey_name, String answer) throws Exception;
	List<SurveyInfoEntity> getSurveyInfoByName(String survey_name) throws Exception;
	List<SurveyRankListEntity> getSurveyRankListAnalysis(String survey_name) throws Exception;
	public List<SurveyInfoEntity> getSurveyInfo(int status) throws Exception;
	public List<SurveyAnalysisEntity> getSurveyAnalysis(String survey_name) throws Exception;
}
