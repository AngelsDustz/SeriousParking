package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class PieChartView extends View<SimulatorController, Simulator> {
    PieChart.Data carSlice;
    PieChart.Data dubbelpSlice;
    PieChart.Data gereserveerdSlice;
    PieChart.Data VrijePlekkenSlice;
    double percentofCars;

    public PieChartView(SimulatorController controller, Simulator model) {
        super(controller, model);

        PieChart pieChart = new PieChart();

        percentofCars=100;
        carSlice = new PieChart.Data("Auto's Geparkeerd", ((model.getNumberOfPasscarsinPark()+model.getNumberOfAddhoccarsinPark())/percentofCars*100));
        dubbelpSlice = new PieChart.Data("Dubbelparkeerder", (model.getNumberOfCarsParkedDouble()/percentofCars*100));
        VrijePlekkenSlice = new PieChart.Data("Vrije Plekken", (model.getNumberOfOpenSpots()/percentofCars*100));
        gereserveerdSlice= new PieChart.Data("Gereserveerd", 0);

        pieChart.getData().add(carSlice);
        pieChart.getData().add(gereserveerdSlice);
        pieChart.getData().add(VrijePlekkenSlice);
        pieChart.getData().add(dubbelpSlice);



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
