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

function submitanswer(){
	var optionanswer = getanswer();
	var username = $("#user_name").val();
	var surveyname = $("#survey_name").val();
	$.ajax({
		type : "GET",
		url : "/system/startsurvey/submitanswer",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : {
			surveyname:surveyname,
			optionanswer:optionanswer,
			username:username},
		async : false,
		success : function(data) {
			if(data.resultCode == 0){
				//
			}else{
				alert(data.resultMessage);
			}
		}
	});
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