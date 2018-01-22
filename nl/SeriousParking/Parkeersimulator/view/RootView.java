package nl.SeriousParking.Parkeersimulator.view;


import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import nl.SeriousParking.Parkeersimulator.controller.SettingsController;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.SettingHandler;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class RootView {



    public void RootView(Stage primaryStage) {
        primaryStage.setTitle("SeriousParking Parkeersimulator");

        SplitPane splitPane = new SplitPane();

        Scene scene = new Scene(splitPane,1500,900);

        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.prefWidthProperty().bind(scene.widthProperty());
        splitPane.prefHeightProperty().bind(scene.heightProperty());

        Simulator model                 = new Simulator();
        SimulatorController controller  = new SimulatorController(model);
        SimView view                    = new SimView(controller, model);

        SettingHandler handler                  = new SettingHandler();
        SettingsController settingscontroller   = new SettingsController(handler);
        SimSettings simsettings                 = new SimSettings(settingscontroller, handler);

        ScrollPane scrollPane = new ScrollPane(simsettings);
        scrollPane.setFitToHeight(true);

        PieChartView piechart = new PieChartView(controller, model);

        SimData legend = new SimData(controller, model);

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
        SimSettings.setContent(scrollPane);
        tabPane.getTabs().add(SimSettings);

        borderPane.setLeft(view);
        borderPane.setCenter(legend);
        borderPane.setRight(piechart);
        borderPane.setBottom(toolBar);
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        splitPane.getItems().addAll(borderPane,tabPane);

        primaryStage.setScene(scene);
        scene.getStylesheets().add("Chart.css");

        primaryStage.show();
    }
}
