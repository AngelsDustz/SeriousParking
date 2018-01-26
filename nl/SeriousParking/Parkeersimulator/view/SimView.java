package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Car;
import nl.SeriousParking.Parkeersimulator.model.Location;
import nl.SeriousParking.Parkeersimulator.model.Simulator;



public class SimView extends View<SimulatorController, Simulator> {
    private Rectangle[][][] garage;

    private HBox container = new HBox();
    private TextField[] input;
    private final int numberOfElements =3;
    private Label floorLbl;
    private Label rowLbl;
    private Label placeLbl;
    private GridPane settings = new GridPane();
    private Button saveButton;
    private Button  defaultButton;

    public SimView(SimulatorController controller, Simulator model) {
        super(controller, model);



        container.setPadding(new Insets(15, 12, 15, 12));
        container.setSpacing(40);
        container.getChildren().add(settings);

        settings.getRowConstraints().add(new RowConstraints(100)); // 1st Row is 100 wide
        settings.getColumnConstraints().add(new ColumnConstraints(100)); // 1st column 100 wide
        input= new TextField[numberOfElements];
        create();

        this.getChildren().add(container);
        model.addView(this);

    }


    @Override
    public void update() {
        repaint();
        draw();
    }


    public void create() {

        floorLbl = new Label();
        floorLbl.setText("Number of floors");
        settings.setConstraints(floorLbl,1,10);
        settings.getChildren().add(floorLbl);

        input[0] = new TextField();
        input[0].setText(""+model.getNumberOfFloors());
        settings.setConstraints(input[0],3,10);
        settings.getChildren().add(input[0]);

        rowLbl = new Label();
        rowLbl.setText("Number of rows");
        settings.setConstraints(rowLbl,1,11);
        settings.getChildren().add(rowLbl);

        input[1] = new TextField();
        input[1].setText(""+model.getNumberOfRows());
        settings.setConstraints(input[1],3,11);
        settings.getChildren().add(input[1]);

        placeLbl = new Label();
        placeLbl.setText("Number of places");
        settings.setConstraints(placeLbl,1,12);
        settings.getChildren().add(placeLbl);

        input[2] = new TextField();
        input[2].setText(""+model.getNumberOfPlaces());
        settings.setConstraints(input[2],3,12);
        settings.getChildren().add(input[2]);

        defaultButton = new Button();
        defaultButton.setText("Default");
        settings.setConstraints(defaultButton,1,13);
        settings.getChildren().add(defaultButton);


        defaultButton.setOnAction(e -> {

         container.getChildren().clear();
         CreateTable();
        });

        model.addView(this);


        saveButton = new Button();
        saveButton.setText("Save");
        settings.setConstraints(saveButton,3,13);
        settings.getChildren().add(saveButton);


        saveButton.setOnAction(e ->{
            String[] content = new String[numberOfElements];
            int i=0;
            for(TextField textField: input) {
                if (textField != null) {
                    content[i] = textField.getText();
                    i++;
                }
            }
            controller.SaveSettings(content);
            container.getChildren().clear();
            CreateTable();

        });

        model.addView(this);
    }

    private void paintCar(int floor, int row, int place, Car car){

        if (car.getHasToPay()) {
            garage[floor][row][place].setFill(Color.DARKBLUE);
        } else {
            garage[floor][row][place].setFill(Color.DARKOLIVEGREEN);
        }
        if (car.getReservation()){
            garage[floor][row][place].setFill(Color.ORANGE);
        }
    }
    private void draw() {

        for (int floor = 0; floor < model.getNumberOfFloors(); floor++) {

            for (int row = 0; row < model.getNumberOfRows(); row++) {

                for (int place = 0; place < model.getNumberOfPlaces(); place++) {
                    Car car = model.getCarAt(new Location(floor, row, place));

                    if (car != null) {
                        if (!car.getisParkedDouble()) {
                            garage[floor][row][place].setStroke(Color.BLACK);
                            paintCar(floor, row, place, car);
                        } else {
                            garage[floor][row][place].setStroke(Color.RED);
                            paintCar(floor, row, place, car);
                        }
                    } else {
                        garage[floor][row][place].setFill(Color.ANTIQUEWHITE);
                        garage[floor][row][place].setStroke(Color.ANTIQUEWHITE);
                    }


                }
            }
        }
    }

    private void CreateTable() {
        garage = new Rectangle[model.getNumberOfFloors()][model.getNumberOfRows()][model.getNumberOfPlaces()];

        for (int floor = 0; floor < model.getNumberOfFloors(); floor++) {
            HBox floorContainer = new HBox();
            floorContainer.setSpacing(5);
            container.getChildren().add(floorContainer);

            for (int row = 0; row < model.getNumberOfRows(); row++) {
                VBox rowContainer = new VBox();
                rowContainer.setSpacing(10);
                floorContainer.getChildren().add(rowContainer);

                for (int place = 0; place < model.getNumberOfPlaces(); place++) {
                    garage[floor][row][place] = new Rectangle();
                    garage[floor][row][place].setWidth(18);
                    garage[floor][row][place].setStrokeWidth(2);
                    garage[floor][row][place].setHeight(8);
                    garage[floor][row][place].setArcWidth(2);
                    garage[floor][row][place].setArcHeight(2);
                    garage[floor][row][place].setFill(Color.ANTIQUEWHITE);
                    rowContainer.getChildren().add(garage[floor][row][place]);
                }
            }
        }
    }
    public void repaint() {

    }
}