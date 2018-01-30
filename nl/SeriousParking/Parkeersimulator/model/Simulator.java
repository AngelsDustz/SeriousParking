package nl.SeriousParking.Parkeersimulator.model;

import javafx.application.Platform;

import java.util.*;

public class Simulator extends Model implements Runnable {
    //@todo Make this a setting.

    private final int ADHOC =1;
    private final int PASS  =2;
    private final int RES  =3;
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

    public Simulator() {

       adhocReservationsPassed=0;
       passPassed=0;


        GarageIsSet =true;
        garage = new Garage();
        adhocReservationSection = new GarageSection(SettingHandler.adhocReservationFloors,SettingHandler.adhocReservationRows,SettingHandler.adhocReservationplaces);
        passSection = new GarageSection(SettingHandler.passFloors,SettingHandler.passRows,SettingHandler.passplaces);
        ticketMachine = new TicketMachine();
        randomGenerator    = new Random();
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



        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                adhocReservationSection.Tick();
                passSection.Tick();

                handleExit();
                notifyViews();
                handleEntrance();
            }
        });






        // Pause.
        try {
            Thread.sleep(SettingHandler.tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    private void handleEntrance(){
        carsArriving();
        Garage.CarsArrivingInQueue();

        adhocReservationsPassed += garage.carsPassingBy(adhocReservationSection, Garage.entranceAdhocQueue);
        passPassed              += garage.carsPassingBy(passSection,Garage.entrancePassReservationQueue);

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
        int numberOfCars = getNumberOfCars(SettingHandler.weekDayArrivals, SettingHandler.weekendArrivals);
        addArrivingCars(numberOfCars, ADHOC);
        numberOfCars = getNumberOfCars(SettingHandler.weekDayPassArrivals, SettingHandler.weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);


        numberOfCars = getNumberOfCars(SettingHandler.weekDayReservations, SettingHandler.weekendReservations);
        addArrivingCars(numberOfCars, RES);
    }

    public  void EnteringGarage(Queue queue){
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


        Date_time.resetTimer();
        adhocReservationSection.clear();
        passSection.clear();


        run     = false;
        adhocReservationsPassed=0;
        passPassed=0;
        ticketMachine.reset();
        notifyViews();

    }


    public boolean isGarageIsSet(){return GarageIsSet;}


    public GarageSection getAdhocReservationSection(){
        return adhocReservationSection;
    }

    public GarageSection getPassSection(){
        return passSection;
    }
}
