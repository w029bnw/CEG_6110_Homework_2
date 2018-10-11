package com.example.brittanywoods.homework2;

/**
 * This is my clockController class. The class communicates between the model and the views by
 * updating the views with values stored within the clock model object.
 */
public class ClockController {

    private ClockView v;
    private Clock clock;

    public ClockController(Clock clock, ClockView view){
        this.clock = clock;
        this.v = view;
    }

    public void updateView(Clock clock, ClockView view){
        view.setYear(clock.getYear());
        view.setMonth(clock.getMonth());
        view.setDay(clock.getDay());
        view.setHour(clock.getHour());
        view.setMinute(clock.getMinute());
        view.setSecond(clock.getSecond());
    }

    public int getDay() {
        return clock.getDay();
    }

    public int getHour() {
        return clock.getHour();
    }

    public int getMinute() {
        return clock.getMinute();
    }

    public int getMonth() {
        return clock.getMonth();
    }

    public int getYear() {
        return clock.getYear();
    }

    public int getSecond() {
        return clock.getSecond();
    }

    public void setDay(int day) {
        clock.setDay(day);
    }


    public void setHour(int hour) {
        clock.setHour(hour);
    }

    public void setMinute(int minute) {
        clock.setMinute(minute);
    }

    public void setMonth(int month) {
        clock.setMonth(month);
    }

    public void setSecond(int second) {
        clock.setSecond(second);
    }

    public void setYear(int year) {
        clock.setYear(year);
    }
}