/*=======================================*/
/*	注册找回密码功能js                            */
/*	@author noviachan                    */
/*=======================================*/

$(document).ready(function(){
	$("#registerBtn").on('click',function(){
		register();
    });
	
	$("#validcodeBtn").on('click',function(){
		validcode();
    });
	
	$("#forgetpasswordBtn").on('click',function(){
		forgetpassword();
    });
});

function register(){
	var btn = $("#registerBtn");
    btn.button('loading');
	var datasent = $("#registerForm").serializeObject();
	params = JSON.stringify(datasent);
	//console.log(params);
	$.ajax({
		type : "POST",
		url : "/system/addUser",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : params,
		async : true,
		success : function(data) {
			if(data.resultCode == 0){   //注册成功
				alert(data.resultMessage);
				window.location.href="/system/login";
			}else{
				alert(data.resultMessage);
			}
			btn.button('reset');
		}
	});
}

function forgetpassword(){
	var btn = $("#forgetpasswordBtn");
    btn.button('loading');
	var datasent = $("#forgetpasswordForm").serializeObject();
	params = JSON.stringify(datasent); 
	console.log(params);
	$.ajax({
		type : "POST",
		url : "/system/getbackpassword",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : params,
		async : true,
		success : function(data) {
			if(data.resultCode == 0){   //修改成功
				alert(data.resultMessage);
				window.location.href="/system/login";
			}else{
				alert(data.resultMessage);
			}
			btn.button('reset');
		}
	});
}

function validcode() {
	var random = new Date().getTime();
	$("#randomString").val(random);
	var user_name = $("#user_name").val();
	
	var myreg = /^(((13)|(15)|(18))+\d{9})$/; 
	var availableCheck = myreg.test(user_name);
	if(!availableCheck)
	{
		alert("请正确输入手机号！");
		return;
	}
	//30秒后重新发送验证码
	showtime(30);
	$.ajax({
		type : "GET",
		url : "/valid/getvaildcode",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : {randomString:random,
			user_name:user_name},
		async : true,
		success : function(data) {
			if(data.resultCode == 0){   //获取验证码
				alert(data.resultMessage);
				//showtime(30);
			}else{
				alert(data.resultMessage);
			}
		}
	});
}

function showtime(t){ 
	$("#validcodeBtn").prop('disabled', true); 
    for(var i=1;i<=t;i++) { 
        window.setTimeout("update_p(" + i + ","+t+")", i * 1000); 
    } 
 
} 
 
function update_p(num,t) { 
    if(num == t) { 
    	$("#validcodeBtn").text("重新发送手机验证码 "); 
    	$("#validcodeBtn").prop('disabled', false); 
    } 
    else { 
        var printnr = t-num; 
        $("#validcodeBtn").text("(" + printnr + ")秒后重新发送"); 
    } 
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