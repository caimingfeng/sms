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
<link rel="stylesheet"
	href="static/css/bootstrap-3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="static/zeroModal/zeroModal.css" />
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
</head>
<body>
	<div class="row-fluid">
		<div id="table_report_wrapper" class="dataTables_wrapper" role="grid">
			<form action="" method="post" onsubmit="return false;">
				<table style="margin: 100px 400px;">
					<tr>
						<td><span class="input-icon" style="margin-left: 30px">
								学期： </span></td>
						<td style="vertical-align: top;"><select class="chzn-select"
							name="term" id="term" data-placeholder="学期"
							style="vertical-align: top; width: 120px;">
								<option
									<c:if test="${pd.term eq '全部'}">selected="selected"</c:if>
									value="全部">全部</option>
								<option
									<c:if test="${pd.term eq '2020年 秋季'}">selected="selected"</c:if>
									value="2020年 秋季">2020年 秋季</option>
								<option
									<c:if test="${pd.term eq '2020年 春季'}">selected="selected"</c:if>
									value="2020年 春季">2020年 春季</option>
								<option
									<c:if test="${pd.term eq '2019年 秋季'}">selected="selected"</c:if>
									value="2019年 秋季">2019年 秋季</option>
								<option
									<c:if test="${pd.term eq '2019年 春季'}">selected="selected"</c:if>
									value="2019年 春季">2019年 春季</option>
								<option
									<c:if test="${pd.term eq '2018年 秋季'}">selected="selected"</c:if>
									value="2018年 秋季">2018年 秋季</option>
								<option
									<c:if test="${pd.term eq '2018年 春季'}">selected="selected"</c:if>
									value="2018年 春季">2018年 春季</option>
								<option
									<c:if test="${pd.term eq '2017年 秋季'}">selected="selected"</c:if>
									value="2017年 秋季">2017年 秋季</option>
								<option
									<c:if test="${pd.term eq '2017年 春季'}">selected="selected"</c:if>
									value="2017年 春季">2017年 春季</option>
						</select></td>
						<td style="vertical-align: top; padding-left: 20px"><button onclick="gpaQc();">查询</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 引入 -->
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
	<!-- <script src="static/js/teacherjs/infor.js"></script> -->

	<!-- 引入 -->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<!--提示框-->
	<script type="text/javascript">
		$(top.hangge());

		$(function() {

			//单选框
			$(".chzn-select").chosen();
			$(".chzn-select-deselect").chosen({
				allow_single_deselect : true
			});

		});

		function gpaQc() {
			var url = "score/calculationGpa";
			var term = $("#term").val();
			$.get(url, {
				term : term
			}, function(data) {
				//alert(data.msg);
				if (data.msg === 'success') {
					zeroModal.success("平均绩点："+ data.avgGpa);
				} else {
					zeroModal.error(data.msg);
				}
			}, 'json');

		}
	</script>
</body>
</html>

