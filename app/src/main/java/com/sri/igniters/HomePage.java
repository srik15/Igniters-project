package com.sri.igniters;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

//    FrameLayout chromepet;
    ArrayList<AreaNameModel> areaNameModels = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        RecyclerView recyclerView = findViewById(R.id.areaRecycleView);
        setUpAreaModels();

        AreaNameRecyclerViewAdapter adapter= new AreaNameRecyclerViewAdapter(this,areaNameModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        chromepet = findViewById(R.id.area_card);
//        chromepet.setOnClickListener(view -> {
//            Intent intent = new Intent(getApplicationContext(), Area1.class);
//            startActivity(intent);
//        });
    }

    public void setUpAreaModels(){
        String[] area = getResources().getStringArray(R.array.area_name_val);
        for (String s : area) {
            areaNameModels.add(new AreaNameModel(s));
        }
    }
}
