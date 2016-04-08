/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/
 KindEditor.ready(function(K) {
//	                window.editor = K.create('#survey_content',{
//						resizeType : 1,
//						allowPreviewEmoticons : false,
//						allowImageUpload : false,
//						items : [
//							'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
//							'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
//							'insertunorderedlist', '|', 'emoticons', 'image', 'link']
//					});
	                window.editor = K.create('#survey_name',{
						resizeType : 1,
						allowPreviewEmoticons : false,
						allowImageUpload : false,
						items : [
							'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
							'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
							'insertunorderedlist', '|', 'emoticons', 'image', 'link']
					});
	                window.editor = K.create('#survey_desc',{
						resizeType : 1,
						allowPreviewEmoticons : false,
						allowImageUpload : false,
						items : [
							'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
							'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
							'insertunorderedlist', '|', 'emoticons', 'image', 'link']
					});
	        });
 

$("#savesurveybtn").on('click',function(){
	savesurvey();
});

function savesurvey(){
	var datasent = $("#surveyinfoForm").serializeObject();
	params = JSON.stringify(datasent); 
	console.log(params);
	$.ajax({
		type : "POST",
		url : "/system/survey/savesurvey",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : params,
		async : false,
		success : function(data) {
			if(data.resultCode == 0){
				alert(data.resultMessage);
			}else{
				alert(data.resultMessage);
			}
		}
	});
}

$("#backbtn").on('click',function(){
	back();
});

function back(){
	window.location.href="/system/survey";
}