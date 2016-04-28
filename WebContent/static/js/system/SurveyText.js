/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/
$(document).ready(function(){
	showsurvey();
});

$("#backbtn").on('click',function(){
	back();
});

$("#submitsurvey").on('click',function(){
	submitanswer();
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
				result = String.fromCharCode(64+parseInt(result));
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
				//alert("提交成功!");
				//back();
				if(infos == 0){
					alert(infomationdesc);
				}else{
					alert("提交成功!");
					top.location.href=infomationdesc;
				}
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
				$("#surveytext").html(data.data[0].survey_text);
				$("#showsurveypanel").show();
				$("#tablepanel").hide();
			}else{
				alert(data.resultMessage);
			}
		}
	});
}

function back(){
	window.location.href="/system/startsurvey";
}