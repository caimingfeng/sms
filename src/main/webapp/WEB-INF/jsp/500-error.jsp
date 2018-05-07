<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>

	<base href="<%=basePath%>">

	<!-- jsp文件头和头部 -->
	<%@ include file="admin/top.jsp"%> 
	
	</head> 

<body>
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="static/UI_new/js/jquery-ui-1.10.2.custom.min.js"></script>
		<script type="text/javascript" src="static/UI_new/js/jquery.easy-pie-chart.min.js"></script>
		<script type="text/javascript" src="static/UI_new/js/jquery.ui.touch-punch.min.js"></script>
		<script type="text/javascript" src="static/UI_new/js/jquery.sparkline.min.js"></script>
		<script type="text/javascript" src="static/UI_new/js/jquery.flot.min.js"></script>
		<script type="text/javascript" src="static/UI_new/js/jquery.flot.pie.min.js"></script>
		<script type="text/javascript" src="static/UI_new/js/jquery.flot.resize.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 单选框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<script type="text/javascript" src="static/UI_new/js/jquery.slimscroll.min.js"></script><!-- slimScrollBar -->
		<!-- 引入 -->	

	<div class="widget-box ">
		<div class="error-container">
			<div class="well">
				<h1 class="grey lighter smaller">
					<span class="blue bigger-125"><i class="icon-random"></i> 500</span> 服务器出现了一些问题
				</h1>
				<hr />
				<h3 class="lighter smaller"> 但我们正在努力修复 <i class="icon-wrench icon-animated-wrench bigger-125"></i> 服务器！ </h3>
				
				<div class="space"></div>
				<div>
					<h4 class="lighter smaller">同时请尝试下列方法:</h4>
					<ul class="unstyled spaced inline bigger-110">
						<li><i class="icon-hand-right blue"></i> 阅读FQA</li>
						<li><i class="icon-hand-right blue"></i> 向我们提交这个问题是如何发生的</li>
					</ul>
				</div>
				
				<hr />
				<div class="space"></div>
				
				<div class="row-fluid">
					<div class="center">
						<a href="#" class="btn btn-grey"><i class="icon-arrow-left"></i> Go Back</a>
						<a href="#" class="btn btn-primary"><i class="icon-dashboard"></i> Dashboard</a>
					</div>
				</div>
			</div>
		</div>
	</div>
		

 <script type="text/javascript">
		
		$(top.hangge());
		
		//检索
		function search(){
			$("#Form").submit();
		}
		
		//新增
		function add(){
			top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>andorra/goAdd.do';
			 diag.Width = 800;
			 diag.Height = 650;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location.reload()",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//修改
		function edit(id){
			top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>andorra/goEdit.do?id='+id;
			 diag.Width = 800;
			 diag.Height = 650;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(id){
			bootbox.confirm("确定要删除该记录?", function(result) {
				if(result) {
					var url = "<%=basePath%>andorra/delete.do?id="+id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if(data=="success"){
							nextPage(${page.currentPage});
						}
					});
				}
			});
		}
		
		</script>
		
		<script type="text/javascript">
		
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		
		</script>
 
		
<!-- codes above-->
		

	</body>
			
		
</html>

