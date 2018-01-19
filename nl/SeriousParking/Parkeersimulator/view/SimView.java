package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Car;
import nl.SeriousParking.Parkeersimulator.model.Location;
import nl.SeriousParking.Parkeersimulator.model.Simulator;



public class SimView extends View<SimulatorController, Simulator> {
    Rectangle[][][]garage;

    private HBox container= new HBox();


    public  SimView(SimulatorController controller, Simulator model) {
        super(controller, model);

        BorderPane borderPane = new BorderPane();

        container.setPadding(new Insets(15, 12, 15, 12));
        container.setSpacing(40);

        CreateTable();

        Button start =new Button("Start/Stop");
        start.setOnAction(e -> {
            controller.startSimulator();
        });


        Button reset =new Button("Reset");
        reset.setOnAction(e -> {
            controller.resetSimulator();
        });


        ToolBar toolBar = new ToolBar();
        toolBar.getItems().addAll(start,reset);
        
        borderPane.setCenter(container);
        borderPane.setBottom(toolBar);

        this.getChildren().add(borderPane);
        model.addView(this);

    }


    @Override
    public void update() {
        draw();

    }
    private void draw(){

        for (int floor = 0; floor < model.getNumberOfFloors(); floor++) {
            for (int row = 0; row < model.getNumberOfRows(); row++) {
                for (int place = 0; place < model.getNumberOfPlaces(); place++) {
                    Car car = model.getCarAt(new Location(floor, row, place));
                    if (car != null) {
                        if (car.getHasToPay()){
                            garage[floor][row][place].setFill(Color.DARKBLUE);
                        }
                        else{
                            garage[floor][row][place].setFill(Color.GREEN);
                        }
                    }
                    else{
                        garage[floor][row][place].setFill(Color.ANTIQUEWHITE);
                    }
                }
            }
        }

    }

    private void CreateTable(){
     garage = new Rectangle[model.getNumberOfFloors()][model.getNumberOfRows()][model.getNumberOfPlaces()];
        for (int floor = 0; floor < model.getNumberOfFloors(); floor++) {
            HBox floorContainer= new HBox();
            floorContainer.setSpacing(5);

            container.getChildren().add(floorContainer);
            for (int row = 0; row < model.getNumberOfRows(); row++) {
                VBox rowContainer=new VBox();
                rowContainer.setSpacing(10);
                floorContainer.getChildren().add(rowContainer);
                for (int place = 0; place < model.getNumberOfPlaces(); place++) {
                    garage[floor][row][place]=new Rectangle();
                    garage[floor][row][place].setWidth(30);
                    garage[floor][row][place].setHeight(20);
                    garage[floor][row][place].setArcWidth(5);
                    garage[floor][row][place].setArcHeight(5);
                    garage[floor][row][place].setFill(Color.ANTIQUEWHITE);
                    rowContainer.getChildren().add(garage[floor][row][place]);



                    }
                }
            }
        }





}