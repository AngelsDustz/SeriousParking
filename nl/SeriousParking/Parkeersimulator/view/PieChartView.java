package nl.SeriousParking.Parkeersimulator.view;

import javafx.application.Platform;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class PieChartView extends View<SimulatorController, Simulator> implements Runnable {

    //PieChart.Data dubbelpSlice;
    PieChart.Data parkedAdhocSlice;
    PieChart.Data parkedPassSlice;
    PieChart.Data parkedReservationSlice;
    PieChart.Data freeAdhocSlice;
    PieChart.Data freePassSlice;
    PieChart.Data freeReservationSlice;
    double size;
    boolean run = false;

    public PieChartView(SimulatorController controller, Simulator model) {
        super(controller, model);

        PieChart pieChart   = new PieChart();
        size     = 500;
       // carSlice            = new PieChart.Data("Auto's Geparkeerd", 30);

        parkedAdhocSlice      = new PieChart.Data("geparkeerde ad-hoc plekken",100);
        parkedPassSlice   = new PieChart.Data("geparkeerde pashouder Plekken", 100);
        parkedReservationSlice   = new PieChart.Data("geparkeerde reserveer Plekken", 100);


        //dubbelpSlice        = new PieChart.Data("Dubbelparkeerder", 10);
        freeAdhocSlice      = new PieChart.Data("Vrije ad-hoc plekken",100);
        freePassSlice   = new PieChart.Data("Vrije pashouder Plekken", 100);
        freeReservationSlice   = new PieChart.Data("Vrije reserveer Plekken", 100);


        pieChart.getData().add(parkedAdhocSlice);
        pieChart.getData().add(freeAdhocSlice);
        pieChart.getData().add(parkedPassSlice);
        pieChart.getData().add(freePassSlice);
        pieChart.getData().add(parkedReservationSlice);
        pieChart.getData().add(freeReservationSlice);

        pieChart.setLegendVisible(false);
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
            double adhocCars            = (model.getAdhocSection().getFilledspots()/size*100);
            double passCars             = (model.getPassSection().getFilledspots()/size*100);
            double reservationCars      =(model.getReservationSection().getFilledspots()/size*100);

            double freeAdhocSpots = (model.getAdhocSection().getFreeSpots()/size*100);
            double freePassSpots = (model.getPassSection().getFreeSpots()/size*100);
            double freeReservationSpots = (model.getReservationSection().getFreeSpots()/size*100);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {


                    parkedAdhocSlice.setPieValue(adhocCars);
                    freeAdhocSlice.setPieValue(freeAdhocSpots);

                    parkedPassSlice.setPieValue((passCars));
                    freePassSlice.setPieValue(freePassSpots);

                   parkedReservationSlice.setPieValue(reservationCars);
                    freeReservationSlice.setPieValue(freeReservationSpots);
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

