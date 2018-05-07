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
<meta charset="utf-8" />
<title></title>
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="static/css/font-awesome.min.css" />
<!-- 下拉框 -->
<link rel="stylesheet" href="static/css/chosen.css" />
<link rel="stylesheet" href="static/css/ace.min.css" />
<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
<link rel="stylesheet" href="static/css/ace-skins.min.css" />
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>

<script type="text/javascript">
	$(top.hangge());
	$(document).ready(function() {
	});

	//保存
	function save() {
		if (!checkNull())
			return;
		var form = $("#courseForm");
		form.submit();
	}

	function checkNull() {
		var cno = $("#cno").val().replace(/\s+/g, "");
		var courseName = $("#courseName").val().replace(/\s+/g, "");
		var categoryId = $("#categoryId").val();
		var state = $("#state").val();
		//console.log(categoryId);
		if (cno === '') {
			$("#cno").tips({
				side : 3,
				msg : '值不能为空',
				bg : '#AE81FF',
				time : 2
			});
			return false;
		}
		if (courseName === '') {
			$("#courseName").tips({
				side : 3,
				msg : '值不能为空',
				bg : '#AE81FF',
				time : 2
			});
			return false;
		}
		if (categoryId === '' || categoryId === null) {
			$("#categoryId").tips({
				side : 3,
				msg : '请选择课程大类',
				bg : '#AE81FF',
				time : 2
			});
			return false;
		}
		if (state === '' || state === null) {
			$("#state").tips({
				side : 3,
				msg : '请选择修读方式',
				bg : '#AE81FF',
				time : 2
			});
			return false;
		}
		return true;
	}
</script>

<style type="text/css">
label {
	float: left;
	width: 60px;
	padding-top: 10px;
	text-align: right;
	margin-right: 5px;
}

div {
	clear: left;
}
</style>
</head>
<body>
	<form action="course/editCourse.do" name="courseForm" id="courseForm"
		method="post">
		<input type="hidden" name="cno" id="cno" value="${course.cno }" />
		<div id="zhongxin" style="padding: 90px">
			<div>
				<label>课程编号</label><input type="text" name="cno" id="cno"
					value="${course.cno }" disabled="disabled" />
			</div>
			<div>
				<label>课程名</label><input type="text" name="courseName"
					id="courseName" placeholder="如:大学英语" value="${course.courseName }" />
			</div>
			<div>
				<label>课程大类</label> <span><select
					class="chzn-select" name="categoryId" id="categoryId"
					data-placeholder="如：公共基础课"
					style="vertical-align: top; width: 220px;">
				</select></span>
			</div>
			<div>
				<label>学分</label><input type="text" name="credit"
					value="${course.credit }" />
			</div>
			<div>
				<label>学时</label><input type="text" name="period"
					value="${course.period }" />
			</div>
			<div>
				<label>修读方式</label> <select class="chzn-select" name="state"
					id="state" data-placeholder="选修/必修"
					style="vertical-align: top; width: 220px;">
					<option value=""></option>
					<option value="1"
						<c:if test="${course.state eq 1 }"> selected="selected"</c:if>>必修</option>
					<option value="2"
						<c:if test="${course.state eq 2 }"> selected="selected"</c:if>>选修</option>
				</select>
			</div>
			<div>
				<label>备注</label><input type="text" name="bz"
					value="${course.bz }" />
			</div>
			<div>
				<label>课程简介</label>
				<textarea rows="2" cols="1" name="introduction"></textarea>
			</div>
			<div class="center">
				<a class="btn btn-mini btn-primary" onclick="save();">保存</a> <a
					class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
			</div>

			<!-- 		 -->
		</div>

		<div id="zhongxin2" class="center" style="display: none">
			<br />
			<br />
			<br />
			<br />
			<img src="static/images/jiazai.gif" /><br />
			<h4 class="lighter block green"></h4>
		</div>

	</form>

	<!-- 引入 -->
	<script type="text/javascript" src="static/js/jquery-1.9.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>
	<!--提示框-->
	<script src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
	<!-- 下拉框 -->
	<script type="text/javascript">
		$(function() {
			//单选框
			$(".chzn-select").chosen();
			$(".chzn-select-deselect").chosen({
				allow_single_deselect : true
			});
			
			var url = "course/getAllCategory";
			var categoryId = ${course.categoryId};
			$.get(url, function(data) {
				for (var i = 0; i < data.length; i++) {
					if (categoryId === data[i].categoryId) {
						$("#categoryId").append(
								'<option selected="selected" value='+data[i].categoryId+' >'
										+ data[i].categoryName + '</option>');
					} else {
						$("#categoryId").append(
								'<option value='+data[i].categoryId+' >'
										+ data[i].categoryName + '</option>');
					}
					$("#categoryId").trigger("liszt:updated");
				}

			}, 'json');
		});

	</script>
</body>
</html>


