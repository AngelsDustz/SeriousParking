package nl.SeriousParking.Parkeersimulator.model;


public class Date_time {
   private static int tickSinceStart;
   private static int minutes;
   private static int hours;
   private static int days;
   private static int weeks;
   private static double years;

    public void Date_time() {
        this.tickSinceStart = 0;
        this.minutes = 0;
        this.hours = 0;
        this.days = 0;
        this.weeks = 0;
        this.years = 0;
    }

    protected static void advanceTime(){
        // Advance the time by one minute.
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

    public static int getTickSinceStart() {
        return tickSinceStart;
    }

    public static void setTickSinceStart(int tickSinceStart) {
        Date_time.tickSinceStart = tickSinceStart;
    }

    public static int getMinutes() {
        return minutes;
    }

    public static void setMinutes(int minutes) {
        Date_time.minutes = minutes;
    }

    public static int getHours() {
        return hours;
    }

    public static void setHours(int hours) {
        Date_time.hours = hours;
    }

    public static int getDays() {
        return days;
    }

    public static void setDays(int days) {
        Date_time.days = days;
    }

    public static int getWeeks() {
        return weeks;
    }

    public static void setWeeks(int weeks) {
        Date_time.weeks = weeks;
    }

    public static double getYears() {
        return years;
    }

    public static void setYears(double years) {
        Date_time.years = years;
    }
}
