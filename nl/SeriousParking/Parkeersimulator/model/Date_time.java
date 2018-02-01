package nl.SeriousParking.Parkeersimulator.model;


/**
 * This class does all time based functions.
 */
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

    /**
     * Advances the time by one minute.
     */
    protected static void advanceTime(){
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

    /**
     * Resets all timers.
     */
    public static void resetTimer(){
        tickSinceStart  = 0;
        minutes         = 0;
        hours           = 0;
        days            = 0;
        weeks           = 0;
        years           = 0;
    }

    /**
     * Returns the amount of ticks since start.
     *
     * @return The amount of ticks since start.
     */
    public static int getTickSinceStart() {
        return tickSinceStart;
    }

    /**
     * Returns the current amount of minutes.
     *
     * @return The current amount of minutes.
     */
    public static int getMinutes() {
        return minutes;
    }

    /**
     * Returns the current amount of hours.
     *
     * @return The current amount of hours.
     */
    public static int getHours() {
        return hours;
    }

    /**
     * Returns the current day.
     *
     * @return The current day.
     */
    public static int getDays() {
        return days;
    }

    /**
     * Returns the current week.
     *
     * @return The current week.
     */
    public static int getWeeks() {
        return weeks;
    }

    /**
     * Returns the current year.
     *
     * @return The current year.
     */
    public static double getYears() {
        return years;
    }
}
