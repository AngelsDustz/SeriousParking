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
        while (run) {
            Double adhocCars    = model.getNumberOfAddhoccarsinPark();
            Double passCars     = model.getNumberOfPasscarsinPark();
            Double reservedCars = model.getNumberOfReservations();
            Double percentCars  = (((adhocCars + passCars)-reservedCars) / 100) * 100;

            Double dubbleCars   = model.getNumberOfCarsParkedDouble();

            Double freeSpots    = model.getNumberOfOpenSpots();

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    carSlice.setPieValue(percentCars);
                    dubbelpSlice.setPieValue(dubbleCars);
                    gereserveerdSlice.setPieValue(reservedCars);
                    VrijePlekkenSlice.setPieValue(freeSpots);
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

