/*=======================================*/
/*	index-js                            */
/*	@author noviachan                    */
/*=======================================*/
$(document).ready(function(){
	$("#submitbtn").on('click',function(){
		//console.log("submit");
		changePassword();
	}); 
});

function changePassword(){
	var datasent = $("#changepass").serializeObject();
	params = JSON.stringify(datasent); 
	console.log(params);
	$.ajax({
	type : "POST",
	url : "/system/changepass",
	dataType : "json",
	contentType : "application/json;charset=utf-8",
	data : params,
	async : true,
	success : function(data) {
		alert(data.resultMessage);
		if(data.resultCode == 0){
			logout();
		}
	}
	});
}
//////////////////////////////////////////////////////////////////