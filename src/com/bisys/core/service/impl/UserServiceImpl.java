package com.bisys.core.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisys.core.dao.UserDao;
import com.bisys.core.entity.shiro.UserManage;
import com.bisys.core.entity.vo.SysUserVo;
import com.bisys.core.exception.ServiceException;
import com.bisys.core.service.UserService;
import com.bisys.core.service.ValidCodeService;
import com.bisys.core.util.MD5Util;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ValidCodeService validCodeService;

	@Override
	public SysUserVo sysAdminLogin(SysUserVo user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//shiro认证
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUser_name(), MD5Util.GetMD5Code(user.getPassword()));
		//token.setRememberMe(true);
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			logger.error("sysAdminLogin:", e);
			throw new ServiceException("用户密码错误！");
		}
		
		if(currentUser.isAuthenticated()){
			SysUserVo existUser = null;
			existUser = userDao.getSysUserByUserName(user.getUser_name());
			if(existUser == null){
				throw new ServiceException("用户不存在！");
			}
			request.getSession().setAttribute("sysUser", existUser);
			return existUser;
		}else{
			throw new ServiceException("用户密码认证失败！");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.bisys.core.service.UserService#sysAdminRegister(com.bisys.core.entity.vo.SysUserVo, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public SysUserVo sysAdminRegister(SysUserVo user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(!user.getPassword().equals(user.getRepassword())){
			throw new ServiceException("两次密码不同");
		}
		if(!validCodeService.checkValidCode(user.getRandomString(), user.getAuthcode())){
			throw new ServiceException("验证码错误！");
		}
		SysUserVo existUser = userDao.getSysUserByUserName(user.getUser_name());
		if(existUser != null){
			throw new ServiceException("用户已存在！");
		}
		user.setRegister_date("注册时间");
		user.setRegister_ip("注册IP");
		user.setRegister_source("注册来源");
		user.setCellphone_zone("手机所属区域");
		user.setIp_zone("IP所属区域");
		userDao.saveUser(user);
		return user;

	}
	
	@Override
	public SysUserVo sysAdminforgetpassword(SysUserVo user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(!user.getPassword().equals(user.getRepassword())){
			throw new ServiceException("两次密码不同");
		}
		if(!validCodeService.checkValidCode(user.getRandomString(), user.getAuthcode())){
			throw new ServiceException("验证码错误！");
		}
		
		SysUserVo existUser = userDao.getSysUserByUserName(user.getUser_name());
		if(existUser == null){
			throw new ServiceException("用户不存在！");
		}
		user.setPassword(MD5Util.GetMD5Code(user.getPassword()));
		if(!userDao.updateSysUser(user, "password")){
			throw new ServiceException("数据库更新失败");
		}
		return user;

	}
	
	@Override
	public SysUserVo sysAdminChangePass(SysUserVo user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(!user.getNewPass().equalsIgnoreCase(user.getConfirmPass())){
			throw new ServiceException("新密码不一致！请确认输入");
		}
		SysUserVo existUser = null;
		existUser = userDao.getSysUserByUserName(user.getUser_name());
		
		if(existUser == null){
			throw new ServiceException("用户不存在！");
		}
		
		if(!existUser.getPassword().equalsIgnoreCase(MD5Util.GetMD5Code(user.getOldPass()))){
			throw new ServiceException("旧密码错误！请确认输入");
		}
		
		user.setPassword(MD5Util.GetMD5Code(user.getNewPass()));
		
		if(!userDao.updateSysUser(user, "password")){
			throw new ServiceException("数据库更新失败");
		}
		return existUser;
	}

	@Override
	public List<UserManage> findUserByUsername(String username){
		// TODO Auto-generated method stub
		List<UserManage> list = null;
		try {
			list = userDao.findUserByUsername(username);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
}
