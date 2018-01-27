package nl.SeriousParking.Parkeersimulator.model;

import javafx.application.Platform;
import nl.SeriousParking.Parkeersimulator.canEvent;

import java.util.*;

public class Simulator extends Model implements Runnable {
    //@todo Make this a setting.
    private static final double CARPRICE = 12.50;

    private boolean run;
	private double profit;
    private boolean firstRun;
    private boolean GarageIsSet;
    private boolean doubleEntrance;

	private Queue entranceAdhocQueue;
    private Queue enteringCars;
    private Queue entrancePassQueue  ;
    private Queue paymentCarQueue;
    private Queue exitCarQueue;


    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int maxQueueSize;
    private int numberOfAdhocPassing;
    private int numberOfPassCarsPassing;

    private double NumberOfCarsParkedDouble;
    private double numberOfAddhoccarsinPark;
    private double numberOfPasscarsinPark;
    private double numberOfOpenSpots;
    private double numberOfReservations;
    private Car[][][] cars;
    private Random randomGenerator;

    private ArrayList<Model> listners = new ArrayList<>();

    private int tickPause   = SettingHandler.tickPause;
    private int chance      = SettingHandler.chance;
    private int reservationShowchance =SettingHandler.reservationShowchance;
    private int weekDayArrivals; // average number of arriving cars per hour
    private int weekendArrivals;// average number of arriving cars per hour
    private int weekDayPassArrivals;// average number of arriving cars per hour
    private int weekendPassArrivals;// average number of arriving cars per hour
    private int weekDayReservations;
    private int WeekendReservations;


    private int enterSpeed; // number of cars that can enter per minute
    private int paymentSpeed; // number of cars that can pay per minute
    private int exitSpeed; // number of cars that can leave per minute

    public Simulator() {

        GarageIsSet =  false;
        NumberOfCarsParkedDouble    = 0;
        numberOfAddhoccarsinPark    = 0;
        numberOfPasscarsinPark      = 0;
        numberOfAdhocPassing        = 0;
        numberOfPassCarsPassing     = 0;

        entranceAdhocQueue = new Queue();
        entrancePassQueue   = new Queue();
        enteringCars        = new Queue();
        paymentCarQueue     = new Queue();
        exitCarQueue        = new Queue();
        profit              = 0;

        numberOfFloors     = SettingHandler.garageFloors;
        numberOfRows       = SettingHandler.garageRows;
        numberOfPlaces     = SettingHandler.garagePlaces;
        maxQueueSize       = SettingHandler.maxQueueSize;

        firstRun           = true;
        randomGenerator    = new Random();
    }

    private void sendEvent(HashMap data) {
        for (Model m : listners) {
            if (m instanceof canEvent) {
                ((canEvent) m).doEvent(data);
            }
        }
    }



    private void setSettings(){
        maxQueueSize       = SettingHandler.maxQueueSize;
        tickPause   = SettingHandler.tickPause;
        chance      = SettingHandler.chance;
        weekDayReservations = SettingHandler.weekDayReservations;
        WeekendReservations = SettingHandler.WeekendReservations;
        weekDayArrivals     = SettingHandler.weekDayArrivals;
        weekendArrivals     = SettingHandler.weekendArrivals;
        weekDayPassArrivals = SettingHandler.weekDayPassArrivals;
        weekendPassArrivals = SettingHandler.weekendPassArrivals;

        enterSpeed      = SettingHandler.enterSpeed;
        paymentSpeed    = SettingHandler.paymentSpeed;
        exitSpeed       = SettingHandler.exitSpeed;
        doubleEntrance  = SettingHandler.doubleEntrance;



    }

    /**
     * @name startSimulator
     *
     * This calls the thread to run the ticks.
     */
    private void startSimulator() {

        if(firstRun){
            cars        = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
            setGarageSizeValue();
            firstRun    = false;
        }

        new Thread(this).start();
    }

    private void setGarageSizeValue(){
          numberOfOpenSpots  = numberOfFloors * numberOfRows * numberOfPlaces;
    }

    public void startStop(){
        if (!run){
           run=true;
                startSimulator();
        } else {
          run=false;
        }
    }

    public void run() {
        while (run) {
            tick();
        }
    }

    public void tickMany(int times) {
        new Thread(() -> {
            for (int i=0;i<times;i++) {
                tick();
            }
        }).start();
    }


    public void singleTick(){
        new Thread(() -> {tick();}).start();
    }

