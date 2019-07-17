<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko">
<head>
<meta charset="EUC-KR">
<%
	request.setCharacterEncoding("EUC-KR");
	String realFolder = "";
	String fileName1 = "";
	int maxSize = 1024 * 1024 * 5;
	String encType = "euc-kr";
	String savefile = "upload";
	String DIR = "C:\\Users\\user\\eclipse-workspace\\imageUpload\\WebContent\\upload";
	ServletContext scontext = getServletContext();
	realFolder = scontext.getRealPath(savefile);

	try {
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType,
				new DefaultFileRenamePolicy());

		Enumeration<?> files = multi.getFileNames();
		String file1 = (String) files.nextElement();
		fileName1 = multi.getFilesystemName(file1);
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script>
	document.location.href = "Main.jsp"
</script>
<title>Insert title here</title>
</head>
<body>

</body>
</html>