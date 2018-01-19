package nl.SeriousParking.Parkeersimulator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;
import nl.SeriousParking.Parkeersimulator.view.RootView;
import nl.SeriousParking.Parkeersimulator.view.SimView;

/**
 * Parkeersimulator
 * @author Berwout Kruit, Jeroen van Wyck, Laurens Wijnsma.
 * @version 1.0.0
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        RootView main =new RootView();
        main.RootView(primaryStage);


        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
}