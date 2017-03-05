package io.egen.api.repository;

import java.util.List;

import io.egen.api.entity.WeatherSensor;
import io.egen.api.entity.Wind;

public interface WeatherRepository {

	public WeatherSensor listen(WeatherSensor weatherSensor);

	public List<String> findCityList();

	public WeatherSensor getLatestWeatherOfCity(String city);

	public Wind getLatestWindOfCity(String city);

	public List<WeatherSensor> getAveragedWeather(String city, int grain);

	public Integer getLatestProperty(String property, String city);

}
