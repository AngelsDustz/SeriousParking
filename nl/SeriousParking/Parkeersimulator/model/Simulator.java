package nl.SeriousParking.Parkeersimulator.model;

import javafx.application.Platform;

import java.util.*;

public class Simulator extends Model implements Runnable {
    public final int ADHOC = 1;
    public final int PASS  = 2;
    public final int RES   = 3;
    private boolean run;
    private boolean GarageIsSet;
    private boolean doubleEntrance;
    private int adhocReservationsPassed;
    private int passPassed;
    private Garage garage;
    private GarageSection adhocReservationSection;
    private GarageSection passSection;
    private TicketMachine ticketMachine;
    private Random randomGenerator;
    private ArrayList<SimEvent> events;

    public Simulator() {
        events                  = new ArrayList<>();
        adhocReservationsPassed = 0;
        passPassed              = 0;
        GarageIsSet             = true;
        garage                  = new Garage();
        adhocReservationSection = new GarageSection(SettingHandler.adhocReservationFloors,SettingHandler.adhocReservationRows,SettingHandler.adhocReservationplaces);
        passSection             = new GarageSection(SettingHandler.passFloors,SettingHandler.passRows,SettingHandler.passplaces);
        ticketMachine           = new TicketMachine();
        randomGenerator         = new Random();
    }

    public int getAdhocReservationsPassed() {
        return adhocReservationsPassed;
    }

    /**
     * Adds an event.
     *
     * @param event The event to add.
     */
    public void addEvent(SimEvent event) {
        events.add(event);
    }

    /**
     * Removes an event.
     *
     * @param event The event to remove.
     */
    public void removeEvent(SimEvent event) {
        events.remove(event);
    }

    public ArrayList<SimEvent> getEvents() {
        return events;
    }

    public int getPassPassed() {
        return passPassed;
    }

    /**
     * This calls the thread to run the ticks.
     */
    private void startSimulator() {
        new Thread(this).start();
    }


    /**
     * Start/Stops the simulator.
     */
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

    /**
     * Does multiple ticks.
     *
     * @param times The amount of ticks to run.
     */
    public void tickMany(int times) {
        new Thread(() -> {
            for (int i=0;i<times;i++) {
                tick();
            }
        }).start();
    }


    /**
     * Runs a single tick.
     */
    public void singleTick(){
        new Thread(() -> {tick();}).start();
    }

    /**
     * Does a tick.
     */
    private void tick() {
        Date_time.advanceTime();



        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                adhocReservationSection.tick();
                passSection.tick();

                handleExit();
                handleEntrance();
                notifyViews();
            }
        });

        // Pause.
        try {
            Thread.sleep(SettingHandler.tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the entrance.
     */
    private void handleEntrance(){
        carsArriving();
        Garage.CarsArrivingInQueue();

        adhocReservationsPassed += garage.carsPassingBy(adhocReservationSection, Garage.entranceAdhocQueue);
        passPassed              += garage.carsPassingBy(passSection,Garage.entrancePassReservationQueue);

        EnteringGarage(Garage.entranceAdhocQueue);
        EnteringGarage(Garage.entrancePassReservationQueue);
        //here the cars go to the designated area
        adhocReservationSection.carsEntering();
        if(SettingHandler.doubleEntrance){
            passSection.carsBackEntering();
        } else {
            passSection.carsEntering();
        }
    }


    /**
     * Handles the exit.
     */
    private void handleExit(){
        ticketMachine.handlePayment();
        garage.carsDrivingToExit();
        adhocReservationSection.carsReadyToLeave();
        adhocReservationSection.carsLeaving();
        passSection.carsReadyToLeave();
        passSection.carsLeaving();

    }

    /**
     * Handles the cars arriving.
     */
    private void carsArriving(){
        int numberOfCars;

        //Ad-hoc cars.
        numberOfCars = getNumberOfCars(SettingHandler.weekDayArrivals, SettingHandler.weekendArrivals);
        addArrivingCars((numberOfCars), ADHOC);

        //Subscriber cars.
        numberOfCars = getNumberOfCars(SettingHandler.weekDayPassArrivals, SettingHandler.weekendPassArrivals);
        addArrivingCars((numberOfCars), PASS);

        //Reservation cars.
        numberOfCars = getNumberOfCars(SettingHandler.weekDayReservations, SettingHandler.weekendReservations);
        addArrivingCars((numberOfCars), RES);
    }

    /**
     * Makes a queue enter the garage.
     *
     * @param queue The queue to make enter.
     */
    public void EnteringGarage(Queue queue){
        int i=0;

        while (i<SettingHandler.enterSpeed){
            Car car= queue.removeCar();

            if (car instanceof AdhocCar || car instanceof ReservationCar){
                adhocReservationSection.getSectionQueue().addCar(car);
            }

            if(car instanceof PassCar){
                passSection.getSectionQueue().addCar(car);
            }

            i++;
        }
    }

    public double getProfit() {
        return ticketMachine.getProfit();
    }



    public int getNumberOfCars(int weekDay, int weekend){
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


    /**
     * This function generates an identifier to the car.
     */

    private void generateIdentification(Car car) {
        int identifier;

        if(car instanceof PassCar){
            identifier=new Random().nextInt(SettingHandler.numberOfPassholders);
            if(!passSection.findCar(identifier,car)){
                car.setIdentification(identifier);
            } else {
                generateIdentification(car);
            }
        } else {
            identifier= new Random().nextInt((Integer.MAX_VALUE-SettingHandler.numberOfPassholders));
            if(!adhocReservationSection.findCar(identifier,car)){
                car.setIdentification((identifier+SettingHandler.numberOfPassholders));
            } else {
                generateIdentification(car);
            }
        }
    }

    /**
     * Spawns an amount of cars.
     *
     * @param numberOfCars The amount of cars to make.
     * @param type The type of cars to make.
     */
    public void addArrivingCars(int numberOfCars, int type) {
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



            if (!(car instanceof ReservationCar)) {
                if (randomGenerator.nextInt(100)<SettingHandler.chanseToParkDouble) {
                    car.setParkedDouble(true);
                }

            }

            generateIdentification(car);
            Garage.arrivingCars.addCar(car);
        }
    }

    /**
     * Resets the simulation.
     */
    public void ResetSim() {
        run = false;
        Date_time.resetTimer();
        adhocReservationSection.clear();
        passSection.clear();
        adhocReservationsPassed=0;
        passPassed=0;
        ticketMachine.reset();
        garage.reset();
        notifyViews();
    }


    public boolean isGarageIsSet(){return GarageIsSet;}


    public GarageSection getAdhocReservationSection(){
        return adhocReservationSection;
    }

    public GarageSection getPassSection(){
        return passSection;
    }

    public TicketMachine getTicketMachine() {
        return ticketMachine;
    }
}
