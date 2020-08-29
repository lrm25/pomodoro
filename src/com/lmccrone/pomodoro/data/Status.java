package com.lmccrone.pomodoro.data;


public class Status {

    public static int DEFAULT_ALARM_MINUTES = 1;
    public static int DEFAULT_ALARM_SECONDS = 0;

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

    public class PomodoroTime {
        private int minutes;
        private int seconds;

        public PomodoroTime(int minutes, int seconds) {
            setTime(minutes, seconds);
        }

        public void setTime(int minutes, int seconds) {
            // TODO: exceptions
            this.minutes = minutes;
            this.seconds = seconds;
        }
    }

    public class AlarmStatus {
        boolean alarmOn = false;
        PomodoroTime alarmTime;
        PomodoroTime timeRemaining;

        public AlarmStatus() {
            // TODO:  magic numbers
            alarmTime = new PomodoroTime(DEFAULT_ALARM_MINUTES, DEFAULT_ALARM_SECONDS);
            timeRemaining = new PomodoroTime(DEFAULT_ALARM_MINUTES, DEFAULT_ALARM_SECONDS);
        }

        public void setAlarmTime(int minutes, int seconds) {
            alarmTime.setTime(minutes, seconds);
        }
    }

    private AlarmStatus alarmStatus;
    private PomodoroTime workTime;
    private PomodoroTime shortBreakTime;
    private PomodoroTime longBreakTime;
    private int intervals;
    private int currentInterval;
    private RunningStatus runningStatus;
    private WorkStatus workStatus;
}