package com.mohammadkz.weather_app.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mohammadkz.weather_app.Adapter.RunwayAdapter;
import com.mohammadkz.weather_app.Model.Metar.Metar;
import com.mohammadkz.weather_app.Model.Metar.Runway;
import com.mohammadkz.weather_app.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MetarFragment extends Fragment {

    View view;
    TextView airportName, countryName, localTime, utcTime, airportMetar, airportRMK;
    RecyclerView runwayList;
    Metar metar = new Metar();
    String icao;
    RequestQueue requestQueue;


    public MetarFragment(String icao) {
        // Required empty public constructor
        this.icao = icao;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_metar, container, false);

        requestQueue = Volley.newRequestQueue(getContext());

        initViews();
        controllerViews();
        reqApi();

        return view;
    }

    private void initViews() {
        airportName = view.findViewById(R.id.airportName);
        countryName = view.findViewById(R.id.countryName);
        localTime = view.findViewById(R.id.localTime);
        utcTime = view.findViewById(R.id.utcTime);
        airportMetar = view.findViewById(R.id.airportMetar);
        airportRMK = view.findViewById(R.id.airportRMK);
        runwayList = view.findViewById(R.id.runwayList);
    }

    private void controllerViews() {

    }

    private void setAdapter() {
        System.out.println();
        RunwayAdapter runwayAdapter = new RunwayAdapter(getContext(), metar.getRunwayList());

        runwayList.setHasFixedSize(true);
        runwayList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        runwayList.setAdapter(runwayAdapter);
    }

    private void reqApi() {

        reqApi_Station();
        reqApi_metar();

    }

    private void reqApi_metar() {
        String url_api_metar = "https://avwx.rest/api/metar/" + icao + "?token=2y2HRs2o2lRathZVytj4WOgJAWpF6YcbVwzWIFMnJ_I";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_api_metar, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    metar.setSanitized(jsonObject.getString("sanitized"));
                    Log.i("check metar", " " + metar.getSanitized());
                    metar.setFlight_rules(jsonObject.getString("flight_rules"));
                    metar.setRemark(jsonObject.getString("remarks"));
                    Log.i("check rmk", " " + metar.getRemark());
                    ///
                    JSONObject a = jsonObject.getJSONObject("time");
                    String A = a.getString("dt");
                    char aChar[] = A.toCharArray();
                    String data[] = getTimeDate(aChar);


                    JSONObject b = jsonObject.getJSONObject("meta");
                    String B = b.getString("timestamp");
                    char[] time = B.toCharArray();
                    metar.setUTC((char) time[11] + "" + (char) time[12] + ":" + (char) time[14] + (char) time[15]);
                    aChar = null;
                    aChar = B.toCharArray();
//                    String data1[] = getTimeDate(aChar);
                    System.out.println();

                    setAdapter();
                    setFilled();

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

    private void reqApi_Station() {
        String url_api_station = "https://avwx.rest/api/station/" + icao + "?token=2y2HRs2o2lRathZVytj4WOgJAWpF6YcbVwzWIFMnJ_I";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_api_station, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

//                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("runways");

                    Log.e("lentgh", "aa  " + jsonArray.length());

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    ArrayList<Runway> runways = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Runway runway = gson.fromJson(String.valueOf(jsonArray.getJSONObject(i)), Runway.class);
                        runways.add(runway);
                    }
                    metar.setRunwayList(runways);

                    metar.setCity(jsonObject.getString("city"));
                    metar.setCountry(jsonObject.getString("country"));
                    metar.setIata(jsonObject.getString("iata"));
                    metar.setIcao(jsonObject.getString("icao"));
                    metar.setName(jsonObject.getString("name"));
                    metar.setType(jsonObject.getString("type"));
                    metar.setElevation_ft(jsonObject.getInt("elevation_ft"));
                    metar.setElevation_m(jsonObject.getInt("elevation_m"));


                } catch (Exception e) {
                    e.getMessage();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "connection failed" + " ");
            }
        });

        requestQueue.add(stringRequest);
    }

    private void setFilled() {
//        setAdapter();
        airportName.setText(metar.getName());
        countryName.setText(metar.getCountry());
        airportMetar.setText(metar.getSanitized());
        airportRMK.setText(metar.getRemark());
        utcTime.setText(metar.getUTC());

    }

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

}