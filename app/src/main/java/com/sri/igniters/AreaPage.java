package com.sri.igniters;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AreaPage extends AppCompatActivity {
    ArrayList<AreaNameModel> areaNameModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_name_page);

        RecyclerView recyclerView = findViewById(R.id.areaRecycleView);
        setUpAreaModels();

        AreaNameRecyclerViewAdapter adapter = new AreaNameRecyclerViewAdapter(this, areaNameModels);

        adapter.setOnItemClickListener(areaNameModel -> {
            Intent intent = new Intent(AreaPage.this, AreaDetailedPage.class);
            intent.putExtra("AREA_NAME", areaNameModel.getArea_name());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpAreaModels(){
        String[] area = getResources().getStringArray(R.array.area_name_val);
        for (String s : area) {
            areaNameModels.add(new AreaNameModel(s));
        }
    }
}
//
//public class AreaPage extends AppCompatActivity {
//    ArrayList<AreaNameModel> areaNameModels = new ArrayList<>();
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.area_name_page);
//
//        RecyclerView recyclerView = findViewById(R.id.areaRecycleView);
//        setUpAreaModels();
//
//        AreaNameRecyclerViewAdapter adapter= new AreaNameRecyclerViewAdapter(this,areaNameModels);
//        adapter.setOnItemClickListener(new AreaNameRecyclerViewAdapter.OnItemClickListener(){
//            @Override
//            public void onItemClick(AreaNameModel areaNameModel) {
//                Intent intent = new Intent(AreaPage.this, AreaDetailsPage.class);
//                intent.putExtra("AREA_NAME", areaNameModel.getArea_name());
//                startActivity(intent);
//            }
//        });
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }
//
//    public void setUpAreaModels(){
//        String[] area = getResources().getStringArray(R.array.area_name_val);
//        for (String s : area) {
//            areaNameModels.add(new AreaNameModel(s));
//        }
//    }
//}
