package com.lmccrone.pomodoro.ui;

import com.lmccrone.pomodoro.app.Pomodoro;
import com.lmccrone.pomodoro.data.InitialStatus;

public class Terminal {

    public void run(Pomodoro pomodoro) {
        InitialStatus initialStatus = pomodoro.getInitialStatus();
        System.out.println(initialStatus.toString());
    }
}