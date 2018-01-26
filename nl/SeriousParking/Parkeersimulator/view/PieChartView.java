package nl.SeriousParking.Parkeersimulator.view;

import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class PieChartView extends View<SimulatorController, Simulator> implements Runnable {
    PieChart.Data carSlice;
    PieChart.Data empty;
    PieChart.Data dubbelpSlice;
    PieChart.Data gereserveerdSlice;
    PieChart.Data VrijePlekkenSlice;
    double percentofCars;
    boolean run = false;

    public PieChartView(SimulatorController controller, Simulator model) {
        super(controller, model);

        PieChart pieChart   = new PieChart();
        percentofCars       = 100;
        carSlice            = new PieChart.Data("Auto's Geparkeerd", 30);
        empty               = new PieChart.Data("", 0);
        dubbelpSlice        = new PieChart.Data("Dubbelparkeerder", 10);
        gereserveerdSlice   = new PieChart.Data("Gereserveerd", 10);
        VrijePlekkenSlice   = new PieChart.Data("Vrije Plekken", 10);

        pieChart.getData().add(carSlice);
        pieChart.getData().add(empty);
        pieChart.getData().add(dubbelpSlice);
        pieChart.getData().add(gereserveerdSlice);
        pieChart.getData().add(VrijePlekkenSlice);

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
        while (false && run) {
            Double adhocCars    = model.getNumberOfAddhoccarsinPark();
            Double passCars     = model.getNumberOfPasscarsinPark();
            Double percentCars  = ((adhocCars + passCars) / 100) * 100;

            Double dubbleCars   = model.getNumberOfCarsParkedDouble();
            Double reservedCars = 0.0;
            Double freeSpots    = model.getNumberOfOpenSpots();

            carSlice.setPieValue(percentCars);
            dubbelpSlice.setPieValue(dubbleCars);
            gereserveerdSlice.setPieValue(reservedCars);
            VrijePlekkenSlice.setPieValue(freeSpots);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

