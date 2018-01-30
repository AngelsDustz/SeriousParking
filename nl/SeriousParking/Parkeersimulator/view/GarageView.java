package nl.SeriousParking.Parkeersimulator.view;

import javafx.application.Platform;
import javafx.geometry.Insets;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.*;
import static javafx.scene.paint.Color.rgb;


public class GarageView extends View<SimulatorController,Simulator> {
   private Rectangle[][][] PassSpots;
   private Rectangle[][][] AdhocReservationSpots;
    private ProgressBar abbores;
    private ProgressBar adhoc;





     GarageView(SimulatorController controller, Simulator model) {
        super(controller, model);
        HBox container = new HBox();
        VBox root = new VBox();
        FlowPane barrs = new FlowPane();

        container.setPadding(new Insets(15, 12, 15, 12));
        container.setSpacing(40);

         abbores = new ProgressBar();
         adhoc = new ProgressBar();

         AdhocReservationSpots=createTable(SettingHandler.getAdhocReservationFloors(),SettingHandler.getAdhocReservationRows(),SettingHandler.getAdhocReservationplaces(),container);
         PassSpots=createTable(SettingHandler.getPassFloors(),SettingHandler.getPassRows(),SettingHandler.getPassplaces(),container);

         abbores.setProgress(0);
         abbores.setStyle("-fx-control-inner-background: rgb(135,255,255); " +
                 "-fx-accent: rgb(53,144,255); " +
                 "-fx-text-box-border:rgb(53,144,255);");
         abbores.setPrefSize(200, 30);

         adhoc.setProgress(0);
         adhoc.setStyle("-fx-control-inner-background: rgb(150,255,170); " +
                 "-fx-accent: rgb(0,255,0); " +
                 "-fx-text-box-border: rgb(0,255,0);");
         adhoc.setPrefSize(200, 30);

         barrs.getChildren().addAll(adhoc,abbores);
         barrs.setHgap(100);
         root.getChildren().addAll(container,barrs);

         this.getChildren().addAll(root);
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
            floorContainer.setSpacing(5);
            root.getChildren().add(floorContainer);

            for (int row = 0; row < rows; row++) {
                VBox rowContainer = new VBox();
                rowContainer.setSpacing(10);
                floorContainer.getChildren().add(rowContainer);

                for (int place = 0; place < places; place++) {
                    table[floor][row][place] = new Rectangle();
                    table[floor][row][place].setWidth(18);
                    table[floor][row][place].setStrokeWidth(2);
                    table[floor][row][place].setHeight(8);
                    table[floor][row][place].setArcWidth(2);
                    table[floor][row][place].setArcHeight(2);
                    table[floor][row][place].setFill(rgb(105, 110, 120));
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
                    if(car!=null&& car.isParkedDouble()){    AdhocReservationSpots[floor][row][place].setStroke(rgb(140,0,0));}
                    if (car instanceof AdhocCar) {
                        AdhocReservationSpots[floor][row][place].setFill(rgb(0, 255, 0));
                    }
                    if (car instanceof ReservationCar) {
                        if(((ReservationCar) car).isActive()) {
                            AdhocReservationSpots[floor][row][place].setFill(rgb(255, 110, 0));

                        } else {
                            AdhocReservationSpots[floor][row][place].setFill(rgb(255, 204, 170));
                        }
                    }
                    if (car == null) {
                        AdhocReservationSpots[floor][row][place].setFill(rgb(150, 255, 170));
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
                        PassSpots[floor][row][place].setFill(rgb(53, 144, 255));
                    }
                    if (car == null) {
                        PassSpots[floor][row][place].setFill(rgb(135, 255, 255));
                    }

                }
            }
        }
    }



}
