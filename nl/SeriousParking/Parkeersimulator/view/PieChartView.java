package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class PieChartView extends View<SimulatorController, Simulator> {
    PieChart.Data abbonementSlice;
    PieChart.Data adhocSlice;
    PieChart.Data dubbelpSlice;
    PieChart.Data gereserveerdSlice;
    PieChart.Data VrijePlekkenSlice;

    public PieChartView(SimulatorController controller, Simulator model) {
        super(controller, model);

        PieChart pieChart = new PieChart();

        abbonementSlice = new PieChart.Data("Abbonoment", model.getNumberOfPasscarsinPark());
        adhocSlice = new PieChart.Data("AdHoc", model.getNumberOfAddhoccarsinPark());
        dubbelpSlice = new PieChart.Data("Dubbelparkeerder", model.getNumberOfCarsParkedDouble());
        VrijePlekkenSlice = new PieChart.Data("Vrije Plekken", model.getNumberOfOpenSpots());
        gereserveerdSlice= new PieChart.Data("Gereserveerd", 0);

        pieChart.getData().add(abbonementSlice);
        pieChart.getData().add(adhocSlice);
        pieChart.getData().add(dubbelpSlice);
        pieChart.getData().add(gereserveerdSlice);
        pieChart.getData().add(VrijePlekkenSlice);
        pieChart.setLegendVisible(false);
        pieChart.setClockwise(true);
        pieChart.setStartAngle(90);

        VBox pie = new VBox(pieChart);
        this.getChildren().add(pie);
        model.addView(this);

    }

    @Override
    public void update() {
        abbonementSlice.setPieValue(model.getNumberOfPasscarsinPark());
        adhocSlice.setPieValue(model.getNumberOfAddhoccarsinPark());
        dubbelpSlice.setPieValue(model.getNumberOfCarsParkedDouble());
        gereserveerdSlice.setPieValue(0);
        VrijePlekkenSlice.setPieValue(model.getNumberOfOpenSpots());

    }


}
