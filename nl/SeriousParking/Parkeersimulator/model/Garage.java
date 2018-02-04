package nl.SeriousParking.Parkeersimulator.model;

/**
 * The garage class handles all garage logistics.
 */
public class Garage {
    protected static Queue arrivingCars                 = new Queue();
    protected static Queue entrancePassReservationQueue = new Queue();
    protected static Queue entranceAdhocQueue           = new Queue();

              static Queue drivingToExit    = new Queue();
    protected static Queue paymentCarQueue  = new Queue();
    protected static Queue exitCarQueue     = new Queue();



    /**
     * Calculates the amount of cars that left because of a full queue.
     *
     * @param section The section to watch.
     * @param queue The queue to watch.
     * @return The amount of cars that left.
     */
    protected int carsPassingBy(GarageSection section, Queue queue){
        int carsPassed=0;

        if (section.getFreeSpots()!=0) {
            while (queue.carsInQueue()>SettingHandler.maxQueueSize){
                queue.removeCar();
                carsPassed++;
            }
        } else {
            while(queue.carsInQueue()>0){
                queue.removeCar();
                carsPassed++;
            }
        }

        return carsPassed;
    }

    /**
     * Resets the garage.
     */
    protected void reset() {
        arrivingCars.reset();
        entrancePassReservationQueue.reset();
        entranceAdhocQueue.reset();
        drivingToExit.reset();
        paymentCarQueue.reset();
        exitCarQueue.reset();
    }

    /**
     * Handles cars arriving into the queue.
     */
    protected static void CarsArrivingInQueue() {
        Car car;

        while (arrivingCars.carsInQueue() > 0) {
            car = arrivingCars.removeCar();
            if (car instanceof AdhocCar) {
                entranceAdhocQueue.addCar(car);
            }

            if (car instanceof PassCar || car instanceof ReservationCar) {
                entrancePassReservationQueue.addCar(car);
            }
        }
    }

    /**
     * Handles cars driving to the exit.
     */
    protected void carsDrivingToExit() {
        int i=0;
        while (drivingToExit.carsInQueue() > 0 && i < SettingHandler.getDriveTroughSpeed()) {
          Car  car = Garage.drivingToExit.removeCar();

            if (car.areAllTransactionsComplete()) {
                Garage.exitCarQueue.addCar(car);
            } else {
                Garage.paymentCarQueue.addCar(car);
            }

            i++;
        }
    }

    /**
     * Returns the amount of cars in the adhoc queue.
     *
     * @return The amount of cars in the adhoc queue.
     */
    public static int getNumberCarsInAdhocQueue() {
        return entranceAdhocQueue.carsInQueue();
    }

    /**
     * Returns the amount of cars in the pass queue.
     *
     * @return The amount of cars in the pass queue.
     */
    public static int getNumberCarsInPassQueue() {
        return entrancePassReservationQueue.carsInQueue();
    }
}


