package com.sri.igniters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.HashMap;
import java.util.Map;

public class LocationPage extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationProviderClient fusedLocationClient;
    private TextView locationTextView;
    private Button getLocationButton;
    private ListenerRegistration listenerRegistration;
    LocationRequest locationRequest;
    private String area_name = "Chromepet";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_page);

//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        DocumentReference docRef_loc = db.collection("street light location").document("Chromepet");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationTextView = findViewById(R.id.latlong);
        getLocationButton = findViewById(R.id.get_location_btn);

        getLocationButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getCurrentLocation();
        }
        });

                locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
                .setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(500)
                .setMaxUpdateDelayMillis(1000)
                .build();
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

//        fusedLocationClient.getLastLocation()
//                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        if (location != null) {
//                            double latitude = location.getLatitude();
//                            double longitude = location.getLongitude();
//                            String locationText = "Latitude: " + latitude + "\nLongitude: " + longitude;
//                            locationTextView.setText(locationText);
//                        } else {
//                            locationTextView.setText("Location not available");
//                        }
//                    }
//                });
//        locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
//                .setWaitForAccurateLocation(false)
//                .setMinUpdateIntervalMillis(500)
//                .setMaxUpdateDelayMillis(1000)
//                .build();
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(1000);
//        locationRequest.setFastestInterval(500);

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    locationTextView.setText("Location not available");
                    return;
                }
                Location location = locationResult.getLastLocation();
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String locationText = "Latitude: " + latitude + "\nLongitude: " + longitude;
                locationTextView.setText(locationText);
                sendDataToFirestore(area_name,latitude,longitude);
                fusedLocationClient.removeLocationUpdates(this);
            }
        };

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendDataToFirestore(String area_name,double latitude,double longitude) {
        // Get a reference to the Firestore database
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Reference to the document where you want to store the data
        DocumentReference docRef_area = db.collection("street light location").document(area_name)
                .collection("area 1").document("light 1");

        // Create a map of the data you want to send
        Map<String, Object> data = new HashMap<>();
        data.put("Latitude", latitude);
        data.put("Longitude", longitude);

        // Set the data to Firestore
        docRef_area.set(data)
                .addOnSuccessListener(aVoid -> {
                    locationTextView.setText("Data sent successfully");
                })
                .addOnFailureListener(e -> {
                    locationTextView.setText("Error sending data: " + e.getMessage());
                });
    }

}

