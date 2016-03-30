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
	public boolean insertIntoVipBaseTable(String username,String password , String register_date , String register_ip , String register_source , String cellphone_zone , String ip_zone)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into vip_base_info_table(username,password,register_date,register_ip,register_source,cellphone_zone,ip_zone) VALUES('"
				+username+"','"+password+"','"+register_date+"','"+register_ip+"','"
				+register_source+"','"+cellphone_zone+"','"+ip_zone+"');";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	
	public boolean replaceIntoVipDynamicTable(String username,int login_num , int test_num , String friend_1 , String friend_2 , String friend_3 , String friend_4 , String friend_5) throws Exception
	{
		String sql = "call replace_vip_dynamic_table ('"
				+username+"',"+login_num+","+test_num+",'"+friend_1+"','"
				+friend_2+"','"+friend_3+"','"+friend_4+"','"+friend_5+"');";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}

}
