package nl.SeriousParking.Parkeersimulator.view;

import javafx.application.Platform;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import nl.SeriousParking.Parkeersimulator.controller.RuntimeController;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.*;
import nl.SeriousParking.Parkeersimulator.model.Runtime;

import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.rgb;


public class GarageView extends View<SimulatorController,Simulator> {
    private Rectangle[][][] PassSpots;
    private Rectangle[][][] AdhocReservationSpots;
    private ProgressBar     abbores;
    private ProgressBar     adhoc;

    GarageView(SimulatorController controller, Simulator model) {
        super(controller, model);
        BorderPane sections = new BorderPane();
        HBox container  = new HBox();
        VBox root       = new VBox();
        FlowPane barrs  = new FlowPane();
        abbores         = new ProgressBar();
        adhoc           = new ProgressBar();

        container.setPadding(new Insets(15, 12, 15, 12));
        container.setSpacing(40);
        root.setId("garage_container");

        AdhocReservationSpots   = createTable(SettingHandler.getAdhocReservationFloors(),SettingHandler.getAdhocReservationRows(),SettingHandler.getAdhocReservationplaces(),container);
        PassSpots               = createTable(SettingHandler.getPassFloors(),SettingHandler.getPassRows(),SettingHandler.getPassplaces(),container);

        abbores.setProgress(0);
        abbores.setStyle("-fx-control-inner-background:#0dd0e2; " +
                "-fx-accent:#006494;");
        abbores.setPrefSize(200, 30);

        adhoc.setProgress(0);
        adhoc.setStyle("-fx-control-inner-background:#b5e28c; " +
                "-fx-accent:#5b8e7d; ");

        adhoc.setPrefSize(200, 30);

        Runtime runtime                     = new Runtime();
        RuntimeController runtimeController = new RuntimeController(runtime);
        RuntimeView runtimeView             = new RuntimeView(runtimeController, runtime);


        Button start            = new Button("Start/Stop");
        Button reset            = new Button("Reset");
        Button tick             = new Button("single tick");
        Button tick100          = new Button("tick +100");
        start.setDefaultButton(true);
        reset.setCancelButton(true);
        ToolBar toolBar         = new ToolBar();


        //Button action handles.
        start.setOnAction(e -> {

            controller.startSimulator();
        });

        reset.setOnAction(e -> {
            controller.resetSimulator();
        });

        tick.setOnAction(e -> {
            controller.tick();
        });


        tick100.setOnAction(e -> {
            controller.tickMany(100);
        });


        toolBar.getItems().addAll(start,reset,tick,tick100, runtimeView);

        barrs.getChildren().addAll(adhoc,abbores);
        barrs.setHgap(100);
        root.getChildren().addAll(container,barrs);
        sections.setBottom(toolBar);
        sections.setCenter(root);
        this.getChildren().addAll(sections);
        model.addView(this);
    }

    @Override
    public void update() {
        drawAdhocReservationSpots();
        drawPassSpots();
        //drawReservationSpots();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                abbores.setProgress(Garage.getNumberCarsInPassQueue());
                adhoc.setProgress(Garage.getNumberCarsInAdhocQueue()/100);
            }
        });
    }

    private Rectangle[][][] createTable(int floors, int rows, int places, HBox root) {
        Rectangle[][][] table = new Rectangle[floors][rows][places];

        for (int floor = 0; floor < floors; floor++) {
            HBox floorContainer = new HBox();
            floorContainer.setSpacing(4);
            root.getChildren().add(floorContainer);

            for (int row = 0; row < rows; row++) {
                VBox rowContainer = new VBox();
                rowContainer.setSpacing(6);
                floorContainer.getChildren().add(rowContainer);

                for (int place = 0; place < places; place++) {
                    table[floor][row][place] = new Rectangle();
                    table[floor][row][place].setWidth(18);
                    table[floor][row][place].setStrokeWidth(2);
                    table[floor][row][place].setHeight(8);
                    table[floor][row][place].setArcWidth(2);
                    table[floor][row][place].setArcHeight(2);
                    table[floor][row][place].setFill(rgb(105, 110, 120));
                    table[floor][row][place].setStroke(rgb(105, 110, 120));
                    rowContainer.getChildren().add(table[floor][row][place]);
                }
            }
        }

        return table;
    }

    private void drawAdhocReservationSpots() {

        for (int floor = 0; floor < model.getAdhocReservationSection().getFloors(); floor++) {

            for (int row = 0; row < model.getAdhocReservationSection().getRows(); row++) {

                for (int place = 0; place < model.getAdhocReservationSection().getPlaces(); place++) {
                    Car car = model.getAdhocReservationSection().getCarAt(new Location(floor, row, place));

                    if (car instanceof AdhocCar) {
                        if (car.isParkedDouble()){
                            AdhocReservationSpots[floor][row][place].setStroke(rgb(163, 62, 67));
                            AdhocReservationSpots[floor][row][place].setFill(rgb(91, 142, 125));
                        } else {
                            AdhocReservationSpots[floor][row][place].setFill(rgb(91, 142, 125));
                            AdhocReservationSpots[floor][row][place].setStroke(rgb(91, 142, 125));
                        }
                    }

                    if (car instanceof ReservationCar) {
                        if (((ReservationCar) car).isActive()) {
                            AdhocReservationSpots[floor][row][place].setFill(rgb(244, 162, 89));
                            AdhocReservationSpots[floor][row][place].setStroke(rgb(244, 162, 89));
                        } else {
                            AdhocReservationSpots[floor][row][place].setFill(rgb(244, 226, 133));
                            AdhocReservationSpots[floor][row][place].setStroke(rgb(244, 226, 133));

                        }
                    }

                    if (car == null) {
                        AdhocReservationSpots[floor][row][place].setFill(rgb(181, 226, 140));
                        AdhocReservationSpots[floor][row][place].setStroke(rgb(181, 226, 140));
                    }
                }
            }
        }
    }

    private void drawPassSpots() {
        for (int floor = 0; floor < SettingHandler.getPassFloors(); floor++) {

            for (int row = 0; row < SettingHandler.getPassRows(); row++) {

                for (int place = 0; place < SettingHandler.getPassplaces(); place++) {
                    Car car = model.getPassSection().getCarAt(new Location(floor, row, place));

                    if (car instanceof PassCar) {
                        if (car.isParkedDouble()){
                            PassSpots[floor][row][place].setStroke(rgb(163, 62, 67));
                            PassSpots[floor][row][place].setFill(rgb(0, 100, 148));
                        } else {
                            PassSpots[floor][row][place].setFill(rgb(0, 100, 148));
                            PassSpots[floor][row][place].setStroke(rgb(0, 100, 148));
                        }
                    }
                    if (car == null) {
                        PassSpots[floor][row][place].setFill(rgb(13, 208, 226));
                        PassSpots[floor][row][place].setStroke(rgb(13, 208, 226));
                    }

                }
            }
        }
    }



}
