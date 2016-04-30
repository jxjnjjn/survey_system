package com.bisys.core.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
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
import com.bisys.core.entity.gson.IPgson;
import com.bisys.core.entity.gson.Phonegson;
import com.bisys.core.entity.shiro.UserManage;
import com.bisys.core.entity.vo.SysUserVo;
import com.bisys.core.entity.vo.VisitorCheck;
import com.bisys.core.exception.ServiceException;
import com.bisys.core.service.UserService;
import com.bisys.core.service.ValidCodeService;
import com.bisys.core.util.DateUtil;
import com.bisys.core.util.HttpUtil;
import com.bisys.core.util.MD5Util;
import com.google.gson.Gson;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ValidCodeService validCodeService;

	@Override
	public boolean sysAdminVisitorCheck(VisitorCheck visitor, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return userDao.updateVisitorTable(visitor.getVisitor_num() , visitor.getVip_num() ,  DateUtil.dateToString(new Date(),"yyyy-MM-dd"));
	}
	
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
	
	@Override
	public void addupUserStatics(String userName , int loginNum , int testNum)throws Exception {
		userDao.addIntoVipDynamicTable(userName , loginNum , testNum);
	}
	
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
		
		Date now = new Date(); 
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		user.setRegister_date(now);
		user.setRegister_ip(HttpUtil.getIpAddr(request));
		
		//user.setRegister_source("未知来源");
		if("0".equals(user.getRegister_source())){
			user.setRegister_source("未知来源");
		}else if("1".equals(user.getRegister_source())){
			user.setRegister_source("微博");
		}else if("2".equals(user.getRegister_source())){
			user.setRegister_source("百度贴吧");
		}else if("3".equals(user.getRegister_source())){
			user.setRegister_source("QQ空间");
		}else if("4".equals(user.getRegister_source())){
			user.setRegister_source("QQ");
		}else if("5".equals(user.getRegister_source())){
			user.setRegister_source("豆瓣");
		}else if("6".equals(user.getRegister_source())){
			user.setRegister_source("微信");
		}
		
		try {
			//获取手机区域
			String phoneresult = HttpUtil.sendGet("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm", "tel=" + user.getUser_name());
			phoneresult = phoneresult.replace(" ", ""); 
			phoneresult = phoneresult.substring(phoneresult.indexOf("=") + 1);
			logger.error("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=" + user.getUser_name() + " ,result= " + phoneresult);
			Phonegson pg = new Phonegson();
			pg = new Gson().fromJson(phoneresult, Phonegson.class);
			user.setCellphone_zone(pg.getProvince());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取手机区域异常");
			user.setCellphone_zone("未知");
		}
		
		try {
			//获取ip区域
			String ipresult = HttpUtil.sendGet("http://ip.taobao.com/service/getIpInfo.php", "ip=" + HttpUtil.getIpAddr(request));
			ipresult = ipresult.replace(" ", ""); 
			logger.error("http://ip.taobao.com/service/getIpInfo.php?ip=" + HttpUtil.getIpAddr(request) + " ,result= " + ipresult);
			IPgson ig = new IPgson();
			ig = new Gson().fromJson(ipresult, IPgson.class);
			String city = ig.getData().getCity();
			if(city.equals(""))
			{
				city = "本机";
			}
			user.setIp_zone(city);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取IP区域异常");
			user.setIp_zone("未知");
		}
		
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
