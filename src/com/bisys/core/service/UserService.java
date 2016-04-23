package com.bisys.core.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bisys.core.entity.shiro.UserManage;
import com.bisys.core.entity.vo.SysUserVo;
import com.bisys.core.entity.vo.VisitorCheck;

public interface UserService {
	
	/**
	 * 系统管理员登录
	 */
	public SysUserVo sysAdminLogin(SysUserVo user, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public boolean sysAdminVisitorCheck(VisitorCheck visitor, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public SysUserVo sysAdminRegister(SysUserVo user, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public SysUserVo sysAdminforgetpassword(SysUserVo user, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public SysUserVo sysAdminChangePass(SysUserVo user, HttpServletRequest request, HttpServletResponse response) throws Exception;

	public List<UserManage> findUserByUsername(String username); 
}
