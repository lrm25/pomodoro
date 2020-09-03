package com.lmccrone.pomodoro.data;

import com.lmccrone.pomodoro.data.PomodoroTime;

import java.util.HashMap;

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

    public static String WORK_DESCRIPTION = "work";
    public static String SHORT_BREAK_DESCRIPTION = "short break";
    public static String LONG_BREAK_DESCRIPTION = "short break";
    public static String ALARM_DESCRIPTION = "alarm";

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

    public Status() throws PomodoroException {

        workTime = new PomodoroTime(WORK_DESCRIPTION, DEFAULT_WORK_MINUTES, DEFAULT_WORK_SECONDS);
        shortBreakTime = new PomodoroTime(SHORT_BREAK_DESCRIPTION, DEFAULT_SHORT_BREAK_MINUTES, DEFAULT_SHORT_BREAK_SECONDS);
        longBreakTime = new PomodoroTime(LONG_BREAK_DESCRIPTION, DEFAULT_LONG_BREAK_MINUTES, DEFAULT_LONG_BREAK_SECONDS);
        alarmTime = new PomodoroTime(ALARM_DESCRIPTION, DEFAULT_ALARM_MINUTES, DEFAULT_ALARM_SECONDS);

        times = new HashMap<String, PomodoroTime>();
        times.put(WORK_DESCRIPTION, workTime);
        times.put(SHORT_BREAK_DESCRIPTION, shortBreakTime);
        times.put(LONG_BREAK_DESCRIPTION, longBreakTime);
        times.put(ALARM_DESCRIPTION, alarmTime);

        if (DEFAULT_INTERVAL_COUNT < 1 || 10 < DEFAULT_INTERVAL_COUNT) {
            throw new PomodoroException(PomodoroCode.INVALID_INTERVAL_COUNT,
                String.format("Interval count must between %d and %d\n", 1, 10), true);
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

        public AlarmStatus() throws PomodoroException {
            alarmTime = new PomodoroTime("alarm", DEFAULT_ALARM_MINUTES, DEFAULT_ALARM_SECONDS);
            timeRemaining = new PomodoroTime("remaining", DEFAULT_ALARM_MINUTES, DEFAULT_ALARM_SECONDS);
        }

        public void setAlarmTime(int minutes, int seconds) {
            alarmTime.set(minutes, seconds);
        }
    }

    public PomodoroTime getTime(String key) throws PomodoroException {
        PomodoroTime time = times.get(key);
        if (time == null) {
            throw new PomodoroException(PomodoroCode.INVALID_TIME_KEY,
                String.format("Invalid time key:  %s", key));
        }
        return times.get(key);
    }

    public PomodoroCode setTime(String key, int minutes, int seconds) {
        PomodoroTime time = times.get(key);
        if (time == null) {
            return new PomodoroCode(PomodoroCode.INVALID_TIME_KEY,
                String.format("Invalid time key:  %s", key));
        }
        return time.set(minutes, seconds);
    }

    private AlarmStatus alarmStatus;
    private HashMap<String, PomodoroTime> times;
    private PomodoroTime workTime;
    private PomodoroTime shortBreakTime;
    private PomodoroTime longBreakTime;
    private PomodoroTime alarmTime;
    private int intervals;
    private int currentInterval;
    private RunningStatus runningStatus;
    private WorkStatus workStatus;
}