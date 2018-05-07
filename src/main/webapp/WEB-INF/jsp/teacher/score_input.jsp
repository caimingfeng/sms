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

<style type="text/css">
.back_color{
	border: 0;
	background-color: #f7f7f7;
}
input {
	border: 0;
	overflow: hidden;
	text-overflow: ellipsis;
	width: 100px;
}
td {
}
</style>
</head>
<body>
	<div class="row-fluid">
		<!-- <h3 class="header smaller lighter blue">系统用户</h3> -->
		<div id="table_report_wrapper" class="dataTables_wrapper" role="grid">
			<form action="score/toScoreInput.do" method="post" name="classForm"
				id="classForm">
				<table>
					<c:if test="${not empty list}">
						<tr>
							<td><span class="input-icon"> <input
									autocomplete="off" id="nav-search-input" type="text"
									name="keyWord" value="${pd.keyWord }" placeholder="教学班名检索" />
									<i id="nav-search-icon" class="icon-search"></i>
							</span></td>
							<td><span class="input-icon"> <input
									autocomplete="off" id="nav-search-input" type="text"
									name="courseName" value="${pd.courseName }" placeholder="课程名检索" />
							</span></td>
							<td style="vertical-align: top;"><button
									class="btn btn-mini btn-light" onclick="search();" title="检索">
									<i id="nav-search-icon" class="icon-search"></i>
								</button></td>
								
						</tr>
					</c:if>
				</table>
				<table id="table_report"
					class="table table-striped table-bordered table-hover dataTable"
					aria-describedby="table_report_info">
					<c:if test="${not empty list}">
						<thead>
							<tr role="row">
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: 30px;">序号</th>
								<!-- <th class="sorting" role="columnheader" tabindex="0" aria-controls="table_report" rowspan="1" colspan="1" style="width: 60px;" >编号</th> -->
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: 50px;">教学班名称</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: 70px;">教授课程</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: 60px;">
									上课人数</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: 30px;">教学方式</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: 40px;">操作</th>
							</tr>
						</thead>
					</c:if>
					<tbody>
						<!-- 开始循环 -->
						<c:choose>
							<c:when test="${not empty list}">
								<c:forEach items="${list}" var="cm" varStatus="vs">
								<input type="hidden" value="${cm.coursesMessageId }" id = "coursesMessageId${vs.index+1}">
									<tr>
										<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td class="center">${cm.teachingClass }</td>
										<td class="center" >${cm.course.courseName }</td>
										<%-- <td class="center">${cm.teaName }</td> --%>
										<td class="center" >${cm.limitSelect}</td>
										<td class="center" >${cm.type}</td>
										
										<td >
											<div class='hidden-phone visible-desktop btn-group center'>
												<a class='btn btn-mini btn-success' title="录入成绩"
													onclick="scoreInput('${cm.coursesMessageId}','${cm.scoreType}');"><i
													class='icon-edit'></i></a>
											</div>
										</td>
										
									</tr>

								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="main_info">
									<td colspan="10" class="center">没有需要录入成绩的班级</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>

				<div class="row-fluid">
					<div class="page-header position-relative">
						<table style="width: 100%; margin-left: 30px;">
							<tr>
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
		//检索
		function search(){
			top.jzts();
			$("#classForm").submit();
		}
		
		//开班详细信息
		function scoreInput(coursesMessageId,scoreType){
			 top.jzts();
			 var diag = new top.Dialog();
			 //alert(currentPage);
			 diag.Drag=true;
			 
			 if(scoreType === '0'){
				 diag.Title ="录入方式";
				 diag.URL = '<%=basePath%>score/goEditScore.do?coursesMessageId='+coursesMessageId;
			 }else{
				 diag.Title ="成绩录入";
				diag.URL = '<%=basePath%>score/toScoreInputdo.do?coursesMessageId='+coursesMessageId;
			 }
			
			 diag.Width = 1000;
			 diag.Height = 500;
			 diag.CancelEvent = function(){ //关闭事件
				 setTimeout("self.location=self.location",100);
				 diag.close();
			 };
			 diag.show();
		}
	</script>
</body>
</html>

