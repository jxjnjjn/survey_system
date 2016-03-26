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
	public SysUserVo getSysUserByUserName(String userName) throws Exception {
		if(StringUtil.isEmpty(userName)){
			return null;
		}
		return generalDao.getEntity(SysUserVo.class, " select username,password from vip_base_info_table where username = ? ", new Object[]{userName});
	}

	@Override
	public List<UserManage> findUserByUsername(String username) throws Exception {
		if(StringUtil.isEmpty(username)){
			return null;
		}
		String sql = " select username,password from vip_base_info_table where username = ?";
		return generalDao.getEntityList(UserManage.class, sql, new Object[]{username});
	}

}
