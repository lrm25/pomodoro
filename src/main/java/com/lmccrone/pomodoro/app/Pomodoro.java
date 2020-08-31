package com.lmccrone.pomodoro.app;

import java.lang.Integer;
import java.lang.Thread;

import com.lmccrone.pomodoro.data.InitialStatus;
import com.lmccrone.pomodoro.data.Status;
import com.lmccrone.pomodoro.ui.Terminal;

public class Pomodoro {
    
    private Terminal terminal;

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
        Status status = null;
        try {
            status = new Status();
        } catch (IllegalArgumentException iae) {
            System.err.printf("Fatal config error:  %s\n", iae.getMessage());
            System.exit(1);
        }
        return status.getInitialStatus();
    }
}
