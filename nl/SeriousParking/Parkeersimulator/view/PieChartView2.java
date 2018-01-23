package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class PieChartView2 extends View<SimulatorController, Simulator> {
    PieChart.Data abbonementSlice;
    PieChart.Data adhocSlice;
    PieChart.Data dubbelpSlice;
    PieChart.Data gereserveerdSlice;
    PieChart.Data VrijePlekkenSlice;
    double numberofCars;
    public PieChartView2(SimulatorController controller, Simulator model) {
        super(controller, model);

        PieChart pieChart = new PieChart();

        numberofCars=100;
        abbonementSlice = new PieChart.Data("Abbonoment", (model.getNumberOfPasscarsinPark()/numberofCars*100));
        adhocSlice = new PieChart.Data("AdHoc", (model.getNumberOfAddhoccarsinPark()/numberofCars*100));



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

        abbonementSlice.setPieValue((model.getNumberOfPasscarsinPark()/numberofCars*100));
        adhocSlice.setPieValue((model.getNumberOfAddhoccarsinPark()/numberofCars*100));
    }


}
