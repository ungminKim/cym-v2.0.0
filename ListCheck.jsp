<%@page import="com.cym.dao.*" %>
<%@page import="com.cym.dto.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("EUC-KR");
	
	String list = request.getParameter("list");
	String name = request.getParameter("name");
	
	CymDtoList dtolist = new CymDtoList();
	dtolist.setList(list);
	dtolist.setName(name);
	
	CymDao dao = CymDao.getInstance();
	dao.addList(dtolist);
%>
<script>
	document.location.href="Main.jsp"
</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>