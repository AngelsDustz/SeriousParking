package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import nl.SeriousParking.Parkeersimulator.controller.SettingsController;
import nl.SeriousParking.Parkeersimulator.model.SettingHandler;


public class SettingsView extends View<SettingsController, SettingHandler> {
    private GridPane container;

    //Header speed.
    private Label       lblSpeed    = new Label();

    //Tick speed.
    private Label       lblTick = new Label();
    private TextField   fldTick = new TextField();

    //Car entrance speed.
    private Label       lblCarEntranceSpeed = new Label();
    private TextField   fldCarEntranceSpeed = new TextField();

    //Car exit speed.
    private Label       lblCarExitSpeed = new Label();
    private TextField   fldCarExitSpeed = new TextField();

    //Car payment speed.
    private Label       lblCarPaySpeed = new Label();
    private TextField   fldCarPaySpeed = new TextField();

    //Weekday header.
    private Label       lblWeekday  = new Label();

    //Weekday Ad-hoc cars.
    private Label       lblWeekdayAdhocAmount   = new Label();
    private TextField   fldWeekdayAdhocAmount   = new TextField();

    //Weekday Pass cars.
    private Label       lblWeekdayPassAmount    = new Label();
    private TextField   fldWeekdayPassAmount    = new TextField();

    //Weekday reservation cars.
    private Label       lblWeekdayReservationAmount = new Label();
    private TextField   fldWeekdayReservationAmount = new TextField();

    //Weekend header.
    private Label       lblWeekend  = new Label();

    //Weekend Ad-hoc cars.
    private Label       lblWeekendAdhocAmount   = new Label();
    private TextField   fldWeekendAdhocAmount   = new TextField();

    //Weekend Pass cars.
    private Label       lblWeekendPassAmount    = new Label();
    private TextField   fldWeekendPassAmount    = new TextField();

    //Weekend reservation cars.
    private Label       lblWeekendReservationAmount = new Label();
    private TextField   fldWeekendReservationAmount = new TextField();

    //Garage header.
    private Label       lbl_gar = new Label();

    //Garage queue size.
    private Label       lblQueueSize    = new Label();
    private TextField   fldQueueSize    = new TextField();

    //Queue go through speed.
    private Label       lblQueueThroughSpeed = new Label();
    private TextField   fldQueueThroughSpeed = new TextField();

    //Opposite entrances.
    private Label       lbl_opp  = new Label();
    private CheckBox    oppbox = new CheckBox();

    //Doubleparkig chance
    private Label lbldouble = new Label();
    private TextField flddouble = new TextField();

    //ReservationShow Chance
    private Label lblreserch = new Label();
    private TextField fldreserch = new TextField();

    private Button  saveButton      = new Button();
    private Button  defaultButton   = new Button();


    public SettingsView(SettingsController settingscontroller, SettingHandler model) {
        super(settingscontroller, model);

        container = new GridPane();

        container.getRowConstraints().add(new RowConstraints(100));
        container.getColumnConstraints().add(new ColumnConstraints(100));
        container.setHgap(10);
        container.setVgap(2);

        lblSpeed.setText("Speed");
        lblSpeed.setId("set_lbl");
        lblTick.setText("tick speed");
        lblCarEntranceSpeed.setText("entrance speed");
        lblCarExitSpeed.setText("exit speed");
        lblCarPaySpeed.setText("paymentspeed");

        lblWeekday.setText("Weekday");
        lblWeekday.setId("set_lbl");
        lblWeekdayAdhocAmount.setText("Without pass");
        lblWeekdayPassAmount.setText("With pass");
        lblWeekdayReservationAmount.setText("Reserveringen");

        lblWeekend.setText("Weekend");
        lblWeekend.setId("set_lbl");
        lblWeekendAdhocAmount.setText("Without pass");
        lblWeekendPassAmount.setText("With pass");
        lblWeekendReservationAmount.setText("Reserveringen");

        lbl_gar.setText("Garage");
        lbl_gar.setId("set_lbl");
        lblQueueSize.setText("Queue size");
        lblQueueThroughSpeed.setText("drive trough");
        lbldouble.setText("Doubleparking chance");
        lblreserch.setText("Reservation show chance");
        lbl_opp.setText("opposite entrances");

        saveButton.setText("save");
        defaultButton.setText("default");

        container.add(lblSpeed,1,0);
        container.add(lblTick,1,1);
        container.add(lblCarEntranceSpeed,1,2);
        container.add(lblCarExitSpeed,1,3);
        container.add(lblCarPaySpeed,1,4);

        container.add(fldTick,2,1);
        container.add(fldCarEntranceSpeed,2,2);
        container.add(fldCarExitSpeed,2,3);
        container.add(fldCarPaySpeed,2,4);

        container.add(lblWeekday,4,0);
        container.add(lblWeekdayAdhocAmount,4,1);
        container.add(lblWeekdayPassAmount,4,2);
        container.add(lblWeekdayReservationAmount,4,3);

        container.add(fldWeekdayAdhocAmount,5,1);
        container.add(fldWeekdayPassAmount,5,2);
        container.add(fldWeekdayReservationAmount,5,3);

        container.add(lblWeekend,7,0);
        container.add(lblWeekendAdhocAmount,7,1);
        container.add(lblWeekendPassAmount,7,2);
        container.add(lblWeekendReservationAmount,7,3);

        container.add(fldWeekendAdhocAmount,8,1);
        container.add(fldWeekendPassAmount,8,2);
        container.add(fldWeekendReservationAmount,8,3);

        container.add(lbl_gar,10,0);
        container.add(lblQueueSize,10,1);
        container.add(lblQueueThroughSpeed,10,2);
        container.add(lbl_opp,10,5);
        container.add(lbldouble,10,3);
        container.add(lblreserch,10,4);

        container.add(fldQueueSize,11,1);
        container.add(fldQueueThroughSpeed,11,2);
        container.add(oppbox,11,5);
        container.add(flddouble,11,3);
        container.add(fldreserch,11,4);

        container.add(saveButton,1,6);
        container.add(defaultButton,2,6);


        saveButton.setOnAction(e -> {
            updateSettings(settingscontroller);
        });

        defaultButton.setOnAction(e -> {
            settingscontroller.defaultValues();
        });

        update();

        this.getChildren().add(container);
        model.addView(this);
    }

