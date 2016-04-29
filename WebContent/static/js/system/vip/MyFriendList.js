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
					creatpanelgroup(result.data);
					creatpage(result.pageInfo);//分页
				}
			}else{
				alert(result.resultMessage);
			}
		}
	});
}

function creatpanelgroup(tableInfo){
	var info = createInfo(tableInfo)
	console.log(JSON.stringify(info));
	var tbodypanel = creatpanel(info);
	var html = "<div class=\"panel-group\" id=\"accordion\" role=\"tablist\" aria-multiselectable=\"true\">" + tbodypanel + "</div>";
	$("#datatable").html(html);
}

function createInfo(data){
	var temp = []; 
	var usernametemp = "";
	for(var i in data){
		if(usernametemp != data[i].user_name){
			var d = {};
			d["user_name"] = data[i].user_name;
			usernametemp = d["user_name"];
			d["data"] = [];
			for(var j in data){
				if(d["user_name"] == data[j].user_name){
					var survey = {
						survey_name:data[j].survey_name,
						survey_answer:data[j].survey_answer,
						user_answer:data[j].user_answer,
						correct_rate:data[j].correct_rate,
					};
					d["data"].push(survey);
				}
			}
			temp.push(d);
		}
	}
	return temp;
}

function creatpanel(tableInfo){
	var tpanel = "";
	for(var i in tableInfo){
		tpanel += "<div class=\"panel panel-default\">";
		tpanel += "<div class=\"panel-heading\" role=\"tab\" id=\"heading"+i+"\">";
		tpanel += "<div class=\"row\"><div class=\"col-md-9\"><h4 class=\"panel-title\">";
		tpanel += "<a role=\"button\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse"+i+"\" aria-expanded=\"false\" aria-controls=\"collapse"+i+"\">";
		tpanel += "<strong>"+tableInfo[i].user_name+"</strong>";
		tpanel += "</a></h4></div><div class=\"col-md-3\"><button type=\"button\" class=\"btn btn-link pull-right\" onclick=\"delfriend('"+tableInfo[i].user_name+"')\">删除</button></div></div></div>";
		tpanel += "<div id=\"collapse"+i+"\" class=\"panel-collapse collapse\" role=\"tabpanel\" aria-labelledby=\"heading"+i+"\">";
		tpanel += "<div class=\"panel-body\" style=\"padding: 5px;\">";
		tpanel += creatTableHtml(tableInfo[i].data);
		tpanel += "</div></div></div>";
	}
	return tpanel;
}

function creatTableHtml(tableInfo){
	var theadhtml = creatTablehead();
	var tbodyhtml = creatTablebody(tableInfo);
	var html = "<div class=\"table-responsive\"><table id=\"detailtable\" class=\"table table-bordered\">" + theadhtml + tbodyhtml + "</table></div>";
	return html;
}

function creatTablehead(){
	var th = "<th>好友近几期问卷名</th>";
		th = th + "<th>正确答案</th>";
		th = th + "<th>用户答案</th>";
		th = th + "<th>正确率</th>";
		
	var tr = "<tr>"+th+"</tr>";
	var thead = "<thead>"+ tr +"</thead>";
	return thead;
}

function creatTablebody(tableInfo){
	var tr =  "";
	for(var i in tableInfo){
		var td = "<th>"+checkname(tableInfo[i].survey_name)+"</th>";
		td = td + "<td>"+checkname(tableInfo[i].survey_answer)+"</td>";
		td = td + "<td>"+checkname(tableInfo[i].user_answer)+"</td>";
		td = td + "<td>"+checkname(tableInfo[i].correct_rate)+"</td>";

		tr = tr + "<tr>"+td+"</tr>";
	}
	
	var tbody = "<tbody>"+ tr +"</tbody>";
	return tbody;
}

function checkname(name){
	if(name == null || name == "" || name == "N/A"){
		return "暂无";
	}
	return name;
}

function addfriend(friendname){
	var myreg = /^(((13)|(15)|(18))+\d{9})$/; 
	var availableCheck = myreg.test(friendname);
	if(!availableCheck)
	{
		alert("请正确输入手机号！");
		return;
	}
	
	if(friendname != ""){
		var username = $("#user_name").val();
		$.ajax({
			type : "GET",
			url : "/system/myfriend/friendcheck",
			dataType : "JSON",
			contentType : "application/json;charset=utf-8",
			data : {
				username:username,
				friendname:friendname},
			async : true,
			success : function(result) {
				if(result.resultCode == 0){
					if(result.data != null){
						var friensNum = result.data[0].friend_num;
						var friensExist = result.data[0].friend_exist;
						if(friensNum < 5){
							if (friensExist != 0){
								alert("该用户已注册或者已在好友列表中。");
							}else{
								addfriendIn(friendname);
							}
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