/*=======================================*/
/*通用js，各个页面公用的js，请加到这里，记得加注释！             */
/*	@author noviachan                    */
/*=======================================*/
var platform;
var type;

var endDate;
var startDate;
var compareendDate;
var comparestartDate;

$("#todaybtn").on('click',function(){
	btnexecute(1,1,2,2);
});

$("#yesterdaybtn").on('click',function(){
	btnexecute(2,2,3,3);
});

$("#weekbtn").on('click',function(){
	btnexecute(1,7,8,14);
});

$("#monthbtn").on('click',function(){
	btnexecute(1,30,31,60);
});

$("#quarterbtn").on('click',function(){
	
});

$("#searchbtn").on('click',function(){
	searchData();
});

$("#exportbtn").on('click',function(){
	//导出excel
	//exportexcel();
});

$('#myTabs a').click(function (e) {
  e.preventDefault();
  $(this).tab('show');
});

//////////////////////////////////////////复选框
$("input[name='alltype']").change(function(){
	if ($(this).is(':checked')){
		$("input[name='type']").prop("checked",true);
	}else{
		$("input[name='type']").prop("checked",false);
	}
});

$("input[name='type']").change(function(){
	var checknum=$("input[name='type']:checked:visible");
	var visiblenum=$("input[name='type']:visible");
	if(checknum.length == visiblenum.length){
		$("input[name='alltype']").prop("checked",true);
	}else{
		$("input[name='alltype']").prop("checked",false);
	}
});

function selectall(){
	$("input[name='alltype']").prop("checked",true);
	$("input[name='type']").prop("checked",true);
}

function checkboxselect(){
	type="";
	var arrChk=$("input[name='type']:checked:visible");
    //遍历得到每个checkbox的value值
    for (var i=0;i<arrChk.length;i++)
    {
    	type+=arrChk[i].value+",";
    }
    type = type.substring(0, type.length==0 ? 0 : type.length-1);
}
//////////////////////////////////////////复选框

$("#changetable").on('click',function(){
	if ($("#detailstable").is(':visible')){
		$("#detailstable").hide();
		$("#annulusdatatable").show();
		$("#changetable").text("切换详情表");
	}else{
		$("#detailstable").show();
		$("#annulusdatatable").hide();
		$("#changetable").text("切换环比表");
	}
});

$("#moredata").on('click',function(){
	if ($("#additionaldata").is(':visible')){
		$("#additionaldata").hide();
		$("#moredata").text("更多");
	}else{
		$("#additionaldata").show();
		$("#moredata").text("收起");
	}
});

$("#contentUl li").on('click',function(){
	var menu = jQuery(this);
	menu.closest('ul').find('li').removeClass('active');
	menu.addClass('active');
	//获取平台信息初始化页面
	platform = getPlatform(menu.text());
	isShowChart(platform);
	//getYesterdayInfo();
	//获取数据
	searchData();
});

function initechart(){
	//自适应屏幕宽度
	var pagewidth = $(window).width()*0.95;
	$(".main-chart").width(pagewidth);
}

function formatdate(date,format){
	var dateTemp=new Date(date); 
	return dateTemp.Format(format);
}
//比较日期,date1>date2或日期相差大于100天返回true
function comparedate100(date1,date2){
	var diff = Math.abs(date2.getTime() - date1.getTime())/ 1000 / 60 / 60 /24;
	return (formatdate(date1,"yyyy-MM-dd") > formatdate(date2,"yyyy-MM-dd")) || (diff > 100);
}
//比较日期,date1>date2返回true
function comparedate(date1,date2){
	return formatdate(date1,"yyyy-MM-dd") > formatdate(date2,"yyyy-MM-dd");
}

function fmoney(s, n)   
{
	//有小数点
	if((s+"").indexOf(".")>-1){
		n = n > 0 && n <= 20 ? n : 2;  
	}else{
		n=0;
	}
    var flag = s < 0 ? "-" : "";
    s = Math.abs(s);
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
    var l = s.split(".")[0].split("").reverse();
    //有小数点
    if(s.split(".").length > 1){
    	r = "." + s.split(".")[1]; 
    }else{
    	r = "";
    } 
    t = "";   
    for(i = 0; i < l.length; i ++ )   
    {   
    	t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
	}   
	return flag + t.split("").reverse().join("") + r;   
} 

function ftime(sec) {
	var flag = sec < 0 ? "-" : "";
	// 计算
	var h=0,i=0,s=parseInt(Math.abs(sec));
	if(s>60){
		i=parseInt(s/60);
		s=parseInt(s%60);
		if(i > 60) {
			h=parseInt(i/60);
			i = parseInt(i%60);
		}
	}
	// 补零
	var zero=function(v){
		return (v>>0)<10?"0"+v:v;
	};
	return flag + [zero(h),zero(i),zero(s)].join(":");
}

