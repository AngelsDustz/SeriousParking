package nl.SeriousParking.Parkeersimulator.model;

public class AdhocCar extends Car {
    protected AdhocCar() {
        super();
    }


    @Override
    protected Car copy(Car car) {
        Car  CopyCar =new AdhocCar();
        CopyCar.setParkedDouble(car.isParkedDouble());
        CopyCar.setAllTransactionsComplete(car.areAllTransactionsComplete());
        CopyCar.setParkingTime(car.getParkingTime());
        CopyCar.setMinutesLeft(car.getMinutesLeft());
        CopyCar.setPrimary(false);
        return CopyCar;
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
