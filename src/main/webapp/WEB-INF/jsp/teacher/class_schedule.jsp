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
		<div id="table_report_wrapper" class="dataTables_wrapper" role="grid">
			<form action="course/toTeacherCm.do" method="post" name="classForm"
				id="classForm">
				<table>
						<tr>
							<td><span class="input-icon" style="margin-left: 30px"> 学期：
								</span></td>
							<td style="vertical-align: top;"><select class="chzn-select"
							name="term" id="term" data-placeholder="学期"
							style="vertical-align: top; width: 120px;">
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
							<td><span class="input-icon" style="margin-left: 30px">周次：</span></td>
							<td style="vertical-align: top;"><select class="chzn-select"
							name="week" id="week" data-placeholder="周次"
							style="vertical-align: top; width: 120px;">
								<option <c:if test="${pd.week eq 1}">selected="selected"</c:if>value="1">第1周</option>
								<option <c:if test="${pd.week eq 2}">selected="selected"</c:if>value="2">第2周</option>
								<option <c:if test="${pd.week eq 3}">selected="selected"</c:if>value="3">第3周</option>
								<option <c:if test="${pd.week eq 4}">selected="selected"</c:if>value="4">第4周</option>
								<option <c:if test="${pd.week eq 5}">selected="selected"</c:if>value="5">第5周</option>
								<option <c:if test="${pd.week eq 6}">selected="selected"</c:if>value="6">第6周</option>
								<option <c:if test="${pd.week eq 7}">selected="selected"</c:if>value="7">第7周</option>
								<option <c:if test="${pd.week eq 8}">selected="selected"</c:if>value="8">第8周</option>
								<option <c:if test="${pd.week eq 9}">selected="selected"</c:if>value="9">第9周</option>
								<option <c:if test="${pd.week eq 10}">selected="selected"</c:if>value="10">第10周</option>
								<option <c:if test="${pd.week eq 11}">selected="selected"</c:if>value="11">第11周</option>
								<option <c:if test="${pd.week eq 12}">selected="selected"</c:if>value="12">第12周</option>
								<option <c:if test="${pd.week eq 13}">selected="selected"</c:if>value="13">第13周</option>
								<option <c:if test="${pd.week eq 14}">selected="selected"</c:if>value="14">第14周</option>
								<option <c:if test="${pd.week eq 15}">selected="selected"</c:if>value="15">第15周</option>
								<option <c:if test="${pd.week eq 16}">selected="selected"</c:if>value="16">第16周</option>
								<option <c:if test="${pd.week eq 17}">selected="selected"</c:if>value="17">第17周</option>
								<option <c:if test="${pd.week eq 18}">selected="selected"</c:if>value="18">第18周</option>
								<option <c:if test="${pd.week eq 19}">selected="selected"</c:if>value="19">第19周</option>
								<option <c:if test="${pd.week eq 20}">selected="selected"</c:if>value="20">第20周</option>
								<option <c:if test="${pd.week eq 21}">selected="selected"</c:if>value="21">第21周</option>
								<option <c:if test="${pd.week eq 22}">selected="selected"</c:if>value="22">第22周</option>
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
									style="width: 30px;">序号</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: 50px;">星期</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: 70px;">节次</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: 60px;">
									课程名</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: 60px;">
									上课地点</th>
							</tr>
						</thead>
					<tbody>
						<!-- 开始循环 -->
						<c:choose>
							<c:when test="${not empty list}">
								<c:forEach items="${list}" var="cm" varStatus="vs">
									<tr>
										<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td class="center" >${cm.weekNum }</td>
										<td class="center" >${cm.section}</td>
										<td class="center" >${cm.courseName}</td>
										<td class="center" >${cm.address}</td>
										
									</tr>

								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="main_info">
									<td colspan="10" class="center">没有课程信息</td>
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

