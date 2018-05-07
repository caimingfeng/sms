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
	function save(){
		var cno = $("#cno").val();
		//console.log(cno);
		if(cno==''){
			$('#cno').tips({
				side:3,
	            msg:'选择课程',
	            bg:'#AE81FF',
	            time:2
	        });
			return;
		}
		
		var teaId = $("#teaId").val();
		//console.log(cno);
		if(teaId==''){
			$('#teaId').tips({
				side:3,
	            msg:'选择教授',
	            bg:'#AE81FF',
	            time:2
	        });
			return;
		}
		
		var teachingClass = $("#teachingClass").val();
		//console.log(cno);
		if(teachingClass==''){
			$('#teachingClass').tips({
				side:3,
	            msg:'开班名称不能为空',
	            bg:'#AE81FF',
	            time:2
	        });
			return;
		}
				
		var regexEnum = /^[1-9]\d*$/;
		var limitSelect = $("#limitSelect").val();
		if(limitSelect!=''){
			if(!regexEnum.test(limitSelect)){
				$('#limitSelect').tips({
					side:3,
		            msg:'上课人数为正整数',
		            bg:'#AE81FF',
		            time:2
		        });
				return;
			}
		}
		
		$("#openClassForm").submit();
	}
	
</script>
	</head>
<body>
	<form action="openClass/saveOpenClass.do" name="openClassForm" id="openClassForm" method="post">
		<div id="zhongxin" class="center">
		<table>
			<c:if test="${not empty courses }">
				<tr class="info">
					<td>
						<select class="chzn-select" name="cno" id="cno" data-placeholder="选择课程" style="vertical-align:top;">
							<option value=""></option>
							<c:forEach items="${courses}" var="course">
								<option value="${course.cno }">${course.courseName }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty teachers }">
				<tr class="info">
					<td>
						<select class="chzn-select" name="teaId" id="teaId" data-placeholder="选择授课教师" style="vertical-align:top;">
							<option value=""></option>
							<c:forEach items="${teachers}" var="teacher">
								<option value="${teacher.teaId }">${teacher.teaName }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
			<tr>
				<td>
					<select class="chzn-select" name="type" id="type" data-placeholder="选择授课方式" style="vertical-align:top;" >
							<option value=""></option>
							<option value="理论教学">理论教学</option>
							<option value="实验实践">实验实践</option>
							<option value="设计论文">设计论文</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><input type="text" name="teachingClass" id="teachingClass" value="" maxlength="32" placeholder="开班名称" title=""/></td>
			</tr>
			<tr>
				<td><input type="text" name="limitSelect" id="limitSelect" value="" maxlength="32" placeholder="上课人数" title=""/></td>
			</tr>
			<tr>
				<td><input type="text" name="content" id="content" value="" maxlength="32" placeholder="教学内容" title=""/></td>
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