<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Details.css">
		<title>Details Page</title>
		<%@page import="classes.Weather" %>
		<%
			Weather weather = (Weather) request.getSession().getAttribute("city");
		%>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	</head>
	<body>
		<div id="background"></div>
		<div id="header">
			<h1 onclick="goHome()">WeatherMeister</h1>
			<div id="form">
				<form action="CitySearchServlet" method="post">
					<div id="cityInput">
						<input type="text" name="city" placeholder="Los Angeles">
						<button type="submit">Submit</button>
					</div>
					<div id="locationInput" style="display:none">
						<input type="text" name="latitude" placeholder="Latitude">
						<input type="text" name="longitude" placeholder="Longitude">
						<button type="submit">Submit</button>
					</div>
					<div id="radio">
						<input type="radio" id="city" name="input" onclick="displayForm()" checked>City
						<input type="radio" id="location" name="input" onclick="displayForm()">Location (Lat./Long.)
					</div>
				</form>
			</div>
		</div>
		<div class="content">
			<h1 id="title"><%=weather.getCity()%></h1>
			<div id="components">
				<div id="row1">
					<div class="component" id="loc" onclick='switchDisplay("#loc")'>
						<img src="img/planet-earth.png">
						<% out.println("<p>" + weather.getState() + ", " + weather.getCountry() + "</p>"); %>
						<h2>Location</h2>
					</div>
					<div class="component" id="templow" onclick='switchDisplay("#templow")'>
						<img src="img/snowflake.png">
						<% out.println("<p>" + weather.getDayLow() + " degrees Fahrenheit</p>"); %>
						<h2>Temp Low</h2>
					</div>
					<div class="component" id="temphigh" onclick='switchDisplay("#temphigh")'>
						<img src="img/sun.png">
						<% out.println("<p>" + weather.getDayHigh() + " degrees Fahrenheit</p>"); %>
						<h2>Temp High</h2>
					</div>
					<div class="component" id="wind" onclick='switchDisplay("#wind")'>
						<img src="img/wind.png">
						<% out.println("<p>" + weather.getWindspeed() + " miles/hour</p>"); %>
						<h2>Wind</h2>
					</div>
				</div>
				<div id="row2">
					<div class="component" id="humidity" onclick='switchDisplay("#humidity")'>
						<img src="img/drop.png">
						<% out.println("<p>" + weather.getHumidity() + "%</p>"); %>
						<h2>Humidity</h2>
					</div>
					<div class="component" id="coordinates" onclick='switchDisplay("#coordinates")'>
						<img src="img/LocationIcon.png">
						<% out.println("<p>" + weather.getLatitude() + ", " + weather.getLongitude() + "</p>"); %>
						<h2>Coordinates</h2>
					</div>
					<div class="component" id="currenttemp" onclick='switchDisplay("#currenttemp")'>
						<img src="img/thermometer.png">
						<% out.println("<p>" + weather.getCurrentTemperature() + " degrees Fahrenheit</p>"); %>
						<h2>Current Temp</h2>
					</div>
					<div class="component" id="sunrise/set" onclick='switchDisplay("#sunrise/set")'>
						<img src="img/sunrise-icon.png">
						<% out.println("<p>" + weather.getSunrise() + ", " + weather.getSunset() + "</p>"); %>
						<h2>Sunrise/set</h2>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script>
		function displayForm() {
			if(document.getElementById('city').checked){
				document.getElementById('cityInput').style.display = 'inline';
				document.getElementById('locationInput').style.display = 'none';
			}
			else{
				document.getElementById('cityInput').style.display = 'none';
				document.getElementById('locationInput').style.display = 'inline';
			}
		}
		
		function goHome(){
			window.location.replace("Home.jsp");
		}
		
		function switchDisplay(div){
			console.log("switching");
			$(div).find("img").toggle();
			$(div).find("p").toggle();
		}
		
		$(document).ready(function(){
			$("p").hide();
		});
	</script>
</html>