package nl.SeriousParking.Parkeersimulator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Pair;
import nl.SeriousParking.Parkeersimulator.controller.SettingsController;
import nl.SeriousParking.Parkeersimulator.model.SettingHandler;
import nl.SeriousParking.Parkeersimulator.view.RootView;
import nl.SeriousParking.Parkeersimulator.view.TextView;
import java.util.Optional;
import java.util.concurrent.TimeoutException;


/**
 * Parkeersimulator
 * @author Berwout Kruit, Jeroen van Wyck, Laurens Wijnsma.
 * @version 1.0.0
 */


public class Main extends Application {
    private TextField adhocfloor;
    private TextField passreserfloor;
    private TextField places;
    private TextField rows;

    @Override
    public void start(Stage primaryStage) throws Exception {
        TestDialog();
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
    public void TestDialog() {
        // Create the custom dialog.
        Dialog<TextField> dialog = new Dialog<>();
        dialog.setTitle("Garage variabelen");
        dialog.setHeaderText("Set Garage variabelen");

        // Set the button types.
        ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

        // Create the labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        SettingHandler model = new SettingHandler();
        SettingsController controller = new SettingsController(model);

        adhocfloor = new TextField("2");
        adhocfloor.setPromptText("adhoc verdiepingen");
        passreserfloor = new TextField("1");
        passreserfloor.setPromptText("passhouders verdiepingen");
        places = new TextField("30");
        places.setPromptText("places");
        rows = new TextField("6");
        rows.setPromptText("rows");

        grid.add(new Label("adhoc verdiepingen:"), 0, 0);
        grid.add(adhocfloor, 1, 0);
        grid.add(new Label("passhouders verdiepingen:"), 0, 1);
        grid.add(passreserfloor, 1, 1);
        grid.add(new Label("places:"), 0, 2);
        grid.add(places, 1, 2);
        grid.add(new Label("rows:"), 0, 3);
        grid.add(rows, 1, 3);

        dialog.initStyle(StageStyle.UTILITY);
        dialog.getDialogPane().setContent(grid);

        Optional<TextField> result = dialog.showAndWait();

        if(Integer.parseInt(rows.getText())<10){
            if(Integer.parseInt(places.getText())<60){
                if((Integer.parseInt(adhocfloor.getText())+Integer.parseInt(passreserfloor.getText()))<10){
                    controller.setPassRows(Integer.parseInt(rows.getText()));
                    controller.setAdhocRows(Integer.parseInt(rows.getText()));
                    controller.setAdhocFloors(Integer.parseInt(adhocfloor.getText()));
                    controller.setPassFloors(Integer.parseInt(passreserfloor.getText()));
                    controller.setPassPlaces(Integer.parseInt(places.getText()));
                    controller.setadhocplaces(Integer.parseInt(places.getText()));
                }}}
                else{
            controller.setPassRows(6);
            controller.setAdhocRows(6);
            controller.setAdhocFloors(2);
            controller.setPassFloors(1);
            controller.setPassPlaces(30);
            controller.setadhocplaces(30);
        }

    }

}