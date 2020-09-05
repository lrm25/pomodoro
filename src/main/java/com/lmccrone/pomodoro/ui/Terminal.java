package com.lmccrone.pomodoro.ui;

import java.util.HashMap;
import java.util.Scanner;

import com.lmccrone.pomodoro.app.Pomodoro;
import com.lmccrone.pomodoro.data.InitialStatus;
import com.lmccrone.pomodoro.data.PomodoroCode;
import com.lmccrone.pomodoro.data.PomodoroException;
import com.lmccrone.pomodoro.data.Status;

public class Terminal {

    private HashMap<String, ValueUpdater> charToOptionMap;

    private Pomodoro pomodoro;
    private Scanner scanner;

    public void run(Pomodoro p) {

        this.pomodoro = p;

        charToOptionMap = new HashMap<String, ValueUpdater>();
        charToOptionMap.put("w", new TimeUpdater(Status.WORK_DESCRIPTION));
        charToOptionMap.put("s", new TimeUpdater(Status.SHORT_BREAK_DESCRIPTION));
        charToOptionMap.put("l", new TimeUpdater(Status.LONG_BREAK_DESCRIPTION));
        charToOptionMap.put("a", new TimeUpdater(Status.ALARM_DESCRIPTION));
        charToOptionMap.put("i", new IntervalUpdater());

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
        ValueUpdater updater = null;
        scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Enter option:  ");
            option = scanner.next();
            updater = charToOptionMap.get(option.toLowerCase());
            if (updater == null) {
                System.err.println("Invalid option");
                continue;
            }
            updater.update();
            System.out.println(initialStatus.toString());
            break;
        }
        scanner.close();
    }

    private abstract class ValueUpdater {
        public abstract void update();
    }

    private class TimeUpdater extends ValueUpdater {

        private String name;

        public TimeUpdater(String name) {
            this.name = name;
        }

        public void update() {
            boolean timeEntered = false;
            PomodoroCode code = null;
            while (!timeEntered) {
                System.out.printf("Enter minutes:  ");
                String minutesStr = scanner.next();
                System.out.printf("Enter seconds:  ");
                String secondsStr = scanner.next();
                code = pomodoro.updateTime(name, minutesStr, secondsStr);
                if (code != null) {
                    System.err.println(code);
                    continue;
                }
                timeEntered = true;
            }
        }
    }

    private class IntervalUpdater extends ValueUpdater {
        public void update() {
            boolean intervalEntered = false;
            PomodoroCode code = null;
            while (!intervalEntered) {
                System.out.printf("Enter interval count:  ");
                String intervalStr = scanner.next();
                code = pomodoro.updateIntervalCount(intervalStr);
                if (code != null) {
                    System.err.println(code);
                    continue;
                }
                intervalEntered = true;
            }
        }
    }
}