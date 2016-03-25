/*=======================================*/
/*	linechart-js                         */
/*	@author noviachan                    */
/*=======================================*/
function creatlineOption(chartinfo,time,unit){
	var option = {
			title : creatlinetitle(),
    	    tooltip : creatlinetooltip(),
    	    legend: creatlinelegend(chartinfo),
    	    grid: creatlinegrid(),
    	    toolbox: creatlinetoolbox(),
    	    dataZoom : creatlinedataZoom(),
    	    xAxis : creatlinexAxis(time),
    	    yAxis : creatlineyAxis(unit),
    	    series : creatlineseries(chartinfo)
    	};
	return option;
}

function creatlinetitle(){
	var title = {
			text: '数据趋势'
	    };
	return title;
}

function creatlinetooltip(){
	var tooltip = {
	        trigger: 'axis'
    	};
	return tooltip;
}

function creatlinelegend(chartinfo){
	var data = [];
	for(var i=0;i<chartinfo.length;i++){
		data.push(chartinfo[i].name);
	}
	var legend = {
	        data:data
    }
	return legend;
}

function creatlinegrid(){
	var grid = {
	        left: '1%',
	        right: '3%',
	        containLabel: true
	    }
	return grid;
}

function creatlinetoolbox(){
	var toolbox = {
	        show : true,
	        feature : {
	            magicType : {show: true, type: ['bar','line']},
	        }
	    };
	return toolbox;
}

function creatlinedataZoom(){
	var dataZoom = {
	        show : true,
	        realtime : true,
	        start : 0,
	        end : 100
	    };
	return dataZoom;
}

function creatlinexAxis(time){
	var xAxis = [
	    	        {
	    	            type : 'category',
	    	            data : time
	    	        }
	    	    ];
	return xAxis;
}

function creatlineyAxis(unit){
	var yAxis = [
	    	        {
	    	            type : 'value',
	    	            axisLabel : {
	    	                formatter: '{value}'+unit
	    	            }
	    	        }
	    	    ];
	return yAxis;
}

function creatlineseries(chartinfo){
	var series = [];
	for(var i=0;i<chartinfo.length;i++){
		var seriestemp;
		seriestemp = {
	            name:chartinfo[i].name,
	            type:'line',
	            data:chartinfo[i].data
        };
		series.push(seriestemp);
	}
	return series;
}