function getNextDay(d){
    d = new Date(d);
    d = +d + 1000*60*60*24;
    d = new Date(d);
    return d;
}

function getLastDay(d){
    d = new Date(d);
    d = +d - 1000*60*60*24;
    d = new Date(d);
    return d;
}

Date.prototype.Format = function(fmt)   
{ 
	var o = {
		"M+" : this.getMonth()+1,                 //月份   
		"d+" : this.getDate(),                    //日   
		"h+" : this.getHours(),                   //小时   
		"m+" : this.getMinutes(),                 //分   
		"s+" : this.getSeconds(),                 //秒   
		"q+" : Math.floor((this.getMonth()+3)/3), //季度   
		"S"  : this.getMilliseconds()             //毫秒   
	};
	if(/(y+)/.test(fmt))
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+ k +")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	return fmt;
} 

$.fn.serializeObject = function() {     
    var o = {};     
    var a = this.serializeArray();     
    $.each(a, function() {     
      if (o[this.name]) {     
        if (!o[this.name].push) {     
          o[this.name] = [ o[this.name] ];     
        }     
        o[this.name].push(this.value || '');     
      } else {     
        o[this.name] = this.value || '';
      }     
    });     
    return o;     
}; 

//文件下载
jQuery.download = function(url, data, method){    // 获得url和data
	if( url && data ){ 
	    // data 是 string 或者 array/object
	    data = typeof data == 'string' ? data : jQuery.param(data);        // 把参数组装成 form的  input
	    var inputs = '';
	    jQuery.each(data.split('&'), function(){ 
	        var pair = this.split('=');
	        inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />'; 
	    });        // request发送请求
	    jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>')
	    .appendTo('body').submit().remove();
	}
};

function ChangeStr2Date(str) {
	return new Date(Date.parse(str.replace(/-/g, "/")));        
}

function sortByKey(array, key) {
    return array.sort(function (a, b) {
    	var x = ChangeStr2Date(formatdate(a[key],"yyyy-MM-dd")); 
    	var y = ChangeStr2Date(formatdate(b[key],"yyyy-MM-dd"));
    	return x-y;
    });    
}

//隐藏显示分平台chart
function isShowChart(platform){
	if(platform == "0"){
		$(".second-chart").show();
	}else{
		$(".second-chart").hide();
	}
}

//search日期非法
function errorDate(){
	$('#dateform').addClass('has-error');
	$("#myalert").show();
	alert("日期有误");
}
//search日期合法
function correctDate(){
	$('#dateform').removeClass('has-error');
	$("#myalert").hide();
}
//search成功
function searchSuccess(){
	$(".loading").hide();
	$("#chartpanel").show();
	$("#proportionpanel").show();
	$("#tablepanel").show();
}
/////////////////////////////////后面加的共用方法///////////////////////////////////////
//按天数查询
function btnexecute(day1,day2,day3,day4){
	endDate = new Date();
	startDate = new Date();
	compareendDate = new Date();
	comparestartDate = new Date();
	endDate.setDate(endDate.getDate()-day1);
	startDate.setDate(startDate.getDate()-day2);
	compareendDate.setDate(compareendDate.getDate()-day3);
	comparestartDate.setDate(comparestartDate.getDate()-day4);
	$("#startdate").datepicker('setDate', formatdate(startDate,"yyyy-MM-dd"));
	$("#enddate").datepicker('setDate', formatdate(endDate,"yyyy-MM-dd"));
	$("#comparestartdate").datepicker('setDate', formatdate(comparestartDate,"yyyy-MM-dd"));
	$("#compareenddate").datepicker('setDate', formatdate(compareendDate,"yyyy-MM-dd"));
	searchData();
}
//按月份查询
function btnmonthexecute(day1,day2,day3,day4){
	endDate = getLastDay(new Date());
	startDate = getLastDay(new Date());
	compareendDate = getLastDay(getLastDay(new Date()));
	comparestartDate = getLastDay(getLastDay(new Date()));
	endDate.setMonth(endDate.getMonth()-day1);
	startDate.setMonth(startDate.getMonth()-day2);
	compareendDate.setMonth(compareendDate.getMonth()-day3);
	comparestartDate.setMonth(comparestartDate.getMonth()-day4);
	$("#startdate").datepicker('setDate', formatdate(startDate,"yyyy-MM-dd"));
	$("#enddate").datepicker('setDate', formatdate(endDate,"yyyy-MM-dd"));
	$("#comparestartdate").datepicker('setDate', formatdate(comparestartDate,"yyyy-MM-dd"));
	$("#compareenddate").datepicker('setDate', formatdate(compareendDate,"yyyy-MM-dd"));
	searchData();
}

