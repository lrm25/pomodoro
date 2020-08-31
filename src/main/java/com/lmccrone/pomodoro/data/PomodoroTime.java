package com.lmccrone.pomodoro.data;

public class PomodoroTime {

    private String description;
    private int minutes;
    private int seconds;

    public PomodoroTime(String description, int minutes, int seconds) throws IllegalArgumentException {
        this.description = description;
        setTime(minutes, seconds);
    }

    public void setTime(int minutes, int seconds) throws IllegalArgumentException {
        if (minutes < 0 || 60 < minutes) {
            throw new IllegalArgumentException(String.format("%s time (%d min, %d sec): minutes must be between 0 and 60", 
                description, minutes, seconds));
        } 
        if (seconds < 0 || 59 < seconds) {
            throw new IllegalArgumentException(String.format("%s time (%d min, %d sec): seconds must be between 0 and 59",
                description, minutes, seconds));
        }
        if (minutes == 0 && seconds == 0) {
            throw new IllegalArgumentException(String.format("%s time: 0 min, 0 sec not allowed", description));
        }
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