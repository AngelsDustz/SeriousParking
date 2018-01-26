package nl.SeriousParking.Parkeersimulator.model;

import nl.SeriousParking.Parkeersimulator.canEvent;
import nl.SeriousParking.Parkeersimulator.view.View;

import java.util.HashMap;

public class Profit extends Model implements canEvent, Runnable {
    private boolean run;
    private HashMap<String, Object> runData;
    private double profit;
    private double perHour;
    private double doubleLost;
    private boolean threadMade;
    private int hours;
    private int minutes;
    private int cars;

    public Profit() {
        run         = false;
        runData     = null;
        threadMade  = false;

        if (!threadMade) {
            System.out.println("Starting new thread.");
            new Thread(this).start();
            threadMade = true;
            run = true;
        }
    }

    private double calcPerHour(int hours, double profit) {
        return (double) profit/hours;
    }

    @Override
    public void doEvent(HashMap data) {
        //System.out.println("doEvent triggered.");
        runData = data;
        /*
        if (data != null) {
            runData = data;
        } else {
            reset();
        }*/
    }

    public boolean isRun() {
        return run;
    }

    public void reset() {
        System.out.println("Reset called");
        runData     = null;
        profit      = 0.0;
        perHour     = 0.0;
        doubleLost  = 0.0;
        hours       = 0;
        minutes     = 0;
        cars        = 0;

        HashMap<String, Object> data = new HashMap<>();
        data.put("cars", this.cars);
        doEvent(data);
    }

    public double getProfit() {
        return profit;
    }

    public int getHours() {
        return hours;
    }

    public double getPerHour() {
        return perHour;
    }

    public int getCars() {
        return cars;
    }

    public double getDoubleLost() {
        return doubleLost;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public void run() {
        int cycles = 0;

        while (run) {
            if (runData != null) {
                System.out.println("Get data at cycle: " + cycles);

                if (runData.containsKey("profit")) {
                    this.profit = (double) runData.get("profit");
                }

                if (runData.containsKey("time_passed")) {
                    int ticks   = (int) runData.get("time_passed");
                    this.hours  = (ticks/60)+1;
                }

                if (runData.containsKey("minutes")) {
                    this.minutes = (int) runData.get("minutes");
                }

                if (runData.containsKey("cars")) {
                    this.cars = (int) runData.get("cars");
                }

                this.perHour = calcPerHour(this.hours, this.profit);

                if (runData.containsKey("doubled") && (boolean) runData.get("doubled")) {
                    this.doubleLost += (double) runData.get("car_price");
                }

                this.notifyViews();
            }

            runData = null;
            cycles++;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
