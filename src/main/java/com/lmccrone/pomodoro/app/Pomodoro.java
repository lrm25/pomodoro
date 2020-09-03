package com.lmccrone.pomodoro.app;

import java.lang.Integer;
import java.lang.Thread;

import com.lmccrone.pomodoro.data.InitialStatus;
import com.lmccrone.pomodoro.data.PomodoroCode;
import com.lmccrone.pomodoro.data.PomodoroException;
import com.lmccrone.pomodoro.data.PomodoroTime;
import com.lmccrone.pomodoro.data.Status;
import com.lmccrone.pomodoro.ui.Terminal;

public class Pomodoro {
    
    private Terminal terminal;
    private Status status;

    public static void main(String args[]) {

        if (args.length != 0) {
            System.err.println("This program takes no arguments");
            return;
        }
        Pomodoro pomodoro = new Pomodoro();
        pomodoro.startUI();
    }

    public void startUI() {
        terminal = new Terminal();
        terminal.run(this);
    }

    public InitialStatus getInitialStatus() throws PomodoroException {
        status = new Status();
        return status.getInitialStatus();
    }

    public PomodoroCode updateTime(String key, String minutesStr, String secondsStr) {
        PomodoroTime time = null;
        try {
            time = status.getTime(key);
        } catch (PomodoroException pe) {
            return new PomodoroCode(pe.code, pe.e);
        }
        return updateTime(time, minutesStr, secondsStr);
    }

    private PomodoroCode updateTime(PomodoroTime time, String minutesStr, String secondsStr) {
        
        int minutes = 0;
        int seconds = 0;
        try {
            minutes = Integer.valueOf(minutesStr);
        } catch (NumberFormatException nfe) {
            return new PomodoroCode(PomodoroCode.INVALID_MINUTES_VALUE_TYPE,
                String.format("Values '%s' is not a valid integer",
                minutesStr));
        }
        try {
            seconds = Integer.valueOf(secondsStr);
        } catch (NumberFormatException nfe) {
            return new PomodoroCode(PomodoroCode.INVALID_SECONDS_VALUE_TYPE,
                String.format("Values '%s' an is not a valid integer", 
                secondsStr));
        }
        return time.set(minutes, seconds);
    }
}
