package com.simurgh.gym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.simurgh.gym.menus.coach.Coach;
import com.simurgh.gym.menus.gym.Gym;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        setUpMenu();

        findViewById(R.id.open_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });

        if (savedInstanceState == null){
            changeFragment(new Gym());
        }

    }

    private void setUpMenu() {

        resideMenu = new ResideMenu(this);
        resideMenu.setUse3D(false);
        resideMenu.setBackground(R.drawable.ic_logo3);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.6f);
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);

        gym = new ResideMenuItem(this, R.drawable.ic_launcher,R.string.gym_menu);
        coach = new ResideMenuItem(this, R.drawable.ic_launcher,R.string.coach_menu);
        planing = new ResideMenuItem(this, R.drawable.ic_launcher,R.string.planing_menu);
        tools = new ResideMenuItem(this, R.drawable.ic_launcher,R.string.tools_menu);
        settings = new ResideMenuItem(this, R.drawable.ic_launcher,R.string.settings_menu);
        communicate = new ResideMenuItem(this, R.drawable.ic_launcher,R.string.communicate);

        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();
            }
        });
        coach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Coach());
                resideMenu.closeMenu();
            }
        });
        planing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();
            }
        });
        tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();
            }
        });
        communicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new Gym());
                resideMenu.closeMenu();
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

    public ResideMenu getResideMenu(){
        return resideMenu;
    }


}