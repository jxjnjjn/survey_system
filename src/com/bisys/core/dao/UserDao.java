package com.bisys.core.dao;

import java.util.List;

import com.bisys.core.entity.bo.SysUserBo;
import com.bisys.core.entity.shiro.UserManage;
import com.bisys.core.entity.vo.SysUserVo;

public interface UserDao {

	public SysUserVo getSysUserByUserName(String userName) throws Exception;
	
	public boolean updateSysUser(SysUserBo user, String... fileds) throws Exception;
	
	public void saveUser(SysUserBo user) throws Exception;
	
	public List<UserManage> findUserByUsername(String username) throws Exception;

	boolean updateVisitorTable(int num, String date_time) throws Exception;

	boolean addVipDynamicInfo(String user_name, int login_num, int test_num) throws Exception;

}
