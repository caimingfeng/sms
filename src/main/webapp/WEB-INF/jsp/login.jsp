<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>登录</title>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="登陆">
<!-- <meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> -->
<link rel="stylesheet"
	href="static/css/bootstrap-3.3.7/css/bootstrap.min.css">
<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<!--<link rel="stylesheet" href="css/bootstrap-theme.min.css">-->


<link rel="stylesheet" type="text/css" href="static/css/login.css" />
</head>

<body>
	<div class="m_1"
		style="background-image: url(static/images/login2.jpg); background-repeat: repeat;">
		<!--<img alt="" src="image/login.jpg">-->
	</div>

	<div class="m_2">
		<h1>欢迎登录</h1>
		<br /> <br />
		<form action="login" method="post" id="login_form"
			class="m_login_form">

			<div class="form-group">

				<input type="text" class="form-control input-lg" name="account"
					id="loginname" placeholder="账号" />
			</div>
			<div class="form-group">

				<input type="password" class="form-control input-lg"
					placeholder="密 码" name="password" id="password" />
			</div>
			<div class="form-group">
				<div style="float: left;" class="codediv">
					<input type="text" name="code" id="code"
						class="form-control login_code" />
				</div>
				<div style="float: left;">
					<img id="codeImg" alt="点击更换" title="点击更换" src="" />
				</div>
			</div>

			<div class="form-group" style="clear: both;" id="roleId">
				<input checked="checked" type="radio" name="roleId" value="3">学生 <input
					type="radio" name="roleId" value="2">教师 <input type="radio"
					name="roleId" value="">管理员
			</div>

			<div class="form-group">
				<button type="button" class="btn btn-primary btn-lg btn-block"
					id="login_form_b" onclick="login();">登录</button>
			</div>

		</form>
	</div>

</body>
<!-- <link rel="stylesheet" type="text/css" href="static/css/normalize.css"> -->
<link
	href="https://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css"
	rel="stylesheet">


<script type="text/javascript" src="static/js/jquery-1.9.1.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="static/js/bootstrap.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>

<script src="static/login/js/jquery-3.2.1.min.js"></script>
<script src="static/login/js/login.ajax.js"></script>
<script src="static/login/js/bootstrap2.min.js"></script>
<script src="static/login/js/camera.min.js"></script>
<script src="static/js/jquery.tips.js"></script>
<script src="static/js/jquery.cookie.js"></script>
</html>