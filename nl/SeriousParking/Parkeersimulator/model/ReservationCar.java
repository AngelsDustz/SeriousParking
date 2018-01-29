package nl.SeriousParking.Parkeersimulator.model;

public class ReservationCar extends Car {
    @Override
    public Car copy(Car car) {
        return null;
    }

    @Override
    public double PaymentMethod(double reservationCost, double pricePerHour) {
        double payment;
        payment = (super.timeStayed *(pricePerHour/60))+reservationCost;

        setAllTransactionsComplete(true);
        return payment;
    }

}
