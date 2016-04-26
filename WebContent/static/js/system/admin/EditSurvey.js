/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/
var editor1,editor2;

$(document).ready(function(){
	initedit();
	
	$("#savesurveybtn").on('click',function(){
		savesurvey();
	});
	
	$("#showsurveybtn").on('click',function(){
		showsurvey();
	});
	
	$("#backbtn").on('click',function(){
		back();
	});
	
	$("#closeshowpanel").on('click',function(){
		$("#showsurveypanel").hide();
		$("#tablepanel").show();
	});
});

KindEditor.ready(function(K) {
    editor1 = K.create('#survey_desc',{
		resizeType : 1,
		newlineTag : "br",
		pasteType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	});
	editor2 = K.create('#survey_text',{
		resizeType : 1,
		newlineTag : "br",
		pasteType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	});
});
 
function initedit(){
	var surveyname = $("#surveynamehide").val();
 	$.ajax({
 		type : "GET",
 		url : "/system/survey/editsurvey",
 		dataType : "JSON",
 		contentType : "application/json;charset=utf-8",
 		data : {
 			surveyname:surveyname},
 		async : true,
 		success : function(result) {
 			if(result.resultCode == 0){
 				if(result.data != null){
 					createform(result.data);
 					//alert(editor2.html());
 				}
 			}else{
 				alert(result.resultMessage);
 			}
 		}
 	});
}
 
function createform(data){
	$("#survey_name").val(data[0].survey_name);
	editor1.html(data[0].survey_desc);
	editor2.html(data[0].survey_text);
	$("#survey_anwser").val(data[0].survey_anwser);
	$("#start_time").val(data[0].start_time);
	$("#end_time").val(data[0].end_time);
}

function savesurvey(){
	var surveyname = $("#surveynamehide").val();
	editor1.sync();
	editor2.sync();
	var datasent = $("#surveyinfoForm").serializeObject();
	datasent['oldname'] = surveyname;
	params = JSON.stringify(datasent); 
	//console.info(params);
	//if(params.indexOf("\"\"")>-1){
	if(checkAvailable(params) != 0){
		alert("请检查信息是否完整");
	}else{
		$.ajax({
			type : "POST",
			url : "/system/survey/savesurvey",
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			data : params,
			async : false,
			success : function(data) {
				alert(data.resultMessage);
				if(data.resultCode == 0){
					back();
				}
			}
		});
	}
}

function showsurvey(){
	editor1.sync();
	editor2.sync();
	var datasent = $("#surveyinfoForm").serializeObject();
	params = JSON.stringify(datasent); 
	//if(params.indexOf("\"\"")>-1){
	if(checkAvailable(params) != 0){
		alert("请检查信息是否完整");
	}else{
		$.ajax({
			type : "POST",
			url : "/system/survey/showsurvey",
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			data : params,
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
}

function checkAvailable(params){
	var result = 0;
	JSON.parse(params, function (k, v) {
		if(k != "survey_anwser"){
			if(v == ""){
			 result = 1;
			}
		}
	});
	
	return result;
}
function back(){
	window.location.href="/system/survey";
}