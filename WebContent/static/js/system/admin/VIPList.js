/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/
getData(1);

function getData(pageNo){
	$.ajax({
		type : "GET",
		url : "/system/vip/getlist",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			pageNo:1},
		async : true,
		success : function(result) {
			if(result.resultCode == 0){
				if(result.data != null){
					creatTableHtml(result.data);
					//creatpage(result.);//分页
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
	var th = "<th>用户名</th>";
		th = th + "<th>注册时间</th>";
		th = th + "<th>注册IP</th>";
		th = th + "<th>注册来源</th>";
		th = th + "<th>手机所属地区</th>";
		th = th + "<th>IP所属地区</th>";
		th = th + "<th>登陆次数</th>";
		th = th + "<th>答题次数</th>";
		th = th + "<th>好友数</th>";
		
	var tr = "<tr>"+th+"</tr>";
	var thead = "<thead>"+ tr +"</thead>";
	return thead;
}

function creatTablebody(tableInfo){
	var tr =  "";
	for(var i in tableInfo){
		var td = "<th>"+tableInfo[i].user_name+"</th>";
		td = td + "<td>"+formatdate(tableInfo[i].register_date,"yyyy-MM-dd hh:mm:ss")+"</td>";
		td = td + "<td>"+tableInfo[i].register_ip+"</td>";
		td = td + "<td>"+tableInfo[i].register_source+"</td>";
		td = td + "<td>"+tableInfo[i].cellphone_zone+"</td>";
		td = td + "<td>"+tableInfo[i].ip_zone+"</td>";
		td = td + "<td>"+tableInfo[i].login_num+"</td>";
		td = td + "<td>"+tableInfo[i].test_num+"</td>";
		td = td + "<td>"+tableInfo[i].friends+"</td>";
		tr = tr + "<tr>"+td+"</tr>";
	}
	
	var tbody = "<tbody>"+ tr +"</tbody>";
	return tbody;
}