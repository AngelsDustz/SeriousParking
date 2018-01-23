package nl.SeriousParking.Parkeersimulator.model;

import nl.SeriousParking.Parkeersimulator.SimulatorView;
import nl.SeriousParking.Parkeersimulator.canEvent;

import java.util.*;

public class Simulator extends Model implements Runnable {
    //@todo Make this a setting.
    private static final double CARPRICE = 12.50;

    private boolean run;
	private double profit;
    private boolean firstRun;
	private Queue entranceCarQueue;
    private Queue entrancePassQueue;
    private Queue paymentCarQueue;
    private Queue exitCarQueue;

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;

    private int NumberOfCarsParkedDouble;
    private int numberOfAddhoccarsinPark;
    private int numberOfPasscarsinPark;
    private int numberOfOpenSpots;
    private Car[][][] cars;
    private Random randomGenerator;

    private ArrayList<Model> listners = new ArrayList<>();

    private int day         = 0;
    private int hour        = 0;
    private int minute      = 0;

    private int tickPause   = SettingHandler.tickPause;
    private int chance      = SettingHandler.chance;

    private int weekDayArrivals; // average number of arriving cars per hour
    private int weekendArrivals;// average number of arriving cars per hour
    private int weekDayPassArrivals;// average number of arriving cars per hour
    private int weekendPassArrivals;// average number of arriving cars per hour

    private int enterSpeed; // number of cars that can enter per minute
    private int paymentSpeed; // number of cars that can pay per minute
    private int exitSpeed; // number of cars that can leave per minute

    public Simulator() {
        NumberOfCarsParkedDouble    = 0;
        numberOfAddhoccarsinPark    = 0;
        numberOfPasscarsinPark      = 0;

        entranceCarQueue    = new Queue();
        entrancePassQueue   = new Queue();
        paymentCarQueue     = new Queue();
        exitCarQueue        = new Queue();
        profit              = 0;


        numberOfFloors     = SettingHandler.garageFloors;
        numberOfRows       = SettingHandler.garageRows;
        numberOfPlaces     = SettingHandler.garagePlaces;
        numberOfOpenSpots  = numberOfFloors * numberOfRows * numberOfPlaces;

        firstRun        = true;
        randomGenerator = new Random();
    }

    private void sendEvent(HashMap data) {
        for (Model m : listners) {
            if (m instanceof canEvent) {
                ((canEvent) m).doEvent(data);
            }
        }
    }

    public void addEventListner(Model m) {
        listners.add(m);
    }

    private void setSettings(){
        tickPause   = SettingHandler.tickPause;
        chance      = SettingHandler.chance;

        weekDayArrivals     = SettingHandler.weekDayArrivals;
        weekendArrivals     = SettingHandler.weekendArrivals;
        weekDayPassArrivals = SettingHandler.weekDayPassArrivals;
        weekendPassArrivals = SettingHandler.weekendPassArrivals;

        enterSpeed      = SettingHandler.enterSpeed;
        paymentSpeed    = SettingHandler.paymentSpeed;
        exitSpeed       = SettingHandler.exitSpeed;
    }
    /**
     * @name startSimulator
     *
     * This calls the thread to run the ticks.
     */
    public void startSimulator() {

        if(firstRun==true){
            cars        = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
            firstRun    = false;
        }

        new Thread(this).start();
    }


    public void startStop(){
        if (run==false){
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


    public void Stop(){
       run = false;
    }

    public void singleTick(){
        tick();
    }

    private void tick() {
    	advanceTime();
    	handleExit();
        carTick();
        notifyViews();
        handleEntrance();
        setSettings();


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
        carsOtherEntering(entrancePassQueue);
        carsOtherEntering(entranceCarQueue);
        carsEntering(entranceCarQueue);
    }
    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void carsArriving(){
    	int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, false);
    	numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, true);

    }

    private void carcounterADD(Car car){
        if (car.getisParkedDouble()==true){
           NumberOfCarsParkedDouble++;
        }

        if (car.getHasToPay() == true) {
            numberOfAddhoccarsinPark++;
        } else {
            numberOfPasscarsinPark++;
        }

    }

    private void carcounterRemove(Car car){
        if (car.getisParkedDouble()==true){
            NumberOfCarsParkedDouble--;

        }

        if (car.getHasToPay() == true) {
            numberOfAddhoccarsinPark--;
        }
        else {
            numberOfPasscarsinPark--;
        }

    }


