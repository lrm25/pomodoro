package com.lmccrone.pomodoro.data;

public class PomodoroException extends Exception {

    public int code;
    public String e;
    public boolean fatal;

    public PomodoroException(int code, String e) {
        this(code, e, false);
    }

    public PomodoroException(int code, String e, boolean fatal) {
        super(String.format("%d:  %s", code, e));
        this.code = code;
        this.e = e;
        this.fatal = fatal;
    }
}