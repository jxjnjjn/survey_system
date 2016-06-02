<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="问卷调查">
	<meta name="keywords" content="问卷调查" />
	<meta name="author" content="noviachan">
	<link rel="shortcut icon" href="/static/img/survey-icon.jpg">
	<title>问卷调查</title>
	<!-- Bootstrap core CSS -->
	<link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
	<!-- 我们自己的css样式文件放在这里 -->
	<link href="/static/css/system/dashboard.css" rel="stylesheet">
</head>
<body>
	<div id="contentDiv" class="container-fluid">
		<button type="button" class="btn btn-default" id="backbtn">返回</button>
		<div id="listDiv" class="listDiv">
			<div id="showsurveypanel" class="panel panel-new" style="display: none;">
				<div class="panel-heading panel-heading-new">
					<div class="row">
						<div class="col-xs-10 col-md-5">
							<h4 style="margin-top: 0px; margin-bottom: 0px;">
								<strong>问卷调查</strong>
							</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<form id="showsurveyform">
						<input type="hidden" name="user_name" id="user_name" value="${username}">
						<input type="hidden" name="survey_name" id="survey_name" value="${surveyname}">
						<input type="hidden" name="infos" id="infos" value="${infos}">
						<input type="hidden" name="infomationdesc" id="infomationdesc" value="${infomationdesc}">
						<h2 class="text-center"><strong id="surveyname">问卷题目</strong><br/><small id="surveydesc">问卷描述</small></h2>
						<div id="surveytext">
						</div>
						<div class="form-group">
							<div class="text-center">
								<shiro:hasAnyRoles name="vip">
								<button type="button" class="btn btn-lg btn-success-new" id="submitsurvey">提交</button>
								</shiro:hasAnyRoles>
								<shiro:guest>  
								<button type="button" class="btn btn-lg btn-success-new" id="registersurvey">马上注册</button>
								</shiro:guest>
							</div>
						</div>
					</form>
					<!-- JiaThis Button BEGIN -->
					<div class="jiathis_style_32x32 pull-right">
						<a class="jiathis_button_tsina"></a>
						<a class="jiathis_button_tieba"></a>
						<a class="jiathis_button_qzone"></a>
						<a class="jiathis_button_cqq"></a>
						<a class="jiathis_button_douban"></a>
						<a class="jiathis_button_weixin"></a>
					</div>
					<!-- JiaThis Button END -->
				</div>
			</div>
		</div>
	</div>
	
	<!-- Bootstrap core JavaScript================================================== -->
	<!-- 请首先引用jquery，再引用其他js文件 -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
	<script type="text/javascript" src="/static/js/common/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="/static/js/common/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/js/common/commonFunc.js"></script>
	<script type="text/javascript" src="/static/js/common/pageInfo.js"></script>
	<script type="text/javascript" src="/static/js/system/SurveyText.js"></script>
</body>
</html>