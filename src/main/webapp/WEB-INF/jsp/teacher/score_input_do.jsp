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
			<form action="score/toScoreInputdo.do" method="post" name="classForm"
				id="classForm">
				<input type="hidden" id="coursesMessageId" name="coursesMessageId" value="${pd.coursesMessageId}"> 
				<table>
					<tr>
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
							<th class="center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 50px;">序号</th>
							<!-- <th class="sorting" role="columnheader" tabindex="0" aria-controls="table_report" rowspan="1" colspan="1" style="width: 60px;" >编号</th> -->
							<th class="center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: ;">学号</th>
							<th class="center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: ;">姓名</th>
							<c:if test="${scoreType eq 1}">
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ;">
									平时成绩</th>
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ;">期末成绩</th>
							</c:if>
							<c:if test="${scoreType eq 2}">
								<th class="center" role="columnheader" tabindex="0"
									aria-controls="table_report" rowspan="1" colspan="1"
									style="width: ;">总成绩</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<!-- 开始循环 -->
						<c:choose>
							<c:when test="${not empty list}">
								<c:forEach items="${list}" var="stu" varStatus="vs">
								<input type="hidden" value="${stu.gradeId }" id = "gradeId${vs.index+1}">
									<tr>
										<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td class="center" >${stu.sno }</td>
										<td class="center" >${stu.stuName }</td>
										<%-- <td class="center">${cm.teaName }</td> --%>
										<c:if test="${scoreType eq 1}">
											<td class="center" contenteditable="true">${stu.regularGrade}</td>
											<td class="center" contenteditable="true">${stu.examGrade}</td>
										</c:if>
										
										<c:if test="${scoreType eq 2}">
											<td class="center" contenteditable="true">${stu.finalGrade}</td>
										</c:if>
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
									class="btn btn-small btn-yellow" onclick="saveFinal();">提交</a>
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
		var scoreType = '${scoreType}';
		//检索
		function search(){
			top.jzts();
			$("#classForm").submit();
		}
		
		var r;
		$(function() {
			
			$('table').on('click','[contenteditable="true"]',function(){
				var c = this.cellIndex;//获取列
				oldValue = $.trim(this.innerText);
				var tableId = document.getElementById("table_report");
				r = this.parentElement.rowIndex;//获取行
				//console.log(scoreType);
		    });
			 //直接编辑某些列
		    $('table').on('blur','[contenteditable="true"]',function(){
		    	var c = this.cellIndex;//获取列
				var newValue = $.trim(this.innerText);
				//console.log('修改后数据：'+newValue);
				if(newValue!=oldValue){
					var data;
					var key;
					//console.log("获得列"+c);
					if(c === 3){
						if(parseInt(scoreType) == 2){
							console.log("11111111111");
							key = "finalGrade";
						}else{
							key = "regularGrade";
							console.log("2222222222");
						}
						
					} else if(c === 4){
						key = "examGrade";
					}
					
					var gradeId = $("#gradeId"+r).val();
					console.log(gradeId);
					var str = '{"gradeId":"'+gradeId+'","'+key+'":"'+newValue+'"}';
					//console.log(str);
					data = JSON.parse(str);
					//console.log(data);
					
					//console.log("发送修改申请");
					var url = "score/saveScore";
					var td = this;
					$.post(url,data,function(result){
						if(result == 'success'){
							//document.location.reload();
						} else {
							td.innerText=oldValue;
						}
					},"json");
				}
		    });
		});
		
		function saveFinal(){
			var coursesMessageId= $("#coursesMessageId").val();
			zeroModal.confirm({
	            content: '确定提交成绩吗？',
	            contentDetail: '提交后不能进行修改。',
	            okFn: function() {
	            	$.post("score/saveFinal",{coursesMessageId:coursesMessageId},function(msg){
	    				if(msg =='success'){
	    					top.Dialog.close();
	    				}else{
	    					zeroModal.error(msg);
	    				}
	    			},'json');
	            },
	            cancelFn: function() {
	            }
	        });
			
		}
	</script>
</body>
</html>

