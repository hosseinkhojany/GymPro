package com.shahbaz.gym.appUtils.Data;

import com.google.android.gms.maps.model.LatLng;
import com.shahbaz.gym.menus.gym.Gym;

import java.util.ArrayList;
import java.util.List;

public class MemoryCache {

    static List<Gym> listStoredGym = new ArrayList<>();
    public static void putListStoredGym(List<Gym> list){
        if (list != null){
            listStoredGym = list;
        }
    }
    public static List<Gym> getListStoredGym(){
        return listStoredGym != null ? listStoredGym : new ArrayList<Gym>();
    }
    static LatLng currentLocationStored;
    public static void putCurrentLocation(LatLng latLng){
        if (latLng != null){
            currentLocationStored = latLng;
        }
    }

    public static LatLng getCurrentLocationStored(){
        return listStoredGym != null ? currentLocationStored : new LatLng(0 , 0);
    }

}
