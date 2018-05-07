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
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		
		<!-- <script src="static/js/jquery.tips.js"></script> -->
<script type="text/javascript">
	$(top.hangge());
	$(document).ready(function(){
	});
	
	//保存
	function save(){
		var headerMonitor = $.trim($("#headerMonitor").val());
		var majorClassId = $.trim($("#majorClassId").val());
		
		 $.ajax({
			type : "POST",
			url : '<%=basePath%>team/editMajorTeam.do',
	    	data: {headerMonitor:headerMonitor,majorClassId:majorClassId,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){
					 document.getElementById('zhongxin').style.display = 'none';
					 top.Dialog.close();
				 }else{
					$("#headerMonitor").tips({
							side : 1,
							msg : data.msg,
							bg : '#D16E6C',
							time : 3
						});
				 }
			}
		}); 
	}
	
</script>
	</head>
<body>
	<form action="team/${msg }.do" name="teamForm" id="teamForm" method="post">
		<input type="hidden" name=majorClassId id="majorClassId" value="${pd.majorClassId }"/>
		<div id="zhongxin">
		<table>
			
			<c:if test="${not empty majors }">
			<tr class="info">
				<td>
				<select class="chzn-select" name="majorName" id="major_name" data-placeholder="专业" style="vertical-align:top;" disabled="disabled">
				<option value=""></option>
				<c:forEach items="${majors}" var="major">
					<option value="${major.majorName }" <c:if test="${team.majorId eq major.majorId }">selected="selected"</c:if> >${major.majorName }</option>
				</c:forEach>
				
				</select>
				</td>
			</tr>
			</c:if>
			
			<tr>
				<td><input disabled="disabled" type="text" name="grade" id="grade" value="${team.grade }" maxlength="32" placeholder="这里输入年级" title=""/></td>
			</tr>
			<tr>
				<td><input disabled="disabled" type="text" name="classNumber" id="classNumber" value="${team.classNumber }"  maxlength="32" placeholder="输入班级名称" title=""/></td>
			</tr>
			<tr>
				<td><input type="text" name="headerMonitor" id="headerMonitor" value="${team.headerMonitor }"  maxlength="32" placeholder="输入班主任姓名" title=""/></td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
		
	</form>
	
		<!-- 引入 -->
		<script src="static/login/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="static/js/jquery-1.9.1.min.js"></script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<!--提示框-->
		<script src="static/js/jquery.tips.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
	<script type="text/javascript">
		
		$(function() {
			//单选框
			$(".chzn-select").chosen(); 
		});
		
		</script>
</body>
</html>