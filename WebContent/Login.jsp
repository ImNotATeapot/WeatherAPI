<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="Login.css">
</head>
	<body>
		<div id="background"></div>
		<div id="header" style="display:inline-block">
			<h1 id="wm" onclick="goHome()" style="float:left">Weather Meister</h1>
			<span style="float:right; display:inline-block">
				<h2 onclick="goLogin()" style="color: white; font-size:30px; float:left; margin-right:20px">Login</h2>
				<h2 onclick="goRegister()" style="color: white; font-size:30px; float:right; margin-right:20px">Register</h2>
			</span>
		</div>
		<div class="blur">
			<img src="img/Keychain_Locked@2x.png" style="width:50px; margin-top:-25px; margin:auto;">
			<form action="UserQuery" method="POST">
				<span class="verticalBlock">Username</span><br><input type="text" name="username" class="verticalBlock"/><br>
				<span class="verticalBlock">Password</span> <br><input type="password" name="password" class="verticalBlock"/><br>
				<input type="submit" name="submit" value="Login" class="verticalBlock"/>
			</form>
		</div>
	</body>
	<%
	Integer name = request.getAttribute("case") == null ? 0 : (Integer) request.getAttribute("case");
	if (name == 0) {
		%> <script>document.getElementById("errorMessage").innerHTML = "";</script> <%
	} else if (name==1) {
		%> <script>document.getElementById("errorMessage").innerHTML = "Username not found";</script> <%
	} else if (name == 2) {
		%> <script>document.getElementById("errorMessage").innerHTML = "Password incorrect";</script> <%
	} else if (name == 3) {
		%> <script>document.getElementById("errorMessage").innerHTML = "";</script> <%
	}
	%>
	<script>
		function goHome(){
			window.location.replace("Home.jsp");
		}
		
		function goRegister(){
			window.location.replace("Register.jsp");
		}
		
		function goLogin(){
			window.location.replace("Login.jsp");
		}
	</script>
</html>