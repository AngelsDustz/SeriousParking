package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Date_time;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

import java.util.HashMap;

public class LineChartView {
    final NumberAxis xAxis;
    final NumberAxis yAxis;
    final LineChart<Number, Number> lineChart;
    protected XYChart.Series series;
    protected Integer interval = 0;
    protected Integer lastHour = 0;
    boolean run = false;

    public LineChartView() {
        xAxis       = new NumberAxis();
        yAxis       = new NumberAxis();
        lineChart   = new LineChart<Number, Number>(xAxis, yAxis);
        series      = new XYChart.Series();

        lineChart.setTitle("Gemiddelde inkomen per auto");
        lineChart.setCreateSymbols(false);
        xAxis.setLabel("Uren");
        yAxis.setLabel("Winst per uur");

        series.setName("Auto's");
        lineChart.getData().add(series);
    }

    public LineChart draw() {
        return lineChart;
    }

    public XYChart.Series getSeries() {
        return series;
    }

    public void addData(int hour, double profit) {
        series.getData().add(new XYChart.Data(hour, profit));
    }
}
