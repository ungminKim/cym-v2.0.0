<%@page import="java.io.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cym.dao.*"%>
<%@page import="com.cym.dto.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	CymDao dao = CymDao.getInstance();
	ArrayList<CymDtoList> arrayList = dao.makeList();
	CymDtoList dtolist = new CymDtoList();
	CymDto dto = new CymDto();

	String name = (String) session.getAttribute("name");
	
	String DIR = "C:\\Users\\user\\eclipse-workspace\\imageUpload\\WebContent\\upload";
	String title = request.getParameter("title"); 
%>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="Main.css">
<title>Min y Chriselle</title>
</head>
<body>
	<h1>Min y Chriselle</h1>
	<hr>
	<div id="myDIV" class="header">
		<h2 style="margin: 5px">Our To do list</h2>
		<form action="ListCheck.jsp" method="post">
			<input type="text" name="list" id="inputList" placeholder="Title...">
			<input type="text" name="name" id="inputName" value="<%=name%>"
				placeholder="Name"> <input type="submit"
				onclick="newElement()" class="addBtn" value="Add">
		</form>
	</div>

	<ul id="myUL">
		<%
			for (int i = 0; i < arrayList.size(); i++) {
				dtolist = arrayList.get(i);
		%>

		<li><%=dtolist.getList()%> <a
			href="deleteList.jsp?name=<%=dtolist.getName()%>" class="delete">&times;</a></li>

		<%
			}
		%>
	</ul>
	<script type="text/javascript">
		// Add a "checked" symbol when clicking on a list item
		var list = document.querySelector('ul');
		list.addEventListener('click', function(ev) {
			if (ev.target.tagName === 'LI') {
				ev.target.classList.toggle('checked');
			}
		}, false);
	</script>

	<div class="Album">
		<h1>Album</h1>

		<form enctype="multipart/form-data" method="post" action="imageSaveInServer.jsp">
			<label for="title">Title:</label> 
			<input type="text" id="title" name="title" class="uploadBox"><br />
			<label for="english">ImageFile:</label>
			<input type="file" id="file1" name="file1" class="uploadBox"><br />
			<input type="submit" value="Upload" name="submit" />
		</form>
		<%
			String dir = DIR;
			File f = new File(dir);
			if (f.exists()) {
				String[] filelist = f.list();
				for (int i = 0; i < filelist.length; i++) {
					File subF = new File(dir + "//" + filelist[i]);
					if (subF.isFile()) {
						String file = dir + "//" + subF.getName();
		%>
		<img src="<%=file%>" width="300px" height="300px" class="albumDisplay"/>
		<h3><%=title %></h3>
		<%
			}
				}
			}
		%>
		<form action="Logout.jsp" method="post">
			<input type="submit" value="Log Out">
		</form>
	</div>
</body>
</html>