///////////////////////////////////////构造页面元素//////////////////////////////////////////////////
//昨日信息
function creatRows(data){
	var dataTemp;
	if(data.length > 8){
		dataTemp = data.slice(8,data.length);
		data = data.slice(0,8);
	}
	var rows = "<div class=\"row\">";
	for(var i = 0;i<data.length;i++){
		if(i%4==0){
			rows += "</div><div class=\"row\">";
		}
		rows += creatYesterdayInfo(data[i]);
	}
	rows += "</div>";
	$("#basicdata").html(rows);
	//超过2行
	if(dataTemp != null){
		$("#moredata").show();
		var rowsTemp = "<div id=\"additionaldata\"><div class=\"row\">";
		for(var i = 0;i<dataTemp.length;i++){
			if(i%4==0){
				rowsTemp += "</div><div class=\"row\">";
			}
			rowsTemp += creatYesterdayInfo(dataTemp[i]);
		}
		rowsTemp += "</div></div>";
		$("#additionaldata").html(rowsTemp);
	}
}

function creatYesterdayInfo(data){
	var yesterdayInfo = "<div class=\"col-xs-6 col-md-3\" style=\"padding-right: 5px;padding-left: 5px;\"><div class=\"panel panel-default yesterdayinfo\">";
	yesterdayInfo += "<div class=\"panel-heading\"><h4 style=\"margin-top:0px;margin-bottom:0px;\"><strong>"+data.name+"</strong></h4></div>";
	if(data.name.indexOf("平均到账时间")>-1)
	{
		yesterdayInfo += "<div class=\"panel-body\"><div class=\"row\"><h4 style=\"margin-top:0px;\"><strong>"+ftime(data.data[0]) + "</strong></h4></div>";
	}else{
		yesterdayInfo += "<div class=\"panel-body\"><div class=\"row\"><h4 style=\"margin-top:0px;\"><strong>"+creatred(data.data[0],"",2) + "</strong>  <small>" + getUnit(data.name) + "</small></h4></div>";
	}
	yesterdayInfo += "<div class=\"row\">";
	
	yesterdayInfo += "<div class=\"col-xs-4 col-md-4\" style=\"padding-right: 0px;padding-left: 0px;\"><p><strong>日环比</strong></p>"+creatcolor(data.data[1],2)+creaticon(data.data[1])+"</div>";
	yesterdayInfo += "<div class=\"col-xs-4 col-md-4\" style=\"padding-right: 0px;padding-left: 0px;\"><p><strong>周环比</strong></p>"+creatcolor(data.data[2],2)+creaticon(data.data[2])+"</div>";
	yesterdayInfo += "<div class=\"col-xs-4 col-md-4\" style=\"padding-right: 0px;padding-left: 0px;\"><p><strong>月环比</strong></p>"+creatcolor(data.data[3],2)+creaticon(data.data[3])+"</div>";
	
	yesterdayInfo += "</div></div></div></div>";
	return yesterdayInfo;
}

function getUnit(data){
	if(data.indexOf("率")>-1||data.indexOf("度")>-1){
		return "%";
	}else if(data.indexOf("人数")>-1||data.indexOf("新注册")>-1||data.indexOf("用户")>-1||data.indexOf("活跃顾客")>-1||data.indexOf("在线峰值")>-1||data.indexOf("人次")>-1){
		return "人";
	}else if(data.indexOf("充提比")>-1||data.indexOf("ARPU")>-1||data.indexOf("消费比")>-1){
		return "";
	}else if(data.indexOf("单数")>-1||data.indexOf("通过数")>-1||data.indexOf("笔数")>-1||data.indexOf("笔次")>-1||data.indexOf("-")>-1||data.indexOf("以上")>-1||data.indexOf("分钟")>-1){
		return "笔";
	}else if(data.indexOf("平均到账时间")>-1){
		return "分钟";
	}else{
		return "元";
	}
}

function creatTableHtml(tableInfo){
	var theadhtml = creatTablehead();
	var tbodyhtml = creatTablebody(tableInfo);
	var html = "<div class=\"table-responsive\"><table id=\"detailtable\" class=\"table table-striped table-hover table-bordered table-condensed\">" + theadhtml + tbodyhtml + "</table></div>";
	$("#datatable").html(html);
}

function creatAnnulusHtml(tableInfo){
	var theadhtml = creatAnnulushead(tableInfo);
	var tbodyhtml = creatAnnulusbody(tableInfo);
	var html = "<div class=\"table-responsive\"><table id=\"annulustable\" class=\"table table-striped table-hover table-bordered table-condensed\">" + theadhtml + tbodyhtml + "</table></div>";
	$("#annulusdatatable").html(html);
}

