/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/
getData(1);

$("#addfriendbtn").on('click',function(){
	var friendname = $("#friend_name").val();
	addfriend(friendname);
});

function getData(pageNo){
	var username = $("#user_name").val();
	$.ajax({
		type : "GET",
		url : "/system/myfriend/getlist",
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
	var html = "<div class=\"table-responsive\"><table id=\"detailtable\" class=\"table table-bordered\">" + theadhtml + tbodyhtml + "</table></div>";
	$("#datatable").html(html);
}

function creatTablehead(){
	var th = "<th>好友名称</th>";
		th = th + "<th>近几期问卷</th>";
		th = th + "<th>操作</th>";
		
	var tr = "<tr>"+th+"</tr>";
	var thead = "<thead>"+ tr +"</thead>";
	return thead;
}

function creatTablebody(tableInfo){
	var tr =  "";
	for(var i in tableInfo){
		var td = "<th>"+tableInfo[i].user_name+"</th>";
		td = td + "<td>"+checkname(tableInfo[i].survey_name)+"</td>";
		td = td + "<td class=\"text-center\">";
		td = td + "<button type=\"button\" class=\"btn btn-danger btn-sm\" style=\"margin-right: 5px;margin-left: 5px;\" onclick=\"delfriend('"+tableInfo[i].user_name+"')\">删除</button>"
		td = td + "</td>";

		tr = tr + "<tr>"+td+"</tr>";
	}
	
	var tbody = "<tbody>"+ tr +"</tbody>";
	return tbody;
}

function checkname(name){
	if(name == null || name == ""){
		return "无";
	}
	return name;
}

function addfriend(friendname){
	if(friendname != ""){
		var username = $("#user_name").val();
		$.ajax({
			type : "GET",
			url : "/system/myfriend/friendcheck",
			dataType : "JSON",
			contentType : "application/json;charset=utf-8",
			data : {
				username:username},
			async : true,
			success : function(result) {
				if(result.resultCode == 0){
					if(result.data != null){
						//result.data[0].friend_num
						//console.error("result.data[0].friend_num");
						var friensNum = result.data[0].friend_num;
						if(friensNum < 5){
							addfriendIn(friendname);
						}else{
							alert("好友数已满！");
						}

				}else{
					alert(result.resultMessage);
				}
				}
			}
			});
	}else{
		alert("请输入好友手机号！");
	}
}

function addfriendIn(friendname){
	var username = $("#user_name").val();
	$.ajax({
		type : "GET",
		url : "/system/myfriend/addfriend",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			username:username,
			friendname:friendname},
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

function delfriend(friendname){
	var username = $("#user_name").val();
	$.ajax({
		type : "GET",
		url : "/system/myfriend/delfriend",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			username:username,
			friendname:friendname},
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