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
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<style type="text/css">
td {
	border: 1px solid #00B2EE;
	width: 125px;
}
</style>
</head>
<body>
	<c:if test="${not empty score}">
		<form action="" method="post">
			<table>
				<tr>
					<td>名称</td>
					<td>成绩</td>
				</tr>
				<tr>
					<td>平时成绩</td>
					<td>${score.regularGrade }</td>
				</tr>
				<tr>
					<td>期末成绩</td>
					<td>${score.examGrade }</td>
				</tr>
				<tr>
					<td>总成绩</td>
					<td>${score.finalGrade }</td>
				</tr>
			</table>
		</form>
	</c:if>
	<script type="text/javascript">
		$(top.hangge());
	</script>
</body>
</html>

