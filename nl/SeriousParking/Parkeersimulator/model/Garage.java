package nl.SeriousParking.Parkeersimulator.model;

public class Garage



{
     Garage() {
    }

    protected static void carToQueue(){
        Car car;
        while(arrivingCars.carsInQueue()>0) {

            car = arrivingCars.removeCar();
            if (car instanceof AdhocCar){
                entranceAdhocQueue.addCar(car);
            }

            if(car instanceof PassCar){
                entrancePassQueue.addCar(car);
            }
            if (car instanceof  ReservationCar){
                reservationQueue.addCar(car);
            }

        }

        int i =0;
        while (i<SettingHandler.getDriveTroughSpeed()) {
            car = Garage.drivingToExit.removeCar();
            if (car.areAllTransactionsComplete()) {
                Garage.exitCarQueue.addCar(car);
            } else {
                Garage.paymentCarQueue.addCar(car);
            }
            i++;
        }
    }

    protected static Queue arrivingCars = new Queue();
    protected static Queue     entrancePassQueue          = new Queue();
    protected static Queue     reservationQueue           = new Queue();
    protected static Queue     entranceAdhocQueue         = new Queue();

    protected static Queue     drivingToExit              = new Queue();
    protected static Queue     paymentCarQueue            = new Queue();
    protected static Queue     exitCarQueue               = new Queue();

    public static int getNumberCarsInAdhocQueue(){
        return entranceAdhocQueue.carsInQueue();

    }
    public static int getNumberCarsInPassQueue(){
        return entrancePassQueue.carsInQueue();

    }
    public static int getNumberCarsInreservationQueue(){
        return reservationQueue.carsInQueue();

    }

}


