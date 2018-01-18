package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import nl.SeriousParking.Parkeersimulator.controller.EmptyController;
import nl.SeriousParking.Parkeersimulator.model.Car;
import nl.SeriousParking.Parkeersimulator.model.Location;
import nl.SeriousParking.Parkeersimulator.model.Simulator;



public class SimView extends View<EmptyController, Simulator> {
    Rectangle[][][]garage;

    private HBox container= new HBox();


    public  SimView(EmptyController controller, Simulator model) {
        super(controller, model);
        container.setSpacing(40);
        model.addView(this);
        CreateTable();
        Button knop =new Button("Start");
        knop.setOnAction(e -> {
            controller.startSimulator();
        });
        container.getChildren().add(knop);
        this.getChildren().add(container);



    }


    @Override
    public void update() {
        draw();
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
                    garage[floor][row][place].setWidth(50);
                    garage[floor][row][place].setHeight(50);
                    garage[floor][row][place].setArcWidth(5);
                    garage[floor][row][place].setArcHeight(5);
                    garage[floor][row][place].setFill(Color.ANTIQUEWHITE);
                    rowContainer.getChildren().add(garage[floor][row][place]);


                    }
                }
            }
        }




   private void draw(){

       for (int floor = 0; floor < model.getNumberOfFloors(); floor++) {
           for (int row = 0; row < model.getNumberOfRows(); row++) {
               for (int place = 0; place < model.getNumberOfPlaces(); place++) {
                   Location location = new Location(floor, row, place);
                   Car car = model.getCarAt(location);
                   if (car != null) {
                       if (car.gethasPass()==true)garage[floor][row][place].setFill(Color.GREEN);
                       else garage[floor][row][place].setFill(Color.DARKCYAN);
                   }
               }
           }
       }

    }
}