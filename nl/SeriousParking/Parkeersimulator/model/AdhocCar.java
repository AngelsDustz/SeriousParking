package nl.SeriousParking.Parkeersimulator.model;

public class AdhocCar extends Car {
    protected AdhocCar() {
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

        if (this.ParkedDouble) {
            payment += 30;
        }

        setAllTransactionsComplete(true);
        return payment;
    }
}
