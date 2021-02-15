package com.mohammadkz.weather_app.Model.Metar;

import java.util.ArrayList;

public class Metar {
    String city, country, iata, icao, name, type, sanitized, flight_rules, remark , UTC;
    int elevation_ft, elevation_m;
    ArrayList<Runway> RunwayList;

    public Metar(String city, String country, String iata, String icao, String name, String type, String sanitized, String flight_rules, int elevation_ft, int elevation_m, ArrayList<Runway> runwayList, String remark , String UTC) {
        this.city = city;
        this.country = country;
        this.iata = iata;
        this.icao = icao;
        this.name = name;
        this.type = type;
        this.sanitized = sanitized;
        this.flight_rules = flight_rules;
        this.elevation_ft = elevation_ft;
        this.elevation_m = elevation_m;
        RunwayList = runwayList;
        this.remark = remark;
        this.UTC = UTC;
    }

    public String getUTC() {
        return UTC;
    }

    public void setUTC(String UTC) {
        this.UTC = UTC;
    }

    public Metar() {

    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSanitized() {
        return sanitized;
    }

    public void setSanitized(String sanitized) {
        this.sanitized = sanitized;
    }

    public String getFlight_rules() {
        return flight_rules;
    }

    public void setFlight_rules(String flight_rules) {
        this.flight_rules = flight_rules;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getElevation_ft() {
        return elevation_ft;
    }

    public void setElevation_ft(int elevation_ft) {
        this.elevation_ft = elevation_ft;
    }

    public int getElevation_m() {
        return elevation_m;
    }

    public void setElevation_m(int elevation_m) {
        this.elevation_m = elevation_m;
    }

    public ArrayList<Runway> getRunwayList() {
        return RunwayList;
    }

    public void setRunwayList(ArrayList<Runway> runwayList) {
        RunwayList = runwayList;
    }

}
//{"city":"Ahwaz","country":"IR","elevation_ft":66,"elevation_m":20,"iata":"AWZ","icao":"OIAW","latitude":31.337400436399996,"longitude":48.7620010376,"name":"Ahwaz Airport","note":null,"reporting":true,
// "runways":[{"length_ft":11149,"width_ft":148,"surface":"asphalt","lights":true,"ident1":"12","ident2":"30","bearing1":124.1,"bearing2":304.1}],"state":"10","type":"medium_airport","website":null,"wiki":"https://en.wikipedia.org/wiki/Ahvaz_Airport"}