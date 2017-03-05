package io.egen.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.egen.api.entity.WeatherSensor;
import io.egen.api.entity.Wind;
import io.egen.api.service.WeatherService;

@RestController
@RequestMapping
@CrossOrigin
public class weatherController {
	private WeatherService weatherService;

	public weatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return "welcome!";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/post/listen")
	public WeatherSensor listen(@RequestBody WeatherSensor weatherSensor) {
		System.out.println(weatherSensor.toString());
		weatherService.listen(weatherSensor);

		return weatherSensor;
	}

	@RequestMapping(method = RequestMethod.GET, value = "average")
	public List<WeatherSensor> getAveragedWeather(@RequestParam("city") String city,
			@RequestParam("grain") String grain) {
		return weatherService.getAveragedWeather(city, grain);
	}

	@RequestMapping(method = RequestMethod.GET, value = "latest")
	public Integer getLatestProperty(@RequestParam("property") String property, @RequestParam("city") String city) {
		return weatherService.getLatestProperty(property, city);
	}

	@RequestMapping(method = RequestMethod.GET, value = "city-list")
	public List<String> findCityList() {
		return weatherService.findCityList();
	}

	@RequestMapping(method = RequestMethod.GET, value = "weather")
	public WeatherSensor getLatestWeatherOfCity(@RequestParam("city") String city) {
		return weatherService.getLatestWeatherOfCity(city);
	}

	@RequestMapping(method = RequestMethod.GET, value = "latest/wind")
	public Wind getLatestWind(@RequestParam("city") String city) {
		Wind w = weatherService.getLatestWindOfCity(city);
		return w;
	}

}
