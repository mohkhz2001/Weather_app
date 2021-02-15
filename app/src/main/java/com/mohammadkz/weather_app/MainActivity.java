package com.mohammadkz.weather_app;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mohammadkz.weather_app.Fragment.AirportListFragment;
import com.mohammadkz.weather_app.Fragment.SearchWeatherFragment;
import com.mohammadkz.weather_app.Fragment.SupportFragment;
import com.mohammadkz.weather_app.Model.City;
import com.mohammadkz.weather_app.Model.CityDA;
import com.mohammadkz.weather_app.Model.Metar.Metar;
import com.mohammadkz.weather_app.Fragment.WeatherFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue, requestQueue1;
    String cityName = "ahwaz";
    String icao = "oiii";
    private String url_api = "http://api.openweathermap.org/data/2.5/weather?q=tehran&appid=25e1f42cf5cd09a5858e1f02c43c476a";// API url
    private String url_api_station = "https://avwx.rest/api/station/" + icao + "?token=QtlFLp-8yFpYCh_AwRZTe4V_Ba6N8mur3mmubu1Ja-U";// API url
    private String url_api_metar = "https://avwx.rest/api/metar/" + icao + "?token=QtlFLp-8yFpYCh_AwRZTe4V_Ba6N8mur3mmubu1Ja-U";// API url

    BottomNavigationView navigation;

    Metar metar = new Metar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue1 = Volley.newRequestQueue(getApplicationContext());

//        CityDA cityDA = new CityDA(MainActivity.this);
//        cityDA.openDB();
//        boolean check = cityDA.addNewCity(new City("ahwaz", "12345", false));
//        Log.e("boolean", " " + check);
//        List<City> cities = cityDA.getCities();
//        Log.e("lenght", " " + cities.size());

        initViews();
        controllerViews();

        checkDB();

//        ApiRequestMetar();
//        ApiRequestStation();
//        ApiRequestWeather();
    }

    private void initViews() {
        navigation = findViewById(R.id.bottom_navigation);
        navigation.setItemIconTintList(null);
    }

    private void controllerViews() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                nav(item);
                return true;
            }
        });
    }

//    private void ApiRequestMetar() {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_api_metar, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//
//                    metar.setSanitized(jsonObject.getString("sanitized"));
//                    metar.setFlight_rules(jsonObject.getString("flight_rules"));
//                    ///
//                    JSONObject a = jsonObject.getJSONObject("time");
//                    String A = a.getString("dt");
//                    char aChar[] = A.toCharArray();
//                    String data[] = getTimeDate(aChar);
//
//
//                    JSONObject b = jsonObject.getJSONObject("meta");
//                    String B = b.getString("timestamp");
//                    aChar = null;
//                    aChar = B.toCharArray();
//                    String data1[] = getTimeDate(aChar);
//                    System.out.println();
//
//                } catch (Exception e) {
//                    e.getMessage();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        requestQueue.add(stringRequest);
//    }
//
//    private void ApiRequestStation() {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_api_station, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//
////                    JSONArray jsonArray = new JSONArray(response);
//                    JSONObject jsonObject = new JSONObject(response);
//
//                    JSONArray jsonArray = jsonObject.getJSONArray("runways");
//
//                    Log.e("lentgh", "aa  " + jsonArray.length());
//
//                    GsonBuilder gsonBuilder = new GsonBuilder();
//                    Gson gson = gsonBuilder.create();
//                    ArrayList<Runway> runways = new ArrayList<>();
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        Runway runway = gson.fromJson(String.valueOf(jsonArray.getJSONObject(i)), Runway.class);
//                        runways.add(runway);
//                    }
//                    metar.setRunwayList(runways);
//
//                    metar.setCity(jsonObject.getString("city"));
//                    metar.setCountry(jsonObject.getString("country"));
//                    metar.setIata(jsonObject.getString("iata"));
//                    metar.setIcao(jsonObject.getString("icao"));
//                    metar.setName(jsonObject.getString("name"));
//                    metar.setType(jsonObject.getString("type"));
//                    metar.setElevation_ft(jsonObject.getInt("elevation_ft"));
//                    metar.setElevation_m(jsonObject.getInt("elevation_m"));
//
//                } catch (Exception e) {
//                    e.getMessage();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Error", "connection failed" + " ");
//            }
//        });
//
//        requestQueue.add(stringRequest);
//    }
//
//    private void ApiRequestWeather() {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_api, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Weather weather = new Weather();
//                try {
//
//                    JSONObject jsonObject = new JSONObject(response);
//
//
//                    JSONObject main = jsonObject.getJSONObject("main");
//
//                    /////////////
//                    weather.setMain(new Main(main.getInt("feels_like") - 273, main.getInt("temp") - 273, main.getInt("temp_min") - 273, main.getInt("temp_max") - 273, main.getInt("humidity"), main.getInt("pressure")));
//
//                    ////////////////////
//
//                    weather.setVisibility(jsonObject.getInt("visibility"));
//
//                    ///
//                    weather.setName(jsonObject.getString("name"));
//                    ////
//                    JSONObject wind_json = jsonObject.getJSONObject("wind");
//                    weather.setWind(new Wind(wind_json.getInt("speed"), wind_json.getInt("deg")));
//                    /////
//
//                    JSONArray weather_json = jsonObject.getJSONArray("weather");
//
//                    weather.setMain(weather_json.getJSONObject(0).getString("main"));
//
//
//                } catch (Exception e) {
//                    e.getMessage();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Error", "connection failed" + " ");
//
//
//            }
//        });
//
//        requestQueue1.add(stringRequest);
//    }

    private String[] getTimeDate(char string[]) {
        String data = "", time = "";
        for (int i = 0; i < string.length; i++) {
            if (i >= 0 && i < 10) {
                if (i == 4 || i == 7) {
                    data += ".";
                    i++;
                } else
                    data = data + (char) string[i];
            } else if (i > 10 && i < 16) {
                time += (char) string[i];
            }
        }
        String a[] = {data, time};
        return a;
    }

    private void nav(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.weather:
                WeatherFragment weatherFragment = new WeatherFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, weatherFragment);
                fragmentTransaction.commit();
                break;
            case R.id.metar:
                AirportListFragment airportListFragment = new AirportListFragment();
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.frameLayout, airportListFragment);
                fragmentTransaction1.commit();
                break;
            case R.id.support:
                SupportFragment supportFragment = new SupportFragment();
                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.frameLayout, supportFragment);
                fragmentTransaction2.commit();
        }
    }

    private void start() {
        WeatherFragment weatherFragment = new WeatherFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, weatherFragment);
        fragmentTransaction.commit();
    }

    private void start2() {
        AirportListFragment weatherFragment = new AirportListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, weatherFragment);
        fragmentTransaction.commit();
    }

    private void checkDB() {

        CityDA cityDA = new CityDA(this);
        cityDA.openDB();
//        boolean check = cityDA.addNewCity_weather(new City("Ahvāz", "144448", false, 31.3203, 48.6693));
//        System.out.println(check);
        ArrayList<City> city = new ArrayList<>();
        city = cityDA.getCities_weather();
        if (city.size() >= 1) {
            start();
        } else {
            SearchWeatherFragment searchWeatherFragment = new SearchWeatherFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, searchWeatherFragment);
            fragmentTransaction.commit();

        }


    }

}