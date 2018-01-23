package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class PieChartView2 extends View<SimulatorController, Simulator> {
    PieChart.Data abbonementSlice;
    PieChart.Data adhocSlice;

    double percentage;
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

    }

    @Override
    public void update() {

        abbonementSlice.setPieValue((model.getNumberOfPasscarsinPark()/percentage*100));
        adhocSlice.setPieValue((model.getNumberOfAddhoccarsinPark()/percentage*100));
    }


}
