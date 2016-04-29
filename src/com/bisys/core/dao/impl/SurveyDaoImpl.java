package com.bisys.core.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bisys.core.dao.GeneralDao;
import com.bisys.core.dao.SurveyDao;
import com.bisys.core.entity.survey.PhoneZoneAnalysisEntity;
import com.bisys.core.entity.survey.ShareAppAnalysisEntity;
import com.bisys.core.entity.survey.SurveyAnalysisEntity;
import com.bisys.core.entity.survey.SurveyInfoEntity;
import com.bisys.core.entity.survey.SurveyRankListEntity;
import com.bisys.core.entity.survey.VipAnalysisEntity;
import com.bisys.core.entity.survey.VipFriendCheck;
import com.bisys.core.entity.survey.VipFriendInfoEntity;
import com.bisys.core.entity.survey.VipListEntity;
import com.bisys.core.entity.survey.VipSurveyFriendInfoEntity;
import com.bisys.core.entity.survey.VipTrendAnalysisEntity;
import com.bisys.core.entity.survey.VipUserSurveyInfoEntity;
import com.bisys.core.entity.survey.VipZoneAnalysisEntity;

@Repository
public class SurveyDaoImpl implements SurveyDao {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GeneralDao generalDao;

	/*
	 * 表格：vip_base_info_table
	 * 操作：INSERT
	 * 成功条件：查找【vip_base_info_table】表格，不存在这样的记录：user_name和要插入数据相同。
	 *
	 * */
	@Override
	public boolean insertIntoVipBaseTable(String user_name,String password , String register_date , String register_ip , String register_source , 
			String cellphone_zone , String ip_zone , String role_name , String permission_name)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "call p_insert_vip_base_table('" 
				+user_name+"','"+password+"','"+register_date+"','"+register_ip+"','"
				+register_source+"','"+cellphone_zone+"','"+ip_zone+"','"+role_name+"','"+permission_name+"');";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	/*
	 * 表格：survey_table
	 * 操作：REPLACE
	 *
	 * */
	@Override
	public boolean replaceSurveyTable(SurveyInfoEntity survey , String old_survey_name) throws Exception
	{
		String sql = "call p_replace_survey_table ('"
				+old_survey_name+"','"
				+survey.getSurvey_name()+"','"+survey.getSurvey_desc()+"','"+survey.getSurvey_text()+"','"
				+survey.getSurvey_anwser()+"','"+survey.getStart_time()+"','"+survey.getEnd_time()+"',"
				+survey.getStatus()+","+survey.getInfos()+",'"+survey.getInfomationdesc()+"');";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	/*
	 * 表格：survey_user_table
	 * 操作：INSERT
	 * 成功条件1：查找【vip_base_info_table】表格，存在这样的记录：user_name和要插入数据相同。
	 * 成功条件2：查找【survey_table】表格，存在这样的记录：survey_name和要插入数据相同。
	 * 成功条件3：查找【survey_user_table】表格，不存在这样的记录：user_name、survey_name都和要插入数据相同。
	 * */
	@Override
	public boolean insertIntoSurveyUserTable(String user_name,String survey_name , int answer_time , String answer , String answer_fillinblank) throws Exception
	{
		String sql = "call p_insert_survey_user_table ('"
				+user_name+"','"+survey_name+"',"+answer_time+",'"+answer+"','"+answer_fillinblank+"');";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	/*
	 * 功能：会员列表
	 * 说明：按会员注册降序返回
	 * */
	@Override
	public List<VipListEntity> getVipList() throws Exception
	{
		String sql = "CALL p_select_vip_list()";
		logger.info(sql);
		return generalDao.getEntityList(VipListEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：会员查询
	 * 说明 ： 结果字段同会员列表
	 * */
	@Override
	public List<VipListEntity> getVipInfo(String user_name) throws Exception
	{
		String sql = "CALL p_select_vip_info('"
				+ user_name +"')";
		logger.info(sql);
		return generalDao.getEntityList(VipListEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：会员删除
	 * 说明：该删除动作会导致【vip_base_info_table】触发器触发，删除【vip_dynamic_info_table】以及【survey_user_table】表中，"user_name"用户的相关信息。
	 * 
	 * */
	@Override
	public boolean deleteVip(String user_name) throws Exception
	{
		String sql = "CALL p_delete_vip_by_name('"
				+ "','"+user_name+"')";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	/*
	 * 功能：会员中心-我的问卷
	 * 
	 * */
	@Override
	public List<VipUserSurveyInfoEntity> getVipSurveyInfo(String user_name) throws Exception
	{
		String sql = "CALL p_select_vip_survey_info('"
				+ user_name +"')";
		logger.info(sql);
		return generalDao.getEntityList(VipUserSurveyInfoEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：会员中心-我的问卷-我的好友
	 * 说明：用户最近三期参加的问卷
	 * */
	@Override
	public List<VipSurveyFriendInfoEntity> getVipFriendSurveyInfo(String user_name) throws Exception
	{
		String sql = "CALL p_select_vip_friend_survey_info('"
				+ user_name +"')";
		logger.info(sql);
		return generalDao.getEntityList(VipSurveyFriendInfoEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：查询问卷信息
	 * 说明：status 为问卷状态 。。。。。
	 * 
	 * */
	@Override
	public List<SurveyInfoEntity> getSurveyInfo(int status , String username) throws Exception
	{
		String sql = "CALL p_select_survey_list('"+username+"',"
				+ status +")";
		logger.info(sql);
		return generalDao.getEntityList(SurveyInfoEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：设置问卷答案
	 * 操作：UPDATE
	 * 说明：survey_name 为问卷名字，answer 为问卷答案
	 * 
	 * */
	@Override
	public boolean setSurveyAnswer(String survey_name , String answer) throws Exception
	{
		String sql = "CALL p_update_survey_anwser('"
				+ survey_name +"','"+answer+"')";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	/*
	 * 功能：查询问卷信息
	 * 说明：survey_name 为问卷名字。。。。。
	 * 
	 * */
	@Override
	public List<SurveyInfoEntity> getSurveyInfoByName(String survey_name) throws Exception
	{
		String sql = "CALL p_select_survey_list_by_name('"
				+ survey_name +"')";
		logger.info(sql);
		return generalDao.getEntityList(SurveyInfoEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：会员分析-注册人数统计
	 * 说明：today 查询当日日期
	 * 
	 * */
	@Override
	public List<VipAnalysisEntity> getVipAnalysis(String today) throws Exception
	{
		String sql = "CALL p_select_vip_analysis('"
				+ today +"')";
		logger.info(sql);
		return generalDao.getEntityList(VipAnalysisEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：会员分析-注册人数趋势
	 * 说明：start 查询开始日期 ， end 查询结束日期
	 * 
	 * */
	@Override
	public List<VipTrendAnalysisEntity> getVipTrendAnalysis(String start , String end) throws Exception
	{
		String sql = "CALL p_select_vip_trend_analysis('"
				+ start +"','"+end
				+"')";
		logger.info(sql);
		return generalDao.getEntityList(VipTrendAnalysisEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：会员分析-区域分布
	 * 说明：start 查询开始日期 ， end 查询结束日期
	 * 
	 * */
	@Override
	public List<VipZoneAnalysisEntity> getVipZoneAnalysis() throws Exception
	{
		String sql = "CALL p_select_vip_zone_analysis()";
		logger.info(sql);
		return generalDao.getEntityList(VipZoneAnalysisEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：问卷分析
	 * 说明：survey_name 查询问卷名字
	 * 
	 * */
	@Override
	public List<SurveyAnalysisEntity> getSurveyAnalysis(String survey_name) throws Exception
	{
		String sql = "CALL p_select_survey_analysis('"
				+survey_name+"')";
		logger.info(sql);
		return generalDao.getEntityList(SurveyAnalysisEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：问卷排行榜
	 * 说明：survey_name 查询问卷名字
	 * 
	 * */
	@Override
	public List<SurveyRankListEntity> getSurveyRankListAnalysis(String survey_name) throws Exception
	{
		String sql = "CALL p_select_survey_correct_rate_top10('"
				+survey_name+"')";
		logger.info(sql);
		return generalDao.getEntityList(SurveyRankListEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：问卷分析-手机区域分布
	 * 说明：survey_name 问卷名称
	 * 
	 * */
	@Override
	public List<PhoneZoneAnalysisEntity> getPhoneZoneAnalysis(String survey_name) throws Exception
	{
		String sql = "CALL p_select_phone_zone_analysis('"
				+survey_name+"')";
		logger.info(sql);
		return generalDao.getEntityList(PhoneZoneAnalysisEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：问卷分析-应用分布
	 * 说明：survey_name 问卷名称
	 * 
	 * */
	public List<ShareAppAnalysisEntity> getAppAnalysis(String survey_name) throws Exception
	{
		String sql = "CALL p_select_app_analysis('"
				+survey_name+"')";
		logger.info(sql);
		return generalDao.getEntityList(ShareAppAnalysisEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：好友信息
	 * 说明：user_name 查询用户名称
	 * 
	 * */
	@Override
	public List<VipFriendInfoEntity> getVipFriendInfo(String user_name) throws Exception
	{
		String sql = "CALL p_select_vip_friend('"
				+ user_name +"')";
		logger.info(sql);
		return generalDao.getEntityList(VipFriendInfoEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：插入好友
	 * 说明：user_name 用户名 , friend_name 要插入的好友名
	 * 操作：update
	 * 成功条件1：查找【vip_base_info_table】表格，不存在这样的记录：user_name和friend_name相同。
	 * 成功条件2：查找【vip_dynamic_info_table】表格，user_name的好友个数小于5个。
	 * 成功条件3：查找【vip_dynamic_info_table】表格，user_name的好友中没有friend_name。
	 * */
	@Override
	public boolean insertUserFriend(String user_name , String friend_name) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "call p_insert_vip_friend('" 
				+user_name+"','"+friend_name+"');";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	/*
	 * 功能：删除好友
	 * 说明1：user_name 用户名 , friend_name 要删除的好友名
	 * 操作：update
	 * 说明2：查找【vip_dynamic_info_table】表格，将friend_name从好友列表中删除。
	 * */
	@Override
	public boolean deleteUserFriend(String user_name , String friend_name) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "call p_delete_vip_friend('" 
				+user_name+"','"+friend_name+"');";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	/*
	 * 功能：更新【survey_table】表格，status字段
	 * 操作：update
	 * */
	@Override
	public boolean updateSurveyStatus(String survey_name , int status) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "call p_update_survey_status('" 
				+survey_name+"',"+status+");";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	/*
	 * 功能：更新【survey_table】表格
	 * 操作：delete
	 * 说明: survey_name 为要删除的 问卷的名称
	 * */
	@Override
	public boolean deleteSurveyByName(String survey_name) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "call p_delete_survey_by_name('" 
				+survey_name+"');";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}

	@Override
	public List<VipFriendCheck> getVipFriendNum(String user_name , String friend_name) throws Exception {
		// TODO Auto-generated method stub
		String sql = "CALL p_select_vip_friend_check('"
				+ user_name +"','"
				+friend_name+"')";
		logger.info(sql);
		return generalDao.getEntityList(VipFriendCheck.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：更新【survey_user_transfer_table】表格
	 * 操作：replace
	 * */
	public boolean updateUserSurveyTransfer(String user_name , String survey_name, int transfer) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "call test_insert_survey_user_transfer_table('" 
				+survey_name+"','"+user_name+"',"+transfer+");";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
}
