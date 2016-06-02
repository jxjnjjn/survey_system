<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="我的好友">
	<meta name="keywords" content="我的好友" />
	<meta name="author" content="noviachan">
	<link rel="shortcut icon" href="/static/img/survey-icon.jpg">
	<title>我的好友</title>
	<!-- Bootstrap core CSS -->
	<link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
	<!-- 我们自己的css样式文件放在这里 -->
	<link href="/static/css/system/dashboard.css" rel="stylesheet">
</head>
<body>
	<div id="contentDiv" class="container-fluid-new">
		<div id="listDiv" class="listDiv-new">
			<div id="tablepanel" class="panel   panel-new">
				<div class="panel-heading panel-heading-new">
					<div class="row">
						<div class="col-xs-10 col-md-5">
							<h4 style="margin-top: 0px; margin-bottom: 0px;">
								<strong>修改密码</strong>
							</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<form id="changepass" class="form-horizontal">
						<input type="hidden" name="user_name" id="user_name" value="${sysUser.user_name}">
						<div class="form-group">
							<label for="oldPass" class="col-sm-4 control-label">旧密码：</label> 
							<div class="col-sm-4">
								<input type="password" class="form-control" name="oldPass" id="oldPass" placeholder="请输入旧密码">
							</div>
						</div>
						<div class="form-group">
							<label for="newPass" class="col-sm-4 control-label">新密码：</label> 
							<div class="col-sm-4">
								<input type="password" class="form-control" name="newPass" id="newPass" placeholder="请输入新密码">
							</div>
						</div>
						<div class="form-group">
							<label for="confirmPass" class="col-sm-4 control-label">再确认：</label> 
							<div class="col-sm-4">
								<input type="password" class="form-control" name="confirmPass" id="confirmPass" placeholder="请确认新密码">
							</div>
						</div>
					</form>
					<div style="text-align: center;">
						<button id="submitbtn" type="button" class="btn btn-primary">确认更新</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript================================================== -->
	<!-- 请首先引用jquery，再引用其他js文件 -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="/static/js/common/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="/static/js/common/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/js/common/commonFunc.js"></script>
	<script type="text/javascript" src="/static/js/common/pageInfo.js"></script>
	<script type="text/javascript" src="/static/js/system/changepassword.js"></script>
</body>
</html>