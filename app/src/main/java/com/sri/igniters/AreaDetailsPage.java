package com.sri.igniters;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AreaDetailsPage extends AppCompatActivity {

    String irval;


    private ListenerRegistration listenerRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_details_page);


        TextView lightCountTextView = findViewById(R.id.lightCountTextView);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("areas").document("chromepet");

//        TextView txt1 = findViewById(R.id.lightCountTextView);
//        TextView txt2 = findViewById(R.id.text2);

        listenerRegistration = docRef.addSnapshotListener((document, e) -> {
            if (e != null) {
                lightCountTextView.setText("Listen failed: " + e);
                return;
            }

            if (document != null && document.exists()) {
                String irValue = document.getString("ir");
                if (irValue != null) {
                    lightCountTextView.setText("IR value: " + irValue);
                } else {
                    lightCountTextView.setText("IR field does not exist in the document");
                }
            } else {
                lightCountTextView.setText("No such document");
            }
        });


        // Get the data passed from the previous activity
        String areaName = getIntent().getStringExtra("AREA_NAME");
//        int lightCount = getIntent().getIntExtra("LIGHT_COUNT", 30);
//        int sensorCount = getIntent().getIntExtra("SENSOR_COUNT", 45);

//         Find your TextViews in the layout
        TextView areaNameTextView = findViewById(R.id.areaNameTextView);
//        TextView sensorCountTextView = findViewById(R.id.sensorCountTextView);

//         Set the text of your TextViews
        areaNameTextView.setText(areaName);
//        sensorCountTextView.setText(String.valueOf(sensorCount));
    }

}
