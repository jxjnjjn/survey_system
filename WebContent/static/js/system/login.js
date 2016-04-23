/*=======================================*/
/*	登录功能js                            */
/*	@author noviachan                    */
/*=======================================*/

$(document).ready(function(){
	//只要登录该页面访客统计数就会+1，不管是否重新登录 ， VIP登录数不会增加。
	updateUserNum(1,0);
	//防止在frame里面出现登录页面  
    if(top.location !== self.location){
        top.location.href=self.location.href;   
    } 
    
	$("#loginBtn").on('click',function(){
		login();
    });
	
	$("#questionBtn").on('click',function(){
		window.location.href="/system";
    });
	
	// 按回车执行查询
	$("#username").on("keypress", function(){
		var event = arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异 
		if (event.keyCode == 13) {
			login();
		} // 回车键的键值为13
	}); 
	$("#password").on("keypress", function(){
		var event = arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异 
		if (event.keyCode == 13) {
			login();
		} // 回车键的键值为13
	}); 
});

function login(){
	var btn = $("#loginBtn");
    btn.button('loading');
	var datasent = $("#loginForm").serializeObject();
	params = JSON.stringify(datasent); 
	console.log(params); 
	$.ajax({
		type : "POST",
		url : "/system/loginCheck",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : params,
		async : false,
		success : function(data) {
			if(data.resultCode == 0){   //登录成功
				//判断是VIP用户登录，那么访客登录数减1，同时VIP用户数加1.
				updateUserNum(-1 , 1);
				window.location.href="/system";
			}else{
				alert(data.resultMessage);
			}
			btn.button('reset');
		}
	});
}

function updateUserNum(visitor_num , vip_num){
	var params = "{\"visitor_num\":"+visitor_num+",\"vip_num\":" +vip_num+ "}"; 
	$.ajax({
		type : "POST",
		url : "/system/visitorCheck",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : params,
		async : false,
		success : function(data) {
			if(data.resultCode == 0){   //success
			}else{
				alert(data.resultMessage);
			}
		}
	});
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