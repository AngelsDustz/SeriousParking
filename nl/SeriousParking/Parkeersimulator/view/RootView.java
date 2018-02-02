package nl.SeriousParking.Parkeersimulator.view;


import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nl.SeriousParking.Parkeersimulator.controller.EventController;
import nl.SeriousParking.Parkeersimulator.controller.SettingsController;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.SettingHandler;
import nl.SeriousParking.Parkeersimulator.model.SimEvent;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

import java.util.Optional;

import static javafx.scene.paint.Color.RED;


public class RootView {

    private TextField adhocfloor;
    private TextField passreserfloor;
    private TextField places;
    private TextField rows;

    public void RootView(Stage primaryStage) {
        // Create the custom dialog.
        Dialog dialog = new Dialog();
        dialog.setTitle("Garage variabelen");
        dialog.setHeaderText("Set Garage variabelen");

        // Set the button types.
        ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.APPLY);
        ButtonType defaultButtonType = new ButtonType("Default", ButtonBar.ButtonData.FINISH);
        dialog.getDialogPane().getButtonTypes().addAll(createButtonType,defaultButtonType, ButtonType.CANCEL);

        // Create the labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        SettingHandler startmodel = new SettingHandler();
        SettingsController startcontroller = new SettingsController(startmodel);

        adhocfloor = new TextField("2");
        adhocfloor.setPromptText("adhoc verdiepingen");
        passreserfloor = new TextField("1");
        passreserfloor.setPromptText("passhouders verdiepingen");
        places = new TextField("30");
        places.setPromptText("places");
        rows = new TextField("6");
        rows.setPromptText("rows");

        Label rowsmax = new Label("max 10");
        rowsmax.setTextFill(RED);
        Label floormax = new Label("total floorsmax 6");
        floormax.setTextFill(RED);
        Label floormax2 = new Label("total floors max 6");
        floormax2.setTextFill(RED);
        Label placesmax = new Label("max 40");
        placesmax.setTextFill(RED);

        grid.add(new Label("adhoc verdiepingen:"), 0, 0);
        grid.add(adhocfloor, 1, 0);
        grid.add(floormax, 2, 0);
        grid.add(new Label("passhouders verdiepingen:"), 0, 1);
        grid.add(passreserfloor, 1, 1);
        grid.add(floormax2, 2, 1);
        grid.add(new Label("rows:"), 0, 2);
        grid.add(rows, 1, 2);
        grid.add(rowsmax, 2, 2);
        grid.add(new Label("places:"), 0, 3);
        grid.add(places, 1, 3);
        grid.add(placesmax, 2, 3);
        grid.add(new Label("if you exceed the limit values will be set to highest value"), 1, 4);

