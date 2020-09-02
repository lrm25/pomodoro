package com.lmccrone.pomodoro.app;

import java.lang.Integer;
import java.lang.Thread;

import com.lmccrone.pomodoro.data.InitialStatus;
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

    public InitialStatus getInitialStatus() {
        try {
            status = new Status();
        } catch (IllegalArgumentException iae) {
            System.err.printf("Fatal config error:  %s\n", iae.getMessage());
            System.exit(1);
        }
        return status.getInitialStatus();
    }

    public void updateWorkTime(String minutesStr, String secondsStr) throws IllegalArgumentException {
        int minutes = 0;
        int seconds = 0;
        try {
            minutes = Integer.valueOf(minutesStr);
            seconds = Integer.valueOf(secondsStr);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(nfe.getMessage());
        }
        status.updateWorkTime(minutes, seconds);
    }
}
