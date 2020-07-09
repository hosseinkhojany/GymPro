package com.simurgh.gym.appUtils.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.simurgh.gym.R;

/**
 * Created by hoang8f on 5/5/14.
 */

public class TextViewFarsi extends AppCompatTextView {

    public TextViewFarsi(Context context) {
        super(context);
        setTypeface(Typeface.createFromAsset(context.getAssets() , "ic_fo.ttf"));

    }
}
