package nl.SeriousParking.Parkeersimulator.model;

public class PassCar extends Car {

    protected PassCar() {
        super();
    }

    @Override
    protected Car copy(Car car) {
        Car  CopyCar =new PassCar();
        CopyCar.setParkedDouble(car.isParkedDouble());
        CopyCar.setAllTransactionsComplete(car.areAllTransactionsComplete());
        CopyCar.setParkingTime(car.getParkingTime());
        CopyCar.setMinutesLeft(car.getMinutesLeft());
        CopyCar.setPrimary(false);
        return CopyCar;
    }
     @Override
    public double PaymentMethod(double reservationCost, double pricePerHour) {

        //TODO payment LOGIC
        setAllTransactionsComplete(true);
        return 0.0;
    }
}
