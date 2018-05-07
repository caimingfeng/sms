//服务器校验
function login(){
	if(login_validate()){
		
		var url = 'userControl/newLogin';
		/*var url = 'login_login';*/

		var loginname = $("#loginname").val();
		var password = $("#password").val();
		var roleId = $('input:radio:checked').val();
		/*alert(roleId);*/
		var info = {
			account:loginname,
			password:password,
			code:$('#code').val(),
			roleId:roleId
		}
		
		console.log(info);
		
		$.ajax({
			type: "POST",
			url: url,
	    	data: JSON.stringify(info),
			dataType:'json',
			contentType: 'application/json',
			cache: false,
			success: function(data){
				if("200" == data.code){
					saveCookie();
					window.location.href=data.msg;
				}else if("399" == data.code){
					$("#code").tips({
						side : 1,
						msg : data.msg,
						bg : '#FF5080',
						time : 3
					});
					$("#loginname").focus();
					$("#codeImg").click();
				}else if("400" == data.code){
					$("#loginname").tips({
						side : 1,
						msg : data.msg,
						bg : '#FF5080',
						time : 3
					});
					$("#loginname").focus();
					$("#codeImg").click();
				}else if("401" == data.code){
					$("#password").tips({
						side : 1,
						msg : data.msg,
						bg : '#FF5080',
						time : 3
					});
					$("#loginname").focus();
					$("#codeImg").click();
				}else if("402" == data.code){
					$("#roleId").tips({
						side : 1,
						msg : data.msg,
						bg : '#FF5080',
						time : 3
					});
					$("#loginname").focus();
					$("#codeImg").click();
				}else if("500" == data.code){
					$("#loginname").tips({
						side : 1,
						msg : data.msg,
						bg : '#FF5080',
						time : 3
					});
					$("#code").focus();
					$("#codeImg").click();
				}else{
					$("#loginname").tips({
						side : 1,
						msg : "缺少参数",
						bg : '#FF5080',
						time : 3
					});
					$("#loginname").focus();
					$("#codeImg").click();
				}
			}
		});
	}
}

//客户端校验
function login_validate() {

	if ($("#loginname").val() == "") {

		$("#loginname").tips({
			side : 2,
			msg : '用户名不得为空',
			bg : '#AE81FF',
			time : 3
		});

		$("#loginname").focus();
		return false;
	} else {
		$("#loginname").val(jQuery.trim($('#loginname').val()));
	}

	if ($("#password").val() == "") {

		$("#password").tips({
			side : 2,
			msg : '密码不得为空',
			bg : '#AE81FF',
			time : 3
		});

		$("#password").focus();
		return false;
	}
	if ($("#code").val() == "") {

		$("#code").tips({
			side : 1,
			msg : '验证码不得为空',
			bg : '#AE81FF',
			time : 3
		});

		$("#code").focus();
		return false;
	}

	return true;
}

$(document).ready(function() {
	changeCode();
	$("#codeImg").bind("click", changeCode);
});

function genTimestamp() {
	var time = new Date();
	return time.getTime();
}

function changeCode() {
	$("#codeImg").attr("src", "code.do?t=" + genTimestamp());
}

function savePaw() {
	if (!$("#saveid").attr("checked")) {
		$.cookie('loginname', '', {
			expires : -1
		});
		$.cookie('password', '', {
			expires : -1
		});
	}
}

function saveCookie() {
	if ($("#saveid").attr("checked")) {
		$.cookie('loginname', $("#loginname").val(), {
			expires : 7
		});
		$.cookie('password', $("#password").val(), {
			expires : 7
		});
	}
}
function reset() {
	$("#loginname").val('');
	$("#password").val('');
}

jQuery(function() {
	var loginname = $.cookie('loginname');
	var password = $.cookie('password');
	if (typeof(loginname) != "undefined"
			&& typeof(password) != "undefined") {
		$("#loginname").val(loginname);
		$("#password").val(password);
		$("#saveid").attr("checked", true);
		$("#code").focus();
	}
});

function toggle(span) {

	var li_1 = $("#li-1");
	var li_2 = $("#li-2");    
	var li_3 = $("#li-3");
	if(span == '#register-form'){
		$(li_2).insertBefore(li_1);
		$(li_1).after(li_3);
	}
	if(span == '#login-form'){
		$(li_1).insertBefore(li_2);
		$(li_2).after(li_3);
	}
		if(span == '#find-pwd-form'){
		$(li_3).insertBefore(li_1);
		$(li_1).after(li_2);
	}

	switch(span) {
		case '#login-form':
			$('#login-form').animate({
				'height':'178px',
			});
			$('#register-form').animate({
				'height':'178px',
			});
			$('#find-pwd-form').animate({
				'height':'178px',
			});
			$('.page-wrapper').removeClass('form-div--show-register');
			$('.page-wrapper').removeClass('form-div--show-forget');

			$('.li-active').removeClass('li-active');
			$('.navigation-link').addClass(function(n){
			if(n == 0)
				return 'li-active';
			});
			break;
		case '#register-form':

			$('#login-form').animate({
				'height':'735px',
			});
			$('#register-form').animate({
				'height':'735px',
			});
			$('#find-pwd-form').animate({
				'height':'735px',
			});
			$('.page-wrapper').removeClass('form-div--show-forget');
			$('.page-wrapper').addClass('form-div--show-register');

			$('.li-active').removeClass('li-active');
			$('.navigation-link').addClass(function(n){
			if(n == 0)
				return 'li-active';
			});
			break;
		case '#find-pwd-form':

			if($('#send-email-form').attr('class').match('form-div-email')){
				$('#login-form').animate({
					'height':'279px',
				});
				$('#register-form').animate({
					'height':'279px',
				});
				$('#find-pwd-form').animate({
				'height':'279px',
				});
			}
			else{
				$('#login-form').animate({
					'height':'194px',
				});
				$('#register-form').animate({
					'height':'194px',
				});
				$('#find-pwd-form').animate({
					'height':'194px',
				});
			}

			$('.page-wrapper').removeClass('form-div--show-register');
			$('.page-wrapper').addClass('form-div--show-forget');

			$('.li-active').removeClass('li-active');
			$('.navigation-link').addClass(function(n){
			if(n == 0)
				return 'li-active';
			});

			break;
	}



}

function show_reset_pwd(){
	$('#find-pwd-form').animate({height:'279px'},"slow");
	$('#send-email-form').addClass('form-div-email');
	$('#reset-pwd-form').addClass('form-div-email');
}

function resend_email(){
	$('#find-pwd-form').animate({height:'194px'},"slow");
	$('.form-div-email').removeClass('form-div-email');
}


//TOCMAT重启之后 点击左侧列表跳转登录首页 
if (window != top) {
	top.location.href = location.href;
}



