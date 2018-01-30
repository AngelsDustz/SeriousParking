package nl.SeriousParking.Parkeersimulator.model;

public class VoidCar extends Car {
    @Override
    protected Car copy(Car car) {
        return null;
    }

    @Override
    public double PaymentMethod(double reservationCost, double pricePerHour) {
        return 0;
    }
}
