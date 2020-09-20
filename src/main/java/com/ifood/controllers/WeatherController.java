package com.ifood.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ifood.model.WeatherMap;
import com.ifood.service.WeatherService;

@RestController
@RequestMapping(path = "/weather")
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;

	@GetMapping
	
	public ResponseEntity<WeatherMap> getWeather(@RequestParam(value = "city") String city) {
		
		Optional<WeatherMap> weatherMap = Optional.ofNullable(weatherService.getWeather(city));
		
		return weatherMap.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());

	}
}
