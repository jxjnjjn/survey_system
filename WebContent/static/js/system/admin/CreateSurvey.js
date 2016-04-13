/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/
var editor1,editor2;

$("#savesurveybtn").on('click',function(){
	savesurvey();
});

$("#backbtn").on('click',function(){
	back();
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

function savesurvey(){
	var survey_name = $("#survey_name").val();
	editor1.sync();
	editor2.sync();
	var datasent = $("#surveyinfoForm").serializeObject();
	datasent['oldname'] = survey_name;
	params = JSON.stringify(datasent); 
	if(params.indexOf("\"\"")>-1){
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

function back(){
	window.location.href="/system/survey";
}