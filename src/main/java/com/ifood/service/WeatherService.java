package com.ifood.service;

import com.ifood.model.WeatherMap;

public interface WeatherService {

	/**
	 * Coleta os dados da API
	 * @param city Cidade
	 * @return {@link WeatherMap}
	 */
	WeatherMap getWeather(String city);
	
	/**
	 * Coleta os dados da API Fallback
	 * @param city Cidade
	 * @return {@link WeatherMap}
	 */
	WeatherMap defaultGetWeather(String city);
}
