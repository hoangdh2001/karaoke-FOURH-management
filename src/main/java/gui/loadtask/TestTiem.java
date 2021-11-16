package gui.loadtask;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestTiem {

    public static void main(String[] args) {
        String dateStart = "11/03/14 09:29:58";
        String dateStop = "11/03/14 09:33:43";

// Custom date format
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
        } catch (ParseException e) {
            e.printStackTrace();
        }

// Get msec from each, and subtract.
        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = TimeUnit.MILLISECONDS.toSeconds(diff);
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        System.out.println("Time in seconds: " + diffSeconds + " seconds.");
        System.out.println("Time in minutes: " + diffMinutes + " minutes.");
        System.out.println("Time in hours: " + diffHours + " hours.");
        
        System.out.println(Time.valueOf(LocalTime.of((int)diffHours, (int)diffMinutes)));
    }
}
