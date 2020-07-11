package com.shahbaz.gym.menus.gym;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.shahbaz.gym.MainActivity;
import com.shahbaz.gym.R;
import com.shahbaz.gym.appUtils.InternetOKey;
import com.shahbaz.gym.appUtils.l;
import com.special.ResideMenu.ResideMenu;

public class Gym extends Fragment implements OnMapReadyCallback {

    private View parentView;
    private Context thisActivityContext;
    private MainActivity menuActivity;
    private ResideMenu resideMenu;
    private static final int REQUEST_GOOGLE_MAP_FINE_LOCATION = 112;
    private static final int REQUEST_LOCATION_CODE = 111;



    private MapView mapView;
    private GoogleMap map;
    private GPSTracker tracker;
    private GoogleApiClient googleApiClient;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_gym, container, false);
        thisActivityContext = getActivity();

        tracker = new GPSTracker(thisActivityContext);

        mapView = (MapView) parentView.findViewById(R.id.map_gym);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        setUpViews();


        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(@Nullable Bundle bundle) {

                            Toast.makeText(thisActivityContext, "Connected", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onConnectionSuspended(int i) {


                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        }
                    }).build();
            googleApiClient.connect();
            ShowGpsDialog();


        }



        return parentView;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(thisActivityContext, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(thisActivityContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {


            map = googleMap;
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map.setMyLocationEnabled(true);





            //add a test markers
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Lat:"+latLng.latitude+"\n"+"Lng:"+latLng.longitude +"\n"+ map.getCameraPosition().zoom);
                    builder.show();
                }
            });

        } else {
            ActivityCompat.requestPermissions((Activity) thisActivityContext,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION , Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_GOOGLE_MAP_FINE_LOCATION);
        }



    }





    public void ShowGpsDialog(){
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        //**************************
        builder.setAlwaysShow(true); //this is the key ingredient
        //**************************

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                    
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        Toast.makeText(thisActivityContext, "Success", Toast.LENGTH_SHORT).show();
                        InternetOKey internetOKey = new InternetOKey();
                        internetOKey.addConnectionChangeListener(new InternetOKey.ConnectionChangeListener() {
                            @Override
                            public void onConnectionChanged(boolean isConnectionAvailable) {

                                if (isConnectionAvailable){
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(tracker.getLatitude() , tracker.longitude) , 15));
                                        }
                                    } , 2000);
                                }else{
                                    Toast.makeText(getActivity(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                            AlertDialog.Builder show_dl = new AlertDialog.Builder(getActivity());
                            show_dl.setMessage("برای کارکرد نقشه نیاز به فعال سازی مکان داریم");
                            show_dl.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    try {
                                        // Show the dialog by calling startResolutionForResult(),
                                        // and check the result in onActivityResult().
                                        status.startResolutionForResult(
                                                getActivity(), REQUEST_LOCATION_CODE);

                                    } catch (IntentSender.SendIntentException e) {
                                        // Ignore the error.
                                        Toast.makeText(getActivity(), "ERoor", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            show_dl.show();



                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settilient can inngs are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }





    private void setUpViews() {
        MainActivity parentActivity = (MainActivity) thisActivityContext;
        resideMenu = parentActivity.getResideMenu();
        resideMenu.setMenu_enable(false);

    }




}