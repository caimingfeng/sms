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
		var regexEnum = /^[1-9]\d*$/;
		var week = $("#week").val();
		//console.log(cno);
		if(week==''){
			$('#week').tips({
				side:3,
	            msg:'不能为空',
	            bg:'#AE81FF',
	            time:2
	        });
			return;
		}
		if(!regexEnum.test(week)){
			$('#week').tips({
				side:3,
	            msg:'请填入正确数字',
	            bg:'#AE81FF',
	            time:2
	        });
			return;
		}else if(week>23){
			$('#week').tips({
				side:3,
	            msg:'周次不能超过23周',
	            bg:'#AE81FF',
	            time:2
	        });
			return;
		}
		
		var weekNum = $("#weekNum").val();
		//console.log(cno);
		if(weekNum==''){
			$('#weekNum').tips({
				side:3,
	            msg:'不能为空',
	            bg:'#AE81FF',
	            time:2
	        });
			return;
		} else if(!regexEnum.test(weekNum)){
			$('#weekNum').tips({
				side:3,
	            msg:'请填入正确数字',
	            bg:'#AE81FF',
	            time:2
	        });
			return;
		}else if(weekNum>8){
			$('#weekNum').tips({
				side:3,
	            msg:'星期不能超过7',
	            bg:'#AE81FF',
	            time:2
	        });
			return;
		}
		
		var section = $("#section").val();
		//console.log(cno);
		if(section==''){
			$('#section').tips({
				side:3,
	            msg:'不能为空',
	            bg:'#AE81FF',
	            time:2
	        });
			return;
		}
		
		var address = $("#address").val();
		//console.log(cno);
		if(address==''){
			$('#address').tips({
				side:3,
	            msg:'不能为空',
	            bg:'#AE81FF',
	            time:2
	        });
			return;
		}
		
		$("#classMessageForm").submit();
	}
	
</script>
	</head>
<body>
	<form action="openClass/saveOpenClassMessage.do" name="classMessageForm" id="classMessageForm" method="post">
		<div id="zhongxin" class="center">
		<input type="hidden" value="${pd.coursesMessageId }" name = "coursesMessageId">
		<table>
			<tr>
				<td><input type="text" name="week" id="week" value="" maxlength="32" placeholder="周次" title=""/></td>
			</tr>
			<tr>
				<td><input type="text" name="weekNum" id="weekNum" value="" maxlength="32" placeholder="星期" title=""/></td>
			</tr>
			<tr>
				<td><input type="text" name="section" id="section" value="" maxlength="32" placeholder="节次(如：1,2)" title=""/></td>
			</tr>
			<tr>
				<td><input type="text" name="address" id="address" value="" maxlength="32" placeholder="上课地点" title=""/></td>
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