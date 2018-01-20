package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class RootView {
    private static final int NUMFLOORS  = 3;
    private static final int NUMROWS    = 6;
    private static final int NUMPLACES  = 20;

    public void RootView(Stage primaryStage) {
        primaryStage.setTitle("SeriousParking Parkeersimulator");

        Group root  = new Group();
        Scene scene = new Scene(root, 650, 500, Color.WHITE);

        Simulator model                 = new Simulator(NUMFLOORS, NUMROWS, NUMPLACES);
        SimulatorController controller  = new SimulatorController(model);
        SimView view                    = new SimView(controller, model);

        TabPane tabPane         = new TabPane();
        BorderPane borderPane   = new BorderPane();
        Button start            = new Button("Start/Stop");
        Button reset            = new Button("Reset");
        ToolBar toolBar         = new ToolBar();

        start.setOnAction(e -> {
            controller.startSimulator();
        });

        reset.setOnAction(e -> {
            controller.resetSimulator();
        });


        toolBar.getItems().addAll(start,reset);

        Tab parkinglot = new Tab();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        parkinglot.setText("parkinglot");
        parkinglot.setContent(view);
        tabPane.getTabs().add(parkinglot);

        Tab parkinglot1 = new Tab();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        parkinglot1.setText("parkinglot");
        parkinglot1.setContent(view);
        tabPane.getTabs().add(parkinglot1);

        borderPane.setBottom(toolBar);

        // add tab pane
        borderPane.setCenter(tabPane);
        // bind to take available space
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        // add border Pane
        root.getChildren().add(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
