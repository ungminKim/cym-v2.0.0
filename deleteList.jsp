<%@page import="com.cym.dao.*" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%
	String name = request.getParameter("name");
	CymDao dao = CymDao.getInstance();
	dao.deleteList(name);
%>
<script type="text/javascript">
	document.location.href="Main.jsp"
</script>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>