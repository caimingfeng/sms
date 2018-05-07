<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/5
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="utf-8"%>
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

