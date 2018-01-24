package nl.SeriousParking.Parkeersimulator.model;

import nl.SeriousParking.Parkeersimulator.canEvent;

import java.util.HashMap;

public class Profit extends Model implements canEvent {
    private double profit;
    private double perHour;
    private double doubleLost;
    private int hours;

    public Profit() {
        hours = 1; //Prevent devision by zero.
    }

    private double calcPerHour(int hours, double profit) {
        return (double) profit/hours;
    }

    @Override
    public void doEvent(HashMap data) {
        if (data != null) {
            this.profit     = (double) data.get("profit");
            this.hours      = (int) data.get("time_passed");
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
        }

        this.notifyViews();
    }

    public double getProfit() {
        return profit;
    }

    public double getPerHour() {
        return perHour;
    }

    public double getDoubleLost() {
        return doubleLost;
    }
}
