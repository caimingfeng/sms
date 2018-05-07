<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<%@ include file="../admin/top.jsp"%>


		<script type="text/javascript">
	top.hangge();
	//保存
	function save(){
		if($("#roleName").val()==""){
			$("#roleName").focus();
			return false;
		}
			$("#form1").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
	}
	
	
</script>
	</head>
<body>
		<form action="role/saveAdd.do" name="form1" id="form1"  method="post">		
			<div id="zhongxin">
			<table>
				<tr>
					<td><input type="text" name="roleName" id="roleName" placeholder="这里输入名称" title="名称"/></td>
				</tr>
				<tr>
					<td style="text-align: center;">
						<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
						<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
					</td>
				</tr>
			</table>
			</div>
		</form>
	
	<div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>

</body>
</html>
