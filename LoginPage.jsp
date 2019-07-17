<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	if (session.getAttribute("ValidMem") != null) {
%>
<jsp:forward page="Main.jsp"></jsp:forward>
<%
	}
%>
<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="LoginPage.css">
<meta charset="utf-8">
<title>Login page</title>
</head>

<body>
	<div class="bg-img">
			<button
				onclick="document.getElementById('id01').style.display='block'"
				class="bg-text">
				<h1 style="font-size: 50px">Chris y Min</h1>
				<p style="font-size: 30px">Let's go to Venezia</p>
			</button>

		<div id="id01" class="modal">

			<form class="modal-content animate" action="LoginOk.jsp" method="post">
				<div class="container_login">
					<label for="uname"><b>Username</b></label> <input type="text"
						placeholder="Enter Username" name="id"
						value="<%if (session.getAttribute("id") != null)
				out.println(session.getAttribute("id"));%>"
						required> <label for="psw"><b>Password</b></label> <input
						type="password" placeholder="Enter Password" name="pw" required>

					<button type="submit">Login</button>
				</div>

				<div class="container_login" style="background-color: #f1f1f1">
					<button type="button"
						onclick="document.getElementById('id01').style.display='none'"
						class="cancelbtn">Cancel</button>
				</div>
			</form>
		</div>

		<script>
			// Get the modal
			var modal = document.getElementById('id01');

			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
				if (event.target == modal) {
					modal.style.display = "none";
				}
			}
		</script>
</body>

</html>