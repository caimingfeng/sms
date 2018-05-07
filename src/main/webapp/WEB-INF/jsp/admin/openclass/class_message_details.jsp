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
		<input type="hidden" value = "${pd.coursesMessageId }" id = "coursesMessageId">
			<form action="#.do" method="post" name="messageForm"
				id="messageForm">
				<table id="table_report"
					class="table table-striped table-bordered table-hover dataTable"
					aria-describedby="table_report_info">
					<thead>
						<tr role="row">
							<th class="center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 100px;">序号</th>
							<th class="center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 100px">周次</th>
							<th class="center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 100px;">星期</th>
							<th class="hidden-phone center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 100px">节次</th>
							<th class="hidden-phone center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 100px">上课地点</th>
							<th class="hidden-480 center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width:100px">操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- 开始循环 -->
						<c:choose>
							<c:when test="${not empty list}">
								<c:forEach items="${list}" var="courseMessage" varStatus="vs">
								<input type="hidden" id = "coursesMessageId${vs.index+1}" value="${courseMessage.coursesMessageId }">
									<tr>
										<td class='center' style="width: 30px;">${vs.index+1}</td>
										<%-- <td>${user.userId }</td> --%>
										<td class="center" contenteditable="true">${courseMessage.week }</td>
										<td class="center" contenteditable="true">${courseMessage.weekNum }</td>
										<td class="center" contenteditable="true">${courseMessage.section}</td>
										<td class="center" contenteditable="true">${courseMessage.address}</td>
										<td style="width: 60px;">
											<div class='hidden-phone visible-desktop btn-group'>
												<a class='btn btn-mini btn-danger'
													title="删除"
													onclick="delClassMessage('${courseMessage.coursesMessageId }');"><i
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
									class="btn btn-small btn-yellow" onclick="add('${pd.coursesMessageId }');">新增</a>
								</td>
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
		 
		//新增
		function add(coursesMessageId){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>openClass/goAddMessage.do?coursesMessageId='+coursesMessageId;
			 diag.Width = 225;
			 diag.Height = 350;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
						top.jzts();
						setTimeout("self.location=self.location",100);
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function delClassMessage(coursesMessageId){
			zeroModal.confirm("确定要删除此条开班信息吗？", function() {
	            $.ajax({
					type: "POST",
					url: '<%=basePath%>openClass/delelteMessage.do',
			    	data: {coursesMessageId:coursesMessageId,tm:new Date().getTime()},
					dataType:'json',
					cache: false,
					success: function(msg){
						 if("success" == msg){
								top.jzts();
								document.location.reload();
						 }else{
							 zeroModal.error(msg);
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
			
			var oldValue;
			
			$('table').on('click','[contenteditable="true"]',function(){
				var c = this.cellIndex;//获取列
				//console.log("点击了");
				oldValue = $.trim(this.innerText);
				//console.log('原来数据：'+oldValue);
				r = this.parentElement.rowIndex;//获取行
		    });
			
			 $('table').on('blur','[contenteditable="true"]',function(){
				 var td = this;
				 var c = this.cellIndex;//获取列
				 var newValue = $.trim(this.innerText);
				 if(newValue!=oldValue){
						var data;
						var key;
						if(c === 1){
							key = "week";
						}else if(c === 2){
							key = "weekNum";
						} else if(c === 3){
							key = "section";
						}
						else if(c === 4){
							key = "address";
						}
						
						var coursesMessageId = $("#coursesMessageId"+r).val();
						var str = '{"coursesMessageId":"'+coursesMessageId+'","'+key+'":"'+newValue+'"}';
						//console.log(str);
						data = JSON.parse(str);
						
						$.post("openClass/editMessage",data,function(msg){
							if(msg === 'success'){
								zeroModal.success('修改成功');
							}else{
								zeroModal.error(msg);
								td.innerText = oldValue;
							}
						},'json');
						
				 }else{
					 console.log('不做任何事');
				 }
			 });
		});
	</script>
</body>
</html>

