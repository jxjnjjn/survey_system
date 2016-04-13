/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/
var editor1,editor2;

$(document).ready(function(){
	initedit();
	
	$("#savesurveybtn").on('click',function(){
		savesurvey();
	});
	
	$("#backbtn").on('click',function(){
		back();
	});
});

KindEditor.ready(function(K) {
    editor1 = K.create('#survey_desc',{
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	});
	editor2 = K.create('#survey_text',{
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	});
});
 
function initedit(){
	var surveyname = $("#surveyname").val();
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
	var surveyname = $("#surveyname").val();
	editor1.sync();
	editor2.sync();
	var datasent = $("#surveyinfoForm").serializeObject();
	datasent['oldname'] = surveyname;
	params = JSON.stringify(datasent); 
	if(params.indexOf("\"\"")>-1){
		alert("请检查信息是否完整");
	}else{
		console.log("before ajax");
		$.ajax({
			type : "POST",
			url : "/system/survey/savesurvey",
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			data : params,
			async : false,
			success : function(data) {
				console.log("after ajax");
				alert(data.resultMessage);
				if(data.resultCode == 0){
					back();
				}
			}
		});
	}
}

function back(){
	console.log("before window.location set");
	window.location.href="/system/survey";
	console.log("after window.location set");
}