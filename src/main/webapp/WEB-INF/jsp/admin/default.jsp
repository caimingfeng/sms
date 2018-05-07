<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>

	<base href="<%=basePath%>">
	<!-- jsp文件头和头部 -->
	<%@ include file="../admin/top.jsp"%>
</head>
<body>
<c:if test="${NEW_USER.roleId==1 }">
<%=basePath%>
<div >start......................(ps: 默认主页)</div>
</c:if>
<c:if test="${NEW_USER.roleId!=1 }">
<table>
	<%-- <tr><td colspan="2">Welcome ${NEW_USER.userName }</td></tr>
	<tr><td>昵称：</td><td>${NEW_USER.name }</td></tr>
	<tr><td>邮箱：</td><td>${NEW_USER.email }</td></tr>
	<tr><td>手机：</td><td>${NEW_USER.phone }</td></tr> --%>
	<div >用户主页(ps: 默认主页)</div>
</table>
</c:if>





	<script type="text/javascript">

		$(top.hangge());


	</script>
</body>
</html>
