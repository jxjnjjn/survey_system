/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/
getData(1);

function getData(pageNo){
	var username = $("#user_name").val();
	$.ajax({
		type : "GET",
		url : "/system/mysurvey/getlist",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			user_name:username,
			pageNo:pageNo},
		async : true,
		success : function(result) {
			if(result.resultCode == 0){
				if(result.data != null){
					creatTableHtml(result.data);
					creatpage(result.pageInfo);//分页
				}
			}else{
				alert(result.resultMessage);
			}
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
	var th = "<th>问卷名称</th>";
		th = th + "<th>答题日期</th>";
		th = th + "<th>用户答案</th>";
		th = th + "<th>正确答案</th>";
		th = th + "<th>正确率</th>";
		
	var tr = "<tr>"+th+"</tr>";
	var thead = "<thead>"+ tr +"</thead>";
	return thead;
}

function creatTablebody(tableInfo){
	var tr =  "";
	for(var i in tableInfo){
		var td = "<th>"+tableInfo[i].survey_name+"</th>";
		td = td + "<td>"+formatdate(tableInfo[i].answer_date,"yyyy-MM-dd hh:mm:ss")+"</td>";
		td = td + "<td>"+tableInfo[i].answer+"</td>";
		td = td + "<td>"+tableInfo[i].survey_anwser+"</td>";
		td = td + "<td>"+tableInfo[i].correct_rate+"</td>";

		tr = tr + "<tr>"+td+"</tr>";
	}
	
	var tbody = "<tbody>"+ tr +"</tbody>";
	return tbody;
}