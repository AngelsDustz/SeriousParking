package nl.SeriousParking.Parkeersimulator.model;

public class SettingHandler extends Model{
    protected static int tickPause;
    protected static int chance;
    protected static int reservationchance;
    protected static Boolean doubleEntrance;

    protected static int weekDayArrivals; // average number of arriving cars per hour
    protected static int weekendArrivals; // average number of arriving cars per hour
    protected static int weekDayPassArrivals; // average number of arriving cars per hour
    protected static int weekendPassArrivals; // average number of arriving cars per hour
    //jeroen - mijn tested minimum stable tick rate is 30 op 25 heb ik desync na een aantal minuten
    protected static int enterSpeed; // number of cars that can enter per minute
    protected static int paymentSpeed; // number of cars that can pay per minute
    protected static int exitSpeed; // number of cars that can leave per minute

    protected static int weekDayReservations;
    protected static int WeekendReservations;

    protected   static int garageFloors =3;
    protected   static int garageRows   =6;
    protected   static int garagePlaces = 20;

    public SettingHandler() {
    defaultValue();
    }
    public void nofifier(){
        notifyViews();
    }
    public void defaultValue(){

    tickPause   = 150;
    chance      = 1;
    reservationchance= 5;
    doubleEntrance = false;
    garageFloors= 3;
    garageRows=6;
    garagePlaces=20;

    weekDayReservations=20;
    WeekendReservations=10;


        weekDayArrivals     = 100;
    weekendArrivals     = 200;
    weekDayPassArrivals = 50;
    weekendPassArrivals = 5;

    enterSpeed      = 3;
    paymentSpeed    = 7;
    exitSpeed       = 5;

    garageFloors= 3;
    garageRows=6;
    garagePlaces=20;
   nofifier();
    }

    public static int getGarageFloors() {
        return garageFloors;
    }

    public static void setGarageFloors(int garageFloors) {
        SettingHandler.garageFloors = garageFloors;
    }

    public static int getGarageRows() {
        return garageRows;
    }

    public static void setGarageRows(int garageRows) {
        SettingHandler.garageRows = garageRows;
    }

    public static int getGaragePlaces() {
        return garagePlaces;
    }

    public static void setGaragePlaces(int garagePlaces) {
        SettingHandler.garagePlaces = garagePlaces;
    }

    public static int getTickPause() {
        return tickPause;
    }

    public static void setTickPause(int tickPause) {
        if(tickPause>0) {
            SettingHandler.tickPause = tickPause;
        }
        else {
            SettingHandler.tickPause=5;
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
}
