package com.shahbaz.gym;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.shahbaz.gym.appUtils.InternetOKey;
import com.shahbaz.gym.appUtils.Views.TextViewFarsi;
import com.shahbaz.gym.menus.coach.Coach;
import com.shahbaz.gym.menus.gym.Gym;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;


public class MainActivity extends AppCompatActivity {

    private ResideMenu resideMenu;
    private ResideMenuItem gym;
    private ResideMenuItem coach;
    private ResideMenuItem planing;
    private ResideMenuItem tools;
    private ResideMenuItem settings;
    private ResideMenuItem communicate;

    private TextViewFarsi lable_menu;
    private ImageView icon_menu;
    private GoogleApiClient googleApiClient;
    private Context thisContextActivity ;

    private static final int REQUEST_GOOGLE_MAP_FINE_LOCATION = 112;
    private static final int REQUEST_LOCATION_CODE = 111;
    private boolean started_map = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        thisContextActivity = getApplicationContext();

        lable_menu = (TextViewFarsi) findViewById(R.id.lable_menu);
        lable_menu.setTypeface(Typeface.createFromAsset(getApplication().getAssets(), "ic_fo3.TTF"));
        lable_menu.setTextColor(Color.parseColor("#000000"));
        icon_menu = (ImageView) findViewById(R.id.icon_menu);

        setUpMenu();

        findViewById(R.id.open_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });


        InitGoogleApiClient();


    }

    private void setUpMenu() {

        resideMenu = new ResideMenu(this);
        resideMenu.setUse3D(false);
        resideMenu.setBackground(R.drawable.ic_logo3);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.6f);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);

        gym = new ResideMenuItem(this, R.drawable.gym_menu_ic,R.string.gym_menu);
        coach = new ResideMenuItem(this, R.drawable.coach_menu_ic,R.string.coach_menu);
        planing = new ResideMenuItem(this, R.drawable.planing_menu_ic,R.string.planing_menu);
        tools = new ResideMenuItem(this, R.drawable.tools_menu_ic,R.string.tools_menu);
        settings = new ResideMenuItem(this, R.drawable.settings_menu_ic,R.string.settings_menu);
        communicate = new ResideMenuItem(this, R.drawable.communicate_menu_ic,R.string.communicate);

        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();

                lable_menu.setText(R.string.gym_menu);
                icon_menu.setImageResource(R.drawable.gym_menu_ic);
            }
        });
        coach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Coach());
                resideMenu.closeMenu();

                lable_menu.setText(R.string.coach_menu);
                icon_menu.setImageResource(R.drawable.coach_menu_ic);
            }
        });
        planing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();

                lable_menu.setText(R.string.planing_menu);
                icon_menu.setImageResource(R.drawable.planing_menu_ic);
            }
        });
        tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();

                lable_menu.setText(R.string.tools_menu);
                icon_menu.setImageResource(R.drawable.tools_menu_ic);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();

                lable_menu.setText(R.string.settings_menu);
                icon_menu.setImageResource(R.drawable.settings_menu_ic);
            }
        });
        communicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();

                lable_menu.setText(R.string.communicate);
                icon_menu.setImageResource(R.drawable.communicate_menu_ic);
            }
        });


        resideMenu.addMenuItem(gym , ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(coach , ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(planing , ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(tools , ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(settings , ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(communicate , ResideMenu.DIRECTION_RIGHT);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }


    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        resideMenu.setMenu_enable(true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void onBackPressed() {

        if (!resideMenu.isOpened()){
            resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public ResideMenu getResideMenu(){
        return resideMenu;
    }



    public void InitGoogleApiClient(){
        if (ActivityCompat.checkSelfPermission(thisContextActivity, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(thisContextActivity, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

            if (googleApiClient == null) {
                googleApiClient = new GoogleApiClient.Builder(thisContextActivity)
                        .addApi(LocationServices.API)
                        .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                            @Override
                            public void onConnected(@Nullable Bundle bundle) {

                                Toast.makeText(thisContextActivity, "Connected", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onConnectionSuspended(int i) {

                                Toast.makeText(thisContextActivity, "Sus", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                            @Override
                            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                                Toast.makeText(thisContextActivity, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }).build();
                googleApiClient.connect();
                Toast.makeText(thisContextActivity, "Hello3", Toast.LENGTH_SHORT).show();
                ShowGpsDialog();
            }else{
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION , Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_GOOGLE_MAP_FINE_LOCATION);
            }


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
                        InternetOKey internetOKey = new InternetOKey();
                        internetOKey.addConnectionChangeListener(new InternetOKey.ConnectionChangeListener() {
                            @Override
                            public void onConnectionChanged(boolean isConnectionAvailable) {

                                if (isConnectionAvailable){
                                    if (!started_map) {
                                        changeFragment(new Gym());
                                        started_map = true;
                                    }
                                }else{
                                    Toast.makeText(thisContextActivity, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.

                                try {
                                    // Show the dialog by calling startResolutionForResult(),
                                    // and check the result in onActivityResult().
                                    status.startResolutionForResult(
                                            MainActivity.this, REQUEST_LOCATION_CODE);

                                } catch (IntentSender.SendIntentException e) {
                                    // Ignore the error.
                                    Toast.makeText(thisContextActivity, "ERoor", Toast.LENGTH_SHORT).show();
                                }



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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOCATION_CODE){
            if (resultCode == Activity.RESULT_OK){
                Toast.makeText(thisContextActivity, "Success", Toast.LENGTH_SHORT).show();
                InternetOKey internetOKey = new InternetOKey();
                internetOKey.addConnectionChangeListener(new InternetOKey.ConnectionChangeListener() {
                    @Override
                    public void onConnectionChanged(boolean isConnectionAvailable) {

                        if (isConnectionAvailable){
                            if (!started_map) {
                                changeFragment(new Gym());
                            started_map = true;
                            }
                        }else{
                            Toast.makeText(thisContextActivity, "No Internet Connection!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }else{
                Toast.makeText(thisContextActivity, "Hello2", Toast.LENGTH_SHORT).show();
                ShowGpsDialog();
            }
        }
    }
}
