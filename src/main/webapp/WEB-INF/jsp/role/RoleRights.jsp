<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>

<script type="text/javascript">
	$(top.hangge());
	$(function(){ 
		 var strs= new Array();		 
		 strs=$("#test").val().split(",");
		 
		 var str_length = strs.length;
		
		  for(var i =0; i<str_length;i++)
		 {
			  $("#"+strs[i]).prop("checked",true); 
			
		 } 
	});	
	

	
	function submit(){
		var count=0;
		var roleId=$("#test2").val();
		 var rights=""; 
		 $("input[name='menu']").each(function(){               
	            if($(this).attr("checked")){   
	                count++;  
	                rights=rights+$(this).attr('id')+',';
	            }  
	        })  
	        rights =  rights.slice(0, rights.length-1);
	         $.post("<%=basePath%>role/saveRights.do",
	        		 {"roleId":roleId,
					 "rights":rights},
					 function(data){					
				$("#zhongxin").hide();	
	        	 top.Dialog.close();
	         });
		
	}
	         
</script>
</head>

<body>
<div id="zhongxin">
	<form action="newRole/saveRights.do" name="menuForm" id="menuForm" method="post">
	<table   id="table_report" class="table table-striped table-bordered ">
		<thead>
		<tr>
			<th style="text-align:center"></th>
			<th style="text-align:center;width: 300px;">名称</th>			
			<input type="hidden" id="test" value="${visibleMenu}" />
			<input type="hidden" id="test2" value="${roleId}" />
		</tr>
		</thead>
		<c:choose>
			<c:when test="${not empty topMenu}">
				<c:forEach items="${topMenu}" var="menu" varStatus="vs">				
						 	  
				<tr style="background:#E6E6FA;opacity: 0.6 " id="tr${menu.menuId }">
				<td ><input type="checkbox"  name="menu" style="opacity: 1" id=${menu.menuId} onclick="z('${menu.menuId }',id)"/></td>
				
				
				<td  class="center" >${menu.menuName }</td>				
				
				
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
				<td colspan="100">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	</form>
	</div>
	<div class="page_and_btn">
		<div>
			&nbsp;&nbsp;<a class="btn btn-small btn-success" onclick="submit();">提交</a>
		</div>
	</div>
	
</body>
</html>