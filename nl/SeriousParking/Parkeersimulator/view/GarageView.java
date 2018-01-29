package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Insets;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.*;
import static javafx.scene.paint.Color.rgb;


public class GarageView extends View<SimulatorController,Simulator> {
   private Rectangle[][][] PassSpots;
   private Rectangle[][][] AdhocReservationSpots;





     GarageView(SimulatorController controller, Simulator model) {
        super(controller, model);
        HBox container;
        container = new HBox();

        container.setPadding(new Insets(15, 12, 15, 12));
        container.setSpacing(40);

        AdhocReservationSpots=createTable(SettingHandler.getAdhocReservationFloors(),SettingHandler.getAdhocReservationRows(),SettingHandler.getAdhocReservationplaces(),container);
        PassSpots=createTable(SettingHandler.getPassFloors(),SettingHandler.getPassRows(),SettingHandler.getPassplaces(),container);
        this.getChildren().add(container);
        model.addView(this);

    }

    @Override
    public void update() {
        drawAdhocReservationSpots();
        drawPassSpots();
        //drawReservationSpots();


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
