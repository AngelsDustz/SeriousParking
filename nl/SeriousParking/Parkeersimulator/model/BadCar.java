package nl.SeriousParking.Parkeersimulator.model;

/**
 * A special type of car that has to be destroyed upon creation.
 */
public class BadCar extends Car {

    /**
     * This function makes a copy of a car.
     *
     * @param car A car to make a copy of.
     * @return Nothing.
     */
    @Override
    public Car copy(Car car) {
        return null;
    }

    /**
     * This function handles the payment of a car.
     *
     * @param reservationCost The cost of reserving a spot.
     * @param pricePerHour The price per hour.
     * @return The maximum value of a double.
     */
    @Override
    public double PaymentMethod(double reservationCost, double pricePerHour) {
    return Double.MAX_VALUE;
    }

}
