package com.mohammadkz.weather_app.Fragment;

import android.content.DialogInterface;
import android.graphics.drawable.Icon;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mohammadkz.weather_app.Adapter.SearchAdapter;
import com.mohammadkz.weather_app.Model.City;
import com.mohammadkz.weather_app.Model.CityDA;
import com.mohammadkz.weather_app.Model.Weather.CurrentWeather;
import com.mohammadkz.weather_app.Model.Weather.Location;
import com.mohammadkz.weather_app.R;

import org.json.JSONObject;

import java.util.ArrayList;


public class SearchWeatherFragment extends Fragment {

    View view;
    EditText searchView;
    RequestQueue requestQueue;
    CurrentWeather weather;
    RecyclerView recyclerView;

    public SearchWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_weather, container, false);

        requestQueue = Volley.newRequestQueue(getContext());
        initView();
        controllerView();

        return view;
    }

    private void initView() {
        searchView = view.findViewById(R.id.searchView);
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    private void controllerView() {
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("check", " " + s);
                reqApi();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void reqApi() {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + searchView.getText() + "&appid=25e1f42cf5cd09a5858e1f02c43c476a";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getInt("cod") != 200) {

                    } else {

                        weather = new CurrentWeather();
                        JSONObject location = jsonObject.getJSONObject("coord");
                        String name = jsonObject.getJSONObject("sys").getString("country") + "," + jsonObject.getString("name");
                        weather.setLocation(new Location(name, null, location.getDouble("lon"), location.getDouble("lat"), 0, 0, 0));
                        weather.setId(String.valueOf(jsonObject.getInt("id")));

                        setAdapter();
                    }


                } catch (Exception e) {
                    e.getMessage();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }

    private void setAdapter() {
        ArrayList<CurrentWeather> currentWeathers = new ArrayList<>();
        currentWeathers.add(weather);
        SearchAdapter adapter = new SearchAdapter(getContext(), currentWeathers);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, View v) {
                addDataDB(currentWeathers.get(pos));
            }
        });

    }

    private void addDataDB(CurrentWeather weather) {
        CityDA cityDA = new CityDA(getActivity());
        cityDA.openDB();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("set this city as your home?");

// Add the buttons
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK butto
                boolean responce = cityDA.addNewCity_weather(new City(weather.getLocation().getName(), weather.getId(), true, weather.getLocation().getLat(), weather.getLocation().getLon()));
                if (responce) {
                    Toast.makeText(getContext(), "city added", Toast.LENGTH_SHORT).show();
                    WeatherFragment weatherFragment = new WeatherFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, weatherFragment);
                    fragmentTransaction.commit();

                } else {

                }

            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                boolean responce = cityDA.addNewCity_weather(new City(weather.getLocation().getName(), weather.getId(), true, weather.getLocation().getLat(), weather.getLocation().getLon()));
                if (responce) {
                    Toast.makeText(getContext(), "city added", Toast.LENGTH_SHORT).show();
                    WeatherFragment weatherFragment = new WeatherFragment();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, weatherFragment);
                    fragmentTransaction.commit();

                } else {

                }
            }
        });

        AlertDialog alert = builder.create();
        alert.show();


    }

}