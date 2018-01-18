package nl.SeriousParking.Parkeersimulator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.SeriousParking.Parkeersimulator.controller.EmptyController;
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
        primaryStage.setTitle("Geld wissel app");

        Simulator model = new Simulator(2,3,5);
        EmptyController controller = new EmptyController(model);
        SimView view = new SimView(controller, model);

        Scene s=new Scene(view);
        primaryStage.setScene(s);
        primaryStage.show();
    }
}