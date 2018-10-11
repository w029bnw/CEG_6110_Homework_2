package com.example.brittanywoods.homework2;

/**
 * This is the abstract class that the analog and digital clock views extend.
 *
 * This class contains all of the base functions and variables implemented and used by the
 * clock views.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public abstract class ClockView extends View{

    int year, month, day, hour, minute, second;
    int height, width;
    Paint paint;
    boolean init;

    public ClockView(Context context) {
        super(context);
    }
    public ClockView(Context context, AttributeSet attributeSet){ super(context, attributeSet);}
    protected abstract void onDraw(Canvas canvas);
    abstract void setYear(int year);
    abstract void setMonth(int month);
    abstract void setDay(int day);
    abstract void setHour(int hour);
    abstract void setMinute(int minute);
    abstract void setSecond(int second);
}
