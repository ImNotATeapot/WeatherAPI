package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.net.URL;
import classes.Weather;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ParseWeatherFileServlet")
public class ParseWeatherFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	ArrayList<Weather> cities = this.parseFile();
    	
    	if(cities != null && !cities.isEmpty()) {
    		session.setAttribute("cities", cities);
        	RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
        	rd.forward(request, response);
    	}
    	else {
    		RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
    		rd.forward(request, response);
    	}
    }
    
    public ArrayList<Weather> parseFile() {
		URL url = getClass().getResource("/../../weather.txt");
		
		ArrayList<Weather> cities = new ArrayList<Weather>();
			
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String info = br.readLine();
			
			while(info != null) {
				if(this.hasConditions("weather.txt", info)) {
					this.parseConditions(cities, info);
				}
				else {
					cities.clear();
					break;
				}
				info = br.readLine();
			}
		} 
		catch (FileNotFoundException fnfe) {
			System.out.println("The file weather.txt could not be found.\n");
		} 
		catch (IOException ioe) {
			System.out.println("The file weather.txt could not be found\n");
		}
		
		return cities;
	}
	
	public void parseConditions(ArrayList<Weather> cities, String info) {
		Weather weather = new Weather();
		
		ArrayList<String> tokens = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(info, "|");
		
		while(tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken());
		}
		
		for(int i = 0; i < tokens.size(); i++) {
			if(i == 0) {
				weather.setCity(tokens.get(i));
			}
			else if(i == 1) {
				weather.setState(tokens.get(i));
			}
			else if(i == 2) {
				weather.setCountry(tokens.get(i));
			}
			else if(i == 3) {
				weather.setLatitude(Double.parseDouble(tokens.get(i)));
			}
			else if(i == 4) {
				weather.setLongitude(Double.parseDouble(tokens.get(i)));
			}
			else if(i == 5) {
				weather.setSunrise(tokens.get(i));
			}
			else if(i == 6) {
				weather.setSunset(tokens.get(i));
			}
			else if(i == 7) {
				weather.setCurrentTemperature(Integer.parseInt(tokens.get(i)));
			}
			else if(i == 8) {
				weather.setDayLow(Integer.parseInt(tokens.get(i)));
			}
			else if(i == 9) {
				weather.setDayHigh(Integer.parseInt(tokens.get(i)));
			}
			else if(i == 10) {
				weather.setHumidity(Integer.parseInt(tokens.get(i)));
			}
			else if(i == 11) {
				weather.setPressure(Float.parseFloat(tokens.get(i)));
			}
			else if(i == 12) {
				weather.setVisibility(Float.parseFloat(tokens.get(i)));
			}
			else if(i == 13) {
				weather.setWindspeed(Float.parseFloat(tokens.get(i)));
			}
			else if(i == 14) {
				weather.setWindDirection(Integer.parseInt(tokens.get(i)));
			}
			else {
				weather.getConditionDescription().add(tokens.get(i));
			}
		}
		
		cities.add(weather);
	}

	public boolean hasConditions(String fileName, String info) {
		ArrayList<String> tokens = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(info, "|");
		
		while(tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken());
		}
		
		if(tokens.size() >= 16) {
			for(int i = 0; i < tokens.size(); i++) {
				if(i >= 0 && i <= 2 && !(tokens.get(i) instanceof String)) {
					System.out.println("");
					return false;
				}
				else if((i == 3 || i == 4) && !this.isDouble(fileName, tokens.get(i))) {
					return false;
				}
				else if((i == 5 || i == 6) && !this.isTime(fileName, tokens.get(i))) {
					return false;
				}
				else if(((i >= 7 && i <= 10) || i == 14 ) && !this.isInteger(fileName, tokens.get(i))) {
					return false;
				}
				else if(i >= 11 && i <= 13 && !this.isFloat(fileName, tokens.get(i))) {
					return false;
				}
				else {
					continue;
				}
			}
		}
		else {
			System.out.println("The file " + fileName + " is not formatted properly.");
			System.out.println("There are not enough parameters on line '" + info + "'.\n");
			return false;
		}
		
		return true;
	}
	
	private boolean isDouble(String fileName, String input) {
		try {
			Double.parseDouble(input);
			return true;
		}
		catch (Exception e) {
			System.out.println("The file " + fileName + " is not formatted properly.");
			System.out.println("Unable to convert '" + input + "' to a double.\n");
			return false;
		}
	}

	private boolean isInteger(String fileName, String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch (Exception e) {
			System.out.println("The file " + fileName + " is not formatted properly.");
			System.out.println("Unable to convert '" + input + "' to an int.\n");
			return false;
		}
	}
	
	private boolean isFloat(String fileName, String input) {
		try {
			Float.parseFloat(input);
			return true;
		}
		catch (Exception e) {
			System.out.println("The file " + fileName + " is not formatted properly.");
			System.out.println("Unable to convert '" + input + "' to a float.\n");
			return false;
		}
	}
	
	private boolean isTime(String fileName, String input) {
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		sdf.setLenient(false);
		
		try {
			sdf.parse(input);
			return true;
		}
		catch (Exception e) {
			System.out.println("The file " + fileName + " is not formatted properly.");
			System.out.println("Unable to convert '" + input + "' to a time.\n");
			return false;
		}
	}
}
