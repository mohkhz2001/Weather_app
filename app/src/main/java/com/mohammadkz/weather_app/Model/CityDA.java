package com.mohammadkz.weather_app.Model;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CityDA {

    private SqliteDB sqliteDB;
    private SQLiteDatabase db;
    private boolean findHome = false;

    public CityDA(Activity context) {
        sqliteDB = new SqliteDB(context);
    }

    public void openDB() {
        db = sqliteDB.getWritableDatabase();
        db = sqliteDB.getReadableDatabase();
    }

    public void closeDB() {
        db.close();
    }

    /*
    weather part...
     */

    public boolean addNewCity_weather(City city) {

        if (checkCity_weather(city)) {
            System.out.println("you have this city");
            return false;
        } else {
            try {
                db.execSQL("INSERT INTO " + Tables.DB_CITY_INFO + " values ( '" + city.getName() + "'" + " , " + "'" + city.getId() + "'" + " , "  + "'" + city.getLat().toString() + "'" +  " , " + "'" + city.getLon().toString() + "'" + " , " + "'" + Boolean.toString(city.isHome()) + "'" + ")");
                return true;
            } catch (Exception e) {
                e.getMessage();
                return false;
            }
        }


    }

    public ArrayList<City> getCities_weather() {

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Tables.DB_CITY_INFO, null);
            ArrayList<City> cities = new ArrayList<>();

            if (cursor.moveToFirst()) {

                int i = 0;

                do {
                    City city = new City();
                    city.setName(cursor.getString(cursor.getColumnIndex(Tables.DB_CITY_INFO_NAME)));
                    city.setId(cursor.getString(cursor.getColumnIndex(Tables.DB_CITY_INFO_ID)));
                    city.setHome(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(Tables.DB_CITY_INFO_HOME))));
                    city.setLat(Double.valueOf(cursor.getString(cursor.getColumnIndex(Tables.DB_CITY_INFO_LAT))));
                    city.setLon(Double.valueOf(cursor.getString(cursor.getColumnIndex(Tables.DB_CITY_INFO_LON))));


                    if (!findHome && city.isHome()) {
                        city.setName(setHome_weather(city.getName()));
                        findHome = true;
                    }
                    cities.add(city);

                    i++;
                } while (cursor.moveToNext());

            }
            return cities;

        } catch (Exception e) {
            e.getMessage();
            return null;
        }

    }

    private boolean checkCity_weather(City city) {

        try {
            Cursor cursor = db.rawQuery("SELECT " + Tables.DB_CITY_INFO_ID + " FROM " + Tables.DB_CITY_INFO + " where " + Tables.DB_CITY_INFO_ID + " = '" + city.getName() + "'", null);
            if (cursor.moveToNext()) {

                int counter = 0;
                do {
                    String a = cursor.getString(cursor.getColumnIndex(Tables.DB_CITY_INFO_ID));
                    if (a == city.getId())
                        return true;


                } while (cursor.moveToNext());


                return true;
            }

            return false;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }

    }

    private String setHome_weather(String city) {
        String cityName = city;
//        cityName += "'home'";

        return cityName;
    }

    /*
    Metar part
     */

    public boolean addNewAirport(Airport airport) {

        try {
            db.execSQL("INSERT INTO " + Tables.DB_METAR_INFO + " VALUES ( '" + airport.getId() + "' , '" + airport.getName() + "' , '" + airport.getIcao() + "')");
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }

    }

    public ArrayList<Airport> getAirport() {

        ArrayList<Airport> cities = null;

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + Tables.DB_METAR_INFO, null);

            if (cursor.moveToFirst()) {
                cities = new ArrayList<>();

                do {
                    Airport airport = new Airport();
                    airport.setId(cursor.getString(cursor.getColumnIndex(Tables.DB_METAR_ID)));
                    airport.setName(cursor.getString(cursor.getColumnIndex(Tables.DB_METAR_NAME)));
                    airport.setIcao(cursor.getString(cursor.getColumnIndex(Tables.DB_METAR_ICAO)));

                    cities.add(airport);

                } while (cursor.moveToNext());
            }

            return cities;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }

    }


}
