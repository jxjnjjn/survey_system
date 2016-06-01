<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="会员分析">
	<meta name="keywords" content="会员分析" />
	<meta name="author" content="noviachan">
	<link rel="shortcut icon" href="/static/img/survey-icon.jpg">
	<title>会员分析</title>
	<!-- Bootstrap core CSS -->
	<link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
	<!-- 我们自己的css样式文件放在这里 -->
	<link href="/static/css/system/dashboard.css" rel="stylesheet">
	<link href="/static/css/common/bootstrap-datepicker.min.css" rel="stylesheet">
</head>
<body>
	<div id="contentDiv" class="container-fluid">
		<ul id="contentUl" class="nav nav-tabs" role="tablist">
			<li role="presentation"><a href="/system/vip">会员列表</a></li>
			<li role="presentation" class="active"><a href="/system/vipanalysis">会员分析</a></li>
		</ul>
		<div id="listDiv" class="listDiv">
			<div id="todaypanel" class="panel panel-default">
				<div class="panel-heading panel-heading-new">
					<h4 style="margin-top: 0px; margin-bottom: 0px;">
						<strong>核心指数</strong>
					</h4>
				</div>
				<div id="todaypanelbody" class="panel-body" style="padding-bottom: 0px;">
					<div id="todaybasicdata" class="row">
					</div>
				</div>
			</div>
			<div id="dateform" class="form-inline">
				<div id="selectdateform" class="form-group">
					<label for="startdate" class="control-label">选择时间：</label> 
					<input type="text" class="form-control myform control-label" id="startdate" data-date-format="yyyy-mm-dd">
					<label for="enddate" class="control-label">至</label> 
					<input type="text" class="form-control myform control-label" id="enddate" data-date-format="yyyy-mm-dd">
				</div>
				<div class="btn-group">
					<button id="todaybtn" type="button" class="btn btn-default">
						<span style="font-size: 10px;">昨日</span>
					</button>
					<button id="weekbtn" type="button" class="btn btn-default">
						<span style="font-size: 10px;">近7日</span>
					</button>
					<button id="monthbtn" type="button" class="btn btn-default">
						<span style="font-size: 10px;">近30日</span>
					</button>
				</div>
				<button id="searchbtn" class="btn btn-primary" type="button">
					<span style="font-size: 10px;">查询</span>
				</button>
			</div>
			<div id="chartpanel" class="panel panel-default">
				<div class="panel-heading panel-heading-new">
					<h4 style="margin-top: 0px; margin-bottom: 0px;">
						<strong>注册人数趋势</strong>
					</h4>
				</div>
				<div class="panel-body">
					<div id="chart" class="main-chart"></div>
				</div>
			</div>
			<div id="locationchartpanel" class="panel panel-default">
				<div class="panel-heading panel-heading-new">
					<h4 style="margin-top: 0px; margin-bottom: 0px;">
						<strong>注册用户地区分布</strong>
					</h4>
				</div>
				<div class="panel-body">
					<div id="locationchart" class="main-chart"></div>
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
	<script type="text/javascript" src="/static/js/common/echarts.min.js"></script>
	<script type="text/javascript" src="/static/js/common/commonFunc.js"></script>
	<script type="text/javascript" src="/static/js/common/pageInfo.js"></script>
	<script type="text/javascript" src="/static/js/common/datetime.js"></script>
	<script type="text/javascript" src="/static/js/echart/linechart.js"></script>
	<script type="text/javascript" src="/static/js/system/admin/VIPAnalysis.js"></script>
</body>
</html>