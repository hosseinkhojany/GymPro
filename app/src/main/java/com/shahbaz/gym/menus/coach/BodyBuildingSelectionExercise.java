package com.shahbaz.gym.menus.coach;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.shahbaz.gym.R;

public class BodyBuildingSelectionExercise extends Fragment {
    private View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_bodybuildingselectioncategory, container, false);


        return parentView;
    }



}
