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
<body onclick="allClick();">
	<div class="row-fluid">
		<!-- <h3 class="header smaller lighter blue">系统用户</h3> -->
		<div class="table-header">开班列表</div>
		<div id="table_report_wrapper" class="dataTables_wrapper" role="grid">
			<form action="openClass/openClassList.do" method="post" name="classForm"
				id="classForm">
				<table>
					<tr>
						<td><span class="input-icon"> <input
								autocomplete="off" id="nav-search-input" type="text"
								name="keyWord" value="${pd.keyWord }" placeholder="开班名检索" />
								<i id="nav-search-icon" class="icon-search"></i>
						</span></td>
						<td><span class="input-icon"> <input
								autocomplete="off" id="nav-search-input" type="text"
								name="courseName" value="${pd.courseName }" placeholder="课程名检索" />
						</span></td>
						<td><span class="input-icon"> <input
								autocomplete="off" id="nav-search-input" type="text"
								name="teaName" value="${pd.teaName }" placeholder="教师检索" />
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
								style="width: 50px;">开班名称</th>
							<th class="center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 70px;">教授课程</th>
							<th class="center" role="columnheader" tabindex="0"
								aria-controls="table_report" rowspan="1" colspan="1"
								style="width: 60px;">
								授课教师</th>
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
					<tbody>
						<!-- 开始循环 -->
						<c:choose>
							<c:when test="${not empty list}">
								<c:forEach items="${list}" var="cm" varStatus="vs">
								<input type="hidden" value="${cm.coursesMessageId }" id = "coursesMessageId${vs.index+1}">
									<tr>
										<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td class="center" contenteditable="true">${cm.teachingClass }</td>
										<td class="center" id= "id1" role="columnheader" tabindex="0" onblur="exit(this);" onclick="selectCourse(this);">${cm.course.courseName }</td>
										<%-- <td class="center">${cm.teaName }</td> --%>
										<td class="center" role="columnheader" tabindex="0">${cm.teaName }</td>
										<td class="center" contenteditable="true">${cm.limitSelect}</td>
										<td class="center" contenteditable="true">${cm.type}</td>
										<td >
											<div class='hidden-phone visible-desktop btn-group center'>
												<a class='btn btn-mini btn-success' title="详细信息"
													onclick="classDetails('${cm.coursesMessageId}');"><i
													class='icon-edit'></i></a>
													<a class='btn btn-mini btn-primary'
													title="学生管理"
													onclick="studentM('${cm.coursesMessageId}');"><i
													class='icon-edit'></i></a>
													<c:if test="${cm.course.state eq 2}">
													<a class='btn btn-mini btn-inverse'
													title="开放选课"
													onclick="openCourse('${cm.cno}');"><i
													class='icon-edit'></i></a>
													</c:if>
													<a class='btn btn-mini btn-danger'
													title="删除"
													onclick="delClass('${cm.coursesMessageId}','${cm.teachingClass }');"><i
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
									class="btn btn-small btn-yellow" onclick="add();">开班</a>
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
		//检索
		function search(){
			top.jzts();
			$("#classForm").submit();
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>openClass/goAddC.do';
			 diag.Width = 222;
			 diag.Height = 350;
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
		
		//开班详细信息
		function classDetails(coursesMessageId){
			 top.jzts();
			 var diag = new top.Dialog();
			 //alert(currentPage);
			 diag.Drag=true;
			 diag.Title ="开班上课信息";
			 diag.URL = '<%=basePath%>openClass/goEditC.do?coursesMessageId='+coursesMessageId;
			 diag.Width = 1000;
			 diag.Height = 500;
			 diag.CancelEvent = function(){ //关闭事件
				 /* if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					nextPage(currentPage);
				} */
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function delClass(coursesMessageId,teachingClass){
			zeroModal.confirm("确定要删除"+teachingClass+"吗？", function() {
	            $.ajax({
					type: "POST",
					url: '<%=basePath%>openClass/delClass.do',
			    	data: {coursesMessageId:coursesMessageId,tm:new Date().getTime()},
					dataType:'json',
					cache: false,
					success: function(data){
						 if("success" == data){
								top.jzts();
								//zeroModal.success('删除成功');
								document.location.reload();
						 }else{
							 zeroModal.error(data);
						 }
					}
				});
	        });
		}
		
	</script>
	<script type="text/javascript">
		var oldValue;//保存原来的数据
		var teachingClass;//保存开班名称
		var r;//保存上一次点击td单元格的所在行
		var td = null;//保存当上一次点击过的td对象单元格
		var exitTd = null;//保存当上一次失去焦点的td对象
		$(function() {
			
			$('table').on('click','[contenteditable="true"]',function(){
				var c = this.cellIndex;//获取列
				//console.log("点击了");
				oldValue = $.trim(this.innerText);
				//console.log('原来数据：'+oldValue);
				var tableId = document.getElementById("table_report");
				r = this.parentElement.rowIndex;//获取行
				teachingClass = tableId.rows[r].cells[1].innerText;//根据开班名称进行修改
				//console.log(teachingClass);
		    });
			
			 //直接编辑某些列
		    $('table').on('blur','[contenteditable="true"]',function(){
		    	var c = this.cellIndex;//获取列
				var newValue = $.trim(this.innerText);
				//console.log('修改后数据：'+newValue);
				if(newValue!=oldValue){
					var data;
					var key;
					if(c === 1){
						key = "teachingClass2";
					}else if(c === 4){
						key = "limitSelect";
					} else if(c === 5){
						key = "type";
					}
					
					var coursesMessageId = $('#coursesMessageId'+r).val();
					var str = '{"coursesMessageId":"'+coursesMessageId+'","'+key+'":"'+newValue+'"}';
					//console.log(str);
					data = JSON.parse(str);
					//console.log(data);
					
					//console.log("发送修改申请");
					var url = "openClass/editClass";
					var td = this;
					$.post(url,data,function(result){
						if(result == 'success'){
							//document.location.reload();
							//console.log(result);
							zeroModal.success('修改成功');
						} else {
							td.innerText=oldValue;
							zeroModal.error(result);
						}
					},"json");
				}
		    });
		});
		
		var flag = false;//标识下次点击是否为select标签对象
		var courseListFlag = false;//标识是否有课程名称列表
		var oldValue2;//保存原来的课程名称 
		var dataCourse=null;//保存课程名称列表
		
		//修改课程名
		function editCourse2(obj){
			//console.log('获取修改后信息');
			var coursesMessageId = $('#coursesMessageId'+r).val();
			
			var cno2 = obj.value;
			var courseName = $(obj).find('option:selected').text();
			zeroModal.confirm("确定修改为"+courseName+"吗？", function() {
				$.post('openClass/editClass',{cno:cno2,coursesMessageId:coursesMessageId},function(result){
					if(result === 'success'){
						//document.location.reload();
						zeroModal.success('修改成功');
						td.innerText=courseName;
						oldValue2 = courseName;
						//console.log('原来数据变更为' + oldValue2);
					} else {
						//console.log(td);
						td.innerText=oldValue2;
						zeroModal.error(result);
					}
				},'json');
			});
			
			td.innerText=oldValue2;
		}
		
		function selectCourse(obj){
			if(flag){
				return;
			}
			//console.log("第一次聚焦");
			td = obj;
			
			if(exitTd!=null||exitTd!=undefined){
				if(td != exitTd){
					//console.log('失去焦点的单元格不是点击的单元格');
					exitTd.innerText = oldValue2;
				}else{
					//console.log('单元格失去焦点,但点击了单元格内部的单选框');
					exitTd = null;
					return;
				}
			} 
			selectClick();
			
			if(oldValue2=='' || oldValue2 == undefined){
				oldValue2 = obj.innerText;
			}
			
			oldValue2 = obj.innerText;
			var tableId = document.getElementById("table_report");
			r = td.parentElement.rowIndex;//获取行
			teachingClass = tableId.rows[r].cells[1].innerText;//获得开班名称
			
			if(courseListFlag){
				//console.log('数据已存在');
				var str = '';
				for(var i=0;i<dataCourse.length;i++){
					if(oldValue2 === dataCourse[i].courseName) {
						str = str+'<option value="'+dataCourse[i].cno+'" selected="selected">'+dataCourse[i].courseName+'</option>';
					} else{
						str = str+'<option value="'+dataCourse[i].cno+'">'+dataCourse[i].courseName+'</option>';
					}
					
				}
				courseList = '<span onclick="selectClick(this);"><select  class="chzn-select" onchange="editCourse2(this);" style="vertical-align: top; width: 150px;">'
				+str+'</select></span>';
				td.innerHTML=courseList;
				
				$(".chzn-select").chosen(); 
				$(".chzn-select-deselect").chosen({allow_single_deselect:true});
				console.log('下拉框初始化完成');
			} else{
				$.get('openClass/getAllCourse',function(data){
					//console.log(data);
					var str = '';
					dataCourse = data;
					for(var i=0;i<data.length;i++){
						
						if(oldValue2 === data[i].courseName) {
							str = str+'<option  value="'+data[i].cno+'" selected="selected">'+data[i].courseName+'</option>';
						} else{
							str = str+'<option value="'+data[i].cno+'">'+data[i].courseName+'</option>';
						}
						
					}
					courseList = '<span onclick="selectClick();"><select class="chzn-select" onchange="editCourse2(this);" style="vertical-align: top; width: 150px;">'
					+str+'</select></span>';
					td.innerHTML=courseList;
					$(".chzn-select").chosen(); 
					$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
					//console.log('下拉框初始化完成');
				},'json');
			}
		}
		
		function exit(obj){
			//console.log("离开单元格");
			exitTd = obj;
			window.setTimeout(function(){
				//console.log(flag);
				if(!flag){//检查是否是离开了单元格内的单选框,true: 不是, false:离开
					//console.log('离开单元格，且不进入单元格内的单选框');
					td.innerText = oldValue2;
				}
				
			},500);
		}
		var flag2 = false;
		function selectClick(){
			flag = true;
			//console.log('进入单选框....');
			flag2 = true;
		}
		function allClick(){
			//console.log('点击了body');
			if(flag2){
				//console.log('点击了单选框');
				flag2 = false;
			} else{
				if(flag){
					//console.log('点击了单元格');
					flag = false;
					exit();
					//td.innerText = oldValue2;
				}
			}
		}
		
		
		//将学生拉进教学班
		function studentM(coursesMessageId){
			top.jzts();
			 var diag = new top.Dialog();
			 //alert(currentPage);
			 diag.Drag=true;
			 diag.Title ="教学班学生管理";
			 diag.URL = '<%=basePath%>openClass/listStudentM.do?coursesMessageId='+coursesMessageId;
			 diag.Width = 1000;
			 diag.Height = 500;
			 diag.CancelEvent = function(){ //关闭事件
				 /* if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					nextPage(currentPage);
				} */
				diag.close();
			 };
			 diag.show();
		}
		
		//开放选课
		function openCourse(cno){
	        zeroModal.show({
	            title: '开放选课',
	            content: '<select name="open" id="open" ><option value="1">开放</option><option value="0">关闭</option></select>',
	            ok:true,
	            cancel: true,
	            okFn: function() {
	            	var open = $("#open").val();
	            	editOpen(cno,open);
	            	return true;
	            }
	        });
		}
		
		function editOpen(cno,open){
			$.post("course/editOpen",{cno:cno,open:open},function(msg){
				//alert("1111111");
				if(msg==='success'){
					zeroModal.success("操作成功");
				}else {
					zeroModal.error("操作失败," + msg);
				}
			},'json');
		}
		
	</script>
</body>
</html>

