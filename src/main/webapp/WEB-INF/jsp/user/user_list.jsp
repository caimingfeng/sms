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
		<div class="table-header">用户列表</div>
		<div id="table_report_wrapper" class="dataTables_wrapper" role="grid">
			<form action="user/listUsers.do" method="post" name="userForm"
				id="userForm">
				<table>
					<tr>
						<td><span class="input-icon"> <input
								autocomplete="off" id="nav-search-input" type="text"
								name="keyWord" value="${pd.keyWord }" placeholder="这里输入关键词" />
								<i id="nav-search-icon" class="icon-search"></i>
						</span></td>
						<td><input class="span10 date-picker" name="lastLoginStart"
							id="lastLoginStart" value="${pd.lastLoginStart}" type="text"
							data-date-format="yyyy-mm-dd" readonly="readonly"
							style="width: 88px;" placeholder="开始日期" title="最近登录开始" /></td>
						<td><input class="span10 date-picker" name="lastLoginEnd"
							id="lastLoginEnd" value="${pd.lastLoginEnd}" type="text"
							data-date-format="yyyy-mm-dd" readonly="readonly"
							style="width: 88px;" placeholder="结束日期" title="最近登录结束" /></td>
						<td style="vertical-align: top;"><select class="chzn-select"
							name="roleId" id="role_id" data-placeholder="请选择用户角色"
							style="vertical-align: top; width: 150px;">
								<option value=""></option>
								<option value="">全部</option>
								<c:forEach items="${roleList}" var="role">
									<option value="${role.roleId }" <c:if test="${pd.roleId eq role.roleId }"> selected="selected"</c:if> >${role.roleName }</option>
								</c:forEach>
						</select></td>
						<td style="vertical-align: top;"><select class="chzn-select"
							name="exist" id="exist" data-placeholder="数据有效性"
							style="vertical-align: top; width: 120px;">
								<option value="-1"></option>
								<option value="3" <c:if test="${pd.exist eq 3 }"> selected="selected"</c:if>>全部</option>
								<option value="0" <c:if test="${pd.exist eq 0 }"> selected="selected"</c:if>>无效</option>
								<option value="1" <c:if test="${pd.exist eq 1 }"> selected="selected"</c:if>>有效</option>
						</select></td>
						<td style="vertical-align: top;"><button
								class="btn btn-mini btn-light" onclick="search();" title="检索">
								<i id="nav-search-icon" class="icon-search"></i>
							</button></td>
						<td style="vertical-align: top;"><a
							class="btn btn-mini btn-light" onclick="toExcel();"
							title="导出到EXCEL"><i id="nav-search-icon"
								class="icon-download-alt"></i></a></td>
						<td style="vertical-align: top;"><a
							class="btn btn-mini btn-light" onclick="fromExcel();"
							title="从EXCEL导入"><i id="nav-search-icon"
								class="icon-cloud-upload"></i></a></td>
					</tr>
				</table>
				<table id="table_report"
					class="table table-striped table-bordered table-hover dataTable"
					aria-describedby="table_report_info">
					<thead>
						<tr role="row">
							<th class="center _disabled" role="columnheader"
								rowspan="1" colspan="1" aria-label="" style="width: 49px;">
								<!-- <label><input type="checkbox" id = "zcheckbox" value="1"><span class="lbl"></span></label> -->
							</th>
							<th class="" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 60px;">序号</th>
							<!-- <th class="" role="columnheader" tabindex="0" aria-controls="table_report" rowspan="1" colspan="1" style="width: 60px;" >编号</th> -->
							<th class="" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 70px;">账号</th>
							<th class="" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 70px;">角色</th>
							<!-- <th class="" role="columnheader" tabindex="0" aria-controls="table_report" rowspan="1" colspan="1" style="width: 120px;" >邮箱</th> -->
							<th class="hidden-phone " role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 150px;">最近登录</th>
							<th class="hidden-phone " role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 120px;"><i class="icon-time hidden-phone"></i>
								上次登录IP</th>
							<th class="hidden-480" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 70px;">操作</th>
							<th class="hidden-480 center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 70px;">有效性</th>
						</tr>
					</thead>
					<tbody>
						<!-- 开始循环 -->
						<c:choose>
							<c:when test="${not empty userList}">
								<c:forEach items="${userList}" var="user" varStatus="vs">
									<tr>
										<td class='center' style="width: 30px;"><c:if
												test="${user.account != 'admin'}">
												<c:if test="${user.account == USERNAME}">
													<label><input type='checkbox' name='ids'
														value="${user.userId }" disabled="disabled" /><span
														class="lbl"></span></label>
												</c:if>
												<c:if test="${user.account != USERNAME}">
													<c:if test="${user.exist == 1 }">
														<label><input type='checkbox' name='ids'
															value="${user.userId }" /><span class="lbl"></span></label>
													</c:if>
													<c:if test="${user.exist != 1 }">
														<label><input type='checkbox' name='ids'
															value="${user.userId }" disabled="disabled" /><span
															class="lbl"></span></label>
													</c:if>
												</c:if>
											</c:if> <c:if test="${user.account == 'admin'}">
												<label><input type='checkbox' disabled="disabled" /><span
													class="lbl"></span></label>
											</c:if></td>
										<td class='center' style="width: 30px;">${vs.index+1}</td>
										<%-- <td>${user.userId }</td> --%>
										<td><a>${user.account }</a></td>
										<td>${user.roleName }</td>
										<td>${user.lastLogin}</td>
										<td>${user.ip}</td>
										<td style="width: 60px;">
											<div class='hidden-phone visible-desktop btn-group'>

												<c:if test="${USERNAME == 'admin' }">
													<c:if test="${user.exist == 1}">
														<a class='btn btn-mini btn-success' title="编辑"
															onclick="editUser('${user.userId }');"><i
															class='icon-edit'></i></a>
														<a class='btn btn-mini btn-danger' title="删除"
															onclick="delUser('${user.userId }','${user.account }');"><i
															class='icon-trash'></i></a>
													</c:if>
												</c:if>

												<c:if test="${USERNAME != 'admin' }">
													<c:if test="${user.parentId != 1}">
														<c:if test="${user.exist == 1}">
															<a class='btn btn-mini btn-success' title="编辑"
																onclick="editUser('${user.userId }');"><i
																class='icon-edit'></i></a>
															<a class='btn btn-mini btn-danger' title="删除"
																onclick="delUser('${user.userId }','${user.account }');"><i
																class='icon-trash'></i></a>
														</c:if>
													</c:if>

													<c:if test="${user.account == USERNAME}">
														<c:if test="${user.exist == 1}">
															<a class='btn btn-mini btn-success' title="编辑"
																onclick="editUser('${user.userId }');"><i
																class='icon-edit'></i></a>
														</c:if>
													</c:if>
												</c:if>
											</div>
										</td>
										<td class="center"><c:if test="${user.exist eq 1}">
												<span class="glyphicon glyphicon-ok" style="color: green;"></span>
											</c:if> <c:if test="${user.exist eq 0}">
												<span class="glyphicon glyphicon-remove" style="color: red"></span>
											</c:if></td>
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
									class="btn btn-small btn-yellow" onclick="add();">新增</a> <a
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
			top.jzts();
			$("#userForm").submit();
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>user/goAddU.do';
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
		function editUser(userId){
			 top.jzts();
			 var diag = new top.Dialog();
			 //alert(currentPage);
			 diag.Drag=true;
			 diag.Title ="资料";
			 diag.URL = '<%=basePath%>user/goEditU.do?userId='+userId;
			 diag.Width = 225;
			 diag.Height = 415;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					nextPage(currentPage);
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function delUser(userId,msg){
			var flag = false;
			if(confirm("确定要删除用户"+msg+"吗？")) flag = true;
			if(flag){
				top.jzts();
				 $.ajax({
						type: "POST",
						url: '<%=basePath%>user/deleteU.do',
				    	data: {userId:userId,tm:new Date().getTime()},
						dataType:'json',
						cache: false,
						success: function(data){
							 if("success" == data.result){
									top.jzts();
									document.location.reload();
							 }else{
								 alert(data.msg);
							 }
						}
					});
			}
		}
		
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
							url: '<%=basePath%>user/deleteAllU.do?tm='+new Date().getTime(),
					    	data: {userIds:str},
							dataType:'json',
							cache: false,
							success: function(data){
								if(data.result == 'success'){
									 //zeroModal.success('操作成功!');
									 alert('操作成功');
									
								} else{
									 //zeroModal.error(data.errormsg);
									alert(data.errormsg);
								}
								$.each(data.list, function(i, list){
										nextPage(currentPage);
								 });
							}
						});
					}
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
		
		//导出excel
		function toExcel(){
			var keyWord = $("#nav-search-input").val();
			var lastLoginStart = $("#lastLoginStart").val();
			var lastLoginEnd = $("#lastLoginEnd").val();
			var roleId = $("#role_id").val();
			var exist =$("#exist").val();
			var url = '<%=basePath%>user/excel.do?tm='+new Date().getTime();
			window.location.href='<%=basePath%>user/excel.do?keyWord='+keyWord+'&lastLoginStart='+lastLoginStart+'&lastLoginEnd='+lastLoginEnd+'&roleId='+roleId+'&exist='+exist;
			
		}
		
		//打开上传excel页面
		function fromExcel(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="EXCEL 导入到数据库";
			 diag.URL = '<%=basePath%>user/goUploadExcel.do';
			diag.Width = 300;
			diag.Height = 150;
			diag.CancelEvent = function() { //关闭事件
				if (diag.innerFrame.contentWindow.document
						.getElementById('zhongxin').style.display == 'none') {
					if ('${page.currentPage}' == '0') {
						top.jzts();
						setTimeout("self.location.reload()", 100);
					} else {
						nextPage(currentPage);
					}
				}
				diag.close();
				//diag.close();
			};
			diag.show();
		}
	</script>
</body>
</html>

