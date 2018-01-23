package nl.SeriousParking.Parkeersimulator.model;

import nl.SeriousParking.Parkeersimulator.canEvent;

import java.util.HashMap;

public class Profit extends Model implements canEvent {
    private double profit;

    public Profit() {
        //
    }

    @Override
    public void doEvent(HashMap data) {
        if (data != null) {
            this.profit = (double) data.get("profit");

        } else {
            //If we receive NULL assume a reset.
            this.profit = 0.0;
        }

        this.notifyViews();
    }

    public double getProfit() {
        return profit;
    }
}
