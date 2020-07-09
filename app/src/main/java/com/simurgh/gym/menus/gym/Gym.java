package com.simurgh.gym.menus.gym;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.simurgh.gym.R;

public class Gym extends Fragment implements OnMapReadyCallback {

    private View parentView;
    private MapView mapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       parentView = inflater.inflate(R.layout.fragment_gym, container, false);


       mapView = (MapView)parentView.findViewById(R.id.map_gym);
       mapView.getMapAsync(this);
       



       return parentView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;



    }


}