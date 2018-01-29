package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import nl.SeriousParking.Parkeersimulator.controller.SettingsController;
import nl.SeriousParking.Parkeersimulator.model.SettingHandler;



public class SimSettings extends View<SettingsController, SettingHandler> {

    private TextField[] input;
    private GridPane container;


    private Label   tickspeedLbl;
    private Label   entranceSpeedLbl;
    private Label   exitSpeedLbl;
    private Label   PaymentSpeedLbl;
    private Label   weekArrivalsLbl;
    private Label   weekendArrivalsLbl;
    private Label   getWeekArrivalspassLbl;
    private Label   getWeekendArrivalspassLbl;
    private Label   chanceLbl;
    private Label   floorLbl;
    private Label   rowLbl;
    private Label   placeLbl;
    private Button  saveButton;
    private Button  defaultButton;
    private CheckBox checkbox;
    private final int     numberOfElements =16;

    public SimSettings(SettingsController settingscontroller, SettingHandler model) {
        super(settingscontroller, model);

        container = new GridPane();
        container.getRowConstraints().add(new RowConstraints(100)); // 1st Row is 100 wide
        container.getColumnConstraints().add(new ColumnConstraints(100)); // 1st column 100 wide
        this.getChildren().add(container);
        input= new TextField[numberOfElements];
        create();
    }

    @Override
    public void update() {
        updateValues();
    }

       private void updateValues(){
        input[0].setText(""+SettingHandler.getTickPause());
        input[1].setText(""+SettingHandler.getEnterSpeed());
        input[2].setText(""+SettingHandler.getExitSpeed());
        input[3].setText(""+SettingHandler.getPaymentSpeed());
        input[4].setText(""+SettingHandler.getWeekDayArrivals());
        input[5].setText(""+SettingHandler.getWeekendArrivals());
        input[6].setText(""+SettingHandler.getWeekDayPassArrivals());
        input[7].setText(""+SettingHandler.getWeekendPassArrivals());
        input[8].setText(""+SettingHandler.getChance());

    }

    private void create(){


        //////////////////////////////////////////////////////////////////////////////////////////////////
        tickspeedLbl = new Label();
        tickspeedLbl.setText("Tickspeed");
        container.setConstraints(tickspeedLbl,1,1);
        container.getChildren().add(tickspeedLbl);

        input[0] = new TextField();
        input[0].setText(""+model.getTickPause());
        container.setConstraints(input[0],3,1);
        container.getChildren().add(input[0]);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        entranceSpeedLbl = new Label();
        entranceSpeedLbl.setText("Car entrance speed");
        container.setConstraints(entranceSpeedLbl,1,2);
        container.getChildren().add(entranceSpeedLbl);

        input[1] = new TextField();
        input[1].setText(""+model.getEnterSpeed());
        container.setConstraints(input[1],3,2);
        container.getChildren().add(input[1]);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        exitSpeedLbl = new Label();
        exitSpeedLbl.setText("Car Exit speed");
        container.setConstraints(exitSpeedLbl,1,3);
        container.getChildren().add(exitSpeedLbl);

        input[2] = new TextField();
        input[2].setText(""+model.getExitSpeed());
        container.setConstraints(input[2],3,3);
        container.getChildren().add(input[2]);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        PaymentSpeedLbl = new Label();
        PaymentSpeedLbl.setText("Car Payment speed");
        container.setConstraints(PaymentSpeedLbl,1,4);
        container.getChildren().add(PaymentSpeedLbl);

        input[3] = new TextField();
        input[3].setText(""+model.getPaymentSpeed());
        container.setConstraints(input[3],3,4);
        container.getChildren().add(input[3]);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        weekArrivalsLbl = new Label();
        weekArrivalsLbl.setText("Avarage weekday without pass");
        container.setConstraints(weekArrivalsLbl,1,5);
        container.getChildren().add(weekArrivalsLbl);

        input[4] = new TextField();
        input[4].setText(""+model.getWeekDayArrivals());
        container.setConstraints(input[4],3,5);
        container.getChildren().add(input[4]);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        weekendArrivalsLbl = new Label();
        weekendArrivalsLbl.setText("Avarage weekend without pass");
        container.setConstraints(weekendArrivalsLbl,1,6);
        container.getChildren().add(weekendArrivalsLbl);

        input[5] = new TextField();
        input[5].setText(""+model.getWeekendArrivals());
        container.setConstraints(input[5],3,6);
        container.getChildren().add(input[5]);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        getWeekArrivalspassLbl = new Label();
        getWeekArrivalspassLbl.setText("Avarage week with pass");
        container.setConstraints(getWeekArrivalspassLbl,1,7);
        container.getChildren().add(getWeekArrivalspassLbl);

        input[6] = new TextField();
        input[6].setText(""+model.getWeekDayPassArrivals());
        container.setConstraints(input[6],3,7);
        container.getChildren().add(input[6]);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        getWeekendArrivalspassLbl = new Label();
        getWeekendArrivalspassLbl.setText("Avarage weekend with pass");
        container.setConstraints(getWeekendArrivalspassLbl,1,8);
        container.getChildren().add(getWeekendArrivalspassLbl);

        input[7] = new TextField();
        input[7].setText(""+model.getWeekendPassArrivals());
        container.setConstraints(input[7],3,8);
        container.getChildren().add(input[7]);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        chanceLbl = new Label();
        chanceLbl.setText("the Chance of double parking");
        container.setConstraints(chanceLbl,1,9);
        container.getChildren().add(chanceLbl);

        input[8] = new TextField();
        input[8].setText(""+model.getChance());
        container.setConstraints(input[8],3,9);
        container.getChildren().add(input[8]);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        floorLbl = new Label();
        floorLbl.setText("Number of floors");
        container.setConstraints(floorLbl,1,10);
        container.getChildren().add(floorLbl);

        input[9] = new TextField();

        container.setConstraints(input[9],3,10);
        container.getChildren().add(input[9]);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        rowLbl = new Label();
        rowLbl.setText("Number of rows");
        container.setConstraints(rowLbl,1,11);
        container.getChildren().add(rowLbl);

        input[10] = new TextField();

        container.setConstraints(input[10],3,11);
        container.getChildren().add(input[10]);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        placeLbl = new Label();
        placeLbl.setText("Number of places");
        container.setConstraints(placeLbl,1,12);
        container.getChildren().add(placeLbl);

        input[11] = new TextField();

        container.setConstraints(input[11],3,12);
        container.getChildren().add(input[11]);
        //////////////////////////////////////////////////////////////////////////////////////////////////
        checkbox = new CheckBox("Opposite entrances");
        checkbox.setIndeterminate(false);
        container.setConstraints(checkbox,1,13);
        container.getChildren().add(checkbox);

        //////////////////////////////Buttons/////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////
        defaultButton = new Button();
        defaultButton.setText("Default");
        container.setConstraints(defaultButton,1,14);
        container.getChildren().add(defaultButton);


        defaultButton.setOnAction(e -> {
            controller.setDefault();


        });

        model.addView(this);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        saveButton = new Button();
        saveButton.setText("Save");
        container.setConstraints(saveButton,3,14);
        container.getChildren().add(saveButton);


        saveButton.setOnAction(e -> {
            String[] content = new String[numberOfElements];
            int i=0;
            for(TextField textField: input){
                if(textField!=null) {
                    content[i] = textField.getText();
                    i++;
                }
                else{
                    break;
                }


            }
            controller.Save(content);
            controller.doubleEntrance(checkbox.isSelected());


        });

        model.addView(this);
        //////////////////////////////////////////////////////////////////////////////////////////////////

    }
}