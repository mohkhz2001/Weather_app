package com.mohammadkz.weather_app.Model;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SqliteDB extends SQLiteOpenHelper {

    public static final int DB_VERSION = 2;
    public static final String DB_NAME = "app_info.db";
    SQLiteDatabase db;

    public SqliteDB(Activity context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_weather = "CREATE TABLE IF NOT EXISTS " + Tables.DB_CITY_INFO + "(" +
                Tables.DB_CITY_INFO_NAME + " TEXT ,"
                + Tables.DB_CITY_INFO_ID + " TEXT ,"
                + Tables.DB_CITY_INFO_LAT + " TEXT ,"
                + Tables.DB_CITY_INFO_LON + " TEXT ,"
                + Tables.DB_CITY_INFO_HOME + " TEXT )";

        String sql_metar = "CREATE TABLE IF NOT EXISTS " + Tables.DB_METAR_INFO + "(" +
                Tables.DB_METAR_ID + " TEXT , " +
                Tables.DB_METAR_NAME + " TEXT ," +
                Tables.DB_METAR_ICAO + " TEXT )";

        db.execSQL(sql_weather);
        db.execSQL(sql_metar);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP table if exists " + Tables.DB_CITY_INFO;
        db.execSQL(drop);
        onCreate(db);
    }
}
