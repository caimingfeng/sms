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
		if($("#user_id").val()!=""){
			/* $("#loginname").attr("readonly","readonly");
			$("#loginname").css("color","gray"); */
		}
	});
	
	//保存
	function save(){
		if($("#role_id").val()==""){
			
			$("#role_id").tips({
				side:3,
	            msg:'选择角色',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#role_id").focus();
			return false;
		}
		if($("#loginname").val()==""){
			
			$("#loginname").tips({
				side:3,
	            msg:'输入用户名',
	            bg:'#AE81FF',
	            time:2
	        });
			
			$("#loginname").focus();
			$("#loginname").val('');
			$("#loginname").css("background-color","white");
			return false;
		}else{
			$("#loginname").val(jQuery.trim($('#loginname').val()));
		}
			hasU();
	}
	
	//判断用户名是否存在
	function hasU(){
		var account = $.trim($("#loginname").val());
		var userId = $("#user_id").val();
		$.ajax({
			type: "POST",
			url: '<%=basePath%>user/hasU.do',
	    	data: {account:account,userId:userId,tm:new Date().getTime()},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){
					$("#userForm").submit();
					$("#zhongxin").hide();
					$("#zhongxin2").show();
				 }else{
					 //alert("2222");
					$("#loginname").tips({
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
	<form action="user/${msg }.do" name="userForm" id="userForm" method="post">
		<input type="hidden" name="userId" id="user_id" value="${pd.userId }"/>
		<div id="zhongxin">
		<table>
			
			<c:if test="${fx != 'head'}">
			<c:if test="${pd.roleId != '1'}">	
			<tr class="info">
				<td>
				<select class="chzn-select" name="roleId" id="role_id" data-placeholder="授予权限" style="vertical-align:top;" <c:if test="${pd.account == USERNAME }"> disabled="disabled"</c:if>>
				<option value=""></option>
				
				<c:forEach items="${roleList}" var="role">
					<option value="${role.roleId }" <c:if test="${role.roleId == pd.roleId }">selected</c:if>>${role.roleName }</option>
				</c:forEach>
				
				</select>
				</td>
			</tr>
			</c:if>
			<c:if test="${pd.roleId == '1'}">
			<input name="roleId" id="role_id" value="1" type="hidden" />
			</c:if>
			</c:if>
			
			<c:if test="${fx == 'head'}">
				<input name="roleId" id="role_id" value="${pd.roleId }" type="hidden" />
			</c:if>
			
			<tr>
				<td><input <c:if test="${pd.account == USERNAME }"> disabled="disabled"</c:if> type="text" name="account" id="loginname" value="${pd.account }" maxlength="32" placeholder="这里输入用户名" title="用户名"/></td>
			</tr>
			<tr>
				<td><input type="password" name="password" id="password"  maxlength="32" placeholder="输入密码" title="密码"/></td>
			</tr>
			<tr>
				<td><input type="text" name="bz" id="BZ"value="${pd.bz }" placeholder="这里输入备注" maxlength="64" title="备注"/></td>
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
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			//$('.date-picker').datepicker();
			
		});
		
		</script>
</body>
</html>