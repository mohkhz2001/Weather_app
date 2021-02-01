package com.mohammadkz.weather_app.Model.Weather;

public class Location {
    private String name , timeZone;
    private Double lon, lat;
    private long sunSet , sunRise , dt;

    public Location(String name, String timeZone, Double lon, Double lat, long sunSet, long sunRise, long dt) {
        this.name = name;
        this.timeZone = timeZone;
        this.lon = lon;
        this.lat = lat;
        this.sunSet = sunSet;
        this.sunRise = sunRise;
        this.dt = dt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public long getSunSet() {
        return sunSet;
    }

    public void setSunSet(long sunSet) {
        this.sunSet = sunSet;
    }

    public long getSunRise() {
        return sunRise;
    }

    public void setSunRise(long sunRise) {
        this.sunRise = sunRise;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }
}
