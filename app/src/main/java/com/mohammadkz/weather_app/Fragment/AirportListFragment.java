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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mohammadkz.weather_app.Adapter.AirportAdapter;
import com.mohammadkz.weather_app.Model.Airport;
import com.mohammadkz.weather_app.Model.CityDA;
import com.mohammadkz.weather_app.R;

import java.util.ArrayList;

public class AirportListFragment extends Fragment {

    View view;
    RecyclerView airportList;
    ArrayList<Airport> Airportarray;
    FloatingActionButton newAirport;

    public AirportListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_airport_list, container, false);

        InitViews();
        ControllerViews();
        getData();

        return view;
    }

    private void InitViews() {
        airportList = view.findViewById(R.id.AirportList);
        newAirport = view.findViewById(R.id.newAirport);
    }

    private void ControllerViews() {

        newAirport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchMetarFragment searchMetarFragment = new SearchMetarFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, searchMetarFragment);
                fragmentTransaction.commit();
            }
        });

    }

    private void getData() {
        CityDA cityDA = new CityDA(getActivity());
        cityDA.openDB();
//        Boolean check = cityDA.addNewAirport(new Airport("mehrabad", "123444", "KJFK"));
//        Log.e("check", " " + check);
        Airportarray = cityDA.getAirport();
        if (Airportarray == null) {
            Airportarray = new ArrayList<>();
        } else
            setAdapter();

    }

    private void setAdapter() {

        AirportAdapter airportAdapter = new AirportAdapter(getContext(), Airportarray);
        airportList.setHasFixedSize(true);
        airportList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        airportList.setAdapter(airportAdapter);

        airportAdapter.setOnItemClickListener(new AirportAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, View v) {
                MetarFragment metarFragment = new MetarFragment(Airportarray.get(pos).getIcao());
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, metarFragment);
                fragmentTransaction.commit();
            }
        });


    }
}