<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

	    <title>${pd.SYSNAME}</title>
	    <!-- Bootstrap -->
	    <link rel="stylesheet" href="static/login/css/bootstrap2.min.css" />
	    <link rel="stylesheet" href="static/login/css/login.css" />
	</head>

	<body>
		<div class="container">

		<div class="jumbotron">
		  <h1>Login with your account</h1>
		  <p>Simple is beautiful!</p>
		</div>
			<div class="content">

	            <ul class="nav nav-tabs header-btn">
	                <li class="navigation-link li-active" id="li-1" onclick="toggle('#login-form');">Login</li>
	                <li class="navigation-link" id="li-2" onclick="toggle('#register-form');">Register</li>
	                <li class="navigation-link" id="li-3" onclick="toggle('#find-pwd-form');">Forget</li>
	            </ul>
	            <div class="page-wrapper">
	            	
				    <div class="form-div current" id="login-form">
		                <form  action="" method="post" name="loginForm" class="form-horizontal" id="loginForm">

		                    <div class="form-group logindiv">
		                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
		                        <input type="text" class="form-control loginname" id="loginname" placeholder="Username">
		                    </div>

		                    <div class="form-group pwddiv">
		                      
		                        <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
		                        <input type="password" class="form-control loginpassword" id="password" placeholder="Password">
		                    </div>


		                    <div class="form-group">

		                    <div class="checkbox">
		                        <label>
		                            <input type="checkbox" id="saveid" onclick="savePaw();"> Remember
		                        </label>
		                    </div>
		                        <div  style="float: left;" class="codediv">
		                            <input type="text" name="code" id="code" class="form-control login_code"/>
		                        </div>
		                        <div  style="float: left;">
		                            <img id="codeImg" alt="点击更换" title="点击更换" src="" />
		                        </div>
		                    </div>

		                    <div class="form-group">
		                        <button type="button" class="btn btn-default login_btn" onclick="login();">                             
		                        	<span class="glyphicon glyphicon glyphicon-log-in" aria-hidden="true"></span>
		                        </button>
		                        <button type="button" class="btn" id="zebra-btn" onclick="redirect_zebra_login()">Zebra</button>
		                    </div>

		            	</form> 
		            </div>     

		            <div class="form-div" id="register-form">
		                <form  action="" method="post" name="registerForm" id="registerForm">
		                    <div class="form-group">
		                        <label for="registerUserame">Username</label>
		                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
		                        <input type="text" class="form-control registerUsername" id="registerUsername" placeholder="Username">
		                    </div>
		                    <div class="form-group">
		                        <label for="registerPassword">Password</label>
		                        <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
		                        <input type="password" class="form-control registerPassword" id="registerPassword" placeholder="Password">
		                    </div>

		                    <div class="form-group">
		                        <label for="registerPassword">Confirm your Password</label>
		                        <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
		                        <input type="password" class="form-control registerPassword2" id="registerPassword2" placeholder="Password">
		                    </div>


		                    <div class="form-group">
		                        <label for="registerName">Name</label>
		                        <input type="text" class="form-control registerName" id="registerName" placeholder="name">
		                    </div>

		                    <div class="form-group">
		                        <label for="registerEmail">Email</label>
		                        <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
		                        <input type="email" class="form-control registerEmail" id="registerEmail" placeholder="Email">
		                    </div>

		                    <div class="form-group">
		                        <label for="registerPhone">Phone</label>
		                        <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
		                        <input type="text" class="form-control registerPhone" id="registerPhone" placeholder="phone">
		                    </div>

		                    <div class="form-group">
		                        <label for="registerRole">Role</label>
		                        <span class="glyphicon glyphicon-sunglasses" aria-hidden="true"></span>
		                        <select class="form-control" id="registerRole">
		                            <option value="1">管理员</option>
		                            <option value="2">供应商</option>
		                            <option value="3">借卖方</option>
		                        </select>
		                    </div>

		                    <div class="form-group">
		                        <label for="registerSkin">Skin</label>
		                        <select class="form-control" id="registerSkin">
		                            <option value="default">皮肤-默认</option>
		                        </select>
		                    </div>

		                    <div class="form-group">
		                        <button type="button" class="btn btn-default submit_btn" onclick="register();">Register</button>
		                    </div>
		                </form>
		            </div>
		                         
		            <div class="form-div" id="find-pwd-form">
		                <div class="form-inline" id="send-email-form">
		                    <div class="form-group">
		                        <label for="validate-email">Email</label>
		                        <input type="text" class="form-control find_input" id="validate-email" placeholder="earl@example.com">
		                    </div>
		                    <button type="button" class="btn btn-default send_btn" onclick="send_email()">Send</button>
		                </div>

			            <div class="form" id="reset-pwd-form">
			            	<div class="form-group">
			                    <label for="validate-email">Email Validate Code</label>
			                    <button class="btn resend-email" onclick="resend_email();">resend</button>
			                    <input type="text" class="form-control code_input" id="validate-email-code" placeholder="Email Validate Code">
			                </div>
			                <div class="form-group">
			                    <label for="validate-email">Reset Your Password</label>
			                    <input type="password" class="form-control reset_password" id="reset-pwd" placeholder="password">
			                </div>
			                 <div class="form-group">
			                    <label for="validate-email">Confirm Your Password</label>
			                    <input type="password" class="form-control reset_password" id="reset-pwd2" placeholder="password">
			                </div>
			                <button type="button" class="btn send_btn" onclick="send_reset_info()">Reset</button>
			            </div>
		            </div>

	            </div>
            </div>  
		</div>

		<script>
		function redirect_zebra_login(){
			window.location.href="<%=basePath%>zebra/loginZebra";
		}
		</script>
		
		<script src="static/login/js/jquery-3.2.1.min.js"></script>
		<script src="static/login/js/login.ajax.js"></script>
		<script src="static/login/js/bootstrap2.min.js"></script>
		<script src="static/login/js/camera.min.js"></script>
		<script src="static/js/jquery.tips.js"></script>
		<script src="static/js/jquery.cookie.js"></script>

	</body>
</html>