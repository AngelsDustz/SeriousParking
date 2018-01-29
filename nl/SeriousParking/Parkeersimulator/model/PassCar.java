package nl.SeriousParking.Parkeersimulator.model;

public class PassCar extends Car {

     PassCar() {
        super();
    }

    @Override
    protected Car copy(Car car) {
        car.ParkedDouble                 = this.ParkedDouble;
        car.allTransactionsComplete      = this.allTransactionsComplete;
        car.timeStayed                   =  this.timeStayed;
        car.minutesLeft                  = this.minutesLeft;
        car.primary=false;

        return car;
    }
     @Override
    public double PaymentMethod(double reservationCost, double pricePerHour) {

        //TODO payment LOGIC
        setAllTransactionsComplete(true);
        return 0.0;
    }
}
