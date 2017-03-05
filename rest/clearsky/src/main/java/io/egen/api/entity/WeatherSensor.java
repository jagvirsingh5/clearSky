package io.egen.api.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WeatherSensor {

	public WeatherSensor() {
		this.id = UUID.randomUUID().toString();
	}

	@Id
	private String id;
	private String city;
	private String description;
	private int humidity;
	private int pressure;
	private int temperature;
	private Timestamp timestamp;
	@OneToOne
	private Wind wind;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "WeatherSensor [city=" + city + ", description=" + description + ", humidity=" + humidity + ", pressure="
				+ pressure + ", temperature=" + temperature + ", timestamp=" + timestamp + ", wind=" + wind + "]";
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

}