    @Override
    public void update() {
        fldTick.setText("" + model.getTickPause());
        fldCarEntranceSpeed.setText("" + model.getEnterSpeed());
        fldCarExitSpeed.setText("" + model.getExitSpeed());
        fldCarPaySpeed.setText("" + model.getPaymentSpeed());
        fldWeekdayAdhocAmount.setText("" + model.getWeekDayArrivals());
        fldWeekdayPassAmount.setText("" + model.getWeekDayPassArrivals());
        fldWeekdayReservationAmount.setText("" + model.getWeekDayReservations());
        fldWeekendAdhocAmount.setText("" + model.getWeekendArrivals());
        fldWeekendPassAmount.setText("" + model.getWeekendPassArrivals());
        fldWeekendReservationAmount.setText("" + model.getweekendReservations());
        fldQueueSize.setText("" + model.getMaxQueueSize());
        fldQueueThroughSpeed.setText("" + model.getDriveTroughSpeed());
        flddouble.setText(""+model.getChanseToParkDouble());
        fldreserch.setText(""+model.getReservationShowchance());
        oppbox.setSelected(model.getDoubleEntrance());

    }

    private void updateSettings(SettingsController settingscontroller) {
        try {
            settingscontroller.updateDoubleEntrance(oppbox.isSelected());
            settingscontroller.updateTickPause(Integer.parseInt(fldTick.getText()));
            settingscontroller.updateEnterSpeed(Integer.parseInt(fldCarEntranceSpeed.getText()));
            settingscontroller.updateExitSpeed(Integer.parseInt(fldCarExitSpeed.getText()));
            settingscontroller.updatePaymentSpeed(Integer.parseInt(fldCarPaySpeed.getText()));
            settingscontroller.updateWeekdayAdhocCars(Integer.parseInt(fldWeekdayAdhocAmount.getText()));
            settingscontroller.updateWeekdayPassCars(Integer.parseInt(fldWeekdayPassAmount.getText()));
            settingscontroller.updateWeekdayReservationCars(Integer.parseInt(fldWeekdayReservationAmount.getText()));
            settingscontroller.updateWeekendAdhocCars(Integer.parseInt(fldWeekendAdhocAmount.getText()));
            settingscontroller.updateWeekendPassCars(Integer.parseInt(fldWeekendPassAmount.getText()));
            settingscontroller.updateWeekendReservationCars(Integer.parseInt(fldWeekendReservationAmount.getText()));
            settingscontroller.updateGarageQueue(Integer.parseInt(fldQueueSize.getText()));
            settingscontroller.updateGarageThroughSpeed(Integer.parseInt(fldQueueThroughSpeed.getText()));
            settingscontroller.updateDoubleParkingChance(Integer.parseInt(flddouble.getText()));
            settingscontroller.updateReservationShowChance(Integer.parseInt(fldreserch.getText()));
        } catch (Exception e) {
            Dialog dialog = new Dialog();
            dialog.setHeaderText("ERROR");
            dialog.setContentText("something went Wrong \n" + e);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
            closeButton.managedProperty().bind(closeButton.visibleProperty());
            closeButton.setVisible(true);
            dialog.showAndWait();
        }


    }
}