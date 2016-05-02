<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="description" content="会员注册">
	<meta name="author" content="noviachan">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" href="/static/img/survey-icon.jpg">
	<title>会员注册</title>
	<!-- Bootstrap core CSS -->
	<link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
	<!-- 我们自己的css样式文件放在这里 -->
	<link href="/static/css/system/dashboard.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<h1 id="thankstitle">${thankstring}</h1>
		<input type="hidden" name="thankstring" id="thankstring" value="${thankstring}">
		<input type="hidden" name="infos" id="infos" value="${infos}">
	</div>
	<!-- Bootstrap core JavaScript================================================== -->
	<!-- 请首先引用jquery，再引用其他js文件 -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="/static/js/common/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="/static/js/common/bootstrap.min.js"></script>
	<script type="text/javascript">
		var infos = $("#infos").val();
		var thankstring = $("#thankstring").val();
		if(infos == 0){
			//alert(infomationdesc);
			//back();
		}else{
			//alert("提交成功!");
			$("#thankstitle").html("感谢您的参与！");
			if(thankstring.indexOf("http://") < 0){
				thankstring = "http://" + thankstring;
			}
			setTimeout("top.location.href = '" + thankstring + "'",3000);
		}
	</script>
</body>
</html>

