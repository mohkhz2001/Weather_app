package com.mohammadkz.weather_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohammadkz.weather_app.Model.City;
import com.mohammadkz.weather_app.R;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.viewHolder> {

    Context context;
    ArrayList<City> cities;
    public OnItemClickListener onItemClickListener;

    public CityAdapter(Context context, ArrayList<City> cities) {
        this.context = context;
        this.cities = cities;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.city_name, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.city_name_txt.setText(cities.get(position).getName());
    }

    @Override
    public int getItemCount() {
        try {

            return cities.size();
        }catch (Exception e){
            e.getMessage();
            return 0;
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView city_name_txt;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            city_name_txt = itemView.findViewById(R.id.city_name_txt);

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
