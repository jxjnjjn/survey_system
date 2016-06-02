/*=======================================*/
/*	index-js                            */
/*	@author noviachan                    */
/*=======================================*/
$(document).ready(function(){
	
	$(".sidebarli").on('click',function(){
		//返回顶部
		rollback();
    });
	
	$(".logout").on('click',function(){
		logout();
    });
	
	$("#managepass").on('click',function(){
		//console.log("managepass");
		openManagePass();
		clearPass();
	});

	$("#topclosebtn").on('click',function(){
		//console.log("topclosebtn");
		closeAdmin();
		clearPass();
	});

	$("#closebtn").on('click',function(){
		//console.log("close");
		closeAdmin();
		clearPass();
	});

	$("#submitbtn").on('click',function(){
		//console.log("submit");
		changePassword();
		closeAdmin();
		clearPass();
	});
	$("#oldPass").on("keypress", function(){
		var event = arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异 
		if (event.keyCode == 13) {
			changePassword();
			closeAdmin();
			clearPass();
		} // 回车键的键值为13
	}); 
	$("#newPass").on("keypress", function(){
		var event = arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异 
		if (event.keyCode == 13) {
			changePassword();
			closeAdmin();
			clearPass();
		} // 回车键的键值为13
	}); 
	$("#confirmPass").on("keypress", function(){
		var event = arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异 
		if (event.keyCode == 13) {
			changePassword();
			closeAdmin();
			clearPass();
		} // 回车键的键值为13
	}); 
});

function rollback(){
	//scroll(0,0);
	//document.body.scrollTop=0;
	$("html,body").animate({scrollTop:"0px"},400);
}

function logout(){
	window.location.href="/system/logout";
}

function changePassword(){
	var datasent = $("#changepass").serializeObject();
	params = JSON.stringify(datasent); 
	console.log(params);
	$.ajax({
	type : "POST",
	url : "/system/changepass",
	dataType : "json",
	contentType : "application/json;charset=utf-8",
	data : params,
	async : true,
	success : function(data) {
		alert(data.resultMessage);
		if(data.resultCode == 0){
			logout();
		}
	}
	});
}

function openManagePass(){
	$("#userinfo").hide();
	$("#changepass").show();
	$("#userModalLabel").text("修改密码");
	$("#submitbtn").show();
}

function closeAdmin(){
	$("#userinfo").show();
	$("#changepass").hide();
	$("#userModalLabel").text("账户管理");
	$("#submitbtn").hide();
	//$("#userModal").modal('hide');
}

function clearPass(){
	$("#oldPass").val("");
	$("#newPass").val("");
	$("#confirmPass").val("");
}

//左侧菜单切换
$("#lside").on('click',function(){
	var menu = jQuery(this);
	$("#lside").find('li').removeClass('active');
	menu.addClass('active');
});
//////////////////////////////////////////////////////////////////