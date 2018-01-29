package nl.SeriousParking.Parkeersimulator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import nl.SeriousParking.Parkeersimulator.view.RootView;


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