package nl.SeriousParking.Parkeersimulator.model;


import java.util.Random;

public abstract class Car extends Model {

    Location        location;
    boolean         primary;
    boolean         allTransactionsComplete;
    int             minutesLeft;
    int             timeStayed;
    boolean         ParkedDouble;


    /**
     * Constructor for objects of class Car
     */
    public  Car() {

        primary             = true;
        ParkedDouble        = false;
        allTransactionsComplete=false;
        Random random       = new Random();
        int stayMinutes     = (int) (15 + random.nextFloat() * 3 * 60);
        minutesLeft         = stayMinutes;
        timeStayed = stayMinutes;
    }

    protected abstract Car copy(Car car);



    public abstract double PaymentMethod(double reservationCost, double pricePerHour);

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

