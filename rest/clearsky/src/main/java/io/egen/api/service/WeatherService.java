package io.egen.api.service;

import java.util.List;

import io.egen.api.entity.WeatherSensor;
import io.egen.api.entity.Wind;

public interface WeatherService {

	public WeatherSensor listen(WeatherSensor weatherSensor);

	public List<String> findCityList();

	public List<WeatherSensor> getAveragedWeather(String city, String grain);

	public Integer getLatestProperty(String property, String city);

	public WeatherSensor getLatestWeatherOfCity(String city);

	public Wind getLatestWindOfCity(String city);

}
