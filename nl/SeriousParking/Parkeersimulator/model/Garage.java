package nl.SeriousParking.Parkeersimulator.model;

public class Garage {
    protected static Queue arrivingCars                 = new Queue();
    protected static Queue entrancePassReservationQueue = new Queue();
    protected static Queue entranceAdhocQueue           = new Queue();

    protected static Queue drivingToExit    = new Queue();
    protected static Queue paymentCarQueue  = new Queue();
    protected static Queue exitCarQueue     = new Queue();

    Garage() {
    }

    protected static void reset() {
        arrivingCars.reset();
        entrancePassReservationQueue.reset();
        entranceAdhocQueue.reset();
        drivingToExit.reset();
        paymentCarQueue.reset();
        exitCarQueue.reset();
    }

    protected static void CarsArrivingInQueue() {
        Car car;
        int i = 0;

        while (arrivingCars.carsInQueue() > 0) {
            car = arrivingCars.removeCar();
            if (car instanceof AdhocCar) {
                entranceAdhocQueue.addCar(car);
            }

            if (car instanceof PassCar || car instanceof ReservationCar) {
                entrancePassReservationQueue.addCar(car);
            }
        }

        while (drivingToExit.carsInQueue() > 0 && i < SettingHandler.getDriveTroughSpeed()) {
            car = Garage.drivingToExit.removeCar();

            if (car.areAllTransactionsComplete()) {
                Garage.exitCarQueue.addCar(car);
            } else {
                Garage.paymentCarQueue.addCar(car);
            }

            i++;
        }
    }


    public static int getNumberCarsInAdhocQueue() {
        return entranceAdhocQueue.carsInQueue();
    }

    public static int getNumberCarsInPassQueue() {
        return entrancePassReservationQueue.carsInQueue();
    }
}


