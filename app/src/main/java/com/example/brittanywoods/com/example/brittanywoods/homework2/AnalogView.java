/**
 * I had to look up how to create an analog clock view online. I originally attempted to use
 * an external library, but was unsure on how to implement the view such that I could create
 * an instance programmatically when a button was clicked.
 *
 * I used the following tutorial to create the view and modified it as necessary:
 * https://viblo.asia/p/simple-way-to-create-a-custom-analog-clock-in-android-1VgZv9aRKAw
 *
 * This class extends the abstract class ClockView and draws the analog clock for the appropriate
 * time set by the model and controller.
 */

package com.example.brittanywoods.homework2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;

public class AnalogView extends ClockView{

    private int padding, handLength, hourHandLength, radius = 0;
    private int[] hours = {1,2,3,4,5,6,7,8,9,10,11,12};
    private Rect rect = new Rect();

    // Constructors for the Analog View
    public AnalogView(Context context){
        super(context);
    }

    public AnalogView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    // The OnDraw method creates the clock and sets the hands to the appropriate times as set
    // by the controller
    @Override
    protected void onDraw(Canvas canvas){

        if(!init){
            paint = new Paint();
            height = getHeight();
            width = getWidth();
            padding = 50;
            int minimumAttributes = Math.min(height,width);
            radius = minimumAttributes/4 - padding;

            handLength = minimumAttributes/20;
            hourHandLength = minimumAttributes/17;

            init = true;
        }

        canvas.drawColor(Color.WHITE);

        paint.reset();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        canvas.drawCircle(width/2,height/2,radius+padding-10,paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width/2,height/2,12,paint);

        int fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14,getResources().getDisplayMetrics());
        paint.setTextSize(fontSize);

        for(int hour : hours){
            String temp = String.valueOf(hour);
            paint.getTextBounds(temp,0,temp.length(),rect);

            double angle = Math.PI/6*(hour-3);
            int x = (int) (width/2 + Math.cos(angle) * radius - rect.width()/2);
            int y = (int) (height/2 + Math.sin(angle) * radius + rect.height()/2);

            canvas.drawText(String.valueOf(hour),x,y,paint);
        }

        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width/2,height/2,12,paint);

        drawHand(canvas, (hour + minute /60) * 5f, true, false);
        drawHand(canvas, minute,false,false);
        drawHand(canvas, second,false,true);

        canvas.drawText(month + "/" + day + "/" + year,250,100,paint);

        postInvalidateDelayed(500);
        invalidate();
    }

    private void drawHand(Canvas canvas, double d, boolean isHour, boolean isSec){
        double angle = Math.PI * d/30 - Math.PI /2;
        int handRadius;

        if(isHour){
            handRadius = radius - handLength - hourHandLength+15;
        } else{
            handRadius = radius - handLength;
        }

        canvas.drawLine(width/2,height/2,(float)(width/2+Math.cos(angle) * handRadius),
                (float)(height/2 + Math.sin(angle) * handRadius),paint);
    }

    // Setters for each of the date and time variables so that the controller can
    // communicate with the view
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
