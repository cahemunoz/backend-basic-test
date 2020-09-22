package com.ifood.service;

import com.ifood.dto.WeatherDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    public static final String CACHE_WEATHER = "weather";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.url}")
    private String weatherUrl;

    @Value(("${weather.api}"))
    private String weatherApi;

    @Cacheable(CACHE_WEATHER)
    @HystrixCommand(fallbackMethod = "defaultWeather",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
    })
    public WeatherDto getWeather(String city) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(weatherUrl)
                .queryParam("id", city)
                .queryParam("appid", weatherApi);

        ResponseEntity<WeatherDto> entity = restTemplate.getForEntity(builder.toUriString(), WeatherDto.class);
        return entity.getBody();
    }

    public WeatherDto defaultWeather(String city) {
        return null;
    }
}
