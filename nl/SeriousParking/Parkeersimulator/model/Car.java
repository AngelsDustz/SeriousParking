package nl.SeriousParking.Parkeersimulator.model;


import java.util.Random;

public class Car extends Model {
    private boolean     active;
    private boolean     reservation;
    private Location    location;
    private int         preTime;
    private int         minutesLeft;
    private boolean     isPaying;
    private boolean     hasToPay;
    private boolean     isParkedDouble;

    /**
     * Constructor for objects of class Car
     */
    public Car() {

        reservationPreTime();
        active= false;


        reservation         = false;

        isParkedDouble      = false;
        hasToPay            = true;
        Random random       = new Random();
        int stayMinutes     = (int) (15 + random.nextFloat() * 3 * 60);
        minutesLeft         = stayMinutes;
    }

    public void reservationPreTime(){

        Random random = new Random();
        preTime= random.nextInt(60);
    }
    public void noShow(){
       minutesLeft= (60 -preTime);

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public int getPreTime() {
        return preTime;
    }

    public void setPreTime(int preTime) {
        this.preTime = preTime;
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
        copiedCar.preTime        = car.preTime;
        copiedCar.active      = car.active;
        return copiedCar;
    }
}

