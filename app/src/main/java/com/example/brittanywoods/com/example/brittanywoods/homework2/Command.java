package com.example.brittanywoods.homework2;

/**
 * This is the interface used for the undo/redo functionality.
 */

public interface Command {

    void undo(Clock clock);
    void redo(Clock clock);
}
