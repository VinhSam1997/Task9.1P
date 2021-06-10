package com.example.task9;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.task9.databinding.ActivityAllplaceactivityBinding;

import java.util.ArrayList;
import java.util.List;

public class allplaceactivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityAllplaceactivityBinding binding;
    DatabaseHelper db;
    List<Location> locationList;
    Double Latitude, Longitude;
    String placename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAllplaceactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        db = new DatabaseHelper(allplaceactivity.this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        locationList = new ArrayList<>();
        locationList = db.GetAllLocations();
        for (int i = 0; i < locationList.size(); i++)
        {
            placename = locationList.get(i).getName();
            Latitude = locationList.get(i).getLatitude();
            Longitude = locationList.get(i).getLongitude();

            LatLng location = new LatLng(Latitude, Longitude);
            mMap.addMarker(new MarkerOptions().position(location).title(placename));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
        }
    }
}