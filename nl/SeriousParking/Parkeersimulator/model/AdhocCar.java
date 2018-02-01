package nl.SeriousParking.Parkeersimulator.model;

public class AdhocCar extends Car {
    protected AdhocCar() {
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
        Car  CopyCar =new AdhocCar();
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
        double payment;
        payment = super.parkingTime *(pricePerHour/60);

        if (this.ParkedDouble) {
            payment += 30;
        }

        setAllTransactionsComplete(true);
        return payment;
    }
}
