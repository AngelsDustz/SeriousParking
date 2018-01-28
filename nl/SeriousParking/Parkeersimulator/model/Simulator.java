package nl.SeriousParking.Parkeersimulator.model;

import javafx.application.Platform;
import nl.SeriousParking.Parkeersimulator.canEvent;

import java.util.*;

public class Simulator extends Model implements Runnable {
    //@todo Make this a setting.
    private static final double CARPRICE = 12.50;
    private final int ADHOC =1;
    private final int PASS  =2;
    private final int RES  =3;

    private boolean run;
	private double profit;
    private boolean firstRun;
    private boolean GarageIsSet;
    private boolean doubleEntrance;


    private Queue enteringCars;





    private int adhocFloors;
    private int adhocrows;
    private int adhocplaces;
    private int numberOfAdhocPassing;




    private int numberOfPassCarsPassing;

    private double NumberOfCarsParkedDouble;
    private double numberOfAddhoccarsinPark;
    private double numberOfPasscarsinPark;




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


   private GarageSection adhocSection;
   private GarageSection passSection;
   private GarageSection reservationSection;

    public Simulator() {

         adhocSection = new GarageSection(SettingHandler.adhocFloors,SettingHandler.adhocRows,SettingHandler.adhocplaces);
         passSection = new GarageSection(SettingHandler.passFloors,SettingHandler.passRows,SettingHandler.passplaces);
         reservationSection = new GarageSection(SettingHandler.reservationFloors,SettingHandler.reservationRows,SettingHandler.reservationplaces);

         adhocFloors                = SettingHandler.getAdhocFloors();
         adhocrows                  = SettingHandler.getAdhocRows();
         adhocplaces                = SettingHandler.getAdhocplaces();
         numberOfAdhocPassing       =0;




        GarageIsSet =  true;
        NumberOfCarsParkedDouble    = 0;
        numberOfAddhoccarsinPark    = 0;
        numberOfPasscarsinPark      = 0;
        numberOfAdhocPassing        = 0;
        numberOfPassCarsPassing     = 0;



        enteringCars        = new Queue();
        profit              = 0;




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
        tickPause   = SettingHandler.tickPause;
        chance      = SettingHandler.chance;
        weekDayReservations = SettingHandler.weekDayReservations;
        WeekendReservations = SettingHandler.weekendReservations;
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

        }

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
        adhocSection.Tick();


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










    private void handleEntrance(){
        carsArriving();
        Garage.carToQueue(enteringCars);

        numberOfAdhocPassing=adhocSection.carsPassingBy(Garage.entranceAdhocQueue)+numberOfAdhocPassing;
        adhocSection.carsEntering(Garage.entranceAdhocQueue);


        passSection.carsEntering(Garage.entrancePassQueue);
        numberOfPassCarsPassing=passSection.carsPassingBy(Garage.entrancePassQueue)+numberOfPassCarsPassing;

        reservationSection.carsEntering(Garage.reservationQueue);
       // numberOfReservationsPassing=passSection.carsPassingBy(Garage.entrancePassQueue)+numberOfPassCarsPassing;








    }

    
    private void handleExit(){
        adhocSection.carsReadyToLeave();
        adhocSection.carsLeaving();

        passSection.carsReadyToLeave();
        passSection.carsLeaving();


       reservationSection.carsReadyToLeave();
       reservationSection.carsLeaving();
        carsPaying();
    }
    
    private void carsArriving(){
    	int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, ADHOC);
    	numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);


        numberOfCars = getNumberOfCars(weekDayReservations, WeekendReservations);
        addArrivingCars(numberOfCars, RES);
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

    private void carsPaying(){
        // Let cars pay.
    	int i=0;

    	while (Garage.paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            HashMap<String, Object> data = new HashMap<>();
            Car car = Garage.paymentCarQueue.removeCar();

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



            adhocSection.carLeavesSpot(car);
            i++;
    	}
    }

    public double getProfit() {
        return profit;
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
               case ADHOC   :   car = new AdhocCar();
                                break;
               case PASS    :   car = new PassCar();
                                break;
               case RES     :   car = new ReservationCar();
                                break;
               default      :   car = new Car();
                                break;
           }





            int rand= randomGenerator.nextInt(100);


            if (rand<=chance && chance!=0 && !(car instanceof ReservationCar)){
                car.setParkedDouble(true);
            }
            enteringCars.addCar(car);

        }

    }



    public void ResetSim() {


        Date_time.resetTimer();
        adhocSection.clear();
        passSection.clear();
        reservationSection.clear();

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


    public double getNumberOfCarsParkedDouble() {
        return NumberOfCarsParkedDouble;
    }



    public boolean isGarageIsSet() {
        return GarageIsSet;
    }



    public GarageSection getAdhocSection(){return
            adhocSection;
    }
    public GarageSection getPassSection(){
        return passSection;
    }
    public GarageSection getReservationSection(){
        return reservationSection;
    }
}






