<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="com.code5.fw.data.Box" %>
<%@page import="com.code5.fw.web.BoxContext" %>
<%
	Box box = BoxContext.getThread();
	String msg = box.getString("msg");
%>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%=msg %>
</body>
</html>