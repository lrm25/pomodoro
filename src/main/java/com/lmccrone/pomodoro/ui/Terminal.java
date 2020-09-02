package com.lmccrone.pomodoro.ui;

import java.util.Scanner;

import com.lmccrone.pomodoro.app.Pomodoro;
import com.lmccrone.pomodoro.data.InitialStatus;

public class Terminal {

    public void run(Pomodoro pomodoro) {
        InitialStatus initialStatus = pomodoro.getInitialStatus();
        System.out.println(initialStatus.toString());

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
            try {
                pomodoro.updateWorkTime(minutesStr, secondsStr);
            } catch (IllegalArgumentException iae) {
                System.err.println(iae.getMessage());
                continue;
            }
            timeEntered = true;
        }
        System.out.println(initialStatus.toString());
        scanner.close();
    }
}