function creatAnnulushead(tableInfo){
	var th = "";
	var tr = "";
	if(tableInfo.length!=0){
		if(tableInfo[0].data.length==4){
			th = th + "<th></th>";
			th = th + "<th>选择区间</th>";
			th = th + "<th>对比区间</th>";
			th = th + "<th>环增量</th>";
			th = th + "<th>环比增长率</th>";
			tr = "<tr>"+th+"</tr>";
		}else{
			th = th + "<th rowspan=\"2\"></th>";
			th = th + "<th colspan=\"2\">和值</th>";
			th = th + "<th colspan=\"2\">均值</th>";
			th = th + "<th rowspan=\"2\">环增量</th>";
			th = th + "<th rowspan=\"2\">环比增长率</th>";
			tr = "<tr>"+th+"</tr>";
			th = "<th>选择区间</th>";
			th = th + "<th>对比区间</th>";
			th = th + "<th>选择区间</th>";
			th = th + "<th>对比区间</th>";
			tr = tr + "<tr>"+th+"</tr>";
		}
	}
		
	var thead = "<thead>"+ tr +"</thead>"
	return thead
}

function creatAnnulusbody(tableInfo){
	var tr =  "";
	if(tableInfo.length!=0){
		for(var i in tableInfo){
			var td = "<th>"+tableInfo[i].name+"</th>";
			var sign = "";
			if(tableInfo[i].name.indexOf("率")>-1 || tableInfo[i].name.indexOf("度")>-1){
				sign = "%";
			}
			if(tableInfo[i].name.indexOf("平均到账时间")>-1){
				//转换时间格式
				if(tableInfo[0].data.length==4){
					td = td + "<td>"+ftime(tableInfo[i].data[0])+"</td>";
					td = td + "<td style=\"color:#949494;\">"+ftime(tableInfo[i].data[1])+sign+"</td>";
					td = td + "<td>"+ftime(tableInfo[i].data[2])+sign+"</td>";
					td = td + "<td>"+fmoney(tableInfo[i].data[3],2)+"%"+creaticon(tableInfo[i].data[3])+"</td>";
				}else{
					td = td + "<td>"+ftime(tableInfo[i].data[0])+"</td>";
					td = td + "<td style=\"color:#949494;\">"+ftime(tableInfo[i].data[1])+sign+"</td>";
					td = td + "<td>"+ftime(tableInfo[i].data[2])+"</td>";
					td = td + "<td style=\"color:#949494;\">"+ftime(tableInfo[i].data[3])+sign+"</td>";
					td = td + "<td>"+ftime(tableInfo[i].data[4])+sign+"</td>";
					td = td + "<td>"+fmoney(tableInfo[i].data[5],2)+"%"+creaticon(tableInfo[i].data[5])+"</td>";
				}
			}else{
				if(tableInfo[0].data.length==4){
					td = td + "<td>"+creatred(tableInfo[i].data[0],sign,2)+"</td>";
					td = td + "<td style=\"color:#949494;\">"+fmoney(tableInfo[i].data[1],2)+sign+"</td>";
					td = td + "<td>"+fmoney(tableInfo[i].data[2],2)+sign+"</td>";
					td = td + "<td>"+fmoney(tableInfo[i].data[3],2)+"%"+creaticon(tableInfo[i].data[3])+"</td>";
				}else{
					td = td + "<td>"+creatred(tableInfo[i].data[0],sign,2)+"</td>";
					td = td + "<td style=\"color:#949494;\">"+fmoney(tableInfo[i].data[1],2)+sign+"</td>";
					td = td + "<td>"+creatred(tableInfo[i].data[2],sign,2)+"</td>";
					td = td + "<td style=\"color:#949494;\">"+fmoney(tableInfo[i].data[3],2)+sign+"</td>";
					td = td + "<td>"+fmoney(tableInfo[i].data[4],2)+sign+"</td>";
					td = td + "<td>"+fmoney(tableInfo[i].data[5],2)+"%"+creaticon(tableInfo[i].data[5])+"</td>";
				}
			}
			
			tr = tr + "<tr>"+td+"</tr>"
		}
	}
	
	var tbody = "<tbody>"+ tr +"</tbody>"
	return tbody
}

function creaticon(date){
	var temp = "";
	if(date > 0){
		temp = "<span class=\"glyphicon glyphicon-arrow-up\" style=\"color:#5cb85c;\"></span>";
	}else if(date < 0){
		temp = "<span class=\"glyphicon glyphicon-arrow-down\" style=\"color:#d9534f;\"></span>";
	}
	return temp;
}

function creatcolor(date,number){
	var temp = fmoney(date,number)+"%";
	if(date > 0){
		temp = "<span style=\"color:#5cb85c;\">"+temp+"</span>";
	}else if(date < 0){
		temp = "<span style=\"color:#d9534f;\">"+temp+"</span>";
	}
	return temp;
}

function creatred(date,sign,number){
	var temp = fmoney(date,number)+sign;
	if(date < 0){
		temp = "<span style=\"color:#d9534f;\">"+temp+"</span>";
	}
	return temp;
}