<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="创建问卷">
	<meta name="keywords" content="创建问卷" />
	<meta name="author" content="noviachan">
	<link rel="shortcut icon" href="/static/img/survey-icon.jpg">
	<title>创建问卷</title>
	<!-- Bootstrap core CSS -->
	<link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
	<!-- 我们自己的css样式文件放在这里 -->
	<link href="/static/css/system/dashboard.css" rel="stylesheet">
	<link href="/static/css/common/bootstrap-datepicker.min.css" rel="stylesheet">
</head>
<body>
	<div id="contentDiv" class="container-fluid">
		<button type="button" class="btn btn-default" id="backbtn">返回</button>
		<div id="listDiv" class="listDiv">
			<div id="showsurveypanel" class="panel panel-primary" style="display: none;">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-10 col-md-5">
							<h4 style="margin-top: 0px; margin-bottom: 0px;">
								<strong>开始问卷</strong>
							</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<form id="showsurveyform">
						<input type="hidden" name="user_name" id="user_name" value="${username}">
						<input type="hidden" name="survey_name" id="survey_name" value="${surveyname}">
						<h2 class="text-center"><strong id="surveyname">问卷题目</strong><br/><small id="surveydesc">问卷描述</small></h2>
						<div id="surveytext">
						</div>
						<div class="form-group">
							<div class="text-center">
								<button type="button" class="btn btn-default" id="submitsurvey">提交</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript================================================== -->
	<!-- 请首先引用jquery，再引用其他js文件 -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="/static/js/common/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="/static/js/common/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/js/common/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript" src="/static/js/common/commonFunc.js"></script>
	<script type="text/javascript" src="/static/js/common/pageInfo.js"></script>
	<script type="text/javascript" src="/static/js/common/time.js"></script>
	<script type="text/javascript" src="/static/js/system/SurveyText.js"></script>
</body>
</html>