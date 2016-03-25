/*=======================================*/
/*	datepicker js                        */
/*	@author noviachan                    */
/*=======================================*/
//设置时间控件开始结束日期
$("#startdate").datepicker('setStartDate', "2015-01-01");
$("#enddate").datepicker('setStartDate', "2015-01-01");
$("#comparestartdate").datepicker('setStartDate', "2015-01-01");
$("#compareenddate").datepicker('setStartDate', "2015-01-01");
$("#startdate").datepicker('setEndDate', formatdate(getLastDay(new Date()),"yyyy-MM-dd"));
$("#enddate").datepicker('setEndDate', formatdate(getLastDay(new Date()),"yyyy-MM-dd"));
$("#comparestartdate").datepicker('setEndDate', formatdate(getLastDay(new Date()),"yyyy-MM-dd"));
$("#compareenddate").datepicker('setEndDate', formatdate(getLastDay(new Date()),"yyyy-MM-dd"));
//设置时间控件响应事件
$('#startdate').datepicker()
	.on('changeDate', function(ev){
		startDate = new Date(ev.date);
		var yesterday = getLastDay(new Date());
		if(comparedate(ev.date , yesterday)){
			$('#dateform').addClass('has-error');
			$("#myalert").show();
		}else if (comparedate100(ev.date , endDate)){
			$('#dateform').addClass('has-error');
			$("#myalert").show();
		} else {
			$('#dateform').removeClass('has-error');
			$("#myalert").hide();
		}
		//修改对比区间
		changedate();
		$('#startdate').datepicker('hide');
	});

$('#enddate').datepicker()
	.on('changeDate', function(ev){
		endDate = new Date(ev.date);
		var yesterday = getLastDay(new Date());
		if(comparedate(ev.date , yesterday)){
			$('#dateform').addClass('has-error');
			$("#myalert").show();
		}else if (comparedate100(startDate , ev.date)){
			$('#dateform').addClass('has-error');
			$("#myalert").show();
		} else {
			$('#dateform').removeClass('has-error');
			$("#myalert").hide();
		}
		//修改对比区间
		changedate();
		$('#enddate').datepicker('hide');
	});

$('#comparestartdate').datepicker()
.on('changeDate', function(ev){
	comparestartDate = new Date(ev.date);
	var yesterday = getLastDay(new Date());
	if(comparedate(ev.date , yesterday)){
		$('#dateform').addClass('has-error');
		$("#myalert").show();
	}else if (comparedate100(ev.date , compareendDate)){
		$('#dateform').addClass('has-error');
		$("#myalert").show();
	} else {
		$('#dateform').removeClass('has-error');
		$("#myalert").hide();
	}
	$('#comparestartdate').datepicker('hide');
});

$('#compareenddate').datepicker()
.on('changeDate', function(ev){
	compareendDate = new Date(ev.date);
	var yesterday = getLastDay(new Date());
	if(comparedate(ev.date , yesterday)){
		$('#dateform').addClass('has-error');
		$("#myalert").show();
	}else if (comparedate100(comparestartDate , ev.date)){
		$('#dateform').addClass('has-error');
		$("#myalert").show();
	} else {
		$('#dateform').removeClass('has-error');
		$("#myalert").hide();
	}
	$('#compareenddate').datepicker('hide');
});

function changedate(){
	var firstDate=new Date();
	firstDate.setFullYear(2015,0,1);
	
	compareendDate = getLastDay(startDate);
	var d = compareendDate.getTime() - (endDate.getTime()-startDate.getTime());
	comparestartDate = new Date(d);
	
	if(comparedate(firstDate , comparestartDate)){
		comparestartDate = firstDate;
		var temp = comparestartDate.getTime() + (endDate.getTime()-startDate.getTime());
		compareendDate = new Date(temp);
	}
	
	$("#comparestartdate").datepicker('setDate', formatdate(comparestartDate,"yyyy-MM-dd"));
	$("#compareenddate").datepicker('setDate', formatdate(compareendDate,"yyyy-MM-dd"));
}