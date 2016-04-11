package com.bisys.core.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bisys.core.dao.GeneralDao;
import com.bisys.core.dao.SurveyDao;
import com.bisys.core.entity.survey.SurveyAnalysisEntity;
import com.bisys.core.entity.survey.SurveyInfoEntity;
import com.bisys.core.entity.survey.VipAnalysisEntity;
import com.bisys.core.entity.survey.VipFriendInfoEntity;
import com.bisys.core.entity.survey.VipInfoEntity;
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
	 * 表格：vip_dynamic_info_table
	 * 操作：REPLACE
	 * 成功条件：查找【vip_base_info_table】表格，存在这样的记录：user_name和要插入数据相同。
	 *
	 * */
	@Override
	public boolean replaceIntoVipDynamicTable(String user_name,int login_num , int test_num , String friends) throws Exception
	{
		String sql = "call p_replace_vip_dynamic_table ('"
				+user_name+"',"+login_num+","+test_num+",'"+friends+"');";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	/*
	 * 表格：survey_table
	 * 操作：INSERT
	 * 成功条件：查找【survey_table】表格，不存在这样的记录：survey_name和要插入数据相同。
	 *
	 * */
	@Override
	public boolean insertIntoSurveyTable(SurveyInfoEntity survey) throws Exception
	{
		String sql = "call p_insert_survey_table ('"
				+survey.getSurvey_name()+"','"+survey.getSurvey_desc()+"','"+survey.getSurvey_text()+"','"+survey.getSurvey_anwser()+"','"
				+survey.getStart_time()+"','"+survey.getEnd_time()+"',"+survey.getStatus()+");";
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
	public boolean insertIntoSurveyUserTable(String user_name,String survey_name , int answer_time , String answer , int transfer_flag) throws Exception
	{
		String sql = "call p_insert_survey_user_table ('"
				+user_name+"','"+survey_name+"',"+answer_time+",'"+answer+"',"
				+transfer_flag+");";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	/*
	 * 功能：会员列表
	 * 
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
	 * 
	 * */
	@Override
	public List<VipInfoEntity> getVipInfo(String user_name) throws Exception
	{
		String sql = "CALL p_select_vip_info('"
				+ user_name +"')";
		logger.info(sql);
		return generalDao.getEntityList(VipInfoEntity.class, sql, new Object[]{});
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
	public List<SurveyInfoEntity> getSurveyInfo(int status) throws Exception
	{
		String sql = "CALL p_select_survey_list("
				+ status +")";
		logger.info(sql);
		return generalDao.getEntityList(SurveyInfoEntity.class, sql, new Object[]{});
	}
	
	/*
	 * 功能：会员分析-注册人数统计
	 * 说明：today 查询当日日期
	 * 
	 * */
	@Override
	public List<VipAnalysisEntity> getVipAnalysis(Date today) throws Exception
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
	public List<VipTrendAnalysisEntity> getVipTrendAnalysis(Date start , Date end) throws Exception
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
	 * 说明：today 查询当日日期
	 * 说明：survey_name 查询问卷名字
	 * 
	 * */
	@Override
	public List<SurveyAnalysisEntity> getSurveyAnalysis(String survey_name , Date today) throws Exception
	{
		String sql = "CALL p_select_survey_analysis('"
				+survey_name+"',"
				+ today +")";
		logger.info(sql);
		return generalDao.getEntityList(SurveyAnalysisEntity.class, sql, new Object[]{});
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
}
