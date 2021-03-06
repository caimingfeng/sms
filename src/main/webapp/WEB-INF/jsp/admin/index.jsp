﻿<%@ page language="java" import="java.util.*,java.io.*"  contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String IMGPATH = "";

	Properties pro = new Properties();
	String realpath = request.getRealPath("/WEB-INF/classes");
	try{
		//读取配置文件
		FileInputStream in = new FileInputStream(realpath+"/config.properties");
		pro.load(in);
	}
	catch(FileNotFoundException e){
		out.println(e);
	}
	catch(IOException e){out.println(e);}

//通过key获取配置文件
	IMGPATH = pro.getProperty("IMGPATH");


%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<base href="<%=IMGPATH%>">
	<!-- jsp文件头和头部 -->
	<%@ include file="top.jsp"%>
	<style type="text/css">
	.commitopacity{position:absolute; width:100%; height:100px; background:#7f7f7f; filter:alpha(opacity=50); -moz-opacity:0.8; -khtml-opacity: 0.5; opacity: 0.5; top:0px; z-index:99999;}
	</style>


	
</head>
<body>

	<!-- 页面顶部¨ -->
	<%@ include file="head.jsp"%>
	<div id="websocket_button"></div>
	<div class="container-fluid" id="main-container">
		<a href="#" id="menu-toggler"><span></span></a>
		<!-- menu toggler -->
		<!-- 左侧菜单 -->
		<%@ include file="left.jsp"%>

		<div id="main-content" class="clearfix">

			<div id="jzts" style="display:none; width:100%; position:fixed; z-index:99999999;">
			<div class="commitopacity" id="bkbgjz"></div>
			<div style="padding-left: 70%;padding-top: 1px;">
				<div style="float: left;margin-top: 3px;"><img src="static/images/loadingi.gif" /> </div>
				<div style="margin-top: 5px;"><h4 class="lighter block red">&nbsp;加载中 ...</h4></div>
			</div>
			</div>

			<div>
				<!-- <iframe name="mainFrame" id="mainFrame" frameborder="0" src="tab.do" style="margin:0 auto;width:100%;height:100%;"></iframe> -->
				<iframe name="mainFrame" id="mainFrame" frameborder="0" src="tab.do" style="margin:0 auto;width:100%;height:100%;"></iframe>
			</div>

		</div>
		<!-- #main-content -->
	</div>
	<!--/.fluid-container#main-container-->
	<!-- basic scripts -->
		<!-- 引入 -->


		<!-- 引入 -->
		
		<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="static/js/myjs/menusf.js"></script>
		
		<!--引入属于此页面的js -->
		<script type="text/javascript" src="static/js/myjs/index.js"></script>
</body>
</html>
