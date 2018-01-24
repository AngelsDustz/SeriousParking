package nl.SeriousParking.Parkeersimulator.model;

import nl.SeriousParking.Parkeersimulator.canEvent;

import java.util.HashMap;

public class Profit extends Model implements canEvent {
    private double profit;
    private double perHour;
    private double doubleLost;
    private int hours;
    private int minutes;
    private int cars;

    public Profit() {
    }

    private double calcPerHour(int hours, double profit) {
        return (double) profit/hours;
    }

    @Override
    public void doEvent(HashMap data) {
        if (data != null) {
            this.profit     = (double) data.get("profit");
            this.hours      = (int) data.get("time_passed");
            this.hours++;
            this.minutes    = (int) data.get("minutes");
            this.cars       = (int) data.get("cars");
            this.perHour    = calcPerHour(this.hours, this.profit);

            if ((boolean) data.get("doubled")) {
                this.doubleLost += (double) data.get("car_price");
            }

        } else {
            //If we receive NULL assume a reset.
            this.profit     = 0.0;
            this.hours      = 0;
            this.perHour    = 0.0;
            this.doubleLost = 0.0;
            this.minutes    = 0;
        }

        this.notifyViews();
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
}
