package com.ifood.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ifood.model.WeatherMap;
import com.ifood.util.Constants;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	private static final Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@Value("${ifoodProperties.openweathermap.api}")
	private String api;
	
	@Value("${ifoodProperties.openweathermap.api.appId}")
	private String appId;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Cacheable(value = {Constants.CACHE_WEATHERS})
	@HystrixCommand(fallbackMethod = "defaultGetWeather", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",         value = "20000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",          value = "10") })
	@Override
	public WeatherMap getWeather(String city) {
		log.info("Consultando API := " + api 
				+ ", Cidade := " + city);

		return restTemplate.exchange(api, HttpMethod.GET, null, 
				new ParameterizedTypeReference<WeatherMap>() {}, 
				city, 
				appId).getBody();
	}
	
	@Override
	public WeatherMap defaultGetWeather(String city) {
		log.info("Consultando API (CACHE) := " + api 
				+ ", Cidade := " + city);
		
	    if (null != cacheManager.getCache(Constants.CACHE_WEATHERS) 
	    		&& null != cacheManager.getCache(Constants.CACHE_WEATHERS).get(SimpleKey.EMPTY)) {
	        return cacheManager.getCache(Constants.CACHE_WEATHERS)
	        		.get(SimpleKey.EMPTY, WeatherMap.class);
	    } else {
	        return null;
	    }
	}
}
