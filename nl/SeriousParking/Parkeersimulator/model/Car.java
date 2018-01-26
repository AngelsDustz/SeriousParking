package nl.SeriousParking.Parkeersimulator.model;


import java.util.Random;

public class Car extends Model {
    private boolean     reservation;
    private Location    location;
    private int         minutesLeft;
    private boolean     isPaying;
    private boolean     hasToPay;
    private boolean     isParkedDouble;

    /**
     * Constructor for objects of class Car
     */
    public Car() {
        reservation    = false;
        isParkedDouble      = false;
        hasToPay       = true;
        Random random       = new Random();
        int stayMinutes     = (int) (15 + random.nextFloat() * 3 * 60);
        minutesLeft    = stayMinutes;
    }

    public boolean getReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public boolean getisParkedDouble() {
        return isParkedDouble;
    }

    public void setParkedDouble(boolean parkedDouble) {
        isParkedDouble = parkedDouble;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    public void tick() {
        minutesLeft--;
    }

    public Car copy(Car car){
        Car copiedCar =new Car();

        copiedCar.location       = car.location;
        copiedCar.minutesLeft    = car.minutesLeft ;
        copiedCar.isPaying       = car.isPaying ;
        copiedCar.hasToPay       = car.hasToPay;
        copiedCar.isParkedDouble = car.isParkedDouble;
        copiedCar.reservation    = car.reservation;

        return copiedCar;
    }
}

