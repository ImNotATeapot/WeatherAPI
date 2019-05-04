package classes;
import java.util.ArrayList;

public class Weather {
	private String city;
	private String state;
	private String country;
	private double latitude;
	private double longitude;
	private String sunrise;
	private String sunset;
	private int currentTemperature;
	private int dayLow;
	private int dayHigh;
	private int humidity;
	private float pressure;
	private float visibility;
	private float windspeed;
	private int windDirection;
	private ArrayList<String> conditionDescription = new ArrayList<String>();
	
	public Weather clone() {
		Weather w = new Weather();
		w.city = this.city;
		w.state = this.state;
		w.country = this.country;
		w.latitude = this.latitude;
		w.longitude = this.latitude;
		w.sunrise = this.sunrise;
		w.sunset = this.sunset;
		w.currentTemperature = this.currentTemperature;
		w.dayLow = this.dayLow;
		w.dayHigh = this.dayHigh;
		w.humidity = this.humidity;
		w.pressure = this.pressure;
		w.visibility = this.visibility;
		w.windspeed = this.windspeed;
		w.windDirection = this.windDirection;
		w.conditionDescription = this.conditionDescription;
		
		return w;
	}
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCurrentTemperature() {
		return currentTemperature;
	}
	public void setCurrentTemperature(int currentTemperature) {
		this.currentTemperature = currentTemperature;
	}
	public int getDayLow() {
		return dayLow;
	}
	public void setDayLow(int dayLow) {
		this.dayLow = dayLow;
	}
	public int getDayHigh() {
		return dayHigh;
	}
	public void setDayHigh(int dayHigh) {
		this.dayHigh = dayHigh;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public float getPressure() {
		return pressure;
	}
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
	public float getVisibility() {
		return visibility;
	}
	public void setVisibility(float visibility) {
		this.visibility = visibility;
	}
	public float getWindspeed() {
		return windspeed;
	}
	public void setWindspeed(float windspeed) {
		this.windspeed = windspeed;
	}
	public int getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(int windDirection) {
		this.windDirection = windDirection;
	}
	public ArrayList<String> getConditionDescription() {
		return conditionDescription;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getSunrise() {
		return sunrise;
	}
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	public String getSunset() {
		return sunset;
	}
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
}
