package com.ifood.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ifood.dto.util.CustomDeserializer;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys implements Serializable {

    private String country;

    @JsonDeserialize(using = CustomDeserializer.class)
    private String sunset;

    @JsonDeserialize(using = CustomDeserializer.class)
    private String sunrise;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }
}
