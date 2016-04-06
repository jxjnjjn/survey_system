/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/
getData();

function getData(){
	var username = $("#user_name").val();
	$.ajax({
		type : "GET",
		url : "/system/myfriend/getlist",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			user_name:username},
		async : true,
		success : function(result) {
			creatTableHtml(result);
			//creatpage(result);//分页
		}
		});
}


function creatTableHtml(tableInfo){
	var theadhtml = creatTablehead();
	var tbodyhtml = creatTablebody(tableInfo);
	var html = "<div class=\"table-responsive\"><table id=\"detailtable\" class=\"table\">" + theadhtml + tbodyhtml + "</table></div>";
	$("#datatable").html(html);
}

function creatTablehead(){
	var th = "<th>好友名称</th>";
		th = th + "<th>问卷名称</th>";
		
	var tr = "<tr>"+th+"</tr>";
	var thead = "<thead>"+ tr +"</thead>";
	return thead;
}

function creatTablebody(tableInfo){
	var tr =  "";
	for(var i in tableInfo){
		var td = "<th>"+tableInfo[i].user_name+"</th>";
		td = td + "<td>"+tableInfo[i].survey_name+"</td>";

		tr = tr + "<tr>"+td+"</tr>";
	}
	
	var tbody = "<tbody>"+ tr +"</tbody>";
	return tbody;
}