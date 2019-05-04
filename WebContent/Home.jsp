<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="Home.css">
		<meta charset="UTF-8">
		<title>Home</title>
	</head>
	<body>
		<div id="background"></div>
		<div id="map" style="position: absolute; height: 80%; width: 80%; margin-top:100px; margin-left:150px; z-index:1000; display: none;"></div>
			<script>     
			var map; 
      		function initMap() {
        		map = new google.maps.Map(document.getElementById('map'), {
          			center: {lat: 34.197, lng: -117.644},
          			zoom: 10
       			});
        		
        		map.addListener('click', function(self) {
        			document.getElementById('map').style.display = 'none';
        			document.getElementById('latitude').value = self.latLng.lat();
        			document.getElementById('longitude').value = self.latLng.lng();
        			
        		});
      		}
      		
      		
    		</script>
    	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVj8szXn2KiPOLco5p-d4Zoj9-iQ2QgPM&callback=initMap" async defer></script>
		<div id="header" style="display:inline-block">
			<h1 id="wm" onclick="goHome()" style="float:left">Weather Meister</h1>
			<span style="float:right; display:inline-block">
				<h2 id="opt1" onclick="goLogin()" style="color: white; font-size:30px; float:left; margin-right:20px">Login</h2>
				<h2 id="opt2" onclick="goRegister()" style="color: white; font-size:30px; float:right; margin-right:20px">Register</h2>
			</span>
		</div>
		<div id="content">
			<img src="img/logo.png">
			<div id="form">
				<form action="CitySearchServlet" method="post">
					<div id="cityInput">
						<input style="width:100%" type="text" name="city" placeholder="Los Angeles" id="city">
						<input type="submit" value="">
					</div>
				</form>
				<form action="LatLongSearchServlet" method="post">
					<div id="locationInput" style="display:none">
						<input type="text" name="latitude" id="latitude" placeholder="Latitude">
						<input style="margin-left: 10px" type="text" name="longitude" id="longitude" placeholder="Longitude">
						<img src="img/MapIcon.png" onclick="popUp()" style="margin-right: 10px; margin-top:-42px; width:40px; height:40px; border-radius:6px; position:relative;">
						<input type="submit" value="" id="submit" style="margin-right: 50px;">
					</div>
					<div id="radio">
						<input type="radio" id="city" name="input" onclick="displayForm()" checked>City
						<input type="radio" id="location" name="input" onclick="displayForm()">Location (Lat./Long.)
					</div>
				</form>
			</div>
		</div>
	</body>
	<%
		Integer user = request.getAttribute("case") == null ? 0 : (Integer) request.getAttribute("case");
		if (user == 3) {
			%> <script>document.getElementById("opt1").innerHTML = 'Profile';</script> <%
			%> <script>document.getElementById("opt2").innerHTML = 'Sign out';</script> <%
		} else {
			%> <script>document.getElementById("opt1").innerHTML = 'Login';</script> <%
			%> <script>document.getElementById("opt2").innerHTML = 'Register';</script> <%
		}
		%>
	<script>
		function displayForm(){
			if(document.getElementById('city').checked){
				document.getElementById('cityInput').style.display = 'block';
				document.getElementById('locationInput').style.display = 'none';
				
			}
			else{
				document.getElementById('cityInput').style.display = 'none';
				document.getElementById('locationInput').style.display = 'block';
			}
		}
		
		function goHome(){
			window.location.replace("Home.jsp");
		}
		
		function goRegister(){
			window.location.replace("Register.jsp");
		}
		
		function goLogin(){
			window.location.replace("Login.jsp");
		}
		
		function popUp(){
			document.getElementById('map').style.display = 'block';
		}
	</script>
</html>

