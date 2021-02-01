package com.mohammadkz.weather_app.Model.Weather;

public class Temperature {
    private int temp , minTemp , maxTemp , feelTemp ;

    public Temperature(int temp, int minTemp, int maxTemp, int feelTemp) {
        this.temp = temp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.feelTemp = feelTemp;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getFeelTemp() {
        return feelTemp;
    }

    public void setFeelTemp(int feelTemp) {
        this.feelTemp = feelTemp;
    }
}
