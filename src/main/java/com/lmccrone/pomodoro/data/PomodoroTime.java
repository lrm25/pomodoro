package com.lmccrone.pomodoro.data;

public class PomodoroTime {

    private int minutes;
    private int seconds;

    public PomodoroTime(int minutes, int seconds) {
        setTime(minutes, seconds);
    }

    public void setTime(int minutes, int seconds) {
        // TODO: exceptions
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
    
    // decrement decreases seconds by one, and returns false if no time is remaining
    public boolean decrement() {
        if (seconds == 0) {
            if (minutes == 0) {
                return false;
            }
            minutes -= 1;
            seconds = 59;
            return true;
        }
        seconds--;
        return true;
    }

    public String toString() {
        return String.format("%d minutes, %d seconds", minutes, seconds);
    }
}