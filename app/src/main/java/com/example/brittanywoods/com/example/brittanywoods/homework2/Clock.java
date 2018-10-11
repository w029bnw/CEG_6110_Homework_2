package com.example.brittanywoods.homework2;

import java.util.Calendar;

/**?
 * This is my clock model object for the MVC design pattern. This class creates an object that stores
 * purely time and date related variables. I was unable to get a calendar instance to update
 * from this object, so a thread is used in the mainActivity to update time in this class. Any values
 * set and stored here are used to communicate with the views via the controller.<img src="https://github.com/w029bnw/CEG_6110_Homework_1/blob/master/Homework1%20Photos/Screenshot_20180916-151631.png" width="256" height="412" title="Colored Text Screen1">
 *
 * This class contains getters and setters that can be used to update the clock values and communicate
 * with the controller in order to update the clock views
 */
public class Clock{

    private int year, month, day, hour, minute, second;
    private Calendar calendar;

    public Clock(Calendar calendar){
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
    }

    public Clock(Clock clock){
        year = clock.getYear();
        month = clock.getMonth();
        day = clock.getDay();
        hour = clock.getHour();
        minute = clock.getMinute();
        second = clock.getSecond();
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

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

    public void setSecond(int second) {
        this.second = second;
    }

    public void undo(){}

    public void redo(){}
}