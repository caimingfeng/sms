<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="../admin/top.jsp"%>
<meta charset="utf-8" />
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="static/css/bootstrap-3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="static/zeroModal/zeroModal.css" />
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<style type="text/css">
.div_span {
	width: 60px;
	float: left;
	/* background-color: red; */
}

.div_span span {
	float: right;
	display: inline-block;
}

.div_row {
	width: 800px;
	height: 40px;
	margin-top: 10px;
}

.clear {
	float: right;
}

.div_inline {
	display: inline-block;
}

.div_inline label {
	float: right;
	display: inline-block;
}

.width_ma_right {
	width: 60px;
	margin-right: 10px;
}

.div_inline2 {
	display: inline-block;
	margin-left: 50px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="col-sm-9 well" style="margin-left: -15px; width: 1000px;">
					<form action="#" method="post" onsubmit="return false;">
					<input type="hidden" name="account" id="account" value="${account }">
						<div class="container div_row">
								<div class="div_inline width_ma_right">
									<label>原密码</label>
								</div>
								<input type="password" name="oldpw" id="oldpw">
						</div>
						<div class="container div_row">
							<div class="div_inline width_ma_right">
									<label>新密码</label>
								</div>
								<input type="password" name="newpw" id="newpw">
						</div>
						<div class="container div_row">
							<div class="div_inline width_ma_right">
									<label>确认密码</label>
								</div>
								<input name="comfirepw" id="comfirepw" type="password">
						</div>
						<div class="container div_row">
							<button class="btn btn-primary"  onclick="save();">保存</button>
						</div>
					</form>
		</div>
	</div>
	<!-- 引入 -->
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>

	<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
	<!-- 下拉框 -->
	<script type="text/javascript"
		src="static/js/bootstrap-datepicker.min.js"></script>
	<!-- 日期框 -->
	<script type="text/javascript" src="static/js/bootbox.min.js"></script>
	<!-- 确认窗口 -->
	<script type="text/javascript" src="static/zeroModal/zeroModal.min.js"></script>
	<!-- <script src="static/js/teacherjs/infor.js"></script> -->

	<!-- 引入 -->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<!--提示框-->
	<script type="text/javascript">
		$(top.hangge());
		
		function save(){
			var url = "savePw";
			var account = $("#account").val();
			var oldpw = $("#oldpw").val();
			var newpw = $("#newpw").val();
			var comfirepw = $("#comfirepw").val();
			if(newpw != comfirepw){
				$("#comfirepw").tips({
					side:3,
		            msg:'密码不一致',
		            bg:'#AE81FF',
		            time:2
		        });
				return;
			}
			
			$.post(url,{account:account,oldpw:oldpw,newpw:newpw},function(msg){
				if(msg === 'success'){
					//zeroModal.success("修改成功");
					window.location.href='logout';
				}else{
					zeroModal.error(msg);
				}
			},'json');

		}
	</script>
</body>
</html>

