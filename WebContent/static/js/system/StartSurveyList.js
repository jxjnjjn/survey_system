/*=======================================*/
/*	开始答题js                            */
/*	@author noviachan                    */
/*=======================================*/
getData(1);

function getData(pageNo){
	$.ajax({
		type : "GET",
		url : "/system/startsurvey/getlist",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			status:1,
			pageNo:pageNo},
		async : true,
		success : function(result) {
			if(result.resultCode == 0){
				if(result.data != null){
					creatListgroupHtml(result.data);
					creatpage(result.pageInfo);//分页
				}
			}else{
				alert(result.resultMessage);
			}
		}
		});
}

function creatListgroupHtml(tableInfo){
	var html = "";
	for(var i in tableInfo){
		html += "<a href=\"#\" class=\"list-group-item\">";
		html += "<h2 class=\"list-group-item-heading\" style=\"text-align: center;\"><strong>"+tableInfo[i].survey_name+"</strong>  <small>"+tableInfo[i].survey_desc+"</small></h2>";
		html += "<div class=\"row\">";
		html += "<p class=\"list-group-item-text col-md-4\"><strong>开始时间：</strong>"+formatdate(tableInfo[i].start_time,"yyyy-MM-dd hh:mm:ss")+"</p>";
		html += "<p class=\"list-group-item-text col-md-4\"><strong>结束时间：</strong>"+formatdate(tableInfo[i].end_time,"yyyy-MM-dd hh:mm:ss")+"</p>";
		html += "<p class=\"list-group-item-text col-md-4\"><strong>答题人数：</strong>"+tableInfo[i].num+"人</p>";
		html += "</div>";
		html += "</a>";
	}
	$("#datatable").html(html);
}