        dialog.initStyle(StageStyle.UTILITY);
        dialog.getDialogPane().setContent(grid);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.get() == createButtonType) {
            try {
                if (Integer.parseInt(rows.getText()) <= 10) {
                    if (Integer.parseInt(places.getText()) <= 40) {
                        if ((Integer.parseInt(adhocfloor.getText()) + Integer.parseInt(passreserfloor.getText())) <= 6) {
                            startcontroller.setPassRows(Integer.parseInt(rows.getText()));
                            startcontroller.setAdhocRows(Integer.parseInt(rows.getText()));
                            startcontroller.setAdhocFloors(Integer.parseInt(adhocfloor.getText()));
                            startcontroller.setPassFloors(Integer.parseInt(passreserfloor.getText()));
                            startcontroller.setPassPlaces(Integer.parseInt(places.getText()));
                            startcontroller.setadhocplaces(Integer.parseInt(places.getText()));

                            createview(primaryStage);
                        } else {
                            startcontroller.setPassRows(Integer.parseInt(rows.getText()));
                            startcontroller.setAdhocRows(Integer.parseInt(rows.getText()));
                            startcontroller.setPassFloors(3);
                            startcontroller.setAdhocFloors(3);
                            startcontroller.setPassPlaces(Integer.parseInt(places.getText()));
                            startcontroller.setadhocplaces(Integer.parseInt(places.getText()));

                            createview(primaryStage);
                        }
                    } else if ((Integer.parseInt(adhocfloor.getText()) + Integer.parseInt(passreserfloor.getText())) <= 6) {
                        startcontroller.setPassRows(Integer.parseInt(rows.getText()));
                        startcontroller.setAdhocRows(Integer.parseInt(rows.getText()));
                        startcontroller.setAdhocFloors(Integer.parseInt(adhocfloor.getText()));
                        startcontroller.setPassFloors(Integer.parseInt(passreserfloor.getText()));
                        startcontroller.setPassPlaces(40);
                        startcontroller.setadhocplaces(40);

                        createview(primaryStage);
                    } else {
                        startcontroller.setPassRows(Integer.parseInt(rows.getText()));
                        startcontroller.setAdhocRows(Integer.parseInt(rows.getText()));
                        startcontroller.setPassFloors(3);
                        startcontroller.setAdhocFloors(3);
                        startcontroller.setPassPlaces(40);
                        startcontroller.setadhocplaces(40);

                        createview(primaryStage);
                    }
                } else if (Integer.parseInt(places.getText()) <= 40) {
                    if ((Integer.parseInt(adhocfloor.getText()) + Integer.parseInt(passreserfloor.getText())) <= 6) {
                        startcontroller.setPassRows(10);
                        startcontroller.setAdhocRows(10);
                        startcontroller.setAdhocFloors(Integer.parseInt(adhocfloor.getText()));
                        startcontroller.setPassFloors(Integer.parseInt(passreserfloor.getText()));
                        startcontroller.setPassPlaces(Integer.parseInt(places.getText()));
                        startcontroller.setadhocplaces(Integer.parseInt(places.getText()));

                        createview(primaryStage);
                    } else {
                        startcontroller.setPassRows(10);
                        startcontroller.setAdhocRows(10);
                        startcontroller.setPassFloors(3);
                        startcontroller.setAdhocFloors(3);
                        startcontroller.setPassPlaces(Integer.parseInt(places.getText()));
                        startcontroller.setadhocplaces(Integer.parseInt(places.getText()));

                        createview(primaryStage);
                    }
                } else if ((Integer.parseInt(adhocfloor.getText()) + Integer.parseInt(passreserfloor.getText())) <= 6) {
                    startcontroller.setPassRows(10);
                    startcontroller.setAdhocRows(10);
                    startcontroller.setAdhocFloors(Integer.parseInt(adhocfloor.getText()));
                    startcontroller.setPassFloors(Integer.parseInt(passreserfloor.getText()));
                    startcontroller.setPassPlaces(40);
                    startcontroller.setadhocplaces(40);

                    createview(primaryStage);
                } else {
                    startcontroller.setPassRows(10);
                    startcontroller.setAdhocRows(10);
                    startcontroller.setPassFloors(3);
                    startcontroller.setAdhocFloors(3);
                    startcontroller.setPassPlaces(40);
                    startcontroller.setadhocplaces(40);

                    createview(primaryStage);
                }
            } catch (NumberFormatException e) {
                // moet mooi gemaakt worden.
                Label numerexep = new Label("no numerical value was given");
                numerexep.setTextFill(RED);
                grid.add(numerexep, 1, 5);
                dialog.showAndWait();
            }
        }
            else if (result.get() == defaultButtonType) {

                startcontroller.setPassRows(6);
                startcontroller.setAdhocRows(6);
                startcontroller.setAdhocFloors(2);
                startcontroller.setPassFloors(1);
                startcontroller.setPassPlaces(30);
                startcontroller.setadhocplaces(30);

                createview(primaryStage);
            } else if (result.get() == ButtonType.CANCEL) {
                Platform.exit();
                System.exit(0);

            }

    }


    public void createview(Stage primaryStage){
        primaryStage.getIcons().add(new Image(RootView.class.getResourceAsStream("ico.png")));;
        primaryStage.setTitle("SeriousParking Parkeersimulator");
        //Window indeling.
        SplitPane splitPanebottom   = new SplitPane();
        SplitPane splitPanetop      = new SplitPane();
        Scene scene                 = new Scene(splitPanebottom,1500,900);

        splitPanebottom.setOrientation(Orientation.VERTICAL);
        splitPanetop.setOrientation(Orientation.HORIZONTAL);

        //Model, View, Controller intitializing.

        //Simulator.
        Simulator model                     = new Simulator();
        SimulatorController controller      = new SimulatorController(model);

        //GarageView
        GarageView view                     = new GarageView(controller, model);

        //Settings
        SettingHandler handler              = new SettingHandler();
        SettingsController settingsc        = new SettingsController(handler);
        SettingsView simsettings            = new SettingsView(settingsc, handler);

        //Profit
        ProfitView profitView               = new ProfitView(controller, model);

        //Events
        EventController eventController     = new EventController(model);
        EventView eventView                 = new EventView(eventController, model);

        //Create and add events.
        SimEvent seChristmas    = new SimEvent();
        seChristmas.setTitle("Christmas");
        seChristmas.setWeek(2);
        seChristmas.setDay(1);

        SimEvent seOpeningWeek      = new SimEvent();
        seOpeningWeek.setTitle("Opening Week");
        seOpeningWeek.setWeek(1);
        seOpeningWeek.setDay(0);

        SimEvent seKoop    = new SimEvent();
        seKoop.setTitle("Koopavond");
        seKoop.setDay(2);

        eventController.addEvent(seOpeningWeek);
        eventController.addEvent(seChristmas);
        eventController.addEvent(seKoop);


        //View panes?
        PieChartView piechart   = new PieChartView(controller, model);
        PieChartView2 piechart2 = new PieChartView2(controller, model);
        TextView textview       = new TextView(controller, model);
        TabPane tabPane         = new TabPane();
        TabPane pie             = new TabPane();


        //Tabs
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        pie.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //Text view
        Tab TextViewTab = new Tab();
        TextViewTab.setText("Text View");
        TextViewTab.setContent(textview);
        tabPane.getTabs().add(TextViewTab);

        //Events
        Tab Events = new Tab();
        Events.setText("Events");
        Events.setContent(eventView);
        tabPane.getTabs().add(Events);

        //Inkomsten
        Tab ProfitTab   = new Tab();
        ProfitTab.setText("Inkomsten");
        ProfitTab.setContent(profitView);
        tabPane.getTabs().add(ProfitTab);

        //Settings
        Tab SimSettings = new Tab();
        SimSettings.setText("Settings");
        SimSettings.setContent(simsettings);
        tabPane.getTabs().add(SimSettings);

        //Bezetting
        Tab PieChart1 = new Tab();
        PieChart1.setText("Bezetting");
        PieChart1.setContent(piechart);
        pie.getTabs().add(PieChart1);

        //Adhoc/pass
        Tab PieChart2 = new Tab();
        PieChart2.setText("AdHoc/pass");
        PieChart2.setContent(piechart2);
        pie.getTabs().add(PieChart2);

        pie.maxWidthProperty().bind(splitPanetop.widthProperty().multiply(0.3));
        pie.minWidthProperty().bind(splitPanetop.widthProperty().multiply(0.15));
        tabPane.minHeightProperty().bind(splitPanetop.widthProperty().multiply(0.02));
        tabPane.maxHeightProperty().bind(splitPanebottom.heightProperty().multiply(0.3));

        splitPanetop.getItems().addAll(view,pie);
        splitPanebottom.getItems().addAll(splitPanetop,tabPane);

        primaryStage.setScene(scene);
        scene.getStylesheets().add("Style.css");
        primaryStage.show();
    }
}
