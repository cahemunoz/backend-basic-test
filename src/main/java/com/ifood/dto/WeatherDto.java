package com.ifood.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ifood.dto.util.CustomDeserializer;

import java.io.Serializable;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto implements Serializable {

    private Integer timezone;
    private Integer id;
    private String name;
    private Integer cod;

    @JsonDeserialize(using = CustomDeserializer.class)
    @JsonProperty("dt")
    private String data;

    private Long visibility;
    private String base;

    private Integer all;

    private Sys sys;

    private Double speed;
    private Integer deg;

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getVisibility() {
        return visibility;
    }

    public void setVisibility(Long visibility) {
        this.visibility = visibility;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("wind")
    private void unpackNested(Map<String,Object> wind) {
        this.speed = (Double)wind.get("speed");
        this.deg = (Integer) wind.get("deg");
    }

    @JsonProperty("clouds")
    private void unpackNestedCloud(Map<String,Object> clouds) {
        this.all = (Integer)clouds.get("all");
    }
}
