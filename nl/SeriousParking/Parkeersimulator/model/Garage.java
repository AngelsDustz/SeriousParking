package nl.SeriousParking.Parkeersimulator.model;

public class Garage



{
     Garage() {
    }

    protected static void carToQueue(Queue queue){
        Car car;
        while(queue.carsInQueue()>0) {

            car = queue.removeCar();
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

    }

    protected static Queue     entrancePassQueue          = new Queue();
    protected static Queue     reservationQueue           = new Queue();
    protected static Queue     entranceAdhocQueue         = new Queue();
    protected static Queue     paymentCarQueue            = new Queue();
    protected static Queue     exitCarQueue               = new Queue();

    public static int getNumberCarsInAdhocQueue(){
        return entranceAdhocQueue.carsInQueue();

    }
    public static int getNumberCarsInPassQueue(){
        return entrancePassQueue.carsInQueue();

    }
}


