/*=======================================*/
/*	登录功能js                            */
/*	@author noviachan                    */
/*=======================================*/

$(document).ready(function(){
	$("#registerBtn").on('click',function(){
		register();
    });
	
	$("#validcodeBtn").on('click',function(){
		validcode();
    });
});

function register(){
// 			var datasent = {"userName":"王test","userPass":"admin","userEmail":"wangyong@qq.com"};
	var btn = $("#registerBtn");
    btn.button('loading');
	var datasent = $("#registerForm").serializeObject();
	params = JSON.stringify(datasent); 
	console.log(params);
	$.ajax({
		type : "POST",
		url : "/system/addUser",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : params,
		async : false,
		success : function(data) {
			if(data.resultCode == 0){   //登录成功
				window.location.href="/system";
			}else{
				randomImg();   //加载页面时生成验证码
				alert(data.resultMessage);
			}
			btn.button('reset');
		}
	});
}

function validcode() {
	var random = new Date().getTime();
	//$("#authCodeImg").attr("src", "/valid/getValidCode?randomString="+random);
	$.ajax({
		type : "GET",
		url : "/valid/getvaildcode",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : {randomString:random},
		async : false,
		success : function(data) {
			if(data.resultCode == 0){   //登录成功
				window.location.href="/system";
			}else{
				randomImg();   //加载页面时生成验证码
				alert(data.resultMessage);
			}
			btn.button('reset');
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