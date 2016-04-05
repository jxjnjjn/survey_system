/*=======================================*/
/*	综合指标js                            */
/*	@author noviachan                    */
/*=======================================*/
getData();

function getData(){
	$.ajax({
		type : "GET",
		url : "/system/survey/getlist",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			status:0},
		async : true,
		success : function(result) {
			creatListgroupHtml(result);
		}
		});
}

function creatListgroupHtml(tableInfo){
	var html = "";
	for(var i in tableInfo){
		html += "<a href=\"#\" class=\"list-group-item\">";
		html += "<h2 class=\"list-group-item-heading\" style=\"text-align: center;\"><strong>"+tableInfo[i].survey_name+"</strong></h2>";
		html += "<div class=\"row\">";
		html += "<p class=\"list-group-item-text col-md-4\"><strong>开始时间：</strong>"+formatdate(tableInfo[i].start_time,"yyyy-MM-dd hh:mm:ss")+"</p>";
		html += "<p class=\"list-group-item-text col-md-4\"><strong>结束时间：</strong>"+formatdate(tableInfo[i].end_time,"yyyy-MM-dd hh:mm:ss")+"</p>";
		html += "<p class=\"list-group-item-text col-md-4\"><strong>答题人数：</strong>"+tableInfo[i].num+"人</p>";
		html += "</div>";
		html += "</a>";
	}
	$("#datatable").html(html);
}