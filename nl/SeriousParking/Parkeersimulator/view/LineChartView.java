package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.HashMap;

public class LineChartView {
    final NumberAxis xAxis;
    final NumberAxis yAxis;
    final LineChart<Number, Number> lineChart;
    protected XYChart.Series series;
    protected Integer interval = 0;
    protected Integer lastHour = 0;

    public LineChartView() {
        xAxis       = new NumberAxis();
        yAxis       = new NumberAxis();
        lineChart   = new LineChart<Number, Number>(xAxis, yAxis);
        series      = new XYChart.Series();

        lineChart.setTitle("Gemiddelde inkomen per auto");
        lineChart.setCreateSymbols(false);
        xAxis.setLabel("Auto's");
        yAxis.setLabel("Gemiddelde Winst");

        series.setName("Auto's");
        lineChart.getData().add(series);
    }

    public LineChart draw() {
        return lineChart;
    }

    public void resetChart() {
        lineChart.getData().clear();
    }

    public void addData(int hours, double profit) {
        if (interval >= 15 && lastHour != hours) {
            System.out.println("Updating graph data.");
            series.getData().add(new XYChart.Data(hours, profit));
            lastHour = hours;
            interval = 0;
        } else {
            interval++;
        }
    }
}
