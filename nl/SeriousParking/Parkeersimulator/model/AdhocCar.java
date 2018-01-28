package nl.SeriousParking.Parkeersimulator.model;

public class AdhocCar extends Car {
    @Override
    public Car copy(Car car) {
        return null;
    }

    @Override
    public double PaymentMethod(double reservationCost, double pricePerHour) {

        double payment;
        payment = super.TimeStayed*(pricePerHour/60);
        //TODO payment LOGIC
        setAllTransactionsComplete(true);
        return payment;
    }
}
