package nl.SeriousParking.Parkeersimulator.model;

public class ReservationCar extends Car {
    @Override
    public Car copy(Car car) {
        return null;
    }

    @Override
    public double PaymentMethod(double reservationCost, double pricePerHour) {

        //TODO payment LOGIC
        setAllTransactionsComplete(true);
        return 0.0;
    }

}
