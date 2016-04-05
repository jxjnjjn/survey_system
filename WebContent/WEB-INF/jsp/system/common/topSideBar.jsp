<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
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
				会员中心
				</shiro:authenticated>
			</a>
		</div>
		<div id="usermanagenav" class="navbar-collapse collapse">
			<shiro:authenticated>
				<ul class="nav navbar-nav">
					<li role="presentation"><a href="" target="mainframe">创建问卷</a></li>
					<li role="presentation"><a href="" target="mainframe">问卷状态</a></li>
					<li role="presentation"><a href="" target="mainframe">会员管理</a></li>
					<li role="presentation"><a href="/system/survey" target="mainframe">开始答题</a></li>
					<li role="presentation"><a href="" target="mainframe">我的问卷</a></li>
					<li role="presentation"><a href="" target="mainframe">我的好友</a></li>
				</ul>
				<div class="pull-right">
					<p style="padding-top: 10px; color: #777;">
						<span class="glyphicon glyphicon-user"></span> 欢迎您， <a class="navbar-link">${sysUser.user_name}</a>&nbsp;&nbsp;
						<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#userModal">账户</button>
						<button id="sysAdminLogout" type="button" class="btn btn-danger btn-sm logout" onfocus="this.blur()">退出</button>
					</p>
				</div>
			</shiro:authenticated>
		</div>
	</div>
</div>