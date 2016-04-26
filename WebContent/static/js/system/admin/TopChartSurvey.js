/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/

$(document).ready(function(){
	$("#backbtn").on('click',function(){
		back();
	});
	
	topchart($("#surveynamehide").val());
});

function back(){
	window.location.href="/system/survey";
}

function topchart(name){
	$.ajax({
		type : "GET",
		url : "/system/surveyranklist/getlist",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			surveyname:name,
			pageNo:1},
		async : true,
		success : function(result) {
			if(result.resultCode == 0){
				if(result.data != null){
					creatTableHtml(result.data);
					creatpage(result.pageInfo);//分页
				}
			}else{
				//alert(result.resultMessage);
			}
		}
	});
}

function creatTableHtml(tableInfo){
	var theadhtml = creatTablehead();
	var tbodyhtml = creatTablebody(tableInfo);
	var html = "<div class=\"table-responsive\"><table id=\"detailtable\" class=\"table table-bordered\">" + theadhtml + tbodyhtml + "</table></div>";
	$("#datatable").html(html);
}

function creatTablehead(){
	var th = "<th>用户名</th>";
		th = th + "<th>注册时间</th>";
		th = th + "<th>答题时间</th>";
		th = th + "<th>用户答案</th>";
		th = th + "<th>正确率</th>";
		
	var tr = "<tr>"+th+"</tr>";
	var thead = "<thead>"+ tr +"</thead>";
	return thead;
}

function creatTablebody(tableInfo){
	var tr =  "";
	for(var i in tableInfo){
		var td = "<th>"+tableInfo[i].user_name+"</th>";
		td = td + "<td>"+formatdate(tableInfo[i].register_date,"yyyy-MM-dd hh:mm:ss")+"</td>";
		td = td + "<td>"+formatdate(tableInfo[i].answer_date,"yyyy-MM-dd hh:mm:ss")+"</td>";
		td = td + "<td>"+tableInfo[i].answer+"</td>";
		td = td + "<td>"+tableInfo[i].correct_rate+"</td>";
		td = td + "</td>";
		
		tr = tr + "<tr>"+td+"</tr>";
	}
	
	var tbody = "<tbody>"+ tr +"</tbody>";
	return tbody;
}

