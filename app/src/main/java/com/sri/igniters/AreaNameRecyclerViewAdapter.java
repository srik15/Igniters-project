package com.sri.igniters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AreaNameRecyclerViewAdapter extends RecyclerView.Adapter<AreaNameRecyclerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<AreaNameModel> areaNameModels;


    public AreaNameRecyclerViewAdapter(Context context, ArrayList<AreaNameModel> areaNameModels) {
        this.context = context;
        this.areaNameModels = areaNameModels;
    }


    @NonNull
    @Override
    public AreaNameRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.area_name_card,parent,false);
        return new AreaNameRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaNameRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.areaName.setText(areaNameModels.get(position).getArea_name());
    }

    @Override
    public int getItemCount() {
        return areaNameModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView areaName;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            areaName = itemView.findViewById(R.id.textAreaName);
        }
    }

}
