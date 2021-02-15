package com.mohammadkz.weather_app.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mohammadkz.weather_app.Model.Airport;
import com.mohammadkz.weather_app.Model.CityDA;
import com.mohammadkz.weather_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchMetarFragment extends Fragment {

    private View view;
    RequestQueue requestQueue;
    EditText icao;
    RecyclerView recyclerView;
    ListView list;
    List<String> airport = new ArrayList<>();

    public SearchMetarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_metar, container, false);
        requestQueue = Volley.newRequestQueue(getContext());
        InitViews();
        ControllerView();


        return view;
    }

    private void InitViews() {
        icao = view.findViewById(R.id.icao);
//        recyclerView = view.findViewById(R.id.recyclerView);
        list = view.findViewById(R.id.list);
    }

    private void ControllerView() {

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                saveAirport();
            }
        });

        icao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("name", " " + s);
                if (s.length() == 4)
                    reqApi(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void reqApi(String icao) {
        String url = "https://avwx.rest/api/station/" + icao + "?token=2y2HRs2o2lRathZVytj4WOgJAWpF6YcbVwzWIFMnJ_I";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject name = new JSONObject(response);
                    airport.add(name.getString("name"));
                    Log.e("name", " " + name);


                    setAdapter();

                } catch (JSONException e) {
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

        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),
                R.layout.airport_search, airport);

        list.setAdapter(adapter);
    }

    private void saveAirport() {
        CityDA cityDA = new CityDA(getActivity());
        cityDA.openDB();

        Boolean check = cityDA.addNewAirport(new Airport(airport.get(0), null, icao.getText().toString()));
        Log.e(" new airport", " " + check);

        AirportListFragment airportListFragment = new AirportListFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, airportListFragment);
        fragmentTransaction.commit();

    }

}