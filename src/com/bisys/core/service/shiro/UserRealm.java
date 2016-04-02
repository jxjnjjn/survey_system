package com.bisys.core.service.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.bisys.core.entity.shiro.UserManage;
import com.bisys.core.service.UserService;
import com.google.gson.Gson;

public class UserRealm extends AuthorizingRealm{
	
	private static Logger logger = Logger.getLogger(UserRealm.class);
	
	@Autowired
	private UserService userService;

	/**
	 * 授权操作
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String username = (String) principals.getPrimaryPrincipal();
		
		List<UserManage> roleSet =  userService.findUserByUsername(username);
		//角色名的集合
		Set<String> roles = new HashSet<String>();
		//权限名的集合
		Set<String> permissions = new HashSet<String>();
		
		for(UserManage bean:roleSet){
			roles.add(bean.getRole_name());
			permissions.add(bean.getPermission_name());
		}
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		
		logger.info(new Gson().toJson(roles));
		
		authorizationInfo.addRoles(roles);
		authorizationInfo.addStringPermissions(permissions);
		
		return authorizationInfo;
	}

	/**
	 * 身份验证操作
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		List<UserManage> roleList =  userService.findUserByUsername(username);
		logger.info(new Gson().toJson(roleList));
		if(null == roleList){
			logger.error("doGetAuthenticationInfo 用户不存在");
			throw new AuthenticationException("用户不存在！");
		}
		UserManage user = roleList.get(0);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUser_name(), user.getPassword(),getName());
		return info;
	}
}
