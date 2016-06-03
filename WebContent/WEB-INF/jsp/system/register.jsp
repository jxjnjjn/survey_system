<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="会员注册">
	<meta name="author" content="noviachan">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" href="/static/img/survey-icon.jpg">
	<title>会员注册</title>
	<!-- Bootstrap core CSS -->
	<link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
	<!-- 我们自己的css样式文件放在这里 -->
	<link href="/static/css/system/dashboard.css" rel="stylesheet">
	<link href="/static/css/system/login.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
<div id="logintop" class="row">
	<div id="loginup">
	<div id="content">
		<form id="registerForm">
			<h1><span style="font-weight:bold;color:rgb(255,255,255);">会员注册</span></h1>
			<input type="hidden" name="register_source" id="register_source" value="${source}" />
			<input type="hidden" name="randomString" id="randomString" value="" />
			<div>
				<input type="text" placeholder="手机号"  name="user_name" id="user_name"  class="myinput"/>
			</div>
			<div>
				<input type="password" placeholder="密码"  name="password" id="password"  class="myinput"/>
			</div>
			<div>
				<input type="password" placeholder="确认密码"  name="repassword" id="repassword"  class="myinput"/>
			</div>
			<div style="margin-bottom: 10px;">
				<button class="btn btn-default" type="button" id="validcodeBtn">发送到手机获取验证码</button>
			</div>
			<div>
				<input type="text" placeholder="请输入验证码"  name="authcode" id="authcode"  class="myinput"/>
			</div>
			<div>
				<input type="button" value="注册" id="registerBtn"/>
				<a href="/system/login">会员登录</a>
			</div>
		</form>
	</div>
	</div>
	</div>
		<div id="loginfoot" class="row">
		  <div class="col-md-3 center-block" style="text-align: center;">
		  	<div class="footimg"><img src="/static/img/icon_1.png" class="img-rounded"><h3 class="text-muted">免费注册5秒</h3></div>
		  </div>
		  <div class="col-md-3 center-block" style="text-align: center;">
		  	<div class="footimg"><img src="/static/img/icon_2.png" class="img-rounded"><h3 class="text-muted">免费答题30秒</h3></div>
		  </div>
		  <div class="col-md-3 center-block" style="text-align: center;">
		  	<div class="footimg"><img src="/static/img/icon_3.png" class="img-rounded"><h3 class="text-muted">高额奖励1000元</h3></div>
		  </div>
		  <div class="col-md-3 center-block" style="text-align: center;">
		  	<div class="footimg"><img src="/static/img/icon_4.png" class="img-rounded"><h3 class="text-muted">邀请好友100元</h3></div>
		  </div>
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

