package com.sri.igniters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button logout, nextpg,addlight;
    TextView textView,area1;
    FirebaseUser user;
    private static final String TAG = "MainActivity";
// ...


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        authentication
        auth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.btn_logout);

//        realtime data base
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("chromepet/ir sensor");

//        elements
        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();
        area1 = findViewById(R.id.area_1);

//        login check
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else{
            textView.setText(user.getEmail());
        }
//        logout code
        logout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                if (value != null) {
                    String stringValue = value.toString();
                    Log.d(TAG, "Value is: " + stringValue);
                    // Update the TextView with the retrieved value
                    area1.setText(stringValue);
                } else {
                    Log.w(TAG, "Value is null");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        nextpg = findViewById(R.id.nextpage);
        nextpg.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AreaPage.class);
            startActivity(intent);
            finish();
        });


        addlight = findViewById(R.id.addlight);
        addlight.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), LocationPage.class);
            startActivity(intent);
        });

    }
}