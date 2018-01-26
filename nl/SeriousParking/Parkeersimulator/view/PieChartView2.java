package nl.SeriousParking.Parkeersimulator.view;

import javafx.application.Platform;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class PieChartView2 extends View<SimulatorController, Simulator> implements Runnable {
    PieChart.Data abbonementSlice;
    PieChart.Data adhocSlice;
    double percentage;
    boolean run = false;


    public PieChartView2(SimulatorController controller, Simulator model) {
        super(controller, model);

        PieChart pieChart = new PieChart();

        percentage=100;
        abbonementSlice = new PieChart.Data("Abbonoment", (50/percentage*100));
        adhocSlice = new PieChart.Data("AdHoc", (50/percentage*100));



        pieChart.getData().add(abbonementSlice);
        pieChart.getData().add(adhocSlice);

        pieChart.setLegendVisible(false);
        pieChart.setClockwise(true);
        pieChart.setStartAngle(90);

        VBox pie = new VBox(pieChart);
        this.getChildren().add(pie);
        model.addView(this);

        new Thread(this, "PieChartView2").start();
        run = true;

    }

    @Override
    public void update() {
    }

    @Override
    public void run() {
        while (run) {
            Double adhoc    = model.getNumberOfAddhoccarsinPark()/100*100;
            Double abbo     = model.getNumberOfPasscarsinPark()/100*100;

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    abbonementSlice.setPieValue(abbo);
                    adhocSlice.setPieValue(adhoc);
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
