package nl.SeriousParking.Parkeersimulator.model;

import nl.SeriousParking.Parkeersimulator.canEvent;

import java.util.HashMap;

public class Profit extends Model implements canEvent, Runnable {
    private boolean run;
    private HashMap<String, Object> runData;
    private double profit;
    private double perHour;
    private double doubleLost;
    private int hours;
    private int minutes;
    private int cars;

    public Profit() {
        run     = false;
        runData = null;
    }

    private double calcPerHour(int hours, double profit) {
        return (double) profit/hours;
    }

    @Override
    public void doEvent(HashMap data) {
        System.out.println("doEvent triggered.");
        runData = data;

        if (run == false) {
            System.out.println("Starting new thread.");
            new Thread(this).start();
            run = true;
        }
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
        while (run) {
            if (runData != null) {
                System.out.println("Got new data.");

                if (runData.containsKey("profit")) {
                    this.profit = (double) runData.get("profit");
                }

                if (runData.containsKey("time_passed")) {
                    this.hours = (int) runData.get("time_passed");
                    this.hours++;
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

                notifyViews();
                runData = null;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
