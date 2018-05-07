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
<link rel="stylesheet" href="static/css/bootstrap.min.css" />

<link rel="stylesheet"
	href="static/css/bootstrap-3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="static/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="static/css/font-awesome.min.css" />
<link rel="stylesheet" href="static/css/ace.min.css" />
<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
<link rel="stylesheet" href="static/css/ace-skins.min.css" />
<link rel="stylesheet" href="static/zeroModal/zeroModal.css" />
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
</head>
<body>
	<div class="row-fluid">
		<!-- <h3 class="header smaller lighter blue">系统用户</h3> -->
		<div class="table-header">课程列表</div>
		<div id="table_report_wrapper" class="dataTables_wrapper" role="grid">
			<form action="course/courseList.do" method="post" name="courseForm"
				id="courseForm">
				<table>
					<tr>
						<td><span class="input-icon"> <input
								autocomplete="off" id="nav-search-input" type="text"
								name="cno" value="${pd.cno }" placeholder="课程号检索" />
								<i id="nav-search-icon" class="icon-search"></i>
						</span></td>
						<td><span class="input-icon"> <input
								autocomplete="off" id="nav-search-input" type="text"
								name="courseName" value="${pd.courseName }" placeholder="课程名检索" />
						</span></td>
						<td style="vertical-align: top;"><select class="chzn-select"
							name="state" id="state" data-placeholder="必修/选修"
							style="vertical-align: top; width: 150px;">
								<option value=""></option>
								<option value="">全部</option>
								<option value="1" <c:if test="${pd.state eq 1 }"> selected="selected"</c:if> >必修</option>
								<option value="2" <c:if test="${pd.state eq 2 }"> selected="selected"</c:if> >选修</option>
						</select></td>
						<td style="vertical-align: top;"><button
								class="btn btn-mini btn-light" onclick="search();" title="检索">
								<i id="nav-search-icon" class="icon-search"></i>
							</button></td>
							
					</tr>
				</table>
				<table id="table_report"
					class="table table-striped table-bordered table-hover dataTable"
					aria-describedby="table_report_info">
					<thead>
						<tr role="row">
							<th class="sorting center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 30px;">序号</th>
							<!-- <th class="sorting" role="columnheader" tabindex="0" aria-controls="table_report" rowspan="1" colspan="1" style="width: 60px;" >编号</th> -->
							<th class="sorting center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 50px;">课程号</th>
							<th class="sorting center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 70px;">课程名</th>
							<!-- <th class="sorting" role="columnheader" tabindex="0" aria-controls="table_report" rowspan="1" colspan="1" style="width: 120px;" >邮箱</th> -->
							<th class="hidden-phone sorting center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 60px;">
								课程大类</th>
							<th class="hidden-phone sorting center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 60px;">
								必修/选修</th>
							<th class="hidden-phone sorting center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 30px;">学分</th>
							<th class="hidden-480 center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 30px;">课程简介</th>
							<th class="hidden-480 center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 40px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- 开始循环 -->
						<c:choose>
							<c:when test="${not empty courses}">
								<c:forEach items="${courses}" var="course" varStatus="vs">
									<tr>
										<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td class="center">${course.cno }</td>
										<td class="center">${course.courseName }</td>
										<td class="center">${course.category.categoryName }</td>
										<c:if test="${course.state eq 1}"><td class="center">必修</td></c:if>
										<c:if test="${course.state eq 2}"><td class="center">选修</td></c:if>
										<c:if test="${course.state eq null}"><td class="center"> </td></c:if>
										<td class="center">${course.credit}</td>
										<td class="center">${course.introduction}</td>
										<td style="width: 40px;">
											<div class='hidden-phone visible-desktop btn-group'>
												<a class='btn btn-mini btn-success' title="编辑"
													onclick="editCourse('${course.cno}');"><i
													class='icon-edit'></i></a> <a class='btn btn-mini btn-danger'
													title="删除"
													onclick="delCourse('${course.cno }','${course.courseName }');"><i
													class='icon-trash'></i></a>
											</div>
										</td>
									</tr>

								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="main_info">
									<td colspan="10" class="center">没有相关数据</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>

				<div class="row-fluid">
					<div class="page-header position-relative">
						<table style="width: 100%; margin-left: 30px;">
							<tr>
								<td style="vertical-align: top;"><a
									class="btn btn-small btn-yellow" onclick="add();">新增</a>
								</td>
								<td style="vertical-align: top;"><div class="pagination"
										style="float: right; padding-top: 0px; margin-top: 0px; margin-right: 30%">${page.pageStr}</div></td>
							</tr>
						</table>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- 引入 -->
	<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>

	<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
	<!-- 下拉框 -->
	<script type="text/javascript"
		src="static/js/bootstrap-datepicker.min.js"></script>
	<!-- 日期框 -->
	<script type="text/javascript" src="static/js/bootbox.min.js"></script>
	<!-- 确认窗口 -->
	<script type="text/javascript" src="static/zeroModal/zeroModal.min.js"></script>

	<!-- 引入 -->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<!--提示框-->
	<script type="text/javascript">
		$(top.hangge());
		var currentPage = '${page.currentPage}';
		
		 $(function () { 
			 var status = '${pd.status}';
			 if(status === '1'){
				 zeroModal.error('pd.errorMsg');
			 }
		 });
		 
		//检索
		function search(){
			top.jzts();
			$("#teamForm").submit();
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>course/goAddCourse.do';
			 diag.Width = 500;
			 diag.Height = 500;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(currentPage);
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//修改
		function editCourse(cno){
			 top.jzts();
			 var diag = new top.Dialog();
			 //alert(currentPage);
			 diag.Drag=true;
			 diag.Title ="课程信息";
			 diag.URL = '<%=basePath%>course/goEditC.do?cno='+cno;
			 diag.Width = 500;
			 diag.Height = 500;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					nextPage(currentPage);
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function delCourse(cno,msg){
			zeroModal.confirm("确定要删除"+msg+"吗？", function() {
	            $.ajax({
					type: "POST",
					url: '<%=basePath%>course/delCourse.do',
			    	data: {cno:cno,tm:new Date().getTime()},
					dataType:'json',
					cache: false,
					success: function(data){
						 if("success" == data.result){
								top.jzts();
								document.location.reload();
						 }else{
							 zeroModal.error(data.msg);
						 }
					}
				});
	        });
		}
		
	</script>
	<script type="text/javascript">
		$(function() {
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
		});	
	</script>
</body>
</html>

