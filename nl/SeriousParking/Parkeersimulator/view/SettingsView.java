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

import java.util.HashMap;


public class SettingsView extends View<SettingsController, SettingHandler> {

    private GridPane container;

    private Label       lbl_speed = new Label();
    private Label       lbl_tick = new Label();
    private TextField   fld_tick = new TextField();
    private Label       lbl_in = new Label();
    private TextField   fld_in = new TextField();
    private Label       lbl_out = new Label();
    private TextField   fld_out = new TextField();
    private Label       lbl_pay = new Label();
    private TextField   fld_pay = new TextField();
    private Label       lbl_wee = new Label();
    private Label       lbl_wopasswee = new Label();
    private TextField   fld_wopasswee = new TextField();
    private Label       lbl_wipasswee = new Label();
    private TextField   fld_wipasswee = new TextField();
    private Label       lbl_reswee = new Label();
    private TextField   fld_reswee = new TextField();
    private Label       lbl_wed = new Label();
    private Label       lbl_wopasswed = new Label();
    private TextField   fld_wopasswed = new TextField();
    private Label       lbl_wipasswed = new Label();
    private TextField   fld_wipasswed = new TextField();
    private Label       lbl_reswed   = new Label();
    private TextField   fld_reswed = new TextField();
    private Label       lbl_gar = new Label();
    private Label       lbl_queue = new Label();
    private TextField   fld_queue = new TextField();
    private Label       lbl_troug = new Label();
    private TextField   fld_troug = new TextField();
    private Label       lbl_dtexit = new Label();
    private TextField   fld_dtexit = new TextField();
    private Label       lbl_opp  = new Label();
    private CheckBox    oppbox = new CheckBox();


    private Button  saveButton = new Button();
    private Button  defaultButton = new Button();


    public SettingsView(SettingsController settingscontroller, SettingHandler model) {
        super(settingscontroller, model);

        container = new GridPane();

        container.getRowConstraints().add(new RowConstraints(100));
        container.getColumnConstraints().add(new ColumnConstraints(100));
        container.setHgap(10);
        container.setVgap(2);

        lbl_speed.setText("Speed");
        lbl_speed.setId("set_lbl");
        lbl_tick.setText("tick speed");
        lbl_in.setText("entrance speed");
        lbl_out.setText("exit speed");
        lbl_pay.setText("paymentspeed");

        lbl_wee.setText("Weekday");
        lbl_wee.setId("set_lbl");
        lbl_wopasswee.setText("Without pass");
        lbl_wipasswee.setText("With pass");
        lbl_reswee.setText("Reserveringen");

        lbl_wed.setText("Weekend");
        lbl_wed.setId("set_lbl");
        lbl_wopasswed.setText("Without pass");
        lbl_wipasswed.setText("With pass");
        lbl_reswed.setText("Reserveringen");

        lbl_gar.setText("Garage");
        lbl_gar.setId("set_lbl");
        lbl_queue.setText("Queue size");
        lbl_troug.setText("drive trough");
        lbl_dtexit.setText("drive exit");
        lbl_opp.setText("opposite entrances");

        saveButton.setText("save");
        defaultButton.setText("default");

        container.add(lbl_speed,1,0);
        container.add(lbl_tick,1,1);
        container.add(lbl_in,1,2);
        container.add(lbl_out,1,3);
        container.add(lbl_pay,1,4);

        container.add(fld_tick,2,1);
        container.add(fld_in,2,2);
        container.add(fld_out,2,3);
        container.add(fld_pay,2,4);

        container.add(lbl_wee,4,0);
        container.add(lbl_wopasswee,4,1);
        container.add(lbl_wipasswee,4,2);
        container.add(lbl_reswee,4,3);

        container.add(fld_wopasswee,5,1);
        container.add(fld_wipasswee,5,2);
        container.add(fld_reswee,5,3);

        container.add(lbl_wed,7,0);
        container.add(lbl_wopasswed,7,1);
        container.add(lbl_wipasswed,7,2);
        container.add(lbl_reswed,7,3);

        container.add(fld_wopasswed,8,1);
        container.add(fld_wipasswed,8,2);
        container.add(fld_reswed,8,3);

        container.add(lbl_gar,10,0);
        container.add(lbl_queue,10,1);
        container.add(lbl_troug,10,2);
        container.add(lbl_dtexit,10,3);
        container.add(lbl_opp,10,4);

        container.add(fld_queue,11,1);
        container.add(fld_troug,11,2);
        container.add(fld_dtexit,11,3);
        container.add(oppbox,11,4);

        container.add(saveButton,1,6);
        container.add(defaultButton,2,6);

        this.getChildren().add(container);
    }

    @Override
    public void update() {
        updateValues();
    }

       private void updateValues(){
//...
    }


}