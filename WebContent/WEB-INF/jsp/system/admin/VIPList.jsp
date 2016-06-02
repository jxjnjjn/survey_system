<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="会员列表">
	<meta name="keywords" content="会员列表" />
	<meta name="author" content="noviachan">
	<link rel="shortcut icon" href="/static/img/survey-icon.jpg">
	<title>会员列表</title>
	<!-- Bootstrap core CSS -->
	<link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
	<!-- 我们自己的css样式文件放在这里 -->
	<link href="/static/css/system/dashboard.css" rel="stylesheet">
</head>
<body>
	<div id="contentDiv" class="container-fluid-new">
		<ul id="contentUl" class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="/system/vip">会员列表</a></li>
			<li role="presentation"><a href="/system/vipanalysis">会员分析</a></li>
		</ul>
		<form class="form-inline" style="margin-top: 10px;">
			<div class="form-group">
				<label for="vip_name">搜索会员</label>
				<input type="text" placeholder="会员名称" name="vip_name" id="vip_name" class="form-control"/>
			</div>
			<button type="button" class="btn btsuccess" id="searchvipbtn">搜索</button>
		</form>
		<div id="listDiv" class="listDiv-new">
			<div id="tablepanel" class="panel   panel-new">
				<div class="panel-heading panel-heading-new">
					<div class="row">
						<div class="col-xs-10 col-md-5">
							<h4 style="margin-top: 0px; margin-bottom: 0px;">
								<strong>VIP</strong>
							</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div id="detailstable">
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
	<script type="text/javascript" src="/static/js/system/admin/VIPList.js"></script>
</body>
</html>