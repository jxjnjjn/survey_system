/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/
var jiathis_config;
var urlString = "http://www.jcai8.com:8080";

$(document).ready(function(){
	showsurvey();
	jiathis_config = { 
			url: urlString+"/system/register"
		}
});

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

$("#backbtn").on('click',function(){
	back();
});

$("#submitsurvey").on('click',function(){
	submitanswer();
});

$("#registersurvey").on('click',function(){
	register();
});

function nextradioname(index)
{
	var radios_name = "";
	index = index +1;
	radios_name = "optionsRadios_" + index;
	return radios_name;
}

function nextfillinblankname(index)
{
	var fillinblank_name = "";
	index = index +1;
	fillinblank_name = "fillinBlank_" + index;
	return fillinblank_name;
}

function getanswer(){
	var radiosexist = 0;
	var radios_name = "";
	var datasent = "";
	var result = 0;
	var index = 0;
	
	do{
		radios_name = nextradioname(index);
		radiosexist = $("input[name='"+radios_name+"']").length;
		if (radiosexist > 0){
			result = $("input[name='"+radios_name+"']:checked").val();

			if(result==undefined){
				datasent = datasent +"A";
			}else{
				result = String.fromCharCode(63+parseInt(result));
				datasent= datasent + result;
			}
		}
		index = index +1;
	}while(radiosexist > 0);
	
	return datasent;
}

function getfillinblankanswer(){
	var fillinblankexist = 0;
	var fillinblankname = "";
	var datasent = "";
	var result = 0;
	var index = 0;
	
	do{
		fillinblankname = nextfillinblankname(index);
		fillinblankexist = $("input[name='"+fillinblankname+"']").length;
		if (fillinblankexist > 0){
			result = $("input[name='"+fillinblankname+"']").val();
			if(result==undefined){
				//do nothing.
			}else{
				if(datasent.length == 0){
					datasent= result;
				}else{
					datasent= datasent +"||" +result;
				}
			}
		}
		
		index = index +1;
	}while(fillinblankexist > 0);
	
	//console.log(datasent);
	return datasent;
}

function submitanswer(){
	var checkresult = availablecheck();
	
	if(checkresult != 0){
		alert("请完整答题！");
		return;
	}
	
	var optionanswer = getanswer();
	var fillinblankanswer = getfillinblankanswer();
	var username = $("#user_name").val();
	var surveyname = $("#survey_name").val();
	var infos = $("#infos").val();
	var infomationdesc = $("#infomationdesc").val();
	$.ajax({
		type : "GET",
		url : "/system/startsurvey/submitanswer",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : {
			surveyname:surveyname,
			optionanswer:optionanswer,
			username:username,
			fillinblankanswer:fillinblankanswer},
		async : false,
		success : function(data) {
			if(data.resultCode == 0){
				alert("提交成功!");
				//back();
//				if(infos == 0){
//					alert(infomationdesc);
//					back();
//				}else{
//					alert("提交成功!");
//					if(infomationdesc.indexOf("http://") < 0){
//						infomationdesc = "http://" + infomationdesc;
//					}
//					top.location.href=infomationdesc;
//				}
				top.location.href ="/system/startsurvey/thanks?infos="+infos+"&thankstring="+infomationdesc;
			}else{
				alert(data.resultMessage);
			}
		}
	});
}

function availablecheck(){
	var availableresult = 0;
	var radiosexist = 0;
	var radios_name = "";
	var datasent = "";
	var result = 0;
	var index = 0;
	
	do{
		radios_name = nextradioname(index);
		radiosexist = $("input[name='"+radios_name+"']").length;
		if (radiosexist > 0){
			result = $("input[name='"+radios_name+"']:checked").val();

			if(result==undefined){
				availableresult = 1;
				return availableresult;
			}
		}
		index = index +1;
	}while(radiosexist > 0);
	
	return availableresult;
}

function showsurvey(){
	var username = $("#user_name").val();
	var surveyname = $("#survey_name").val();
	$.ajax({
		type : "GET",
		url : "/system/startsurvey/getsurveytext",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : {
			surveyname:surveyname},
		async : false,
		success : function(data) {
			if(data.resultCode == 0){
				$("#infos").val(data.data[0].infos);
				$("#infomationdesc").val(data.data[0].infomationdesc);
				$("#surveyname").html(data.data[0].survey_name);
				$("#surveydesc").html(data.data[0].survey_desc);
				$("#surveytext").html(createtext(data.data[0].survey_text));
				$("#showsurveypanel").show();
				$("#tablepanel").hide();
			}else{
				alert(data.resultMessage);
			}
		}
	});
}

function createtext(surveytext){
	var radio_name = "";
	var radio_num = 0;
	
	var fillinblank_name = "";
	var fillinblank_num = 0;
	
	var Stemp = "";
	if(null != surveytext){
		var questionsList = surveytext.split("<br />\r\n<br />\r\n");
		for(var question in questionsList){
			if("[单选题]" == questionsList[question].substring(0, questionsList[question].indexOf("]")+1)||
			   "[选择题]" == questionsList[question].substring(0, questionsList[question].indexOf("]")+1)||
			   "[选择]" == questionsList[question].substring(0, questionsList[question].indexOf("]")+1)||
			   "[单选]" == questionsList[question].substring(0, questionsList[question].indexOf("]")+1)
					){
				radio_num++;
				radio_name = "optionsRadios_"+radio_num;
				var ls = questionsList[question].split("<br />\r\n");
				Stemp += "<div class=\"form-group form-group-lg\">";
				Stemp += "<h4><strong>"+ls[1]+"</strong></h4>";
				Stemp += "<div class=\"btn-group-vertical\" data-toggle=\"buttons\">";
				for(var i=2;i<ls.length;i++){
					Stemp += "<label class=\"btn btn-default btn-lg\">";
					Stemp += "<input type=\"radio\" name=\""+ radio_name  +"\" value=\""+i+"\">";
					Stemp += ls[i];
					Stemp += " </label>";
				}
				Stemp += "</div>";
				Stemp += "</div>";
			}else if("[填空题]" == questionsList[question].substring(0, questionsList[question].indexOf("]")+1)||
					"[填空]" == questionsList[question].substring(0, questionsList[question].indexOf("]")+1)
					){
				var ls = questionsList[question].split("<br />\r\n");
				fillinblank_num++;
				fillinblank_name = "fillinBlank_"+fillinblank_num;
				Stemp += "<div class=\"form-group form-group-lg\">";
				Stemp += "<h4><strong>"+ls[1]+"</strong></h4>";
				Stemp += "<input type=\"text\" name=\""+ fillinblank_name  +"\" class=\"form-control input-lg\" placeholder=\"请输入\">";
				Stemp += "</div>";
			}else{
				Stemp += questionsList[question];
			}
		}
	}
	return Stemp;
}

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

function back(){
	window.location.href="/system/startsurvey";
}

function register(){
	top.location.href="/system/register";
}