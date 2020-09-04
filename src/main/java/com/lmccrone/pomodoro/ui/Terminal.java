package com.lmccrone.pomodoro.ui;

import java.util.HashMap;
import java.util.Scanner;

import com.lmccrone.pomodoro.app.Pomodoro;
import com.lmccrone.pomodoro.data.InitialStatus;
import com.lmccrone.pomodoro.data.PomodoroCode;
import com.lmccrone.pomodoro.data.PomodoroException;
import com.lmccrone.pomodoro.data.Status;

public class Terminal {

    private HashMap<String, String> charToOptionMap;

    public void run(Pomodoro pomodoro) {

        charToOptionMap = new HashMap<String, String>();
        charToOptionMap.put("w", Status.WORK_DESCRIPTION);
        charToOptionMap.put("s", Status.SHORT_BREAK_DESCRIPTION);
        charToOptionMap.put("l", Status.LONG_BREAK_DESCRIPTION);
        charToOptionMap.put("a", Status.ALARM_DESCRIPTION);

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
        System.out.println("(s)hort break time");
        System.out.println("(l)ong break time");
        System.out.println("(a)larm time");
        System.out.println("(i)intervals");
        String option = "";
        String timeDescription = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Enter option:  ");
            option = scanner.next();
            timeDescription = charToOptionMap.get(option.toLowerCase());
            if (timeDescription == null) {
                System.err.println("Invalid option");
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
            PomodoroCode code = pomodoro.updateTime(timeDescription, minutesStr, secondsStr);
            timeEntered = true;
        }
        System.out.println(initialStatus.toString());
        scanner.close();
    }
}