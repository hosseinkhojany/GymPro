package com.shahbaz.gym.appUtils.Views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;


/**
 * Created by hoang8f on 5/5/14.
 */

public class TextViewFarsi extends AppCompatTextView {

    public TextViewFarsi(Context context) {
        super(context);
        setTypeface(Typeface.createFromAsset(context.getAssets() , "ic_fo2.TTF"));
        setTextColor(Color.parseColor("#000000"));

    }

    public TextViewFarsi(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewFarsi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
