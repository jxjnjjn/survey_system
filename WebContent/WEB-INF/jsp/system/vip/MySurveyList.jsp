<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="我的问卷">
	<meta name="keywords" content="我的问卷" />
	<meta name="author" content="noviachan">
	<link rel="shortcut icon" href="/static/img/survey-icon.jpg">
	<title>我的问卷</title>
	<!-- Bootstrap core CSS -->
	<link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
	<!-- 我们自己的css样式文件放在这里 -->
	<link href="/static/css/system/dashboard.css" rel="stylesheet">
</head>
<body>
	<div id="contentDiv" class="container-fluid-new panel panel-new">

		<div id="listDiv" class="listDiv-new">
			<div id="tablepanel" class="panel   panel-new">
				<div class="panel-heading panel-heading-new">
					<div class="row">
						<div class="col-xs-10 col-md-5">
							<h4 style="margin-top: 0px; margin-bottom: 0px;">
								<strong>问卷详情</strong>
							</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div id="detailstable">
						<input type="hidden" name="user_name" id="user_name" value="${sysUser.user_name}">
						<div id="datatable"></div>
						<div id="pageinfo" class="module-head" style="height: 45px; line-height: 45px;"></div>
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
	<script type="text/javascript" src="/static/js/system/vip/MySurveyList.js"></script>
</body>
</html>