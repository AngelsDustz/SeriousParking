package nl.SeriousParking.Parkeersimulator.view;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Date_time;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class ProfitView extends View<SimulatorController, Simulator> implements Runnable {
    private Label lProfitVal        = new Label();
    private Label lProfitHourVal    = new Label();
    private Label lDoubleLostVal    = new Label();
    protected LineChartView lineChart;
    protected boolean run = false;
    protected int last_hour;

    public ProfitView(SimulatorController controller, Simulator model) {
        super(controller, model);

        HBox container      = new HBox();
        GridPane grid       = new GridPane();
        Label lProfit       = new Label("Winst");
        Label lPerHour      = new Label("Winst per uur");
        Label lLostDouble   = new Label("Misgelopen auto's door te lage inrij snelheid");

        grid.add(lProfit, 0, 2);
        grid.add(lProfitVal, 2, 2);

        grid.add(lPerHour, 0, 4);
        grid.add(lProfitHourVal, 2, 4);

        grid.add(lLostDouble, 0, 6);
        grid.add(lDoubleLostVal, 2, 6);

        last_hour = 0;

        lineChart = new LineChartView();

        container.getChildren().addAll(grid, lineChart.draw());

        this.getChildren().add(container);
        model.addView(this);

        new Thread(this, "ProfitView").start();
        run = true;
    }

    @Override
    public void update() {
    }

    @Override
    public void run() {
        while(run) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    int hours       = Date_time.getTickSinceStart();
                    hours           = hours/60; //1 tick = 1 minute.
                    hours++;
                    Double profit   = model.getProfit();
                    lProfitVal.setText(" " + profit);
                    profit = profit / hours;
                    lProfitHourVal.setText(" " + profit);

                    if (hours != last_hour) {
                        lineChart.addData(hours-1, profit);
                        last_hour = hours;
                    }
                }
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
