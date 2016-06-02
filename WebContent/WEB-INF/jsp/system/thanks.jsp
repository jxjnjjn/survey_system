<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="感谢页面">
	<meta name="keywords" content="感谢页面" />
	<meta name="author" content="noviachan">
	<link rel="shortcut icon" href="/static/img/survey-icon.jpg">
	<title>感谢页面</title>
	<!-- Bootstrap core CSS -->
	<link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
	<!-- 我们自己的css样式文件放在这里 -->
	<link href="/static/css/system/dashboard.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<input type="hidden" name="thankstring" id="thankstring" value="${thankstring}">
		<input type="hidden" name="infos" id="infos" value="${infos}">
		<input type="hidden" name="user_name" id="user_name" value="${username}">
		<input type="hidden" name="survey_name" id="survey_name" value="${surveyname}">
		<div id="listDiv" class="listDiv">
			<div id="showsurveypanel" class="panel panel-new">
				<div class="panel-body">
					<div style="text-align: center;padding: 100px;">
						<img src="/static/img/100.jpg" class="img-rounded" style="width:400px;height:200px;">
						<h4 id="thankstitle"  class="text-muted">${thankstring}</h4>
					</div>
					<!-- JiaThis Button BEGIN -->
					<div  class="modal-footer">
						<div class="jiathis_style_32x32" style="margin-left:400px">
							<a class="jiathis_button_tsina"></a>
							<a class="jiathis_button_tieba"></a>
							<a class="jiathis_button_qzone"></a>
							<a class="jiathis_button_cqq"></a>
							<a class="jiathis_button_douban"></a>
							<a class="jiathis_button_weixin"></a>
						</div>
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
	<script type="text/javascript">
		var infos = $("#infos").val();
		var thankstring = $("#thankstring").val();
		if(infos == 0){
			//alert(infomationdesc);
			//back();
			//setTimeout("top.location.href = '" + "/system" + "'",3000);
		}else{
			//alert("提交成功!");
			$("#thankstitle").html("感谢您的参与！");
			if(thankstring.indexOf("http://") < 0){
				thankstring = "http://" + thankstring;
			}
			//setTimeout("top.location.href = '" + thankstring + "'",3000);
		}
		
		var jiathis_config= { 
				url: urlString+"/system/register"
			}
		var urlString = "http://www.jcai8.com:8080";

		$(".jiathis_button_tsina").on('click',function(){
			sharing(1);
			jiathis_config = { 
					url: urlString+"/system/register?source=1"
				}
		});

		$(".jiathis_button_tieba").on('click',function(){
			sharing(2);
			jiathis_config = { 
					url: urlString+"/system/register?source=2"
				}
		});

		$(".jiathis_button_qzone").on('click',function(){
			sharing(3); 
			jiathis_config = { 
					url: urlString+"/system/register?source=3"
				}
		});

		$(".jiathis_button_cqq").on('click',function(){
			sharing(4); 
			jiathis_config = { 
					url: urlString+"/system/register?source=4"
				}
		});

		$(".jiathis_button_douban").on('click',function(){
			sharing(5); 
			jiathis_config = { 
					url: urlString+"/system/register?source=5"
				}
		});

		$(".jiathis_button_weixin").on('click',function(){
			sharing(6);
			jiathis_config = { 
					url: urlString+"/system/register?source=6"
				} 
		});
		
		function sharing(num){
			var username = $("#user_name").val();
			var surveyname = $("#survey_name").val();
			$.ajax({
				type : "GET",
				url : "/system/startsurvey/sharing",
				dataType : "json",
				contentType : "application/json;charset=utf-8",
				data : {
					surveyname:surveyname,
					username:username,
					num:num},
				async : true,
				success : function(data) {
					if(data.resultCode == 0){
						//alert(data.resultMessage);
					}else{
						alert(data.resultMessage);
					}
				}
			});
		}
	</script>
</body>
</html>

