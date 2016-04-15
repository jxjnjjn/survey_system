package com.bisys.core.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bisys.core.dao.GeneralDao;
import com.bisys.core.dao.UserDao;
import com.bisys.core.entity.bo.SysUserBo;
import com.bisys.core.entity.shiro.UserManage;
import com.bisys.core.entity.vo.SysUserVo;
import com.bisys.core.util.MD5Util;
import com.bisys.core.util.StringUtil;

@Repository
public class UserDaoImpl implements UserDao {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private GeneralDao generalDao;

	@Override
	public boolean updateSysUser(SysUserBo user, String... fileds)
			throws Exception {
		if(user == null){
			return false;
		}
		try {
			return generalDao.updateEntity(user, fileds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void saveUser(SysUserBo user) throws Exception {
		try {
			generalDao.saveEntity(
					" insert into vip_base_info_table (user_name, password, register_date, register_ip, register_source, cellphone_zone, ip_zone,role_name,permission_name) values(?, ?, ?, ?, ?, ?, ?, ?, ?)", 
					new Object[]{user.getUser_name(), MD5Util.GetMD5Code(user.getPassword()),user.getRegister_date(),user.getRegister_ip(),user.getRegister_source(),user.getCellphone_zone(),user.getIp_zone(),"vip","vip"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public SysUserVo getSysUserByUserName(String userName) throws Exception {
		if(StringUtil.isEmpty(userName)){
			return null;
		}
		return generalDao.getEntity(SysUserVo.class, " select user_name,password from vip_base_info_table where user_name = ? ", new Object[]{userName});
	}
	
	@Override
	public boolean updateVisitorTable(int num , String date_time) throws Exception {
		String sql = "CALL p_update_visitor_table('"
				+ num +"','"+date_time+"')";
		logger.info(sql);
		return generalDao.saveEntity(sql, new Object[]{});
	}
	

	@Override
	public List<UserManage> findUserByUsername(String username) throws Exception {
		if(StringUtil.isEmpty(username)){
			return null;
		}
		String sql = " select user_name,password,role_name,permission_name from vip_base_info_table where user_name = ?";
		return generalDao.getEntityList(UserManage.class, sql, new Object[]{username});
	}

}
