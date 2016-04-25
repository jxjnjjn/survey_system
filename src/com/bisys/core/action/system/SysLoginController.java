package com.bisys.core.action.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bisys.core.entity.JsonResult;
import com.bisys.core.entity.vo.SysUserVo;
import com.bisys.core.entity.vo.VisitorCheck;
import com.bisys.core.exception.ServiceException;
import com.bisys.core.service.UserService;
import com.google.gson.Gson;

/**
 * 登录页面
 * @author noviachan
 *
 */
@Controller
@RequestMapping("/system")
public class SysLoginController{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request){
		logger.info("管理员/会员登录");
		return "system/login";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(HttpServletRequest request){
		logger.info("会员注册");
		return "system/register";
	}
	
	@RequestMapping(value = "forgetpassword", method = RequestMethod.GET)
	public String forgetpassword(HttpServletRequest request){
		logger.info("忘记密码");
		return "system/forgetpassword";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		logger.info("系统管理员退出登录");
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		return "system/login";
	}
	
	/**
	 * 系统管理员登录接口
	 */
	@RequestMapping(value = "loginCheck", method = RequestMethod.POST)
	@ResponseBody 
	public String loginCheck( @RequestBody SysUserVo user, HttpServletRequest request, HttpServletResponse response){
		
		logger.info("用户登录信息:" + user.toString());
		
		boolean flag = false;
		String errorMessage = "用户登录失败";
		JsonResult<SysUserVo> jsonResult = new JsonResult<SysUserVo>();
		
		try {
			user = userService.sysAdminLogin(user, request, response);
			//用户登录次数统计， 登录次数+1 ， 测试次数+0。
			userService.addupUserStatics(user.getUser_name() , 1 , 0);
			flag = true;
		}catch (ServiceException serviceE){
			logger.error("sys admin login failed!"+serviceE.getMessage());
			errorMessage = serviceE.getMessage();
		}catch (Exception e) {
			logger.error("sys admin login failed!", e);
		}
		
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "用户登录成功" : errorMessage);
		//jsonResult.setData(user);
		return new Gson().toJson(jsonResult);
	}
	
	/**
	 * 系统登录统计
	 */
	@RequestMapping(value = "visitorCheck", method = RequestMethod.POST)
	@ResponseBody 
	public String visitorCheck( @RequestBody VisitorCheck visitor, HttpServletRequest request, HttpServletResponse response){
		
		logger.info("登录统计:" + new Gson().toJson(visitor));
		
		boolean flag = false;
		String errorMessage = "统计失败";
		JsonResult<SysUserVo> jsonResult = new JsonResult<SysUserVo>();
		
		try {
			userService.sysAdminVisitorCheck(visitor, request, response);
			flag = true;
		}catch (ServiceException serviceE){
			logger.error("sys admin login failed!"+serviceE.getMessage());
			errorMessage = serviceE.getMessage();
		}catch (Exception e) {
			logger.error("sys admin login failed!", e);
		}
		
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "统计成功" : errorMessage);
		return new Gson().toJson(jsonResult);
	}
	
	/**
	 * 添加用户
	 */
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	@ResponseBody 
	public String addUser( @RequestBody SysUserVo user, HttpServletRequest request, HttpServletResponse response){
		
		logger.info("添加用户:" + user.toString());
		boolean flag = false;
		String errorMessage = "用户注册失败";
		JsonResult<SysUserVo> jsonResult = new JsonResult<SysUserVo>();
		
		try {
			user = userService.sysAdminRegister(user, request, response);
			flag = true;
		}catch (ServiceException serviceE){
			logger.error("sys admin adduser failed!"+serviceE.getMessage());
			errorMessage = serviceE.getMessage();
		}catch (Exception e) {
			logger.error("sys admin adduser failed!", e);
		}
		
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "用户注册成功" : errorMessage);
		//jsonResult.setData(user);
		return new Gson().toJson(jsonResult);
	}
	
	/**
	 * 找回密码
	 */
	@RequestMapping(value = "getbackpassword", method = RequestMethod.POST)
	@ResponseBody 
	public String getBackPassword( @RequestBody SysUserVo user, HttpServletRequest request, HttpServletResponse response){
		
		logger.info("找回密码:" + user.toString());
		
		boolean flag = false;
		String errorMessage = "修改密码失败";
		JsonResult<SysUserVo> jsonResult = new JsonResult<SysUserVo>();
		
		try {
			user = userService.sysAdminforgetpassword(user, request, response);
			flag = true;
		}catch (ServiceException serviceE){
			logger.error("sys admin getBackPassword failed!"+serviceE.getMessage());
			errorMessage = serviceE.getMessage();
		}catch (Exception e) {
			logger.error("sys admin getBackPassword failed!", e);
		}
		
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "修改密码成功" : errorMessage);
		//jsonResult.setData(user);
		return new Gson().toJson(jsonResult);
	}
	
	/**
	 * 系统管理员修改密码接口
	 */
	@RequestMapping(value = "changepass", method = RequestMethod.POST)
	@ResponseBody 
	public String changePass( @RequestBody SysUserVo user, HttpServletRequest request, HttpServletResponse response){
		
		logger.info("修改密码用户信息:" + user.toString());
		
		boolean flag = false;
		String errorMessage = "修改密码失败";
		JsonResult<SysUserVo> jsonResult = new JsonResult<SysUserVo>();
		
		try {
			user = userService.sysAdminChangePass(user, request, response);
			flag = true;
		}catch (ServiceException serviceE){
			logger.error("change user failed!"+serviceE.getMessage());
			errorMessage = serviceE.getMessage();
		}catch (Exception e) {
			logger.error("change user failed!", e);
		}
		
		jsonResult.setResultCode(flag ? 0 : 1);
		jsonResult.setResultMessage(flag ? "修改密码成功" : errorMessage);
		//jsonResult.setData(user);
		return new Gson().toJson(jsonResult);
	}
}
