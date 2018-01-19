package nl.SeriousParking.Parkeersimulator.model;

import nl.SeriousParking.Parkeersimulator.SimulatorView;

import java.util.*;

public class Simulator extends Model implements Runnable {


	
	private boolean run;
	private Queue entranceCarQueue;
    private Queue entrancePassQueue;
    private Queue paymentCarQueue;
    private Queue exitCarQueue;

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private Car[][][] cars;


    private int day         = 0;
    private int hour        = 0;
    private int minute      = 0;
    private int tickPause   = 1000;

    int weekDayArrivals     = 100; // average number of arriving cars per hour
    int weekendArrivals     = 200; // average number of arriving cars per hour
    int weekDayPassArrivals = 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour

    int enterSpeed      = 3; // number of cars that can enter per minute
    int paymentSpeed    = 7; // number of cars that can pay per minute
    int exitSpeed       = 5; // number of cars that can leave per minute

    public Simulator(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        entranceCarQueue    = new Queue();
        entrancePassQueue   = new Queue();
        paymentCarQueue     = new Queue();
        exitCarQueue        = new Queue();


        this.numberOfFloors     = numberOfFloors;
        this.numberOfRows       = numberOfRows;
        this.numberOfPlaces     = numberOfPlaces;
        this.numberOfOpenSpots  = numberOfFloors * numberOfRows * numberOfPlaces;

        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
    }
    public boolean getRun(){
        return run;
    }
    /**
     * @name startSimulator
     *
     * This calls the thread to run the ticks.
     */
    public void startSimulator() {
        new Thread(this).start();
    }

    public void run() {
        while (run) {
            tick();
        }
    }
    public void setRun(boolean run){
        this.run =run;
    }
    public void Stop(){
       run=false;


    }

    private void tick() {
    	advanceTime();
    	handleExit();
        carTick();
        handleEntrance();
        notifyViews();

    	// Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void carTick(){
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    if (cars[floor][row][place]!=null) {
                        cars[floor][row][place].tick();
                    }
                }
            }
        }
    }
    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }

    private void handleEntrance(){
        carsArriving();
        carsEntering(entrancePassQueue);
        carsEntering(entranceCarQueue);


    }
    
    private void handleExit(){
        System.out.println("A car is leaving");
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, false);
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, true);
    }

    private void carsEntering(Queue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 &&
                getNumberOfOpenSpots()>0 &&
    			i<enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = getFirstFreeLocation();
            setCarAt(freeLocation, car);
            i++;
        }
    }

    //hier zit de fout geen idee tough
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();

        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        	    //geerft geen plaats door
        		carLeavesSpot(car);
        	}
            car = getFirstLeavingCar();
        }
    }

    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
    	}
    }
    
    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
    
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private void addArrivingCars(int numberOfCars, boolean hasPass){
        // Add the cars to the back of the queue.

            for (int i = 0; i < numberOfCars; i++) {
                Car car =new Car();
                car.setHasToPay(!hasPass);
            	entrancePassQueue.addCar(car);
            }

    	}
    private void carLeavesSpot(Car car){
        System.out.println("carleavessopt");

    	removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }


    public int getNumberOfOpenSpots() {
        return numberOfOpenSpots;
    }

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }


        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        return cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
    }

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }

        return null;
    }

    public void ResetSim() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    cars[floor][row][place] = null;


                }

            }

        }
        entranceCarQueue.emptyQueue();
        entrancePassQueue.emptyQueue();
        paymentCarQueue.emptyQueue();
        exitCarQueue.emptyQueue();
        numberOfOpenSpots   = numberOfFloors *numberOfRows * numberOfPlaces;

        day=0;
        hour=0;
        minute=0;
        run=false;
        notifyViews();
    }
}






