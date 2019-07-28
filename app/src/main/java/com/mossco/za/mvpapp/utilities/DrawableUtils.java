package com.mossco.za.mvpapp.utilities;

import android.content.Context;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class DrawableUtils {
    public static CircularProgressDrawable getCircularProgressDrawable(Context context) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(10f);
        circularProgressDrawable.setCenterRadius(60f);
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }
}
