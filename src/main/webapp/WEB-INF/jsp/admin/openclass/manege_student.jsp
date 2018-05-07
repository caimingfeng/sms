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
<%@ include file="../top.jsp"%>
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
		<div class="table-header"></div>
		<div id="table_report_wrapper" class="dataTables_wrapper" role="grid">
			<form action="openClass/listStudentM.do" method="post" name="studentForm"
				id="studentForm">
				<input type="hidden" value="${pd.coursesMessageId } " name = "coursesMessageId">
				<table>
					<tr>
						<td><span class="input-icon"> <input
								autocomplete="off" id="nav-search-input" type="text"
								name="majorName" value="${pd.majorName }" placeholder="专业检索" />
						</span></td>
						<td><span class="input-icon"> <input
								autocomplete="off" id="nav-search-input" type="text"
								name="grade" value="${pd.grade }" placeholder="年级检索" />
						</span></td>
						<td><span class="input-icon"> <input
								autocomplete="off" id="nav-search-input" type="text"
								name="classNumber" value="${pd.classNumber }" placeholder="班级检索" />
								<i id="nav-search-icon" class="icon-search"></i>
						</span></td>
						<td><span class="input-icon"> <input
								autocomplete="off" id="nav-search-input" type="text"
								name="sno" value="${pd.sno }" placeholder="学号检索" />
								<i id="nav-search-icon" class="icon-search"></i>
						</span></td>
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
						<th class="center _disabled" role="columnheader"
								rowspan="1" colspan="1" aria-label="" style="width: 49px;">
								<label><input type="checkbox" id = "zcheckbox" value="1"><span class="lbl"></span></label>
							</th>
							<th class="center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 30px;">序号</th>
							<!-- <th class="sorting" role="columnheader" tabindex="0" aria-controls="table_report" rowspan="1" colspan="1" style="width: 60px;" >编号</th> -->
							<th class="center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 70px;">学院</th>
							<th class="center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 70px;">专业名</th>
							<!-- <th class="sorting" role="columnheader" tabindex="0" aria-controls="table_report" rowspan="1" colspan="1" style="width: 120px;" >邮箱</th> -->
							<th class="hidden-phone center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 50px;">年级</th>
							<th class="hidden-phone center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 50px;">
								班级</th>
							<th class="hidden-phone center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 50px;">
								学号</th>
							<th class="hidden-phone center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 50px;">
								姓名</th>
						</tr>
					</thead>
					<tbody>
						<!-- 开始循环 -->
						<c:choose>
							<c:when test="${not empty students}">
								<c:forEach items="${students}" var="student" varStatus="vs">
									<tr>
									<td class='center'><label><input type='checkbox' name='ids'
															value="${student.gradeId }" /><span class="lbl"></span></label></td>
										<td class='center' style="width: 30px;">${vs.index+1}</td>
										<%-- <td>${user.userId }</td> --%>
										<td class="center">${student.department }</td>
										<td class="center">${student.majorName }</td>
										<td class="center">${student.grade}</td>
										<td class="center">${student.classNumber}</td>
										<td class="center">${student.sno}</td>
										<td class="center">${student.stuName}</td>
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
									class="btn btn-small btn-yellow" onclick="add('${pd.coursesMessageId }');">新增</a>
									<a
									title="批量删除" class="btn btn-small btn-danger"
									onclick="makeAll('确定要删除选中的数据吗?');"><i class='icon-trash'></i></a>
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
			//top.jzts();
			$("#studentForm").submit();
		}
		
		//新增
		function add(coursesMessageId){
			 top.jzts();
			 var coursesMessageId = coursesMessageId;
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>student/studentList.do?coursesMessageId='+coursesMessageId;
			 diag.Width = 1000;
			 diag.Height = 500;
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
				 if(currentPage === '0' ){
					 $("#studentForm").submit();
				 }else{
					 nextPage(currentPage);
				 }
				
			 };
			 diag.show();
		}
		
	</script>
	<script type="text/javascript">
		$(function() {
			
			//日期框
			$('.date-picker').datepicker();
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true});
			
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
		});
		
			
		//批量操作
		function makeAll(msg){
			zeroModal.confirm(msg, function() {
	            //alert('ok');
	            	var str = '';
					var emstr = '';
					var phones = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  }
					}
	            
					if(str == ''){
						zeroModal.alert('没有选择任何内容!');
					} else {
						top.jzts();
						$.ajax({
							type: "POST",
							url: '<%=basePath%>openClass/deleteStudentByGradeId.do?tm='+new Date().getTime(),
					    	data: {gradeIds:str},
							dataType:'json',
							cache: false,
							success: function(msg){
								if(msg == 'success'){
									 zeroModal.success('操作成功!');
									 //alert('操作成功');
									 nextPage(currentPage);
								} else{
									 zeroModal.error(msg);
								}
								
							}
						});
					}
			});
		}
	</script>
</body>
</html>

