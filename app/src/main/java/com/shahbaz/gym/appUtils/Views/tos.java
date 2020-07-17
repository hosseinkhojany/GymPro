package com.shahbaz.gym.appUtils.Views;

import android.app.Activity;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snacky;
import com.chootdev.csnackbar.Type;
import com.shahbaz.gym.R;

public class tos {
    public static void tos(Activity activity, String msg){
        Snacky.with(activity,null)
                .type(Type.SUCCESS)
                .message(msg)
                .textAlign(Align.RIGHT)
                .duration(Duration.CUSTOM , 5000)
                .show();
        }
    public static void success(Activity activity, int string_id){
        String msg = activity.getResources().getString(string_id);
        Snacky.with(activity,null)
                .type(Type.SUCCESS)
                .message(msg)
                .textAlign(Align.RIGHT)
                .duration(Duration.CUSTOM , 5000)
                .show();
        }
    public static void error(Activity activity,int string_id){
        String msg = activity.getResources().getString(string_id);
        if (string_id == R.string.network_no){
            Snacky.with(activity,null)
                    .type(Type.ERROR)
                    .message(msg)
                    .textAlign(Align.RIGHT)
                    .duration(Duration.CUSTOM , 5000)
                    .show();
        }else {
            Snacky.with(activity,null)
                    .type(Type.ERROR)
                    .message(msg)
                    .textAlign(Align.RIGHT)
                    .duration(Duration.CUSTOM , 5000)
                    .show();
        }
    }
    public static void warning(Activity activity ,int string_id)
    {
        String msg = activity.getResources().getString(string_id);
        Snacky.with(activity,null)
                .type(Type.WARNING)
                .message(msg)
                .textAlign(Align.RIGHT)
                .duration(Duration.CUSTOM , 5000)
                .show();
    }
}
