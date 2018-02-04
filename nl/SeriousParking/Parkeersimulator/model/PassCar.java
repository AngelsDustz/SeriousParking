package nl.SeriousParking.Parkeersimulator.model;

import java.util.Random;

public class PassCar extends Car {

    protected PassCar() {
        super();
    }

    /**
     * This function makes a copy of a car.
     *
     * @param car A car to make a copy of.
     * @return A copy of the given car.
     */
    @Override
    protected Car copy(Car car) {
        Car  CopyCar =new PassCar();
        CopyCar.setParkedDouble(car.isParkedDouble());
        CopyCar.setAllTransactionsComplete(car.areAllTransactionsComplete());
        CopyCar.setParkingTime(car.getParkingTime());
        CopyCar.setMinutesLeft(car.getMinutesLeft());
        CopyCar.setPrimary(false);
        return CopyCar;
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

        //TODO payment LOGIC
        setAllTransactionsComplete(true);
        return 0.0;
    }


}
