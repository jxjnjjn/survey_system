<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="找回密码">
	<meta name="author" content="noviachan">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" href="/static/img/survey-icon.jpg">
	<title>找回密码</title>
	<!-- Bootstrap core CSS -->
	<link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
	<!-- 我们自己的css样式文件放在这里 -->
	<link href="/static/css/system/dashboard.css" rel="stylesheet">
	<link href="/static/css/system/login.css" rel="stylesheet">
</head>
<body>
	<div id="loginup">
	<div id="content">
		<form id="forgetpasswordForm">
			<h1><span style="font-weight:bold;color:rgb(255,255,255);">修改密码</span></h1>
			<input type="hidden" name="randomString" id="randomString" value="" />
			<div>
				<input type="text" placeholder="手机号"  name="user_name" id="user_name"  class="myinput"/>
			</div>
			<div>
				<input type="password" placeholder="新密码"  name="password" id="password"  class="myinput"/>
			</div>
			<div>
				<input type="password" placeholder="确认新密码"  name="repassword" id="repassword"  class="myinput"/>
			</div>
			<div style="margin-bottom: 10px;">
				<button class="btn btn-default" type="button" id="validcodeBtn">发送到手机获取验证码</button>
			</div>
			<div>
				<input type="text" placeholder="请输入验证码"  name="authcode" id="authcode"  class="myinput"/>
			</div>
			<div>
				<input type="button" value="确认" id="forgetpasswordBtn"/>
				<a href="/system/login">返回登录</a>
			</div>
		</form>
	</div>
	</div>
	<jsp:include page="common/footerSideBar.jsp" />
	<!-- Bootstrap core JavaScript================================================== -->
	<!-- 请首先引用jquery，再引用其他js文件 -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="/static/js/common/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="/static/js/common/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/js/system/register.js"></script>
</body>
</html>

