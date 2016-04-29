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
	//$("#authCodeImg").attr("src", "/valid/getValidCode?randomString="+random);
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