package nl.SeriousParking.Parkeersimulator.model;

public class BadCar extends Car {
   //this type must be destroyed after Creation

    @Override
    public Car copy(Car car) {
        return null;
    }

    @Override
    public double PaymentMethod(double reservationCost, double pricePerHour) {
    return Double.MAX_VALUE;
    }
}
