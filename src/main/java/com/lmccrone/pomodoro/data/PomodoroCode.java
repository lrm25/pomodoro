package com.lmccrone.pomodoro.data;

public class PomodoroCode {
    
    public static int INVALID_TIME_KEY = 0x1001;
    public static int MINUTES_OUT_OF_RANGE = 0x1002;
    public static int SECONDS_OUT_OF_RANGE = 0x1003;
    public static int ZERO_TIME_ASSIGNMENT = 0x1004;
    public static int INVALID_INTERVAL_COUNT = 0x1005;
    public static int INVALID_MINUTES_VALUE_TYPE = 0x1006;
    public static int INVALID_SECONDS_VALUE_TYPE = 0x1006;

    public int code;
    public String description;

    public PomodoroCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String toString() {
        return String.format("%x:  %s", code, description);
    }
}