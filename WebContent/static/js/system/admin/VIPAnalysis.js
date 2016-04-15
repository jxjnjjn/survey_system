/*=======================================*/
/*	@author noviachan                    */
/*=======================================*/
var endDate;
var startDate;

getlist();

btnexecute(1,7);

getlistzone();

$("#todaybtn").on('click',function(){
	btnexecute(1,1);
});

$("#weekbtn").on('click',function(){
	btnexecute(1,7);
});

$("#monthbtn").on('click',function(){
	btnexecute(1,30);
});

$("#searchbtn").on('click',function(){
	getlisttrend();
});

//按天数查询
function btnexecute(day1,day2){
	endDate = new Date();
	startDate = new Date();
	endDate.setDate(endDate.getDate()-day1);
	startDate.setDate(startDate.getDate()-day2);
	$("#enddate").datepicker('setDate', formatdate(endDate,"yyyy-MM-dd"));
	$("#startdate").datepicker('setDate', formatdate(startDate,"yyyy-MM-dd"));
	getlisttrend();
}

function getlist(){
	var today = formatdate(new Date(),"yyyy-MM-dd");
	$.ajax({
		type : "GET",
		url : "/system/vipanalysis/getlist",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			today:today},
		async : true,
		success : function(result) {
			if(result.resultCode == 0){
				if(result.data != null){
					creattoday(result.data[0]);
				}
			}else{
				alert(result.resultMessage);
			}
		}
		});
}

function creattoday(data){
	var temp = "";
	temp += "<div class=\"col-md-4\"><div class=\"panel panel-default\">";
	temp += "<div class=\"panel-heading text-center\"><h5><strong>注册总人数</strong></h5></div>";
	temp += "<div class=\"panel-body text-center\"><h5><strong>"+data.total_num+"人</strong></h5></div>";
	temp += "</div></div>";
	
	temp += "<div class=\"col-md-4\"><div class=\"panel panel-default\">";
	temp += "<div class=\"panel-heading text-center\"><h5><strong>当日注册数</strong></h5></div>";
	temp += "<div class=\"panel-body text-center\"><h5><strong>"+data.today_num+"人</strong></h5></div>";
	temp += "</div></div>";
	
	temp += "<div class=\"col-md-4\"><div class=\"panel panel-default\">";
	temp += "<div class=\"panel-heading text-center\"><h5><strong>当日浏览未注册人数</strong></h5></div>";
	temp += "<div class=\"panel-body text-center\"><h5><strong>"+data.unregister_num+"人</strong></h5></div>";
	temp += "</div></div>";
	
	$("#todaybasicdata").html(temp);
}

function getlisttrend(){
	$.ajax({
		type : "GET",
		url : "/system/vipanalysis/getlisttrend",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {
			start:formatdate(startDate,"yyyy-MM-dd"),
			end:formatdate(endDate,"yyyy-MM-dd")},
		async : true,
		success : function(result) {
			if(result.resultCode == 0){
				if(result.data != null){
					var datatemp = creatdata(result.data);
					var timetemp = creattime(result.data);
					var option=creatlineOption(datatemp,timetemp,"人");
					var Chart = echarts.init(document.getElementById('chart'));
					Chart.setOption(option, true);
				}
			}else{
				alert(result.resultMessage);
			}
		}
		});
}

function creatdata(data){
	var tempdata = new Array();
	var o = {};  
	o["name"]="注册人数";
	var temp = new Array();
	for(var i=0;i<data.length;i++){
		temp.push(data[i].today_num);
	}
	o["data"]=temp;
	tempdata.push(o);
	return tempdata;
}

function creattime(data){
	var temp = new Array();
	for(var i=0;i<data.length;i++){
		temp.push(formatdate(data[i].register_date,"MMdd"));
	}
	return temp;
}

function getlistzone(){
	$.ajax({
		type : "GET",
		url : "/system/vipanalysis/getlistzone",
		dataType : "JSON",
		contentType : "application/json;charset=utf-8",
		data : {},
		async : true,
		success : function(result) {
			if(result.resultCode == 0){
				if(result.data != null){
//					var locationChart = echarts.init(document.getElementById('locationchart'));
//					locationChart.setOption(totalsalesoption, true);
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
	o["name"]="注册人数";
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
