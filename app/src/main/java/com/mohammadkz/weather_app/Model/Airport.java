package com.mohammadkz.weather_app.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Airport {
    String name, id, icao;

    public Airport(String name, @Nullable String id, String icao) {
        this.name = name;
        this.id = id;
        this.icao = icao;
    }

    public Airport() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
