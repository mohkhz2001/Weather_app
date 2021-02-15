package com.mohammadkz.weather_app.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.progresviews.ProgressWheel;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.mohammadkz.weather_app.Adapter.CityAdapter;
import com.mohammadkz.weather_app.Model.City;
import com.mohammadkz.weather_app.Model.CityDA;
import com.mohammadkz.weather_app.Model.Weather.CurrentWeather;
import com.mohammadkz.weather_app.Model.Weather.Location;
import com.mohammadkz.weather_app.Model.Weather.Temperature;
import com.mohammadkz.weather_app.Model.Weather.Wind;
import com.mohammadkz.weather_app.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import xyz.matteobattilana.library.Common.Constants;
import xyz.matteobattilana.library.WeatherView;

public class WeatherFragment extends Fragment {

    View view;
    MaterialTextView date_location, city_name, situation, temp, wind;
    TextView wind_deg, wind_speed, min_max, feelLike;
    LottieAnimationView weather_img;
    RecyclerView city_list;
    RequestQueue requestQueue;
    CityDA cityDA;
    ProgressWheel progressWheel;

    CurrentWeather currentWeather;
    boolean firstTime = true;
    ArrayList<City> cities;

    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_weather, container, false);

        requestQueue = Volley.newRequestQueue(getContext());

        initView();
        ViewController();
        getData();
        start();

        return view;
    }

    private void initView() {
        city_list = view.findViewById(R.id.city_list);
        wind_deg = view.findViewById(R.id.wind_deg);
        wind_speed = view.findViewById(R.id.wind_speed);
        date_location = view.findViewById(R.id.date_location);
        city_name = view.findViewById(R.id.city_name);
        situation = view.findViewById(R.id.situation);
        temp = view.findViewById(R.id.temp);
        weather_img = view.findViewById(R.id.weather_img);
        min_max = view.findViewById(R.id.min_max);
//        anyChartView= view.findViewById(R.id.wind_img);
        progressWheel = view.findViewById(R.id.humadityProgress);
        feelLike = view.findViewById(R.id.feelLike);

    }

    private void ViewController() {

    }

    private void getData() {
        cityDA = new CityDA(getActivity());
        cityDA.openDB();

//        boolean checl = cityDA.addNewCity_weather(new City("Ahwaz" , "144448" , true));
//        Log.e("chekc" , " " +checl);

        cities = cityDA.getCities_weather();

        cityDA.closeDB();

        cities.add(new City("new", "0", false, 0.0, 0.0));// bug

        setRecycler(cities);

    }

    private void setRecycler(ArrayList<City> cities) {
        CityAdapter adapter = new CityAdapter(getContext(), cities);

        city_list.setHasFixedSize(true);
        city_list.setLayoutManager(new LinearLayoutManager(view.getContext()));
        city_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        city_list.setAdapter(adapter);

        adapter.setOnItemClickListener(new CityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, View v) {
                Log.e("pos", " " + pos);

                // if wanna add new city
                if (pos == (cities.size() - 1)) {
                    // star the search fragment.....
                    SearchWeatherFragment searchWeatherFragment = new SearchWeatherFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, searchWeatherFragment);
                    fragmentTransaction.commit();

                } else {  // then send the request with ID **
                    Log.e("item", "done");
                    currentWeather.setLocation(new Location(cities.get(pos).getName(), null, cities.get(pos).getLon(), cities.get(pos).getLat(), 0, 0, 0));

                    ApiReq();
                }


            }
        });

    }

    private void ApiReq() {
        String url = "https://api.openweathermap.org/data/2.5/onecall?lat=" + currentWeather.getLocation().getLat().toString() + "&lon=" + currentWeather.getLocation().getLon().toString() + "&units=metric&exclude=hourly,minutely&appid=25e1f42cf5cd09a5858e1f02c43c476a";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject current = jsonObject.getJSONObject("current");
                    currentWeather.setLocation(new Location(currentWeather.getLocation().getName(), jsonObject.getString("timezone"), jsonObject.getDouble("lon"), jsonObject.getDouble("lat"), current.getLong("sunset"), current.getLong("sunrise"), current.getLong("dt")));
                    currentWeather.setHumidity(current.getInt("humidity"));
                    JSONArray dailyArray = jsonObject.getJSONArray("daily");
                    JSONObject daily = dailyArray.getJSONObject(0);
                    JSONObject temp = daily.getJSONObject("temp");
                    currentWeather.setTemperature(new Temperature(current.getInt("temp"), temp.getInt("min"), temp.getInt("max"), current.getInt("feels_like")));

                    currentWeather.setWind(new Wind(current.getInt("wind_speed"), current.getInt("wind_deg")));
                    JSONArray weather = current.getJSONArray("weather");
                    currentWeather.setSituation(weather.getJSONObject(0).getString("main"));
                    currentWeather.setIcon(weather.getJSONObject(0).getString("icon"));

                    setInField();

                } catch (Exception e) {
                    e.getMessage();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", "error");
            }
        });

        requestQueue.add(stringRequest);
    }

    private void setInField() {

        city_name.setText(currentWeather.getLocation().getName());
        situation.setText(currentWeather.getSituation());
        temp.setText(String.valueOf(currentWeather.getTemperature().getTemp()));
        wind_deg.setText("deg: " + String.valueOf(currentWeather.getWind().getDegree()) + "°");
        wind_speed.setText("speed: " + String.valueOf(currentWeather.getWind().getSpeed()) + " km");
        min_max.setText(currentWeather.getTemperature().getMinTemp() + "° / " + currentWeather.getTemperature().getMaxTemp() + "°");
        feelLike.setText(currentWeather.getTemperature().getFeelTemp() + "°C");
//        Picasso.get().load("http://openweathermap.org/img/wn/"+weather.getIcon()+"@2x.png").into(weather_img);
        progressWheel.setPercentage(currentWeather.getHumidity() * 36 / 10);
        progressWheel.setStepCountText(String.valueOf(currentWeather.getHumidity()));

        time();
        setAnimation();
    }

    private void start() {
        currentWeather = new CurrentWeather();
        currentWeather.setLocation(new Location(cities.get(0).getName(), null, cities.get(0).getLon(), cities.get(0).getLat(), 0, 0, 0));
        ApiReq();

    }

    private void setAnimation() {
        char[] icon = currentWeather.getIcon().toCharArray();
        String name = "";
        name += (char) icon[2];
        name += "_";
        name += (char) icon[0] + "" + (char) icon[1] + ".json";

        switch (name) {
            case "d_01.json":
                weather_img.setAnimation(R.raw.d_01);
                break;
            case "n_01.json":
                weather_img.setAnimation(R.raw.n_01);
                break;
            case "d_02.json":
                weather_img.setAnimation(R.raw.d_02);
                break;
            case "n_03.json":
                weather_img.setAnimation(R.raw.n_03);
                break;
            case "d_03.json":
                weather_img.setAnimation(R.raw.n_03);
                break;
            case "n_04.json":
                weather_img.setAnimation(R.raw.n_03);
                break;
            case "d_04.json":
                weather_img.setAnimation(R.raw.n_03);
                break;
            case "n_09.json":
                weather_img.setAnimation(R.raw.n_09);
                break;
            case "d_09.json":
                weather_img.setAnimation(R.raw.d_09);
                break;
            case "n_11.json":
                weather_img.setAnimation(R.raw.n_11);
                break;
            case "d_11.json":
                weather_img.setAnimation(R.raw.n_11);
                break;
            case "n_13.json":
                weather_img.setAnimation(R.raw.n_13);
                break;
            case "d_13.json":
                weather_img.setAnimation(R.raw.d_13);
                break;
            case "n_50.json":
                weather_img.setAnimation(R.raw.n_50);
                break;
            case "d_50.json":
                weather_img.setAnimation(R.raw.n_50);
                break;
        }

        weather_img.loop(true);
        weather_img.playAnimation();

    }

    private void time() {
        try {
            DateTimeFormatter formatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formatter = DateTimeFormatter.ofPattern("E,c'th' MMMM").withZone(ZoneId.of(currentWeather.getLocation().getTimeZone()));
                String time = formatter.format(Instant.ofEpochSecond(currentWeather.getLocation().getDt()));
//                date_location.setText(time);
            }

        } catch (Exception e) {
            e.getMessage();
        }

    }

}