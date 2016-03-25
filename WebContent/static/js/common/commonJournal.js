/*=======================================*/
/*           commonJournal               */
/*	@author noviachan                    */
/*=======================================*/
function launch(){
	initechart();
	//加载隐藏环比表
	$("#annulusdatatable").hide();
	//加载默认平台信息
	var menu = $("#contentUl li").first();
	menu.addClass('active');
	//初始化工作
	platform = getPlatform(menu.text());
	isShowChart(platform);
	//getYesterdayInfo();
	//默认一个月数据
	btnexecute(1,30,31,60);
}

function searchData(){
	if(comparedate100(startDate,endDate) || comparedate(endDate,getLastDay(new Date()))){
		//日期有误
		errorDate();
	}else{
		//日期合法
		correctDate();
		$("#chartpanel").hide();
		$("#proportionpanel").hide();
		//放这里每次查询都会刷新昨日数据，因为有多选按钮时需要刷新数据
		getYesterdayInfo();
		//获取数据
		getData(1);
	}
}

function creattime(startdate,enddate){
	var dateTemp;
	var data = new Array();
	for(dateTemp=startdate;dateTemp<=enddate;dateTemp=getNextDay(dateTemp)){
		data.push(formatdate(dateTemp,"MMdd"));
	}
	return data;
}
///////////////////////////////////////构造页面元素//////////////////////////////////////////////////