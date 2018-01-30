package nl.SeriousParking.Parkeersimulator.model;

public class AdhocCar extends Car {
     AdhocCar() {
        super();
    }

    @Override
    protected Car copy(Car car) {
        car.ParkedDouble            = this.ParkedDouble;
        car.allTransactionsComplete = this.allTransactionsComplete;
        car.parkingTime = this.parkingTime;
        car.minutesLeft             = this.minutesLeft;
        car.primary                 = false;

        return car;
    }


    @Override
    public double PaymentMethod(double reservationCost, double pricePerHour) {
        double payment;
        payment = super.parkingTime *(pricePerHour/60);

        setAllTransactionsComplete(true);
        return payment;
    }
}
