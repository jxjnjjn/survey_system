<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="问卷分析">
	<meta name="keywords" content="问卷分析" />
	<meta name="author" content="noviachan">
	<link rel="shortcut icon" href="/static/img/survey-icon.jpg">
	<title>问卷分析</title>
	<!-- Bootstrap core CSS -->
	<link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
	<!-- 我们自己的css样式文件放在这里 -->
	<link href="/static/css/system/dashboard.css" rel="stylesheet">
</head>
<body>
	<div id="contentDiv" class="container-fluid">
		<button type="button" class="btn btn-default" id="backbtn">返回</button>
		<div id="listDiv" class="listDiv">
			<div id="tablepanel" class="panel panel-primary">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-10 col-md-5">
							<h4 style="margin-top: 0px; margin-bottom: 0px;">
								<strong>问卷分析  -  ${surveyname}</strong>
							</h4>
						</div>
					</div>
				</div>
				<div id="datatable" class="list-group"></div>
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-10 col-md-5">
							<h4 style="margin-top: 0px; margin-bottom: 0px;">
								<strong>来源分析  -  ${surveyname}</strong>
							</h4>
						</div>
					</div>
				</div>
				
				<div id="locationchartpanel" class="panel panel-default">
				<div class="panel-heading">
					<h4 style="margin-top: 0px; margin-bottom: 0px;">
						<strong>手机地区分布</strong>
					</h4>
				</div>
				<div class="panel-body">
					<div id="locationchart" class="main-chart"></div>
				</div>
				</div>
				
				<div class="panel-body">
					<div id="detailstable">
						<input type="hidden" id="surveynamehide" value="${surveyname}"/>
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
	<script type="text/javascript" src="/static/js/common/echarts.min.js"></script>
	<script type="text/javascript" src="/static/js/echart/linechart.js"></script>
	<script type="text/javascript" src="/static/js/system/admin/SurveyAnalysis.js"></script>
</body>
</html>