package nl.SeriousParking.Parkeersimulator.view;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import nl.SeriousParking.Parkeersimulator.controller.SettingsController;
import nl.SeriousParking.Parkeersimulator.model.SettingHandler;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

import java.util.Map;


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

    private Button  saveButton;
    private Button  defaultButton;
    private final int     numberOfElements =10;

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

    public void updateValues(){
        input[0].setText(""+model.getTickPause());
        input[1].setText(""+model.getEnterSpeed());
        input[2].setText(""+model.getExitSpeed());
        input[3].setText(""+model.getPaymentSpeed());
        input[4].setText(""+model.getWeekDayArrivals());
        input[5].setText(""+model.getWeekendArrivals());
        input[6].setText(""+model.getWeekDayPassArrivals());
        input[7].setText(""+model.getWeekendPassArrivals());
        input[8].setText(""+model.getChance());
    }

    public void create(){


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

        //////////////////////////////Buttons/////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////
        defaultButton = new Button();
        defaultButton.setText("Default");
        container.setConstraints(defaultButton,1,10);
        container.getChildren().add(defaultButton);


        defaultButton.setOnAction(e -> {
            controller.setDefault();


        });

        model.addView(this);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////
        saveButton = new Button();
        saveButton.setText("Save");
        container.setConstraints(saveButton,3,10);
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


        });

        model.addView(this);
        //////////////////////////////////////////////////////////////////////////////////////////////////

    }
}