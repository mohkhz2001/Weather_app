package com.mohammadkz.weather_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohammadkz.weather_app.Model.Airport;
import com.mohammadkz.weather_app.R;

import java.util.ArrayList;

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.viewHolder> {

    Context context;
    ArrayList<Airport> airportList;
    private OnItemClickListener onItemClickListener;

    public AirportAdapter(Context context, ArrayList<Airport> airportList) {
        this.context = context;
        this.airportList = airportList;
    }

    @NonNull
    @Override
    public AirportAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.airport_name, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AirportAdapter.viewHolder holder, int position) {
        holder.airportName.setText(airportList.get(position).getName());
//        holder.rules.setText(airportList.get(position).get);
        holder.icao.setText(airportList.get(position).getIcao());
    }

    @Override
    public int getItemCount() {
        return airportList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView airportName, icao, rules;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            icao = itemView.findViewById(R.id.icao);
            airportName = itemView.findViewById(R.id.airportName);
            rules = itemView.findViewById(R.id.rules);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition(), v);/////
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, View v);
    }
}
