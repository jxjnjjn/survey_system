<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation"  style="height: 65px;">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#usermanagenav">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand"> 
				<shiro:authenticated>
				<img class="img-rounded" src="/static/img/logo.png"  style="height: 40px;width:100px;">
				</shiro:authenticated>
			</a>
		</div>
		<div id="usermanagenav" class="navbar-collapse collapse">
			<shiro:authenticated>
				<ul class="nav navbar-nav">
					<shiro:hasAnyRoles name="admin">
						<li role="presentation"><a href="/system/startsurvey" target="mainframe"><strong>在线问卷</strong></a></li>
						<li role="presentation"><a href="/system/survey" target="mainframe"><strong>问卷管理</strong></a></li>
						<li role="presentation"><a href="/system/vip" target="mainframe"><strong>会员管理</strong></a></li>
						<li role="presentation"><a data-toggle="modal" data-target="#userModal"><strong>修改密码</strong></a></li>
					</shiro:hasAnyRoles>
					<shiro:hasAnyRoles name="vip">
						<li role="presentation"><a href="/system/startsurvey" target="mainframe"><strong>开始答题</strong></a></li>
						<li role="presentation"><a href="/system/mysurvey" target="mainframe"><strong>我的问卷</strong></a></li>
						<li role="presentation"><a href="/system/myfriend" target="mainframe"><strong>我的好友</strong></a></li>
						<li role="presentation"><a data-toggle="modal" data-target="#userModal"><strong>修改密码</strong></a></li>
					</shiro:hasAnyRoles>
				</ul>
			</shiro:authenticated>
			<div class="pull-right">
				<p style="padding-top: 10px; color: #777;">
					<shiro:authenticated>
						<span class="glyphicon glyphicon-user"></span><strong> 欢迎您， <a class="navbar-link">${sysUser.user_name}</a></strong>&nbsp;&nbsp;
						<button id="sysAdminLogout" type="button" class="btn logout" onfocus="this.blur()">退出</button>
					</shiro:authenticated>
					<shiro:guest>  
						<a href="/system/login">登录</a> <a href="/system/register">注册</a>
					</shiro:guest>
				</p>
			</div>
		</div>
	</div>
</div>