package com.ifood.controllers;

import com.ifood.dto.WeatherDto;
import com.ifood.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/weather")
public class Weather {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping
    public ResponseEntity getWeather(@RequestParam(value = "city") String city) {
        WeatherDto dto = weatherService.getWeather(city);
        if (dto == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(dto);
    }
}
