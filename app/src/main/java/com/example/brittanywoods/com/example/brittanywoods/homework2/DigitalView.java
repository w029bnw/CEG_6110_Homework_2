package com.example.brittanywoods.homework2;

/**
 * This is the digital view that displays the digital clock. This view simply displays text
 * that updates via the model and controller, and contains the appropriate setters for the time
 * and date variables to be set by the controller.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;

public class DigitalView extends ClockView {

    // Constructors for the digital clock view
    public DigitalView(Context context){
        super(context);
    }

    public DigitalView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    // This draws the digital clock by drawing the text depicting the time and date of the clock
    @Override
    protected void onDraw(Canvas canvas) {

        if(!init){
            paint = new Paint();
            height = getHeight();
            width = getWidth();

            init = true;
        }

        canvas.drawColor(Color.WHITE);

        paint.reset();
        paint.setColor(Color.BLACK);
        int fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14,getResources().getDisplayMetrics());
        paint.setTextSize(fontSize);

        canvas.drawText(hour + " : " + minute + " : " + second,100,175,paint);
        canvas.drawText(month+ "/" + day + "/" + year,100,125,paint);

        postInvalidateDelayed(500);
        invalidate();

    }

    // Setters used by the controller to set the view to the values specified by the model
    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second){
        this.second = second;
    }
}