package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class PieChartView extends View<SimulatorController, Simulator> {


    public PieChartView(SimulatorController controller, Simulator model) {
        super(controller, model);

        PieChart pieChart = new PieChart();

        PieChart.Data slice1 = new PieChart.Data("Abbonoment", 213);
        PieChart.Data slice2 = new PieChart.Data("AdHoc", 67);
        PieChart.Data slice3 = new PieChart.Data("Dubbelparkeerder", 36);
        PieChart.Data slice4 = new PieChart.Data("Gereserveerd", 50);
        PieChart.Data slice5 = new PieChart.Data("Vrije Plekken", 80);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);
        pieChart.getData().add(slice4);
        pieChart.getData().add(slice5);

        VBox pie = new VBox(pieChart);
        this.getChildren().add(pie);

    }

    @Override
    public void update() {
    }


}
