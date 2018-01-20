package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class RootView {
    private static final int NUMFLOORS  = 3;
    private static final int NUMROWS    = 3;
    private static final int NUMPLACES  = 15;

    public void RootView(Stage primaryStage) {
        primaryStage.setTitle("SeriousParking Parkeersimulator");

        Group root  = new Group();
        Scene scene = new Scene(root, 650, 400, Color.WHITE);

        Simulator model                 = new Simulator(NUMFLOORS, NUMROWS, NUMPLACES);
        SimulatorController controller  = new SimulatorController(model);
        SimView view                    = new SimView(controller, model);


        TabPane tabPane         = new TabPane();
        BorderPane borderPane   = new BorderPane();

        Tab parkinglot = new Tab();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        parkinglot.setText("parkinglot");
        parkinglot.setContent(view);
        tabPane.getTabs().add(parkinglot);


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
