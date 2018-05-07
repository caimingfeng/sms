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
			<form action="score/toStudentScore.do" method="post" name="scoreForm"
				id="scoreForm">
				<table>
						<tr>
							<td><span class="input-icon" style="margin-left: 30px"> 学期：
								</span></td>
							<td style="vertical-align: top;"><select class="chzn-select"
							name="term" id="term" data-placeholder="学期"
							style="vertical-align: top; width: 120px;">
								<option <c:if test="${pd.term eq '全部'}">selected="selected"</c:if>value="全部">全部</option>
								<option <c:if test="${pd.term eq '2020年 秋季'}">selected="selected"</c:if>value="2020年 秋季">2020年 秋季</option>
								<option <c:if test="${pd.term eq '2020年 春季'}">selected="selected"</c:if>value="2020年 春季">2020年 春季</option>
								<option <c:if test="${pd.term eq '2019年 秋季'}">selected="selected"</c:if>value="2019年 秋季">2019年 秋季</option>
								<option <c:if test="${pd.term eq '2019年 春季'}">selected="selected"</c:if>value="2019年 春季">2019年 春季</option>
								<option <c:if test="${pd.term eq '2018年 秋季'}">selected="selected"</c:if>value="2018年 秋季">2018年 秋季</option>
								<option <c:if test="${pd.term eq '2018年 春季'}">selected="selected"</c:if>value="2018年 春季">2018年 春季</option>
								<option <c:if test="${pd.term eq '2017年 秋季'}">selected="selected"</c:if>value="2017年 秋季">2017年 秋季</option>
								<option <c:if test="${pd.term eq '2017年 春季'}">selected="selected"</c:if>value="2017年 春季">2017年 春季</option>
							</select>
							</td>
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
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: 50px;">序号</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ">学号</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ">姓名</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ">课程编号</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ">课程名</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ">总成绩</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ">绩点</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ">学时</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ">学分</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ">课程大类</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ">修读方式</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ">成绩方式</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ">备注</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ">操作</th>
							</tr>
						</thead>
					<tbody>
						<!-- 开始循环 -->
						<c:choose>
							<c:when test="${not empty list}">
								<c:forEach items="${list}" var="score" varStatus="vs">
									<tr>
										<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td class="center" >${score.sno }</td>
										<td class="center" >${score.stuName}</td>
										<td class="center" >${score.cno}</td>
										<td class="center" >${score.courseName}</td>
										<td class="center" >${score.finalGrade }</td>
										<td class="center" >${score.gpa}</td>
										<td class="center" >${score.period}</td>
										<td class="center" >${score.credit}</td>
										<td class="center" >${score.categoryName }</td>
										<td class="center" ><c:if test="${score.state eq 1}">必修</c:if><c:if test="${score.state eq 2}">选修</c:if></td>
										<td class="center" ><c:if test="${score.scoreType eq 1}">百分制</c:if><c:if test="${score.scoreType eq 2}">五级制</c:if></td>
										<td class="center" >${score.bz}</td>
										<td >
											<div class='hidden-phone visible-desktop btn-group center'>
												<a class='btn btn-mini btn-success' title="查看明细"
													onclick="scoreQc('${score.gradeId}');"><i
													class='icon-edit'></i></a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="main_info">
									<td colspan="10" class="center"></td>
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
		
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
		});
		
		//检索
		function search(){
			top.jzts();
			$("#scoreForm").submit();
		}
		
		//开班详细信息
		function scoreQc(gradeId){
			 top.jzts();
			 var diag = new top.Dialog();
			 //alert(currentPage);
		 	diag.Drag=true;
			
			diag.Title ="成绩明细";
			diag.URL = '<%=basePath%>score/toScoreDetail.do?gradeId='+gradeId;
			
			diag.Width = 250;
			diag.Height = 115;
			diag.CancelEvent = function(){ //关闭事件
				diag.close();
			};
			diag.show();
		}
		
	</script>
</body>
</html>

