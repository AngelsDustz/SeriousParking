package nl.SeriousParking.Parkeersimulator.model;


public class Date_time {
   private static int tickSinceStart;
   private static int minutes;
   private static int hours;
   private static int days;
   private static int weeks;
   private static double years;

    private Date_time() {
        tickSinceStart  = 0;
        minutes         = 0;
        hours           = 0;
        days            = 0;
        weeks           = 0;
        years           = 0;
    }

    protected static void advanceTime(){
        // Advance the time by one minute.
        tickSinceStart++;
        minutes++;

        while (minutes > 59) {
            minutes -= 60;
            hours++;
        }

        while (hours > 23) {
            hours -= 24;
            days++;
        }

        while (days > 6) {
            days -= 7;
            weeks++;
        }

        while (weeks > 51){
            weeks -= 52;
            years++;
        }
    }

    public static void resetTimer(){
        tickSinceStart  = 0;
        minutes         = 0;
        hours           = 0;
        days            = 0;
        weeks           = 0;
        years           = 0;
    }

    public static int getTickSinceStart() {
        return tickSinceStart;
    }

    public static int getMinutes() {
        return minutes;
    }

    public static int getHours() {
        return hours;
    }

    public static int getDays() {
        return days;
    }

    public static int getWeeks() {
        return weeks;
    }

    public static double getYears() {
        return years;
    }
}
