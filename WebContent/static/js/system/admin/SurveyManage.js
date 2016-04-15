/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/
getData(1);

$("#createsurveybtn").on('click',function(){
	createsurvey();
});

function createsurvey(){
	window.location.href="/system/survey/createsurvey";
}

function getData(pageNo){
	$.ajax({
		type : "GET",
		url : "/system/survey/getlist",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			status:0,
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
	var html = "<div class=\"table-responsive\"><table id=\"detailtable\" class=\"table table-bordered\">" + theadhtml + tbodyhtml + "</table></div>";
	$("#datatable").html(html);
}

function creatTablehead(){
	var th = "<th>问卷名</th>";
		th = th + "<th>开始时间</th>";
		th = th + "<th>结束时间</th>";
		th = th + "<th>答题人数</th>";
		th = th + "<th>问卷状态</th>";
		th = th + "<th>操作</th>";
		
	var tr = "<tr>"+th+"</tr>";
	var thead = "<thead>"+ tr +"</thead>";
	return thead;
}

function creatTablebody(tableInfo){
	var tr =  "";
	for(var i in tableInfo){
		var td = "<th>"+tableInfo[i].survey_name+"</th>";
		td = td + "<td>"+formatdate(tableInfo[i].start_time,"yyyy-MM-dd hh:mm:ss")+"</td>";
		td = td + "<td>"+formatdate(tableInfo[i].end_time,"yyyy-MM-dd hh:mm:ss")+"</td>";
		td = td + "<td>"+tableInfo[i].num+"</td>";
		td = td + "<td>"+getstatus(tableInfo[i].status)+"</td>";
		td = td + "<td class=\"text-center\">";
		td = td + "<button type=\"button\" class=\"btn btn-link btn-sm\" style=\"margin-right: 5px;margin-left: 5px;\" onclick=\"editsurvey('"+tableInfo[i].survey_name+"')\">编辑</button>";
		td = td + "<button type=\"button\" class=\"btn btn-link btn-sm\" style=\"margin-right: 5px;margin-left: 5px;\" onclick=\"updatestatus('"+tableInfo[i].survey_name+"','"+tableInfo[i].status+"')\">"+getbtn(tableInfo[i].status)+"</button>";
		td = td + "<button type=\"button\" class=\"btn btn-link btn-sm\" style=\"margin-right: 5px;margin-left: 5px;\" onclick=\"delsurvey('"+tableInfo[i].survey_name+"')\">删除</button>";
		td = td + "<button type=\"button\" class=\"btn btn-link btn-sm\" style=\"margin-right: 5px;margin-left: 5px;\" onclick=\"delfriend('"+tableInfo[i].survey_name+"')\">问卷分析</button>";
		td = td + "</td>";
		
		tr = tr + "<tr>"+td+"</tr>";
	}
	
	var tbody = "<tbody>"+ tr +"</tbody>";
	return tbody;
}

function editsurvey(surveyname){
	window.location.href="/system/survey/editsurveyview?surveyname="+surveyname;
}

function updatestatus(surveyname,status){
	$.ajax({
		type : "GET",
		url : "/system/survey/updatestatus",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			surveyname:surveyname,
			status:status},
		async : true,
		success : function(result) {
			if(result.resultCode == 0){
				alert(result.resultMessage);
				getData(1);
			}else{
				alert(result.resultMessage);
			}
		}
	});
}

function delsurvey(surveyname){
	$.ajax({
		type : "GET",
		url : "/system/survey/deletesurvey",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			surveyname:surveyname},
		async : true,
		success : function(result) {
			if(result.resultCode == 0){
				alert(result.resultMessage);
				getData(1);
			}else{
				alert(result.resultMessage);
			}
		}
	});
}

function getstatus(status){
	var temp="未知";
	if(status == "1"){
		temp="<span style=\"color:#5cb85c;\">已发布</span>";
	}else if(status == "2"){
		temp="<span>未发布</span>";
	}else if(status == "3"){
		temp="<span style=\"color:#d9534f;\">已暂停</span>";
	}
	return temp;
}

function getbtn(status){
	var temp="";
	if(status == "1"){
		temp="<span>暂停</span>";
	}else{
		temp="<span>发布</span>";
	}
	return temp;
}