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
		<link rel="stylesheet" href="static/zeroModal/zeroModal.css" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		
		<!-- <script src="static/js/jquery.tips.js"></script> -->
<script type="text/javascript">
	$(top.hangge());
	//保存
	function save(){
		if($("#scoreType").val()==""){
			
			$("#scoreType").tips({
				side:3,
	            msg:'选择成绩方式',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#scoreType").focus();
			return false;
		}
		if($("#scoreType").val()=="2"){
			$("#examPer").val("");
			$("#regularPer").val("");
			
			$.post("score/setUpScore",{scoreType:$("#scoreType").val(),coursesMessageId:$("#coursesMessageId").val()},function(msg){
				if(msg =='success'){
					alert("设置成功");
				}else{
					alert("设置失败");
				}
				top.Dialog.close();
			},'json');
			
		}
		if($("#scoreType").val()=="1"){
			
			var regularPer = $("#regularPer").val();
			var examPer = $("#examPer").val();
			
			if(regularPer == ""){
				$("#regularPer").tips({
					side:3,
		            msg:'不能为空',
		            bg:'#AE81FF',
		            time:2
		        });
				
				$("#regularPer").focus();
				return false;
			}
			
			if(examPer == ""){
				$("#examPer").tips({
					side:3,
		            msg:'不能为空',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#examPer").focus();
				return false;
			}
			console.log(parseFloat(examPer) + parseFloat(regularPer));
			if(parseFloat(examPer) + parseFloat(regularPer) != 1){
				$("#examPer").tips({
					side:3,
		            msg:'百分比设置不正确',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#examPer").focus();
				return false;
			}
		}
		$.post("score/setUpScore",{scoreType:$("#scoreType").val(),coursesMessageId:$("#coursesMessageId").val(),regularPer:regularPer,examPer:examPer},function(msg){
			if(msg =='success'){
				alert("设置成功");
			}else{
				alert("设置失败");
			}
			top.Dialog.close();
		},'json');
		//$("#loginname").val(jQuery.trim($('#loginname').val()));
	}
	
</script>
	</head>
<body>
	<form action="score/setUpScore.do" name="scoreForm" id="scoreForm" method="post">
		<input type="hidden" name="coursesMessageId" id="coursesMessageId" value="${pd.coursesMessageId }"/>
		<div id="zhongxin">
		
			<select class="chzn-select" name="scoreType" id="scoreType" data-placeholder="选择成绩方式" style="vertical-align:top;">
				<option value=""></option>
				<option value="1">百分制</option>
				<option value="2">五分制</option>
			</select>
				<input type="text" name="regularPer" id="regularPer"  maxlength="32" placeholder="平时成绩百分比(如:0.2)" title=""/>
				<input type="text" name="examPer" id="examPer"  maxlength="32" placeholder="期末成绩百分比(如:0.8)" title=""/>
				<div class="center">
					<a class="btn btn-mini btn-primary" onclick="save();">确定</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</div>
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
		<!-- 确认窗口 -->
	<script type="text/javascript" src="static/zeroModal/zeroModal.min.js"></script>
	<script type="text/javascript">
		
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
		});
		
		</script>
</body>
</html>