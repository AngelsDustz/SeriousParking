package nl.SeriousParking.Parkeersimulator.model;


import java.util.Random;

/**
 * The abstract class of a car.
 */
public abstract class Car extends Model {

    Location        location;
    boolean         primary;
    boolean         allTransactionsComplete;
    int             minutesLeft;
    int             parkingTime;
    boolean         ParkedDouble;


    /**
     * Constructor for objects of class Car
     */
    public  Car() {
        Random random           = new Random();
        int stayMinutes         = (int) (15 + random.nextFloat() * 3 * 60);
        primary                 = true;
        ParkedDouble            = false;
        allTransactionsComplete = false;
        minutesLeft             = stayMinutes;
        parkingTime             = stayMinutes;
    }

    /**
     * This function makes a copy of a car.
     *
     * @param car A car to make a copy of.
     * @return A copy of the given car.
     */
    protected abstract Car copy(Car car);

    /**
     * This function handles the payment of a car.
     *
     * @param reservationCost The cost for reserving a spot.
     * @param pricePerHour The price per hour.
     * @return The total amount to pay.
     */
    public abstract double PaymentMethod(double reservationCost, double pricePerHour);

    /**
     * This does a single tick for a car.
     * A tick is a minute.
     */
    public void tick() {
        minutesLeft--;
    }

    /**
     * This function returns the location of a car.
     *
     * @return The location of a car.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets a car to a new location.
     *
     * @param location The location the car has to go to.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Checks weither all payments are done.
     *
     * @return Weither all payments are done or not.
     */
    public boolean areAllTransactionsComplete() {
        return allTransactionsComplete;
    }

    /**
     * Sets the state of all the transactions.
     *
     * @param allTransactionsComplete The new state.
     */
    public void setAllTransactionsComplete(boolean allTransactionsComplete) {
        this.allTransactionsComplete = allTransactionsComplete;
    }

    /**
     * Returns the amount of minutes a car has left.
     *
     * @return The amount of minutes a car has left.
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * Sets the amount of minutes a car has left.
     *
     * @param minutesLeft The amount of minutes a car has left.
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    /**
     * Returns weither a car is double parked or not.
     *
     * @return If a car is double parked or not.
     */
    public boolean isParkedDouble() {
        return ParkedDouble;
    }

    /**
     * Sets if a car is double parked.
     *
     * @param parkedDouble The new value for double parked.
     */
    public void setParkedDouble(boolean parkedDouble) {
        ParkedDouble = parkedDouble;
    }

    /**
     * Returns if a car is primary.
     *
     * @return If a car is primary or not.
     */
    public boolean isPrimary() {
        return primary;
    }

    /**
     * Sets the primary status of a car.
     *
     * @param primary The primary status.
     */
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    /**
     * Returns the parking time of a car.
     *
     * @return The parking time.
     */
    public int getParkingTime() {
        return parkingTime;
    }

    /**
     * Sets the parking time of a car.
     *
     * @param parkingTime The new parking time.
     */
    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }
}

