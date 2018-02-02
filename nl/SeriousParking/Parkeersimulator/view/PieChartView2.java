package nl.SeriousParking.Parkeersimulator.view;

import javafx.application.Platform;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class PieChartView2 extends View<SimulatorController, Simulator> implements Runnable {
    PieChart.Data abbonementSlice;
    PieChart.Data adhocReservationSlice;
    final double SIZE   = 100;
    boolean run         = false;


    public PieChartView2(SimulatorController controller, Simulator model) {
        super(controller, model);

        PieChart pieChart       = new PieChart();
        abbonementSlice         = new PieChart.Data("Abbonoment", (33/SIZE*100));
        adhocReservationSlice   = new PieChart.Data("AdHoc", (33/SIZE*100));

        pieChart.getData().add(abbonementSlice);
        pieChart.getData().add(adhocReservationSlice);

        pieChart.setLabelsVisible(false);
        pieChart.setLegendVisible(true);
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
            double adhocRes = (model.getAdhocReservationSection().getFilledspots()/SIZE*100);
            double abbo     = (model.getPassSection().getFilledspots()/SIZE*100);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    abbonementSlice.setPieValue(abbo);
                    adhocReservationSlice.setPieValue(adhocRes);
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
