package io.egen.api.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import io.egen.api.entity.WeatherSensor;
import io.egen.api.entity.Wind;
import io.egen.api.exception.BadRequestException;
import io.egen.api.repository.WeatherRepository;
import io.egen.api.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {
	WeatherRepository weatherRepository;
	Wind wind;

	public WeatherServiceImpl(WeatherRepository weatherRepository) {
		this.weatherRepository = weatherRepository;
	}

	public WeatherSensor listen(WeatherSensor weatherSensor) {
		weatherRepository.listen(weatherSensor);
		return weatherSensor;
	}

	@Override
	public Wind getLatestWindOfCity(String city) {
		return weatherRepository.getLatestWindOfCity(city);
	}

	@Override
	public List<String> findCityList() {

		return weatherRepository.findCityList();
	}

	@Override
	public WeatherSensor getLatestWeatherOfCity(String city) {
		return weatherRepository.getLatestWeatherOfCity(city);
	}

	@Override
	public List<WeatherSensor> getAveragedWeather(String city, String grain) {
		int grainN = 0;
		if (grain.equals("1d"))
			grainN = 24 * 3600;
		if (grain.equals("1h"))
			grainN = 3600;

		return weatherRepository.getAveragedWeather(city, grainN);
	}

	@Override
	public Integer getLatestProperty(String property, String city) {
		if (property.equals("temperature") || property.equals("pressure") || property.equals("humidity")) {
			return weatherRepository.getLatestProperty(property, city);
		} else {
			throw new BadRequestException("given property " + property + "is invalid: ");
		}

	}

}
