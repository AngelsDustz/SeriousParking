package nl.SeriousParking.Parkeersimulator;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;
import nl.SeriousParking.Parkeersimulator.view.SimView;

/**
 * Parkeersimulator
 * @author Berwout Kruit, Jeroen van Wyck, Laurens Wijnsma.
 * @version 1.0.0
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Parkeer Simulator");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 400, Color.WHITE);

        Simulator model = new Simulator(2,5,10);
        SimulatorController controller = new SimulatorController(model);
        SimView view = new SimView(controller, model);


        TabPane tabPane = new TabPane();
        BorderPane borderPane = new BorderPane();

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