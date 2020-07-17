package com.shahbaz.gym;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
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
import com.shahbaz.gym.appUtils.Data.StoreValue;
import com.shahbaz.gym.appUtils.Views.TextViewFarsi;
import com.shahbaz.gym.appUtils.Views.tos;
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
                resideMenu.closeMenu();
                InitGoogleApiClient();
                setUpToolbarAndInit(R.string.gym_menu , R.drawable.gym_menu_ic , true);
            }
        });
        coach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Coach());
                resideMenu.closeMenu();
                setUpToolbarAndInit(R.string.coach_menu , R.drawable.coach_menu_ic , false);
            }
        });
        planing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();
                setUpToolbarAndInit(R.string.planing_menu , R.drawable.planing_menu_ic , false);
            }
        });
        tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();
                setUpToolbarAndInit(R.string.tools_menu , R.drawable.tools_menu_ic , false);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();
                setUpToolbarAndInit(R.string.settings_menu , R.drawable.settings_menu_ic , false);
            }
        });
        communicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();
                setUpToolbarAndInit(R.string.communicate , R.drawable.communicate_menu_ic , false);
            }
        });


        resideMenu.addMenuItem(gym , ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(coach , ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(planing , ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(tools , ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(settings , ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(communicate , ResideMenu.DIRECTION_RIGHT);

    }
    public void setUpToolbarAndInit(int string_id , int icon_id,boolean its_gym){
        if (its_gym) {
            lable_menu.setText(string_id);
            icon_menu.setImageResource(icon_id);
        }else{
            lable_menu.setText(string_id);
            icon_menu.setImageResource(icon_id);
            started_map = false;
        }
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

    private void changeFragment(Fragment targetFragment  , String[] args){
        resideMenu.clearIgnoredViewList();
        resideMenu.setMenu_enable(true);

        Bundle bundle = new Bundle();
        for (int i = 0; i < args.length; i++) {
            bundle.putString(args[i].split(":")[0] , args[i].split(":")[1]);
        }
        targetFragment.setArguments(bundle);
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
                            }

                            @Override
                            public void onConnectionSuspended(int i) {

                            }
                        })
                        .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                            @Override
                            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                                Toast.makeText(thisContextActivity, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }).build();
                googleApiClient.connect();
                ShowGpsDialog();
            }else{
                ShowGpsDialog();
            }


        }else{
            ActivityCompat.requestPermissions(MainActivity.this,
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
                if (state.isGpsPresent() && state.isGpsUsable()){
                    if (!started_map) {
                        started_map = true;
                        changeFragment(new Gym() , new String[]{"gps:on"});
                    }
                }
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.

                        try {
                            //get a last location user if a location not null load it and if it null
                            //request to enable gps

                            float zoom = Float.parseFloat(StoreValue.getVal(getApplicationContext() ,
                                    StoreValue.User.last_user_location , "0:0:0")
                                    .split(":")[2]);

                            if (zoom != 0f){
                                if (zoom > 20f){
                                    tos.warning(MainActivity.this , R.string.request_enable_gps);
                                    status.startResolutionForResult(
                                            MainActivity.this, REQUEST_LOCATION_CODE);
                                }else{
                                    changeFragment(new Gym() ,new String[]{"gps:off"});
                                }
                            }else{
                                tos.warning(MainActivity.this , R.string.request_enable_gps);
                                status.startResolutionForResult(
                                        MainActivity.this, REQUEST_LOCATION_CODE);
                            }

                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
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
                if (!started_map) {
                    changeFragment(new Gym() ,new String[]{"gps:on"});
                    started_map = true;
                }
            }else{
                if (!started_map) {
                    changeFragment(new Gym() ,new String[]{"gps:off"});
                    started_map = true;
                }
                tos.error(MainActivity.this , R.string.no_enable_gps);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_GOOGLE_MAP_FINE_LOCATION){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                InitGoogleApiClient();
                tos.success(MainActivity.this , R.string.network_ok);
            }else{
                tos.error(MainActivity.this , R.string.no_access);
                tos.warning(MainActivity.this , R.string.make_access);
            }
        }

    }
}
