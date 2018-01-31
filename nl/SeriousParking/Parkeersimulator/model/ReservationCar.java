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
