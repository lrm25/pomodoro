package com.lmccrone.pomodoro.data;

public class PomodoroTime {

    private String description;
    private int minutes;
    private int seconds;

    public PomodoroTime(String description, int minutes, int seconds) throws PomodoroException {
        this.description = description;
        PomodoroCode code;
        if ((code = set(minutes, seconds)) != null) {
            throw new PomodoroException(code.code, code.description, true);
        }
    }

    public PomodoroCode set(int minutes, int seconds) {
        // TODO:  make higher end customizable
        if (minutes < 0 || 60 < minutes) {
            return new PomodoroCode(PomodoroCode.MINUTES_OUT_OF_RANGE,
                String.format("%s time:  minutes (%d) must be between 0 and 60", 
                description, minutes));
        } 
        if (seconds < 0 || 59 < seconds) {
            return new PomodoroCode(PomodoroCode.SECONDS_OUT_OF_RANGE,
                String.format("%s time:  seconds (%d) must be between 0 and 60", 
                description, seconds));
        }
        if (minutes == 0 && seconds == 0) {
            return new PomodoroCode(PomodoroCode.ZERO_TIME_ASSIGNMENT,
                String.format("%s time: 0 min, 0 sec not allowed", description));
        }
        this.minutes = minutes;
        this.seconds = seconds;
        return null;
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