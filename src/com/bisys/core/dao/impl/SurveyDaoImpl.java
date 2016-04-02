package com.bisys.core.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bisys.core.dao.GeneralDao;
import com.bisys.core.dao.SurveyDao;

@Repository
public class SurveyDaoImpl implements SurveyDao {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GeneralDao generalDao;

	@Override
	/*
	 * 直接调用insert方法，不会做唯一性验证
	 * 
	 *
	 * */
	public boolean insertIntoVipBaseTable(String username,String password , String register_date , String register_ip , String register_source , 
			String cellphone_zone , String ip_zone , String role_name , String permission_name)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "call insert_vip_base_table('" 
				+username+"',"+password+","+register_date+",'"+register_ip+"','"
				+register_source+"',"+cellphone_zone+","+ip_zone+",'"+role_name+"','"+permission_name+"','"
				+"');";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	@Override
	public boolean replaceIntoVipDynamicTable(String username,int login_num , int test_num , String friend_1 , String friend_2 , String friend_3 , String friend_4 , String friend_5) throws Exception
	{
		String sql = "call replace_vip_dynamic_table ('"
				+username+"',"+login_num+","+test_num+",'"+friend_1+"','"
				+friend_2+"','"+friend_3+"','"+friend_4+"','"+friend_5+"');";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	@Override
	public boolean insertIntoSurveyTable(String survey_name,String survey_desc , String survey_text , String survey_anwser , String start_time , String end_time , int status) throws Exception
	{
		String sql = "call insert_survey_table ('"
				+survey_name+"','"+survey_desc+"','"+survey_text+"','"+survey_anwser+"','"
				+start_time+"','"+end_time+"',"+status+");";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	@Override
	public boolean insertIntoSurveyUserTable(String user_name,String survey_name , int answer_time , String answer , int transfer_flag) throws Exception
	{
		String sql = "call insert_survey_user_table ('"
				+user_name+"','"+survey_name+"',"+answer_time+",'"+answer+"',"
				+transfer_flag+");";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
}
