package com.mohammadkz.weather_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohammadkz.weather_app.Model.Metar.Runway;
import com.mohammadkz.weather_app.R;

import java.util.ArrayList;

public class RunwayAdapter extends RecyclerView.Adapter<RunwayAdapter.viewHolder> {

    Context context;
    ArrayList<Runway> runways;

    public RunwayAdapter(Context context, ArrayList<Runway> runways) {
        this.context = context;
        this.runways = runways;
    }

    @NonNull
    @Override
    public RunwayAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.runway_info, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RunwayAdapter.viewHolder holder, int position) {
        holder.idents.setText(runways.get(position).getIdent1() + "/" + runways.get(position).getIdent2());
        holder.length.setText(runways.get(position).getLength_ft());
        holder.width.setText(runways.get(position).getWidth_ft());
        holder.surface.setText(runways.get(position).getSurface());

    }

    @Override
    public int getItemCount() {
        try {

            return runways.size();
        } catch (Exception e) {
            e.getMessage();
            return 0;

        }
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView idents, length, width, surface;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            idents = itemView.findViewById(R.id.idents);
            length = itemView.findViewById(R.id.length);
            width = itemView.findViewById(R.id.width);
            surface = itemView.findViewById(R.id.surface);

        }
    }

}
