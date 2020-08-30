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
        Status status = new Status();
        return status.getInitialStatus();
    }
}