    private void tick() {
    	Date_time.advanceTime();
        carTick();


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                handleExit();
                notifyViews();
                handleEntrance();
            }
        });


        setSettings();

        HashMap<String, Object> data = new HashMap<>();
        data.put("time_passed", Date_time.getTickSinceStart());
        data.put("profit", profit);
        sendEvent(data);


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
                            Car car = cars[floor][row][place];
                    if (car!=null) {
                        if(!car.getReservation()) {
                            car.tick();
                        } else {
                            if (car.getPreTime() > 0) {
                                car.setPreTime(car.getPreTime() - 1);
                            }
                            if(car.getPreTime()==0){
                                chance=randomGenerator.nextInt(100);
                                if(reservationShowchance< chance) {
                                    car.setActive(true);
                                } else {
                                    car.noShow();
                                }

                                car.setPreTime(car.getPreTime()-1);
                            } else {
                                car.tick();
                            }
                        }
                    }
                }
            }
        }
    }


    private int carsPassingBy(Queue queue){
        int carsPassed=0;
        if(numberOfOpenSpots!=0){
            while (queue.carsInQueue()>maxQueueSize){
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


    private void carToQueue(){
        Car car;
        while(enteringCars.carsInQueue()>0) {

            car = enteringCars.removeCar();
            if (car.getHasToPay() && !car.getReservation()){
                entranceAdhocQueue.addCar(car);
            } else {
                entrancePassQueue.addCar(car);
            }

        }

    }


    private void handleEntrance(){
        carsArriving();
        carToQueue();
        carsEntering(entranceAdhocQueue);
        numberOfAdhocPassing=carsPassingBy(entranceAdhocQueue)+numberOfAdhocPassing;

        carsBackEntering(entrancePassQueue);
        numberOfPassCarsPassing=carsPassingBy(entrancePassQueue)+numberOfPassCarsPassing;







    }

    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void carsArriving(){
    	int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, false, false);
    	numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, true, false);

        numberOfCars = getNumberOfCars(weekDayReservations/2, WeekendReservations/2);
        addArrivingCars(numberOfCars, true, true);
        numberOfCars = getNumberOfCars(weekDayReservations/2, WeekendReservations/2);
        addArrivingCars(numberOfCars, false, true);
    }

    private void carcounterADD(Car car){
        if (car.getReservation()){
            numberOfReservations++;
        }

        if (car.getisParkedDouble()){
           NumberOfCarsParkedDouble=NumberOfCarsParkedDouble+.5;


            if (car.getReservation()){
                numberOfReservations=numberOfReservations+.5;
            }
            if (car.getHasToPay()) {
                numberOfAddhoccarsinPark=numberOfAddhoccarsinPark+.5;
            } else {
                numberOfPasscarsinPark=numberOfPasscarsinPark+.5;
            }
        } else if (car.getHasToPay()){
            numberOfAddhoccarsinPark++;
        } else {
            numberOfPasscarsinPark++;
        }

    }

    public double getNumberOfReservations() {
        return numberOfReservations;
    }

    private void carCounterRemove(Car car){
         if (car.getReservation()){
             numberOfReservations--;
         }
        if (car.getisParkedDouble()){
            NumberOfCarsParkedDouble=NumberOfCarsParkedDouble-.5;
            if (car.getReservation()){numberOfReservations=numberOfReservations-.5;}

            if (car.getHasToPay()) {
                numberOfAddhoccarsinPark=numberOfAddhoccarsinPark-.5;
            } else {
                numberOfPasscarsinPark=numberOfPasscarsinPark-.5;
            }
        }
        else if (car.getHasToPay()) {
                numberOfAddhoccarsinPark--;
        } else {
          numberOfPasscarsinPark--;
        }

    }


    private void carsEntering(Queue queue){
        int i=0;

        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue()>0 && getNumberOfOpenSpots()>0 && i<enterSpeed) {
            Car car = queue.removeCar();

            if (!car.getisParkedDouble()){
                Location freeLocation = getFirstFreeLocation();
                setCarAt(freeLocation, car);

            } else {
                Location[] freeLocation = getFirstFreeDoubleLocation();


                if (freeLocation != null) {
                    Location loc1   = freeLocation[0];
                    Location loc2   = freeLocation[1];
                    Car car2        = new Car();
                    car2            = car2.copy(car);

                    setCarAt(loc1, car);
                    setCarAt(loc2, car2);

                } else {

                    queue.addCar(car);
                }
            }

            i++;
        }
    }
    private void carsBackEntering(Queue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue()>0 && getNumberOfOpenSpots()>0 && i<enterSpeed) {

            Car car = queue.removeCar();

            if (!car.getisParkedDouble()){
                Location freeLocation = getLastFreeLocation();
                setCarAt(freeLocation, car);


            } else {
                Location[] freeLocation = getLastFreeDoubleLocation();


                if (freeLocation != null) {
                    Location loc1   = freeLocation[0];
                    Location loc2   = freeLocation[1];
                    Car car2        = new Car();
                    car2            = car2.copy(car);

                    setCarAt(loc1, car);
                    setCarAt(loc2, car2);

                }
            }
            i++;
        }
    }

    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();

        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	} else {
               carLeavesSpot(car);

        	}
            car = getFirstLeavingCar();
        }
    }



    private void carsPaying(){
        // Let cars pay.
    	int i=0;

    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            HashMap<String, Object> data = new HashMap<>();
            Car car = paymentCarQueue.removeCar();

            if (car.getisParkedDouble()) {
                this.profit += (CARPRICE/2);
                data.put("car_price", (CARPRICE/2));
            } else {
                this.profit += CARPRICE;
                data.put("car_price", CARPRICE);
            }



            data.put("profit", profit);
            data.put("time_passed", Date_time.getTickSinceStart());
            data.put("minutes", Date_time.getMinutes());
            data.put("cars", numberOfAdhocPassing+numberOfPassCarsPassing);
            data.put("doubled", car.getisParkedDouble());
            sendEvent(data);
            carLeavesSpot(car);
            i++;
    	}
    }

    public double getProfit() {
        return profit;
    }

    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            carCounterRemove(exitCarQueue.removeCar());

            i++;
    	}
    }
    
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = Date_time.getDays() < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation    = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour  = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private void addArrivingCars(int numberOfCars, boolean hasPass, boolean reservation) {
        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCars; i++) {
            Car car =new Car();

            car.setHasToPay(!hasPass);
            int rand= randomGenerator.nextInt(100);
            car.setReservation(reservation);
            if(!car.getReservation()){car.setActive(true);}
            if (rand<=chance && chance!=0 && !reservation){
                car.setParkedDouble(true);
            }
            enteringCars.addCar(car);

        }

    }

    private void carLeavesSpot(Car car){

            removeCarAt(car.getLocation());

            exitCarQueue.addCar(car);

    }


    private boolean locationIsValid(Location location) {
        if (location == null) {
            return false;
        }

        int floor   = location.getFloor();
        int row     = location.getRow();
        int place   = location.getPlace();

        if (floor < 0 || floor >= numberOfFloors) {
            return false;
        }

        if (row < 0 || row > numberOfRows) {
            return false;
        }

        if (place < 0 || place > numberOfPlaces) {
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

    private boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }

        Car oldCar = getCarAt(location);
        if (oldCar == null) {

            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            carcounterADD(car);
            numberOfOpenSpots--;
            return true;
        }

        return false;
    }

    private Car removeCarAt(Location location) {
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

    private Location getFirstFreeLocation() {
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

    private Location getLastFreeLocation() {
        for (int floor = getNumberOfFloors()-1; floor >=0; floor--) {

            for (int row = getNumberOfRows()-1; row >=0; row--) {

                for (int place = getNumberOfPlaces()-1; place >=0; place--) {
                    Location location = new Location(floor, row, place);

                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    private Location[] getFirstFreeDoubleLocation() {
        Location[] locations = new Location[2];
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        if (place<numberOfPlaces-1){
                            Location secondLocation = new Location(floor, row, place + 1);
                            if (getCarAt(secondLocation) == null) {

                                locations[0] = location;
                                locations[1] = secondLocation;
                                return locations;
                            }
                        }
                    }
                }
            }

        }
        return null;
    }



    private Location[] getLastFreeDoubleLocation() {
        Location[] locations = new Location[2];
        for (int floor = getNumberOfFloors()-1; floor >=0; floor--) {
            for (int row = getNumberOfRows()-1; row >=0; row--) {
                for (int place = getNumberOfPlaces()-1; place >=0; place--) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        if (place<numberOfPlaces-1){
                            Location secondLocation = new Location(floor, row, place - 1);
                            if (getCarAt(secondLocation) == null) {

                                locations[0] = location;
                                locations[1] = secondLocation;
                                return locations;
                            }
                        }
                    }
                }
            }

        }
        return null;
    }


    private Car getFirstLeavingCar() {
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

        Date_time.resetTimer();
        NumberOfCarsParkedDouble=0;
        numberOfAddhoccarsinPark=0;
        numberOfPasscarsinPark=0;
        numberOfPassCarsPassing =0;
        numberOfAdhocPassing    =0;
        enteringCars.emptyQueue();
        entranceAdhocQueue.emptyQueue();
        entrancePassQueue.emptyQueue();
        paymentCarQueue.emptyQueue();
        exitCarQueue.emptyQueue();
        numberOfOpenSpots   = numberOfFloors *numberOfRows * numberOfPlaces;
        numberOfReservations=0;

        profit  = 0;
        run     = false;

        sendEvent(null);
        notifyViews();

    }

    public int getTotalCarsPassed() {
        return numberOfPassCarsPassing+numberOfAdhocPassing;
    }

    public double getNumberOfPasscarsinPark() {
        return numberOfPasscarsinPark;
    }

    public double getNumberOfAddhoccarsinPark() {
        return numberOfAddhoccarsinPark;
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

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public double getNumberOfOpenSpots() {
        return numberOfOpenSpots;
    }

    public double getNumberOfCarsParkedDouble() {
        return NumberOfCarsParkedDouble;
    }

    public int getNumberOfCarsInQueue() { return entranceAdhocQueue.carsInQueue(); }

    public boolean isGarageIsSet() {
        return GarageIsSet;
    }

    public boolean isDoubleEntrance() {
        return doubleEntrance;
    }

    public void setGarageIsSet(boolean garageIsSet) {
        GarageIsSet = garageIsSet;
    }
    public int getNumberOfCarsInBackQueue(){
        return entrancePassQueue.carsInQueue();
    }
}






