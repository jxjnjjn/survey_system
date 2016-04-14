/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/
showsurvey();

$("#backbtn").on('click',function(){
	back();
});

function showsurvey(){
	var username = $("#user_name").val();
	var surveyname = $("#survey_name").val();
	console.log(surveyname);
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