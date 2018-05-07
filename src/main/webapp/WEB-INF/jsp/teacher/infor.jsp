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
				<c:when test="${not empty teacher}">
					<form action="#" method="post" onsubmit="return false;">
					<input type="hidden" name="tno" id="tno" value="${teacher.tno }">
						<div class="container div_row">
							<div class="div_inline">
								<div class="div_inline width_ma_right">
									<label>姓 名</label>
								</div>
								<input disabled="disabled" value="${teacher.teaName }" name="teaName">
							</div>
							<div class="div_inline2">
								<div class="div_inline width_ma_right">
									<label>职 称</label>
								</div>
								<input disabled="disabled" value="${teacher.professional }" name="professional">
							</div>
						</div>
						<div class="container div_row">
							<div class="div_inline">
								<div class="div_inline width_ma_right">
									<label>身份证</label>
								</div>
								<input value="${teacher.idCard }" name="idCard" id="idCard" required="required">
							</div>
							<div class="div_inline2">
								<div class="div_inline width_ma_right">
									<label>学 历</label>
								</div>
								<input disabled="disabled" value="${teacher.degree }" name="degree">
							</div>
						</div>
						<div class="container div_row">
							<div class="div_inline">
								<div class="div_inline width_ma_right">
									<label>性 别</label>
								</div>
								<select style="width: 80px" name="teaSex" required="required" id="teaSex">
									<option value="男" <c:if test="${teacher.teaSex eq '男'}"> selected="selected"</c:if> >男
									<option value="女" <c:if test="${teacher.teaSex eq '女'}"> selected="selected"</c:if>>女
								</select>
							</div>
						</div>
						<div class="container div_row">
							<div class="div_inline">
								<div class="div_inline width_ma_right">
									<label>手 机</label>
								</div>
								<input value="${teacher.phone }" name="phone" id="phone" required="required">
							</div>
							<div class="div_inline2">
								<div class="div_inline width_ma_right">
									<label>办公地址</label>
								</div>
								<input value="${teacher.address }" name="address" id="address">
							</div>
						</div>
						<div class="container div_row">
							<div class="div_inline">
								<div class="div_inline width_ma_right">
									<label>邮 箱</label>
								</div>
								<input value="${teacher.email }" name="email" id="email">
							</div>
							<div class="div_inline2">
								<div class="div_inline width_ma_right">
									<label>邮政编码</label>
								</div>
								<input value="${teacher.postalCode }" name="postalCode" id="postalCode">
							</div>
						</div>
						<div class="container div_row">
							<button class="btn btn-primary"  onclick="save();">保存</button>
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
			var url = "teacher/saveInfor";
			var tno = $("#tno").val();
			var idCard = $("#idCard").val();
			var teaSex = $("#teaSex").val();
			var phone = $("#phone").val();
			var address = $("#address").val();
			var email = $("#email").val();
			var postalCode = $("#postalCode").val();
			
			$.post(url,{tno:tno,idCard:idCard,teaSex:teaSex,phone:phone,address:address,email:email,postalCode:postalCode},function(msg){
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

