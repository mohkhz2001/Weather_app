package com.mohammadkz.weather_app.Model.Weather;

public class Wind {
    int speed, degree;

    public Wind(int speed, int degree) {
        this.speed = speed;
        this.degree = degree;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
