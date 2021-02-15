package com.mohammadkz.weather_app.Model.Metar;

public class Runway {
    String length_ft;
    String width_ft;
    String surface;
    String ident1;
    String ident2;

    public Runway(String length_ft, String width_ft, String surface, String ident1, String ident2) {
        this.length_ft = length_ft;
        this.width_ft = width_ft;
        this.surface = surface;
        this.ident1 = ident1;
        this.ident2 = ident2;
    }

    public Runway() {

    }

    public String getLength_ft() {
        return length_ft;
    }

    public void setLength_ft(String length_ft) {
        this.length_ft = length_ft;
    }

    public String getWidth_ft() {
        return width_ft;
    }

    public void setWidth_ft(String width_ft) {
        this.width_ft = width_ft;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getIdent1() {
        return ident1;
    }

    public void setIdent1(String ident1) {
        this.ident1 = ident1;
    }

    public String getIdent2() {
        return ident2;
    }

    public void setIdent2(String ident2) {
        this.ident2 = ident2;
    }
}
