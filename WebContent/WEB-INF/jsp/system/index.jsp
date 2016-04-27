<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="会员管理后台">
	<meta name="author" content="noviachan">
	<link rel="shortcut icon" href="/static/img/bisys-icon.png">
	<title>会员管理后台</title>
	<!-- Bootstrap core CSS -->
	<link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
	<!-- 我们自己的css样式文件放在这里 --> 
	<link href="/static/css/system/dashboard.css" rel="stylesheet">
</head>
<body style="padding-top: 50px;">
	<div class="container" style="width: 1200px;text-align: center;">
			<jsp:include page="common/topSideBar.jsp" />
			<div class="container-fluid">
				<div class="main">
					<iframe id="mainframe" frameborder="0" src="/system/startsurvey" name="mainframe"></iframe>
				</div>
			</div>
			<jsp:include page="common/footerSideBar.jsp" />
			
			<jsp:include page="common/userModal.jsp" />
	</div>
	<!-- Bootstrap core JavaScript================================================== -->
	<!-- 请首先引用jquery，再引用其他js文件 -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="/static/js/common/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="/static/js/common/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/js/common/commonFunc.js"></script>
	<script type="text/javascript" src="/static/js/system/index.js"></script>
</body>
</html>