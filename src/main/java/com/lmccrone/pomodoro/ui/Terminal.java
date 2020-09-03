package com.lmccrone.pomodoro.ui;

import java.util.Scanner;

import com.lmccrone.pomodoro.app.Pomodoro;
import com.lmccrone.pomodoro.data.InitialStatus;
import com.lmccrone.pomodoro.data.PomodoroCode;
import com.lmccrone.pomodoro.data.PomodoroException;
import com.lmccrone.pomodoro.data.Status;

public class Terminal {

    public void run(Pomodoro pomodoro) {
        InitialStatus initialStatus = null;
        try {
            initialStatus = pomodoro.getInitialStatus();
            System.out.println(initialStatus.toString());
        } catch (PomodoroException pe) {
            System.err.println(pe.getMessage());
            System.err.println("Fatal error, exiting ...");
            System.exit(1);
        }

        System.out.println("Options:");
        System.out.println("(w)ork time");
        String option = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Enter option:  ");
            option = scanner.next();
            if (!option.toLowerCase().equals("w")) {
                System.out.println("Invalid option");
                continue;
            }
            break;
        }
        boolean timeEntered = false;
        while (!timeEntered) {
            System.out.printf("Enter minutes:  ");
            String minutesStr = scanner.next();
            System.out.printf("Enter seconds:  ");
            String secondsStr = scanner.next();
            PomodoroCode code = pomodoro.updateTime(Status.WORK_DESCRIPTION, minutesStr, secondsStr);
            timeEntered = true;
        }
        System.out.println(initialStatus.toString());
        scanner.close();
    }
}