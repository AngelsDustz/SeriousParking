package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Insets;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color.*;
import javafx.scene.shape.Rectangle;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.*;
import static javafx.scene.paint.Color.rgb;


public class GarageView extends View<SimulatorController,Simulator> {
    Rectangle[][][] AdhocSpots;
    Rectangle[][][] PassSpots;
    Rectangle[][][] ReservationSpots;



    HBox container;

    public GarageView(SimulatorController controller, Simulator model) {
        super(controller, model);
        container = new HBox();

        container.setPadding(new Insets(15, 12, 15, 12));
        container.setSpacing(40);

        AdhocSpots=createTable(SettingHandler.getAdhocFloors(),SettingHandler.getAdhocRows(),SettingHandler.getAdhocplaces(),container);
        PassSpots=createTable(SettingHandler.getPassFloors(),SettingHandler.getPassRows(),SettingHandler.getPassplaces(),container);
        ReservationSpots=createTable(SettingHandler.getReservationFloors(),SettingHandler.getReservationRows(),SettingHandler.getReservationplaces(),container);
        this.getChildren().add(container);
        model.addView(this);

    }

    @Override
    public void update() {
        drawAdhocSpots();
        drawPassSpots();
        drawReservationSpots();


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



    private void drawAdhocSpots() {

        for (int floor = 0; floor <SettingHandler.getAdhocFloors(); floor++) {

            for (int row = 0; row <SettingHandler.getAdhocRows(); row++) {

                for (int place = 0; place < SettingHandler.getAdhocplaces(); place++) {

                    Car car= model.getAdhocSection().getCarAt(new Location(floor,row,place));
                    if(car instanceof AdhocCar){
                        AdhocSpots[floor][row][place].setFill(rgb(0,255,0));
                    }
                    if (car ==null){
                        AdhocSpots[floor][row][place].setFill(rgb(150,255,170));
                    }

                }
            }
        }
    }
    private void drawPassSpots() {

        for (int floor = 0; floor <SettingHandler.getPassFloors(); floor++) {

            for (int row = 0; row <SettingHandler.getPassRows(); row++) {

                for (int place = 0; place < SettingHandler.getPassplaces(); place++) {

                    Car car= model.getPassSection().getCarAt(new Location(floor,row,place));
                    if(car instanceof PassCar){

                      PassSpots[floor][row][place].setFill(rgb(53,144,255));
                    }

                    if (car ==null){
                        PassSpots[floor][row][place].setFill(rgb(135,255,255));
                    }

                }
            }
        }
    }

    private void drawReservationSpots() {

        for (int floor = 0; floor <SettingHandler.getReservationFloors(); floor++) {

            for (int row = 0; row <SettingHandler.getReservationRows(); row++) {

                for (int place = 0; place < SettingHandler.getReservationplaces(); place++) {

                    Car car= model.getReservationSection().getCarAt(new Location(floor,row,place));

                    if(car instanceof ReservationCar){
                        ReservationSpots[floor][row][place].setFill(rgb(255,110,0));
                    }
                    if (car ==null){
                        ReservationSpots[floor][row][place].setFill(rgb(255,215,120));
                    }

                }
            }
        }
    }


}
