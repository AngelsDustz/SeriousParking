package nl.SeriousParking.Parkeersimulator.view;

import javafx.application.Platform;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class PieChartView extends View<SimulatorController, Simulator> implements Runnable {

    //PieChart.Data dubbelpSlice;
    PieChart.Data parkedAdhocReservationSlice;
    PieChart.Data parkedPassSlice;
    PieChart.Data parkedReservationSlice;
    PieChart.Data freeAdhocReservationSlice;
    PieChart.Data freePassSlice;
    PieChart.Data freeReservationSlice;
    double size;
    boolean run = false;

    public PieChartView(SimulatorController controller, Simulator model) {
        super(controller, model);

        PieChart pieChart   = new PieChart();
        size     = 500;
       // carSlice            = new PieChart.Data("Auto's Geparkeerd", 30);
        parkedAdhocReservationSlice = new PieChart.Data("geparkeerde ad-hoc en gereserveerde plekken",100);
        parkedPassSlice   = new PieChart.Data("geparkeerde pashouder Plekken", 100);

        //dubbelpSlice        = new PieChart.Data("Dubbelparkeerder", 10);
        freeAdhocReservationSlice = new PieChart.Data("Vrije ad-hoc en reserveerbare plekken",100);
        freePassSlice   = new PieChart.Data("Vrije pashouder Plekken", 100);



        pieChart.getData().add(parkedAdhocReservationSlice);
        pieChart.getData().add(freeAdhocReservationSlice);
        pieChart.getData().add(parkedPassSlice);
        pieChart.getData().add(freePassSlice);

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
            double adhocReservationCars            = (model.getAdhocReservationSection().getFilledspots()/size*100);
            double passCars             = (model.getPassSection().getFilledspots()/size*100);


            double freeAdhocReservationSpots = (model.getAdhocReservationSection().getFreeSpots()/size*100);
            double freePassSpots = (model.getPassSection().getFreeSpots()/size*100);


            Platform.runLater(new Runnable() {
                @Override
                public void run() {


                    parkedAdhocReservationSlice.setPieValue(adhocReservationCars);
                    freeAdhocReservationSlice.setPieValue(freeAdhocReservationSpots);

                    parkedPassSlice.setPieValue((passCars));
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

