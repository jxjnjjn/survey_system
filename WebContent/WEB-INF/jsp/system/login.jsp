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
	<jsp:include page="common/topSideBar.jsp" />
	<div class="row">
		<div class="container-fluid col-md-6" style="padding-top: 150px;">
			<section id="content">
				<form id="loginForm">
					<h1>会员登陆</h1>
					<div>
						<input type="text" placeholder="手机号/用户名" name="user_name" id="user_name" />
					</div>
					<div>
						<input type="password" placeholder="密码" name="password" id="password" />
					</div>
					<div>
						<input type="button" value="登录" id="loginBtn"/>
						<a href="#">预览题目</a>
						<a href="/system/register">会员注册</a>
					</div>
				</form><!-- form -->
			</section><!-- content -->
		</div><!-- container -->
	</div><!-- row -->
	<jsp:include page="common/footerSideBar.jsp" />
	<!-- Bootstrap core JavaScript================================================== -->
	<!-- 请首先引用jquery，再引用其他js文件 -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="/static/js/common/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="/static/js/common/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/js/system/login.js"></script>
</body>
</html>

