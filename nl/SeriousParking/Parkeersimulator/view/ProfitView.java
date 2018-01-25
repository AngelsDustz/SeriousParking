package nl.SeriousParking.Parkeersimulator.view;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import nl.SeriousParking.Parkeersimulator.controller.ProfitController;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Profit;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class ProfitView extends View<ProfitController, Profit> {
    private Label lProfitVal        = new Label();
    private Label lProfitHourVal    = new Label();
    private Label lDoubleLostVal    = new Label();
    protected LineChartView lineChart;

    public ProfitView(ProfitController controller, Profit model) {
        super(controller, model);

        HBox container      = new HBox();

        GridPane grid       = new GridPane();
        Label lProfit       = new Label("Winst");
        Label lPerHour      = new Label("Winst per uur");
        Label lLostDouble   = new Label("Misgelopen winst door dubbelparkeerders.");

        //grid.getColumnConstraints().add(new ColumnConstraints(200));

        grid.add(lProfit, 0, 2);
        grid.add(lProfitVal, 2, 2);

        grid.add(lPerHour, 0, 4);
        grid.add(lProfitHourVal, 2, 4);

        grid.add(lLostDouble, 0, 6);
        grid.add(lDoubleLostVal, 2, 6);

        lineChart = new LineChartView();

        container.getChildren().addAll(grid, lineChart.draw());

        this.getChildren().add(container);
        model.addView(this);
    }

    @Override
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Double dProfit  = model.getProfit();
                Double dPerHour = model.getPerHour();
                Double dLost    = model.getDoubleLost();

                lProfitVal.setText("€ " + dProfit);
                lProfitHourVal.setText("€ " + dPerHour);
                lDoubleLostVal.setText("€ " + dLost);
                lineChart.addData(model.getCars(), dPerHour);

                /*if (!model.isRun()) {
                    lineChart.resetChart();
                }*/
            }
        });
    }
}
