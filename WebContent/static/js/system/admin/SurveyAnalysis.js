/*=======================================*/
/*	开始答题js                            */
/*	@author noviachan                    */
/*=======================================*/

$(document).ready(function(){
	$("#backbtn").on('click',function(){
		back();
	});
	surveyanalysis($("#surveynamehide").val());
});

function back(){
	window.location.href="/system/survey";
}

function surveyanalysis(surveyname){
	$.ajax({
		type : "GET",
		url : "/system/surveyanalysis/getlist",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			surveyname:surveyname},
		async : true,
		success : function(result) {
			if(result.resultCode == 0){
				if(result.data != null){
					creatTableHtml(result.data);
				}
			}else{
				//alert(result.resultMessage);
			}
		}
	});
}

function creatTableHtml(tableInfo){
	var username = $("#user_name").val();
	var html = "";
	for(var i in tableInfo){
		html += "<br><div> <font size=\"5\">";
		html += "<p><strong>1，参与的VIP人数： </strong>"+tableInfo[i].vip_num+"</p><br>";

		html += "<p><strong>2，新会员比率： </strong>"+tableInfo[i].new_vip_rate*100+" %</p><br>";

		html += "<p><strong>3，转发比率： </strong>"+tableInfo[i].transfer_rate+"</p><br>";
		html += "</div>";
	}
	$("#datatable").html(html);
}