import java.lang.Integer;
import java.lang.Thread;

public class Pomodoro {

    public static void main(String args[]) {

        int seconds = 0;

        if (args.length != 1) {
            System.out.printf("Invalid number of args:  %d\n", args.length);
            return;
        }
        try {
            seconds = Integer.valueOf(args[0]);
        } catch (NumberFormatException nfe) {
            System.err.println(nfe.toString());
            return;
        }
        if (seconds < 0) {
            System.err.println("Seconds cannot be less than zero");
            return;
        }

        for (; 0 < seconds; seconds--) {
            System.out.printf("%d seconds remaining\n", seconds);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.err.println(ie.toString());
            }
        }
        System.out.println("Time's up!");
    }
}
