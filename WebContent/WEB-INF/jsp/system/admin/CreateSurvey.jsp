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
			<div id="tablepanel" class="panel panel-primary">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-10 col-md-5">
							<h4 style="margin-top: 0px; margin-bottom: 0px;">
								<strong>问卷设计</strong>
							</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<form class="form-horizontal">
						<input type="hidden" name="user_name" id="user_name" value="${sysUser.user_name}">
						<div class="form-group">
							<label for="survey_title" class="col-sm-2 control-label">问卷标题</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="survey_title" placeholder="问卷标题">
							</div>
						</div>
						<div class="form-group">
							<label for="survey_desc" class="col-sm-2 control-label">问卷描述</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="survey_desc" placeholder="问卷描述">
							</div>
						</div>
						<div class="form-group">
							<label for="survey_content" class="col-sm-2 control-label">问卷内容</label>
							<div class="col-sm-10">
								<textarea id="survey_content" class="form-control" rows="20"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="starttime" class="col-sm-2 control-label">问卷开始时间</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" placeholder="开始时间" name="starttime" id="starttime" />
							</div>
						</div>
						<div class="form-group">
							<label for="endtime" class="col-sm-2 control-label">问卷结束时间</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" placeholder="结束时间" name="endtime" id="endtime" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">提交后的操作</label>
							<div class="col-sm-10">
								<label class="radio-inline">
									<input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">显示感谢信息
								</label>
								<label class="radio-inline">
									<input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">跳转到
								</label>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default" id="savesurveybtn">保存问卷</button>
								<button type="submit" class="btn btn-info" id="showsurveybtn">预览问卷</button>
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
	<script type="text/javascript" src="/static/js/common/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="/static/js/common/bootstrap.min.js"></script>
	<script charset="utf-8" src="/static/editor/kindeditor.js"></script>
	<script charset="utf-8" src="/static/editor/lang/zh-CN.js"></script>
	<script type="text/javascript" src="/static/js/common/commonFunc.js"></script>
	<script type="text/javascript" src="/static/js/common/pageInfo.js"></script>
	<script type="text/javascript" src="/static/js/system/admin/CreateSurvey.js"></script>
</body>
</html>