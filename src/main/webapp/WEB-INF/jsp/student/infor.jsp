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
			<c:choose>
				<c:when test="${not empty student}">
					<form action="" method="post">
						<input type="hidden" id="sno" value="${student.sno }">
						<div class="container div_row">
							<div class="div_inline">
								<div class="div_inline width_ma_right">
									<label>姓 名</label>
								</div>
								<input disabled="disabled" value="${student.stuName }">
							</div>
							<div class="div_inline2">
								<div class="div_inline width_ma_right">
									<label>出生日期</label>
								</div>
								<input disabled="disabled" value="${student.birth }">
							</div>
						</div>
						<div class="container div_row">
							<div class="div_inline">
								<div class="div_inline width_ma_right">
									<label>学	院</label>
								</div>
								<input disabled="disabled" value="${student.department }">
							</div>
							<div class="div_inline2">
								<div class="div_inline width_ma_right">
									<label>专	业</label>
								</div>
								<input disabled="disabled" value="${student.majorName }">
							</div>
						</div>
						<div class="container div_row">
							<div class="div_inline">
								<div class="div_inline width_ma_right">
									<label>年	级</label>
								</div>
								<input disabled="disabled" value="${student.grade }">
							</div>
							<div class="div_inline2">
								<div class="div_inline width_ma_right">
									<label>班	别</label>
								</div>
								<input disabled="disabled" value="${student.classNumber }">
							</div>
						</div>
						<div class="container div_row">
							<div class="div_inline">
								<div class="div_inline width_ma_right">
									<label>身份证</label>
								</div>
								<input value="${student.idCard }" id="idCard">
							</div>
							<div class="div_inline2">
								<div class="div_inline width_ma_right">
									<label>性 别</label>
								</div>
								<select style="width: 80px" id="sex">
									<option value="男" <c:if test="${student.sex eq '男'}">selected="selected"</c:if>>男
									<option value="女" <c:if test="${student.sex eq '女'}">selected="selected"</c:if>>女
								</select>
							</div>
						</div>
						<div class="container div_row">
							<div class="div_inline">
								<div class="div_inline width_ma_right">
									<label>手 机</label>
								</div>
								<input value="${student.phone }" name="phone" id="phone" required="required">
							</div>
							<div class="div_inline2">
								<div class="div_inline width_ma_right">
									<label>现居地址</label>
								</div>
								<input value="${student.address }" name="address" id="address">
							</div>
						</div>
						<div class="container div_row">
							<div class="div_inline">
								<div class="div_inline width_ma_right">
									<label>邮 箱</label>
								</div>
								<input value="${student.email }" name="email" id="email">
							</div>
							<div class="div_inline2">
								<div class="div_inline width_ma_right">
									<label>邮政编码</label>
								</div>
								<input value="${student.postalCode }" name="postalCode" id="postalCode">
							</div>
						</div>
						<div class="container div_row">
							<input class="btn btn-primary" type="button" value="保存" onclick="save();">
						</div>
					</form>
				</c:when>
				<c:otherwise>
					<div>没有相关数据</div>
				</c:otherwise>
			</c:choose>
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
			var url = "student/saveInfor";
			var sno = $("#sno").val();
			var idCard = $("#idCard").val();
			var sex = $("#sex").val();
			var phone = $("#phone").val();
			var address = $("#address").val();
			var email = $("#email").val();
			var postalCode = $("#postalCode").val();
			
			$.post(url,{sno:sno,idCard:idCard,sex:sex,phone:phone,address:address,email:email,postalCode:postalCode},function(msg){
				if(msg === 'success'){
					zeroModal.success("保存成功");
				}else{
					zeroModal.error(msg);
				}
			},'json');

		}
	</script>
</body>
</html>

