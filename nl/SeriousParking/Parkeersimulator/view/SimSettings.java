package nl.SeriousParking.Parkeersimulator.view;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.*;
import nl.SeriousParking.Parkeersimulator.controller.SettingsController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class SimSettings extends View<SettingsController, Simulator> {

    private TextField[] input;
    private GridPane container;


    private Label   tickspeedLbl;
    private Label   entranceSpeedLbl;
    private Label   exitSpeedLbl;
    private Label   PaymentSpeedLbl;

    private Button  saveButton;


    public SimSettings(SettingsController settingscontroller, Simulator model) {
        super(settingscontroller, model);

        container = new GridPane();
        container.getRowConstraints().add(new RowConstraints(100)); // 1st Row is 100 wide
        container.getColumnConstraints().add(new ColumnConstraints(100)); // 1st column 100 wide
        this.getChildren().add(container);
        input= new TextField[4];

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
        saveButton = new Button();
        saveButton.setText("Save");
        container.setConstraints(saveButton,2,5);
        container.getChildren().add(saveButton);


        saveButton.setOnAction(e -> {
            String[] content = new String[4];
            int i=0;
            for(TextField textField: input){
                content[i]=textField.getText();
                i++;
            }
            controller.Save(content);

        });


        //////////////////////////////////////////////////////////////////////////////////////////////////



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


    }
}