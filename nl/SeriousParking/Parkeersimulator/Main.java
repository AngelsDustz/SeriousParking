package nl.SeriousParking.Parkeersimulator;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Parkeersimulator
 * @author Berwout Kruit, Jeroen van Wyck, Laurens Wijnsma.
 * @version 1.0.0
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene MainScene;
        Group root;
        root= new Group();
        MainScene= new Scene(root);

         root.getChildren().add(simView());

        primaryStage.setTitle("Parkeer Garage Simulator");
        primaryStage.setScene(MainScene);
        primaryStage.show();

    }
}
