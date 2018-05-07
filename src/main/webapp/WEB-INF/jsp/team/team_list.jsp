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
		<div class="table-header">班级列表</div>
		<div id="table_report_wrapper" class="dataTables_wrapper" role="grid">
			<form action="team/teamList.do" method="post" name="teamForm"
				id="teamForm">
				<table>
					<tr>
						<td><span class="input-icon"> <input
								autocomplete="off" id="nav-search-input" type="text"
								name="keyWord" value="${pd.keyWord }" placeholder="学院检索" />
								<i id="nav-search-icon" class="icon-search"></i>
						</span></td>
						<td><span class="input-icon"> <input
								autocomplete="off" id="nav-search-input" type="text"
								name="majorName" value="${pd.majorName }" placeholder="专业检索" />
						</span></td>
						<td><span class="input-icon"> <input
								autocomplete="off" id="nav-search-input" type="text"
								name="grade" value="${pd.grade }" placeholder="年级检索" />
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
							<th class="hidden-480 center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 50px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- 开始循环 -->
						<c:choose>
							<c:when test="${not empty teamList}">
								<c:forEach items="${teamList}" var="team" varStatus="vs">
									<tr>
										<td class='center' style="width: 30px;">${vs.index+1}</td>
										<%-- <td>${user.userId }</td> --%>
										<td class="center">${team.department }</td>
										<td class="center">${team.majorName }</td>
										<td class="center">${team.grade}</td>
										<td class="center">${team.classNumber}</td>
										<td style="width: 60px;">
											<div class='hidden-phone visible-desktop btn-group'>
												<a class='btn btn-mini btn-success' title="编辑"
													onclick="editTeam('${team.majorClassId}');"><i
													class='icon-edit'></i></a> <a class='btn btn-mini btn-danger'
													title="删除"
													onclick="delMajorTeam('${team.majorClassId }','${team.majorName }${team.grade }${team.classNumber }');"><i
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
			 diag.URL = '<%=basePath%>team/goAddMajorTeam.do';
			 diag.Width = 225;
			 diag.Height = 415;
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
		function editTeam(majorClassId){
			 top.jzts();
			 var diag = new top.Dialog();
			 //alert(currentPage);
			 diag.Drag=true;
			 diag.Title ="班级信息";
			 diag.URL = '<%=basePath%>team/goEditT.do?majorClassId='+majorClassId;
			 diag.Width = 225;
			 diag.Height = 300;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					nextPage(currentPage);
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function delMajorTeam(majorClassId,msg){
			zeroModal.confirm("确定要删除"+msg+"吗？", function() {
	            $.ajax({
					type: "POST",
					url: '<%=basePath%>team/delMajorTeam.do',
			    	data: {majorClassId:majorClassId,tm:new Date().getTime()},
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
			
			//日期框
			$('.date-picker').datepicker();
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true});
		});
			
			//复选框
/* 			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			
		});	 */
	</script>
</body>
</html>

