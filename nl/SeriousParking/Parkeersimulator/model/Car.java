package nl.SeriousParking.Parkeersimulator.model;


import java.util.Random;

public abstract class Car extends Model {

    protected   Location        location;
    private     GarageSection   section;
    private     boolean         allTransactionsComplete;
    private     int             minutesLeft;
    protected   int timeStayed;
    private     boolean         ParkedDouble;

    /**
     * Constructor for objects of class Car
     */
    public  Car() {


        ParkedDouble        = false;
        allTransactionsComplete=false;
        Random random       = new Random();
        int stayMinutes     = (int) (15 + random.nextFloat() * 3 * 60);
        minutesLeft         = stayMinutes;
        timeStayed = stayMinutes;
    }


    public abstract Car copy(Car car);
    public abstract double PaymentMethod(double reservationCost, double pricePerHour);

    public void setSection(GarageSection section){
        this.section = section;
    }
    public GarageSection getSection(){
        return section;
    }

    public void tick() {
        minutesLeft--;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean areAllTransactionsComplete() {
        return allTransactionsComplete;
    }

    public void setAllTransactionsComplete(boolean allTransactionsComplete) {
        this.allTransactionsComplete = allTransactionsComplete;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    public boolean isParkedDouble() {
        return ParkedDouble;
    }

    public void setParkedDouble(boolean parkedDouble) {
        ParkedDouble = parkedDouble;
    }
}

