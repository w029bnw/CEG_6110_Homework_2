package com.example.brittanywoods.homework2;

/**
 *  This is the class for the undo/redo functions.
 *
 *  This class contains private stacks to store clock objects as time and date changes are made
 *  to the views. Each method moves the actions between stacks so that the action can be undone
 *  and redone endlessly, and sets the clock object to the desired time or date respectively.
 */

import java.util.Stack;

public class Execute implements Command {

    private Stack<Clock> undoStack;
    private Stack<Clock> redoStack;
    private Clock oldClock, newClock;

    public Execute(Stack<Clock> undoStack, Stack<Clock> redoStack){

        this.undoStack = undoStack;
        this.redoStack = redoStack;
    }

    public void undo(Clock clock){
        if (undoStack.empty()){
            return;
        } else {
            Clock temp = new Clock(clock);
            redoStack.push(temp);
            oldClock = undoStack.pop();

            clock.setYear(oldClock.getYear());
            clock.setMonth(oldClock.getMonth());
            clock.setDay(oldClock.getDay());
            clock.setHour(oldClock.getHour());
            clock.setMinute(oldClock.getMinute());
            clock.setSecond(oldClock.getSecond());
        }
    }

    public void redo(Clock clock){
        if(redoStack.empty()){
            return;
        } else {
            Clock temp = new Clock(clock);
            undoStack.push(temp);
            newClock = redoStack.pop();

            clock.setYear(newClock.getYear());
            clock.setMonth(newClock.getMonth());
            clock.setDay(newClock.getDay());
            clock.setHour(newClock.getHour());
            clock.setMinute(newClock.getMinute());
            clock.setSecond(newClock.getSecond());
        }
    }
}
