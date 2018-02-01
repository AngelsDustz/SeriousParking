package nl.SeriousParking.Parkeersimulator.model;

import java.util.Random;

public class ReservationCar extends Car {
    private boolean active;
    private int leadTime;

    protected ReservationCar() {
         super();
         active     = false;
         leadTime   = new Random().nextInt(15);
    }

    /**
     * This function makes a copy of a car.
     *
     * @param car A car to make a copy of.
     * @return A copy of the given car.
     */
    @Override
    protected Car copy(Car car) {
        Car  CopyCar =new ReservationCar();
        CopyCar.setParkedDouble(car.isParkedDouble());
        CopyCar.setAllTransactionsComplete(car.areAllTransactionsComplete());
        CopyCar.setParkingTime(car.getParkingTime());
        CopyCar.setMinutesLeft(car.getMinutesLeft());
        CopyCar.setPrimary(false);
        return CopyCar;
    }

    /**
     * Handles weither a car shows up in the current tick or not.
     */
    protected void carShows(){
        if (leadTime>0){
            leadTime--;
        }

        if (leadTime==0){
            if(new Random().nextInt(100)<SettingHandler.getReservationShowchance()){
                active=true;
            }

            leadTime--;
        }
    }

    /**
     * This function handles the payment of a car.
     *
     * @param reservationCost The cost for reserving a spot.
     * @param pricePerHour The price per hour.
     * @return The total amount to pay.
     */
    @Override
    public double PaymentMethod(double reservationCost, double pricePerHour) {
        double payment;
        payment = (super.parkingTime *(pricePerHour/60))+reservationCost;

        setAllTransactionsComplete(true);
        return payment;
    }

    public boolean isActive() {
        return active;
    }
}
