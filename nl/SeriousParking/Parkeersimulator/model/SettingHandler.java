package nl.SeriousParking.Parkeersimulator.model;

public class SettingHandler extends Model{
    protected static int tickPause;
    protected static int chance;
    protected static int reservationShowchance;
    protected static boolean doubleEntrance;
    protected static int chanseToParkDouble;
    protected static int weekDayArrivals; // average number of arriving cars per hour
    protected static int weekendArrivals; // average number of arriving cars per hour
    protected static int weekDayPassArrivals; // average number of arriving cars per hour
    protected static int weekendPassArrivals; // average number of arriving cars per hour
    protected static int weekDayReservations;
    protected static int weekendReservations;
    protected static int enterSpeed; // number of cars that can enter per minute
    protected static int paymentSpeed; // number of cars that can pay per minute
    protected static int maxQueueSize =10;
    protected static int exitSpeed; // number of cars that can leave per minute
    protected static int driveTroughSpeed= 10;
    protected static int adhocReservationFloors = 2;
    protected static int adhocReservationRows =6;
    protected static int adhocReservationplaces =30;
    protected static int passFloors = 1;
    protected static int passRows =6;
    protected static int passplaces =30;

    //TODO make Settings in Startup to se this.
    protected static int numberOfPassholders=1200;





    public SettingHandler() {
        defaultValue();
    }

    public void setDriveTroughSpeed(int speed) {
        driveTroughSpeed = speed;
    }

    public void defaultValue() {
        weekDayArrivals         = 40; // average number of arriving cars per hour
        weekendArrivals         = 100; // average number of arriving cars per hour
        weekDayPassArrivals     = 50; // average number of arriving cars per hour
        weekendPassArrivals     = 5; // average number of arriving cars per hour
        weekDayReservations     = 30;
        weekendReservations     = 10;
        chanseToParkDouble      = 2;
        maxQueueSize            = 10;
        tickPause               = 150;
        chance                  = 1;
        reservationShowchance   = 80;
        doubleEntrance          = false;
        enterSpeed              = 2;
        exitSpeed               = 4;
        paymentSpeed            = 6;

        notifyViews();
    }


    public static int getReservationShowchance() {
        return reservationShowchance;
    }

    public static void setReservationShowchance(int reservationShowchance) { SettingHandler.reservationShowchance = reservationShowchance; }

    public static void setChanseToParkDouble(int chanseToParkDouble){ SettingHandler.chanseToParkDouble = chanseToParkDouble; }

    public static int getMaxQueueSize() {
        return maxQueueSize;
    }

    public static void setMaxQueueSize(int maxQueueSize) {
        SettingHandler.maxQueueSize = maxQueueSize;
    }

    public static int getWeekDayReservations() {
        return weekDayReservations;
    }

    public static void setweekDayReservations(int weekDayReservations) { SettingHandler.weekDayReservations = weekDayReservations; }

    public static int getweekendReservations() {
        return weekendReservations;
    }

    public static void setweekendReservations(int weekendReservations) {
        weekendReservations = weekendReservations;
    }

    public static int getTickPause() {
        return tickPause;
    }

    public static void setTickPause(int tickPause) {
        if (tickPause>0) {
            SettingHandler.tickPause = tickPause;
        } else {
            SettingHandler.tickPause = 5;
        }

    }

    public static int getChance() {
        return chance;
    }

    public static void setChance(int chance) {
        SettingHandler.chance = chance;
    }

    public static int getWeekDayArrivals() {
        return weekDayArrivals;
    }

    public static void setWeekDayArrivals(int weekDayArrivals) {
        SettingHandler.weekDayArrivals = weekDayArrivals;
    }

    public static int getWeekendArrivals() {
        return weekendArrivals;
    }

    public static void setWeekendArrivals(int weekendArrivals) {
        SettingHandler.weekendArrivals = weekendArrivals;
    }

    public static int getWeekDayPassArrivals() {
        return weekDayPassArrivals;
    }

    public static void setWeekDayPassArrivals(int weekDayPassArrivals) {
        SettingHandler.weekDayPassArrivals = weekDayPassArrivals;
    }

    public static int getWeekendPassArrivals() {
        return weekendPassArrivals;
    }

    public static void setWeekendPassArrivals(int weekendPassArrivals) {
        SettingHandler.weekendPassArrivals = weekendPassArrivals;
    }

    public static int getEnterSpeed() {
        return enterSpeed;
    }

    public static void setEnterSpeed(int enterSpeed) {
        SettingHandler.enterSpeed = enterSpeed;
    }

    public static int getPaymentSpeed() {
        return paymentSpeed;
    }

    public static void setPaymentSpeed(int paymentSpeed) {
        SettingHandler.paymentSpeed = paymentSpeed;
    }

    public static int getExitSpeed() {
        return exitSpeed;
    }

    public static void setExitSpeed(int exitSpeed) {
        SettingHandler.exitSpeed = exitSpeed;
    }

    public static void setDoubleEntrance(Boolean doubleEntrance) {
        SettingHandler.doubleEntrance = doubleEntrance;
    }

    public static boolean getDoubleEntrance() {
        return doubleEntrance;
    }

    public static int getAdhocReservationFloors() {
        return adhocReservationFloors;
    }

    public static void setAdhocReservationFloors(int adhocReservationFloors) {
        SettingHandler.adhocReservationFloors = adhocReservationFloors;
    }

    public static int getAdhocReservationRows() {
        return adhocReservationRows;
    }

    public static void setAdhocReservationRows(int adhocReservationRows) {
        SettingHandler.adhocReservationRows = adhocReservationRows;
    }

    public static int getAdhocReservationplaces() {
        return adhocReservationplaces;
    }

    public static void setAdhocReservationplaces(int adhocReservationplaces) {
        SettingHandler.adhocReservationplaces = adhocReservationplaces;
    }

    public static int getPassFloors() {
        return passFloors;
    }

    public static void setPassFloors(int passFloors) {
        SettingHandler.passFloors = passFloors;
    }

    public static int getPassRows() {
        return passRows;
    }

    public static void setPassRows(int passRows) {
        SettingHandler.passRows = passRows;
    }

    public static int getPassplaces() {
        return passplaces;
    }

    public static void setPassplaces(int passplaces) {
        SettingHandler.passplaces = passplaces;
    }

    public static int getDriveTroughSpeed() {
        return driveTroughSpeed;
    }

    public static void setNumberOfPassholders(int numberOfPassholders) {
        SettingHandler.numberOfPassholders = numberOfPassholders;
    }
}
