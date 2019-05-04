package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.CityNameAscendingComparator;
import classes.CityNameDescendingComparator;
import classes.TempHighAscendingComparator;
import classes.TempHighDescendingComparator;
import classes.TempLowAscendingComparator;
import classes.TempLowDescendingComparator;
import classes.Weather;
import json.JSONArray;
import json.JSONObject;
import json.JSONValue;

@WebServlet("/LatLongSearchServlet")
public class LatLongSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String latitude = request.getParameter("latitude");
    	String longitude = request.getParameter("longitude");
    	
    	URL url = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&units=metric&APPID=9c6b18fff67b226789507070e83f753a");
    	HttpURLConnection con = (HttpURLConnection) url.openConnection();
    	con.setRequestMethod("GET");
    	int status = con.getResponseCode();
    	
    	System.out.println("\nSending 'GET' request to URL : " + url);
    	System.out.println("Response Code : " + status);
    	
    	BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		
		System.out.println(content.toString());
		
		ArrayList<Weather> selected = new ArrayList<Weather>();
 

//		try {
//			JSONArray object = (JSONArray)JSONValue.parse(content.toString());
//		} catch (ClassCastException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			JSONObject object = (JSONObject)JSONValue.parse(content.toString());
			Weather weather = new Weather();
			weather.setCity(object.get("name").toString());
			weather.setVisibility((Float.parseFloat(object.get("visibility").toString())));
			
			JSONObject coord = (JSONObject)JSONValue.parse(object.get("coord").toString());
			weather.setLatitude((Float.parseFloat(coord.get("lat").toString())));
			weather.setLongitude((Float.parseFloat(coord.get("lon").toString())));
			
			JSONObject main = (JSONObject)JSONValue.parse(object.get("main").toString());
			weather.setCurrentTemperature((int)(Double.parseDouble((main.get("temp").toString()))));
			weather.setDayLow((int)(Double.parseDouble(main.get("temp_min").toString())));
			weather.setDayHigh((int)(Double.parseDouble(main.get("temp_max").toString())));
			weather.setHumidity((int)(Double.parseDouble(main.get("humidity").toString())));
			weather.setPressure((Float.parseFloat(main.get("pressure").toString())));
			
			JSONObject wind = (JSONObject)JSONValue.parse(object.get("wind").toString());
			weather.setLatitude((Double.parseDouble(wind.get("speed").toString())));
			weather.setLongitude((Double.parseDouble(wind.get("deg").toString())));
			
			JSONObject sys = (JSONObject)JSONValue.parse(object.get("sys").toString());
			weather.setSunrise(sys.get("sunrise").toString());
			weather.setSunset(sys.get("sunset").toString());
			weather.setCountry(sys.get("country").toString());
			
			selected.add(weather);
			
			
		} catch (ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			catch (NullPointerException npe) {
			npe.printStackTrace();
		}
		
			    
		HttpSession session = request.getSession();
    	session.setAttribute("selected", selected);
    	
    	ArrayList<Weather> nameasc = new ArrayList<Weather>();
    	ArrayList<Weather> namedes = new ArrayList<Weather>();
    	ArrayList<Weather> templowasc = new ArrayList<Weather>();
    	ArrayList<Weather> templowdes = new ArrayList<Weather>();
    	ArrayList<Weather> temphighasc = new ArrayList<Weather>();
    	ArrayList<Weather> temphighdes = new ArrayList<Weather>();
    	
    	Collections.sort(selected, new CityNameAscendingComparator());
    	for(Weather w : selected) {
    		nameasc.add(w.clone());
    	}
    	Collections.sort(selected, new CityNameDescendingComparator());
    	for(Weather w : selected) {
    		namedes.add(w.clone());
    	}
    	Collections.sort(selected, new TempLowAscendingComparator());
    	for(Weather w : selected) {
    		templowasc.add(w.clone());
    	}
    	Collections.sort(selected, new TempLowDescendingComparator());
    	for(Weather w : selected) {
    		templowdes.add(w.clone());
    	}
    	Collections.sort(selected, new TempHighAscendingComparator());
    	for(Weather w : selected) {
    		temphighasc.add(w.clone());
    	}
    	Collections.sort(selected, new TempHighDescendingComparator());
    	for(Weather w : selected) {
    		temphighdes.add(w.clone());
    	}
    	
    	session.setAttribute("nameasc", nameasc);
    	session.setAttribute("namedes", namedes);
    	session.setAttribute("templowasc", templowasc);
    	session.setAttribute("templowdes", templowdes);
    	session.setAttribute("temphighasc", temphighasc);
    	session.setAttribute("temphighdes", temphighdes);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/Results.jsp");
    	rd.forward(request, response);
    }
}