    private void carsEntering(Queue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue()>0 &&
                getNumberOfOpenSpots()>0 &&
                i<enterSpeed) {
            Car car = queue.removeCar();

            if (!car.getisParkedDouble()){
                Location freeLocation = getFirstFreeLocation();
                setCarAt(freeLocation, car);
                carcounterADD(car);

               }


            else{
                Location[] freeLocation = getFirstFreeDoubleLocation();
                if (freeLocation!=null) {
                    Location loc1 = freeLocation[0];
                    Location loc2 = freeLocation[1];
                    Car car2 = new Car();
                    car2 = car2.copy(car);
                    setCarAt(loc1, car);
                    setCarAt(loc2, car2);
                    carcounterADD(car);
                }
                else{
                    queue.addCar(car);
                }
            }
            i++;
        }
    }
    private void carsOtherEntering(Queue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue()>0 &&
                getNumberOfOpenSpots()>0 &&
                i<enterSpeed) {
            Car car = queue.removeCar();

            if (!car.getisParkedDouble()){
                Location freeLocation = getLastFreeLocation();
                setCarAt(freeLocation, car);
                carcounterADD(car);

            }


            else{
                Location[] freeLocation = getLastFreeDoubleLocation();
                if (freeLocation!=null) {
                    Location loc1 = freeLocation[0];
                    Location loc2 = freeLocation[1];
                    Car car2 = new Car();
                    car2 = car2.copy(car);
                    setCarAt(loc1, car);
                    setCarAt(loc2, car2);
                    carcounterADD(car);
                }
                else{
                    queue.addCar(car);
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
               carcounterRemove(car);
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
            this.profit += CARPRICE;
            HashMap<String, Object> data = new HashMap<>();
            data.put("profit", this.profit);
            data.put("time_passed", 123);
            sendEvent(data);
            carcounterRemove(car);
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
        double standardDeviation    = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour  = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private void addArrivingCars(int numberOfCars, boolean hasPass) {
        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCars; i++) {
            Car car =new Car();
            car.setHasToPay(!hasPass);
            int rand= randomGenerator.nextInt(100);
            if (rand<=chance && chance!=0){
                car.setParkedDouble(true);
            }
            else{
                car.setParkedDouble(false);
            }
            entrancePassQueue.addCar(car);
        }

    }

    private void carLeavesSpot(Car car){

            removeCarAt(car.getLocation());
            exitCarQueue.addCar(car);

    }


    private boolean locationIsValid(Location location) {

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

    public Location getLastFreeLocation() {
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

    public Location[] getFirstFreeDoubleLocation() {
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



    public Location[] getLastFreeDoubleLocation() {
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

        NumberOfCarsParkedDouble=0;
        numberOfAddhoccarsinPark=0;
        numberOfPasscarsinPark=0;

        entranceCarQueue.emptyQueue();
        entrancePassQueue.emptyQueue();
        paymentCarQueue.emptyQueue();
        exitCarQueue.emptyQueue();
        numberOfOpenSpots   = numberOfFloors *numberOfRows * numberOfPlaces;

        day     = 0;
        hour    = 0;
        minute  = 0;
        profit  = 0;
        run     = false;

        sendEvent(null);
        notifyViews();
    }

    public int getNumberOfPasscarsinPark() {
        return numberOfPasscarsinPark;
    }

    public int getNumberOfAddhoccarsinPark() {
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

    public int getNumberOfOpenSpots() {
        return numberOfOpenSpots;
    }

    public int getNumberOfCarsParkedDouble() {
        return NumberOfCarsParkedDouble/2;
    }

    public int getTickPause() {
        return tickPause;
    }

    public void setTickPause(int tickPause) {
        this.tickPause = tickPause;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public int getWeekDayArrivals() {
        return weekDayArrivals;
    }

    public void setWeekDayArrivals(int weekDayArrivals) {
        this.weekDayArrivals = weekDayArrivals;
    }

    public int getWeekendArrivals() {
        return weekendArrivals;
    }

    public void setWeekendArrivals(int weekendArrivals) {
        this.weekendArrivals = weekendArrivals;
    }

    public int getWeekDayPassArrivals() {
        return weekDayPassArrivals;
    }

    public void setWeekDayPassArrivals(int weekDayPassArrivals) {
        this.weekDayPassArrivals = weekDayPassArrivals;
    }

    public int getWeekendPassArrivals() {
        return weekendPassArrivals;
    }

    public void setWeekendPassArrivals(int weekendPassArrivals) {
        this.weekendPassArrivals = weekendPassArrivals;
    }

    public int getEnterSpeed() {
        return enterSpeed;
    }

    public void setEnterSpeed(int enterSpeed) {
        this.enterSpeed = enterSpeed;
    }

    public int getPaymentSpeed() {
        return paymentSpeed;
    }

    public void setPaymentSpeed(int paymentSpeed) {
        this.paymentSpeed = paymentSpeed;
    }

    public int getExitSpeed() {
        return exitSpeed;
    }

    public void setExitSpeed(int exitSpeed) {
        this.exitSpeed = exitSpeed;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}






