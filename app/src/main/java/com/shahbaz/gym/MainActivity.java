package com.shahbaz.gym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shahbaz.gym.appUtils.Views.TextViewFarsi;
import com.shahbaz.gym.menus.coach.Coach;
import com.shahbaz.gym.menus.gym.Gym;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;


public class MainActivity extends AppCompatActivity {

    private int a;
    private ResideMenu resideMenu;
    private ResideMenuItem gym;
    private ResideMenuItem coach;
    private ResideMenuItem planing;
    private ResideMenuItem tools;
    private ResideMenuItem settings;
    private ResideMenuItem communicate;

    private TextViewFarsi lable_menu;
    private ImageView icon_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        lable_menu =(TextViewFarsi) findViewById(R.id.lable_menu);
        lable_menu.setTypeface(Typeface.createFromAsset(getApplication().getAssets() , "ic_fo3.TTF"));
        lable_menu.setTextColor(Color.parseColor("#000000"));
        icon_menu =(ImageView)findViewById(R.id.icon_menu);

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


}
