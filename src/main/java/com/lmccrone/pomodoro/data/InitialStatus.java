package com.lmccrone.pomodoro.data;

public class InitialStatus {

    public PomodoroTime workTime;
    public PomodoroTime shortBreakTime;
    public PomodoroTime longBreakTime;
    public PomodoroTime alarmTime;
    public int intervals;

    public InitialStatus(PomodoroTime workTime, PomodoroTime shortBreakTime,
        PomodoroTime longBreakTime, PomodoroTime alarmTime, int intervals) 
    {
        this.workTime = workTime;
        this.shortBreakTime = shortBreakTime;
        this.longBreakTime = longBreakTime;
        this.alarmTime = alarmTime;
        this.intervals = intervals;
    }

    public String toString() {
        return String.format(
            "Work time:  \t\t%s\n" +
            "Short break time:  \t%s\n" +
            "Long break time:  \t%s\n" +
            "Alarm time:  \t\t%s\n" +
            "Intervals:  \t\t%d\n",
            workTime, shortBreakTime, longBreakTime, alarmTime, intervals);
    }
}