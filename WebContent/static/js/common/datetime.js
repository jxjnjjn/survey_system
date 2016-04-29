/*=======================================*/
/*	datepicker js                        */
/*	@author noviachan                    */
/*=======================================*/
//设置时间控件响应事件
$('#start_time').datepicker()
	.on('changeDate', function(ev){
		$('#start_time').datepicker('hide');
	});

$('#end_time').datepicker()
	.on('changeDate', function(ev){
		$('#end_time').datepicker('hide');
	});

$('#startdate').datepicker()
	.on('changeDate', function(ev){
		startDate = new Date(ev.date);
		$('#startdate').datepicker('hide');
	});

$('#enddate').datepicker()
	.on('changeDate', function(ev){
		endDate = new Date(ev.date);
		$('#enddate').datepicker('hide');
	});
