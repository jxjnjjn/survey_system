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