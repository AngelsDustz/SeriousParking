package nl.SeriousParking.Parkeersimulator.model;

import javafx.application.Platform;

import java.util.*;

public class Simulator extends Model implements Runnable {
    private final int ADHOC = 1;
    private final int PASS  = 2;
    private final int RES   = 3;
    private int tickPause   = SettingHandler.tickPause;
    private int chance      = SettingHandler.chance;
    private boolean run;
    private boolean GarageIsSet;
    private boolean doubleEntrance;
    private int numberOfAdhocPassing;
    private Random randomGenerator;
    private int weekDayArrivals; // average number of arriving cars per hour
    private int weekendArrivals;// average number of arriving cars per hour
    private int weekDayPassArrivals;// average number of arriving cars per hour
    private int weekendPassArrivals;// average number of arriving cars per hour
    private int weekDayReservations;
    private int WeekendReservations;
    private int enterSpeed; // number of cars that can enter per minute
    private int paymentSpeed; // number of cars that can pay per minute
    private int exitSpeed; // number of cars that can leave per minute
    private GarageSection adhocReservationSection;
    private GarageSection passSection;
    private TicketMachine ticketMachine;

    public Simulator() {
        adhocReservationSection = new GarageSection(SettingHandler.adhocReservationFloors,SettingHandler.adhocReservationRows,SettingHandler.adhocReservationplaces);
        passSection             = new GarageSection(SettingHandler.passFloors,SettingHandler.passRows,SettingHandler.passplaces);
        ticketMachine           = new TicketMachine();
        numberOfAdhocPassing    = 0;
        GarageIsSet             = true;
        numberOfAdhocPassing    = 0;
        randomGenerator         = new Random();
    }

    private void setSettings(){
        tickPause           = SettingHandler.tickPause;
        chance              = SettingHandler.chance;
        weekDayReservations = SettingHandler.weekDayReservations;
        WeekendReservations = SettingHandler.weekendReservations;
        weekDayArrivals     = SettingHandler.weekDayArrivals;
        weekendArrivals     = SettingHandler.weekendArrivals;
        weekDayPassArrivals = SettingHandler.weekDayPassArrivals;
        weekendPassArrivals = SettingHandler.weekendPassArrivals;
        enterSpeed          = SettingHandler.enterSpeed;
        paymentSpeed        = SettingHandler.paymentSpeed;
        exitSpeed           = SettingHandler.exitSpeed;
        doubleEntrance      = SettingHandler.doubleEntrance;
    }

    /**
     * @name startSimulator
     *
     * This calls the thread to run the ticks.
     */
    private void startSimulator() {
        new Thread(this).start();
    }


    public void startStop(){
        if (!run) {
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
        adhocReservationSection.Tick();
        passSection.Tick();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                handleExit();
                notifyViews();
                handleEntrance();
            }
        });

    	// Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfAdhocPassing() {
        return numberOfAdhocPassing;
    }

    private void handleEntrance(){
        carsArriving();
        Garage.CarsArrivingInQueue();
        EnteringGarage(Garage.entranceAdhocQueue);
        EnteringGarage(Garage.entrancePassReservationQueue);

        adhocReservationSection.carsEntering();
        passSection.carsEntering();
    }

    
    private void handleExit(){
        ticketMachine.handlePayment();
        adhocReservationSection.carsReadyToLeave();
        adhocReservationSection.carsLeaving();
        passSection.carsReadyToLeave();
        passSection.carsLeaving();

    }
    
    private void carsArriving(){
        int numberOfCars;

        //Add normal cars.
        numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, ADHOC);

        //Add pass cars.
        numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);

        //Add reservation cars.
        numberOfCars = getNumberOfCars(weekDayReservations, WeekendReservations);
        addArrivingCars(numberOfCars, RES);
    }

    public  void EnteringGarage(Queue queue){
        int i=0;

        while (i<SettingHandler.enterSpeed) {
            Car car= queue.removeCar();

            if (car instanceof AdhocCar || car instanceof ReservationCar) {
                adhocReservationSection.getSectionQueue().addCar(car);
            }

            if(car instanceof PassCar) {
                passSection.getSectionQueue().addCar(car);
            }

            i++;
        }
    }

    public double getProfit() {
        return ticketMachine.getProfit();
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
    
    private void addArrivingCars(int numberOfCars, int type) {
        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCars; i++) {
            Car car;

            switch (type){
                case ADHOC:
                    car = new AdhocCar();
                    break;

                case PASS:
                    car = new PassCar();
                    break;

                case RES:
                    car = new ReservationCar();
                    break;

                default:
                    car = new BadCar();
                    break;
            }


            if(!(car instanceof ReservationCar)){
                if(randomGenerator.nextInt(100)<SettingHandler.chanseToParkDouble) {
                   // car.setParkedDouble(true);
                }
            }

            Garage.arrivingCars.addCar(car);
        }
    }



    public void ResetSim() {
        run = false;
        Date_time.resetTimer();
        Garage.reset();
        adhocReservationSection.clear();
        passSection.clear();
        ticketMachine.reset();
        notifyViews();
    }

    public boolean isGarageIsSet() {
        return GarageIsSet;
    }

    public GarageSection getAdhocReservationSection(){
        return adhocReservationSection;
    }

    public GarageSection getPassSection(){
        return passSection;
    }
}






