package com.lmccrone.pomodoro.data;

public class Status {

    public static int DEFAULT_WORK_MINUTES = 25;
    public static int DEFAULT_WORK_SECONDS = 0;
    public static int DEFAULT_SHORT_BREAK_MINUTES = 5;
    public static int DEFAULT_SHORT_BREAK_SECONDS = 0;
    public static int DEFAULT_LONG_BREAK_MINUTES = 15;
    public static int DEFAULT_LONG_BREAK_SECONDS = 0;
    public static int DEFAULT_ALARM_MINUTES = 1;
    public static int DEFAULT_ALARM_SECONDS = 0;
    public static int DEFAULT_INTERVAL_COUNT = 4;

    public enum RunningStatus {
        RUNNING,
        PAUSED,
        STOPPED
    }

    public enum WorkStatus {
        WORK,
        SHORT_BREAK,
        LONG_BREAK
    }

    public Status() throws IllegalArgumentException {
        workTime = new PomodoroTime("work", DEFAULT_WORK_MINUTES, DEFAULT_WORK_SECONDS);
        shortBreakTime = new PomodoroTime("short break", DEFAULT_SHORT_BREAK_MINUTES, DEFAULT_SHORT_BREAK_SECONDS);
        longBreakTime = new PomodoroTime("long break", DEFAULT_LONG_BREAK_MINUTES, DEFAULT_LONG_BREAK_SECONDS);
        alarmTime = new PomodoroTime("alarm", DEFAULT_ALARM_MINUTES, DEFAULT_ALARM_SECONDS);
        if (DEFAULT_INTERVAL_COUNT < 1 || 10 < DEFAULT_INTERVAL_COUNT) {
            throw new IllegalArgumentException(String.format("Interval count (%d) must be between 1 and 10", intervals));
        }
        intervals = DEFAULT_INTERVAL_COUNT;
    }

    public InitialStatus getInitialStatus() {
        return new InitialStatus(workTime, shortBreakTime, longBreakTime, alarmTime, intervals);
    }

    public class AlarmStatus {
        boolean alarmOn = false;
        PomodoroTime alarmTime;
        PomodoroTime timeRemaining;

        public AlarmStatus() {
            alarmTime = new PomodoroTime("alarm", DEFAULT_ALARM_MINUTES, DEFAULT_ALARM_SECONDS);
            timeRemaining = new PomodoroTime("remaining", DEFAULT_ALARM_MINUTES, DEFAULT_ALARM_SECONDS);
        }

        public void setAlarmTime(int minutes, int seconds) {
            alarmTime.setTime(minutes, seconds);
        }
    }

    private AlarmStatus alarmStatus;
    private PomodoroTime workTime;
    private PomodoroTime shortBreakTime;
    private PomodoroTime longBreakTime;
    private PomodoroTime alarmTime;
    private int intervals;
    private int currentInterval;
    private RunningStatus runningStatus;
    private WorkStatus workStatus;
}