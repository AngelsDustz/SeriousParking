package nl.SeriousParking.Parkeersimulator.view;

import javafx.application.Platform;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class PieChartView extends View<SimulatorController, Simulator> implements Runnable {
   private PieChart.Data parkedAdhocReservationSlice;
   private PieChart.Data parkedPassSlice;
   private PieChart.Data freeAdhocReservationSlice;
   private PieChart.Data freePassSlice;
   private double size;
   private boolean run ;

    public PieChartView(SimulatorController controller, Simulator model) {
        super(controller, model);

        PieChart pieChart           = new PieChart();
        size                        = 500;
        parkedAdhocReservationSlice = new PieChart.Data("geparkeerde ad-hoc en gereserveerde plekken",100);
        parkedPassSlice             = new PieChart.Data("geparkeerde pashouder Plekken", 100);
        freeAdhocReservationSlice   = new PieChart.Data("Vrije ad-hoc en reserveerbare plekken",100);
        freePassSlice               = new PieChart.Data("Vrije pashouder Plekken", 100);

        pieChart.getData().add(parkedAdhocReservationSlice);
        pieChart.getData().add(parkedPassSlice);
        pieChart.getData().add(freePassSlice);
        pieChart.getData().add(freeAdhocReservationSlice);

        pieChart.setLabelsVisible(false);
        pieChart.setLegendVisible(true);
        pieChart.setClockwise(true);
        pieChart.setStartAngle(90);

        VBox pie = new VBox(pieChart);
        this.getChildren().add(pie);
        model.addView(this);

        new Thread(this, "PieChartView").start();
        run = true;
    }

    @Override
    public void update() {
    }

    @Override
    public void run() {
        while (run) {
            double adhocReservationCars         = (model.getAdhocReservationSection().getFilledspots()/size*100);
            double passCars                     = (model.getPassSection().getFilledspots()/size*100);
            double freeAdhocReservationSpots    = (model.getAdhocReservationSection().getFreeSpots()/size*100);
            double freePassSpots                = (model.getPassSection().getFreeSpots()/size*100);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    parkedAdhocReservationSlice.setPieValue(adhocReservationCars);
                    freeAdhocReservationSlice.setPieValue(freeAdhocReservationSpots);
                    parkedPassSlice.setPieValue(passCars);
                    freePassSlice.setPieValue(freePassSpots);
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

