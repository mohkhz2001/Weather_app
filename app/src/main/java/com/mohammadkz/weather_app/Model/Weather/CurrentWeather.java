package com.mohammadkz.weather_app.Model.Weather;

import android.widget.TextView;

public class CurrentWeather {
    private Location location;
    private Temperature temperature;
    private Wind wind;
    private float uvi;
    private String id , situation;
    private int humidity;
    private  String icon;

    public CurrentWeather(Location location, Temperature temperature, Wind wind, float uvi, String id, String situation, int humidity, String icon) {
        this.location = location;
        this.temperature = temperature;
        this.wind = wind;
        this.uvi = uvi;
        this.id = id;
        this.situation = situation;
        this.humidity = humidity;
        this.icon = icon;
    }

    public CurrentWeather(){

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public float getUvi() {
        return uvi;
    }

    public void setUvi(float uvi) {
        this.uvi = uvi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
