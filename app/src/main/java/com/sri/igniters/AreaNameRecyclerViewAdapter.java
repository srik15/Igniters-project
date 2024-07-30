
package com.sri.igniters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class AreaNameRecyclerViewAdapter extends RecyclerView.Adapter<AreaNameRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<AreaNameModel> areaNameModels;
    private OnItemClickListener listener;

    public AreaNameRecyclerViewAdapter(Context context, ArrayList<AreaNameModel> areaNameModels) {
        this.context = context;
        this.areaNameModels = areaNameModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.area_name_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AreaNameModel model = areaNameModels.get(position);
        holder.areaName.setText(model.getArea_name());

        holder.areaDetails.setOnClickListener(v -> {
            if (listener != null && position != RecyclerView.NO_POSITION) {
                listener.onItemClick(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return areaNameModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView areaName;
        Button areaDetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            areaName = itemView.findViewById(R.id.textAreaName);
            areaDetails = itemView.findViewById(R.id.areadetails);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(AreaNameModel areaNameModel);
    }

    // Change this method to accept your custom OnItemClickListener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
//
//public class AreaNameRecyclerViewAdapter extends RecyclerView.Adapter<AreaNameRecyclerViewAdapter.MyViewHolder>{
//
//    private Context context;
//    private ArrayList<AreaNameModel> areaNameModels;
//    private AdapterView.OnItemClickListener listener;
//
//
//
//    public AreaNameRecyclerViewAdapter(Context context, ArrayList<AreaNameModel> areaNameModels) {
//        this.context = context;
//        this.areaNameModels = areaNameModels;
//    }
//
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.area_name_card, parent, false);
////        LayoutInflater inflater = LayoutInflater.from(context);
////        View view =inflater.inflate(R.layout.area_name_card,parent,false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        AreaNameModel models = areaNameModels.get(position);
//        holder.areaName.setText(models.getArea_name());
//
//        holder.itemView.setOnClickListener(v -> {
//            if (listener != null && position != RecyclerView.NO_POSITION) {
//                listener.onItemClick(models);
//            }
//        });
////        holder.itemView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (listener != null) {
////                    listener.onItemClick(models);
////                }
////            }
////        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return areaNameModels.size();
//    }
//
//
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//        TextView areaName;
//        public MyViewHolder(@NonNull View itemView){
//            super(itemView);
//            areaName = itemView.findViewById(R.id.textAreaName);
//        }
//    }
//
//    public interface OnItemClickListener{
//        void onItemClick(AreaNameModel areaNameModel);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.listener =listener;
//    }
//
//}
