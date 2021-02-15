package com.mohammadkz.weather_app.Model;

public class City {
    private String name;
    private String id;
    private boolean home;
    private Double lat, lon;


    public City(String name, String id, boolean home, Double lat, Double lon) {
        this.name = name;
        this.id = id;
        this.home = home;
        this.lat = lat;
        this.lon = lon;
    }

    public City() {

    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }
}
