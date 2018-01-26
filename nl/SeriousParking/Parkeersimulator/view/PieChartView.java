package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class PieChartView extends View<SimulatorController, Simulator> {
    PieChart.Data carSlice;
    PieChart.Data empty;
    PieChart.Data dubbelpSlice;
    PieChart.Data gereserveerdSlice;
    PieChart.Data VrijePlekkenSlice;
    double percentofCars;

    public PieChartView(SimulatorController controller, Simulator model) {
        super(controller, model);

        PieChart pieChart = new PieChart();
        //TODO kleuren refactoren
        percentofCars=100;
        carSlice = new PieChart.Data("Auto's Geparkeerd", 30);
        empty= new PieChart.Data("", 0);
        dubbelpSlice = new PieChart.Data("Dubbelparkeerder", 10);
        gereserveerdSlice= new PieChart.Data("Gereserveerd", 10);
        VrijePlekkenSlice = new PieChart.Data("Vrije Plekken", 10);

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

    }

    @Override
    public void update() {
        carSlice.setPieValue(((model.getNumberOfPasscarsinPark()+model.getNumberOfAddhoccarsinPark())/percentofCars*100));
        dubbelpSlice.setPieValue(model.getNumberOfCarsParkedDouble());
        gereserveerdSlice.setPieValue(0);
        VrijePlekkenSlice.setPieValue(model.getNumberOfOpenSpots());

    }


}
