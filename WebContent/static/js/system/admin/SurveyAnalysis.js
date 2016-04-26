/*=======================================*/
/*	开始答题js                            */
/*	@author noviachan                    */
/*=======================================*/

$(document).ready(function(){
	$("#backbtn").on('click',function(){
		back();
	});
	surveyanalysis($("#surveynamehide").val());
	cellphonezone($("#surveynamehide").val());
});

function back(){
	window.location.href="/system/survey";
}

function cellphonezone(surveyname){
	$.ajax({
		type : "GET",
		url : "/system/surveyanalysis/getlistphone",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			surveyname:surveyname
		},
		async : true,
		success : function(result) {
			if(result.resultCode == 0){
				if(result.data != null){
					var datatemp = creatnum(result.data);
					var zonetemp = creatzone(result.data);
					var locationoption=creatlineOption(datatemp,zonetemp,"人");
					var locationChart = echarts.init(document.getElementById('locationchart'));
					locationChart.setOption(locationoption, true);
				}
			}else{
				alert(result.resultMessage);
			}
		}
		});
}

function creatnum(data){
	var tempdata = new Array();
	var o = {};  
	o["name"]="VIP人数";
	var temp = new Array();
	for(var i=0;i<data.length;i++){
		temp.push(data[i].zone_num);
	}
	o["data"]=temp;
	tempdata.push(o);
	return tempdata;
}

function creatzone(data){
	var temp = new Array();
	for(var i=0;i<data.length;i++){
		temp.push(data[i].zone_name);
	}
	return temp;
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
		html += "<br><div> <font size=\"3\">";
		html += "<p><strong>1，参与的VIP人数： </strong>"+tableInfo[i].vip_num+"</p><br>";
		html += "<p><strong>2，新会员比率： </strong>"+tableInfo[i].new_vip_rate*100+" %</p><br>";
		html += "<p><strong>3，转发比率： </strong>"+tableInfo[i].transfer_rate+"</p><br>";
		html += "</div>";
	}
	$("#datatable").html(html);
}