<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="会员登陆">
	<meta name="author" content="noviachan">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" href="/static/img/survey-icon.jpg">
	<title>会员登录</title>
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
			<div id="loginleft" class="col-md-8 center-block">
				<div class="row" style="margin: auto;padding: 30px;">
					<img class="img-rounded" src="/static/img/logo.png"  style="height: 50px;width:100px;">
				</div>
				<div class="row">
					<div id="loginjumbo">
					  <h1><strong><span style="font-weight:bold;color:rgb(237,148,66);">开创免费竞猜先河</span></strong></h1>
					  <h2><strong><span style="font-weight:bold;color:rgb(255,255,255);">只需30秒，赢取1000元</span></strong></h2>
					  <p><a  id="questionBtn" class="btn btn-primary btn-lg" href="#" role="button">查看今日竞猜</a></p>
					</div>
				</div>
			</div>
			<div id="loginright" class="col-md-4">
				<div id="content">
					<form id="loginForm">
						<h1><span style="font-weight:bold;color:rgb(255,255,255);">注册|</span><img class="img-rounded" src="/static/img/1.png"></h1>
						<div>
							<input type="text" placeholder="手机号/用户名" name="user_name" id="user_name" class="myinput"/>
						</div>
						<div>
							<input type="password" placeholder="密码" name="password" id="password"  class="myinput"/>
						</div>
						<div>
							<input type="button" value="登录" id="loginBtn"/>
						</div>
						<div style="padding: 20px;">
							<a href="/system/forgetpassword" class="pull-left"><span style="font-weight:bold;color:rgb(237,148,66);">忘记密码</span></a>
							<a href="/system/register" class="pull-right"><span style="font-weight:bold;color:rgb(237,148,66);">会员注册</span></a>
						</div>
					</form><!-- form -->
				</div><!-- content -->
			</div>
			</div>
		</div>
		<div id="loginfoot" class="row">
		  <div class="col-md-3 center-block" style="text-align: center;">
		  	<div class="footimg"><img src="/static/img/icon_1.png" class="img-rounded"><h4 class="text-muted">免费注册5秒</h4></div>
		  </div>
		  <div class="col-md-3 center-block" style="text-align: center;">
		  	<div class="footimg"><img src="/static/img/icon_2.png" class="img-rounded"><h4 class="text-muted">免费答题30秒</h4></div>
		  </div>
		  <div class="col-md-3 center-block" style="text-align: center;">
		  	<div class="footimg"><img src="/static/img/icon_3.png" class="img-rounded"><h4 class="text-muted">高额奖励1000元</h4></div>
		  </div>
		  <div class="col-md-3 center-block" style="text-align: center;">
		  	<div class="footimg"><img src="/static/img/icon_4.png" class="img-rounded"><h4 class="text-muted">邀请好友100元</h4></div>
		  </div>
		</div>
		
	</div><!-- container -->
	<jsp:include page="common/footerSideBar.jsp" />
	<!-- Bootstrap core JavaScript================================================== -->
	<!-- 请首先引用jquery，再引用其他js文件 -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="/static/js/common/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="/static/js/common/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/js/system/login.js"></script>
</body>
</html>

