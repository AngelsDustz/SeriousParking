package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.SeriousParking.Parkeersimulator.controller.SettingsController;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.SettingHandler;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class RootView {



    public void RootView(Stage primaryStage) {
        primaryStage.setTitle("SeriousParking Parkeersimulator");

        GridPane grid           = new GridPane();
        Scene scene = new Scene(grid);

        Simulator model                 = new Simulator();
        SimulatorController controller  = new SimulatorController(model);
        SimView view                    = new SimView(controller, model);

        SettingHandler handler                  = new SettingHandler();
        SettingsController settingscontroller   = new SettingsController(handler);
        SimSettings simsettings                 = new SimSettings(settingscontroller, handler);

        PieChartView piechart = new PieChartView(controller, model);

        TextView textview  = new TextView(controller, model);


        TabPane tabPane         = new TabPane();
        BorderPane borderPane   = new BorderPane();
        Button start            = new Button("Start/Stop");
        Button reset            = new Button("Reset");
        Button tick             = new Button("single tick");
        ToolBar toolBar         = new ToolBar();

        start.setOnAction(e -> {
            controller.startSimulator();
        });

        reset.setOnAction(e -> {
            controller.resetSimulator();
        });

        tick.setOnAction(e -> {
            controller.tick();
        });


        toolBar.getItems().addAll(start,reset,tick);

        Tab TextViewTab = new Tab();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        TextViewTab.setText("Text View");
        TextViewTab.setContent(textview);
        tabPane.getTabs().add(TextViewTab);

        Tab SimSettings = new Tab();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        SimSettings.setText("Settings");
        SimSettings.setContent(simsettings);
        tabPane.getTabs().add(SimSettings);

        borderPane.setLeft(view);
        borderPane.setRight(piechart);
        borderPane.setBottom(toolBar);
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        grid.add(tabPane,0,1);
        grid.add(borderPane,0,0);


        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
