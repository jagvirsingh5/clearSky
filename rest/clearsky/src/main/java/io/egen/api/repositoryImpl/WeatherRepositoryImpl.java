package io.egen.api.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.entity.WeatherSensor;
import io.egen.api.entity.Wind;
import io.egen.api.repository.WeatherRepository;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public WeatherSensor listen(WeatherSensor weatherSensor) {
		em.persist(weatherSensor.getWind());
		em.persist(weatherSensor);
		return weatherSensor;
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> findCityList() {
		TypedQuery<String> query = em.createQuery("select distinct ws.city from WeatherSensor ws", String.class);
		List<String> cityList = query.getResultList();
		return cityList;
	}

	@Override
	@Transactional(readOnly = true)
	public WeatherSensor getLatestWeatherOfCity(String city) {
		TypedQuery<WeatherSensor> query = em.createQuery(
				"select ws from WeatherSensor ws where ws.city = :pCity order by ws.timestamp", WeatherSensor.class);
		query.setParameter("pCity", city);
		List<WeatherSensor> ws = query.getResultList();
		return ws.get(0);
	}

	@Override
	@Transactional(readOnly = true)
	public Wind getLatestWindOfCity(String city) {
		String queryString = "select w.* from (SELECT wind_id,city from WeatherSensor a where a.city = :pCity  order by a.timestamp) wind_id,Wind w where wind_id.wind_id = w.id";
		Query query = em.createNativeQuery(queryString, Wind.class);
		query.setParameter("pCity", city);
		List<Wind> wind = query.getResultList();
		return wind.get(0);

	}

	@Override
	public List<WeatherSensor> getAveragedWeather(String city, int grain) {
		String queryString = "select ws.*"
				+ " from WeatherSensor ws where ws.city = :pCity  group by UNIX_TIMESTAMP (ws.timestamp ) DIV (:pGrain )";
		Query query = em.createNativeQuery(queryString);
		query.setParameter("pCity", city);
		query.setParameter("pGrain", grain);
		List<WeatherSensor> ws = query.getResultList();
		return ws;
	}

	@Override
	public Integer getLatestProperty(String property, String city) {
		String queryString = "select ws." + property
				+ " from WeatherSensor ws where ws.city = :pCity order by ws.timestamp desc";
		Query query = em.createNativeQuery(queryString);
		query.setParameter("pCity", city);
		// query.setParameter("pProperty", property);
		List<Integer> ws = query.getResultList();
		return ws.get(0);
	}

}
