package com.mohammadkz.weather_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.mohammadkz.weather_app.Model.Weather.CurrentWeather;

import java.util.ArrayList;
import java.util.Currency;

import static android.os.Build.VERSION_CODES.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Context context;
    ArrayList<CurrentWeather> weathers;
    public OnItemClickListener onItemClickListener;

    public SearchAdapter(Context context, ArrayList<CurrentWeather> weather) {
        this.context = context;
        this.weathers = weather;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(com.mohammadkz.weather_app.R.layout.search_name, parent, false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.name.setText(weathers.get(position).getLocation().getName());
        holder.lon.setText("lon:  " + String.valueOf(weathers.get(position).getLocation().getLon()));
        holder.lat.setText("lat:  " + String.valueOf(weathers.get(position).getLocation().getLat()));
        holder.id.setText("ID: " + weathers.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, lon, lat, id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(com.mohammadkz.weather_app.R.id.city);
            lat = itemView.findViewById(com.mohammadkz.weather_app.R.id.lat);
            lon = itemView.findViewById(com.mohammadkz.weather_app.R.id.lon);
            id = itemView.findViewById(com.mohammadkz.weather_app.R.id.id);
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
