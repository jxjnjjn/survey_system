/*=======================================*/
/*	datepicker js                        */
/*	@author noviachan                    */
/*=======================================*/
//设置时间控件响应事件
$('#start_time').datetimepicker()
	.on('changeDate', function(ev){
		$('#start_time').datetimepicker('hide');
	});

$('#end_time').datetimepicker()
	.on('changeDate', function(ev){
		$('#end_time').datetimepicker('hide');
	});

$('#startdate').datetimepicker()
	.on('changeDate', function(ev){
		startDate = new Date(ev.date);
		$('#startdate').datetimepicker('hide');
	});

$('#enddate').datetimepicker()
	.on('changeDate', function(ev){
		endDate = new Date(ev.date);
		$('#enddate').datetimepicker('hide');
	});
