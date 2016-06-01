<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="问卷编辑">
	<meta name="keywords" content="问卷编辑" />
	<meta name="author" content="noviachan">
	<link rel="shortcut icon" href="/static/img/survey-icon.jpg">
	<title>问卷编辑</title>
	<!-- Bootstrap core CSS -->
	<link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
	<!-- 我们自己的css样式文件放在这里 -->
	<link href="/static/css/system/dashboard.css" rel="stylesheet">
	<link href="/static/css/common/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body>
	<div id="contentDiv" class="container-fluid">
		<button type="button" class="btn btn-default" id="backbtn">返回</button>
		<div id="listDiv" class="listDiv">
			<div id="showsurveypanel" class="panel   panel-new" style="display: none;">
				<div class="panel-heading panel-heading-new">
					<div class="row">
						<div class="col-xs-10 col-md-5">
							<h4 style="margin-top: 0px; margin-bottom: 0px;">
								<strong>问卷编辑</strong>
							</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<form id="showsurveyform">
						<h2 class="text-center"><strong id="surveyname">问卷题目</strong><br/><small id="surveydesc">问卷描述</small></h2>
						<div id="surveytext">
						</div>
						<div class="form-group">
							<div class="text-center">
								<button type="button" class="btn btn-default" id="closeshowpanel">关闭</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div id="tablepanel" class="panel   panel-new">
				<div class="panel-heading panel-heading-new">
					<div class="row">
						<div class="col-xs-10 col-md-5">
							<h4 style="margin-top: 0px; margin-bottom: 0px;">
								<strong>问卷设计</strong>
							</h4>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<form id="surveyinfoForm" class="form-horizontal">
						<input type="hidden" id="surveynamehide" value="${surveyname}"/>
						<div class="form-group">
							<label for="survey_name" class="col-sm-2 control-label">问卷标题</label>
							<div class="col-sm-10">
								<textarea id="survey_name" name="survey_name" class="form-control" rows="5"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="survey_desc" class="col-sm-2 control-label">问卷描述</label>
							<div class="col-sm-10">
								<textarea id="survey_desc" name="survey_desc" class="form-control" rows="5"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="survey_text" class="col-sm-2 control-label">问卷内容</label>
							<div class="col-sm-10">
								<textarea id="survey_text" name="survey_text" class="form-control" rows="20"></textarea>
								<span id="helpBlock" class="help-block">A block of help text that breaks onto a new line and may extend beyond one line.</span>
							</div>
						</div>
						<div class="form-group">
							<label for="survey_anwser" class="col-sm-2 control-label">问卷答案</label>
							<div class="col-sm-10">
								<textarea id="survey_anwser" name="survey_anwser" class="form-control" rows="5"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="start_time" class="col-sm-2 control-label">问卷开始时间</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" placeholder="开始时间" name="start_time" id="start_time" data-date-format="yyyy-mm-dd hh:ii:ss"/>
							</div>
						</div>
						<div class="form-group">
							<label for="end_time" class="col-sm-2 control-label">问卷结束时间</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" placeholder="结束时间" name="end_time" id="end_time" data-date-format="yyyy-mm-dd hh:ii:ss"/>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">提交后的操作</label>
							<div class="col-sm-10">
								<label class="radio-inline">
									<input type="radio" name="infos" id="inlineRadio1" value=0>显示感谢信息
								</label>
								<label class="radio-inline">
									<input type="radio" name="infos" id="inlineRadio2" value=1>跳转到
								</label>
								<textarea id="infomationdesc" name="infomationdesc" class="form-control" rows="2"></textarea>
							</div>
							
						</div>
						
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="button" class="btn btn-default" id="savesurveybtn">保存问卷</button>
								<button type="button" class="btn btn-info" id="showsurveybtn">预览问卷</button>
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
	<script type="text/javascript" src="/static/js/common/bootstrap-datetimepicker.min.js"></script>
	<script charset="utf-8" src="/static/editor/kindeditor.js"></script>
	<script charset="utf-8" src="/static/editor/lang/zh-CN.js"></script>
	<script type="text/javascript" src="/static/js/common/commonFunc.js"></script>
	<script type="text/javascript" src="/static/js/common/pageInfo.js"></script>
	<script type="text/javascript" src="/static/js/common/time.js"></script>
	<script type="text/javascript" src="/static/js/system/admin/EditSurvey.js"></script>
</body>
</html>