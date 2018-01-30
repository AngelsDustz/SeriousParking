package nl.SeriousParking.Parkeersimulator.view;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Date_time;
import nl.SeriousParking.Parkeersimulator.model.Garage;
import nl.SeriousParking.Parkeersimulator.model.SettingHandler;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class TextView extends View<SimulatorController, Simulator>  {
    //Amount of ad-hoc cars.
    private Label lblTotalAdhoc = new Label("aantal ad-hoc auto's");
    private Label lblAdhoc      = new Label();

    //Amount of subscription cars.
    private Label lblTotalSubs  = new Label("Abbonoment's houders");
    private Label lblSubs       = new Label();

    //Amount of reservations
    private Label lblTotalReservations  = new Label("reserveringen");
    private Label lblReservations       = new Label();

    //Amount of cars in ad-hoc queue.
    private Label lblAdhocQueue         = new Label("Auto's Ad-hoc rij");
    private Label lblAdhocQueueAmount   = new Label();
    private Label lblAdhocLimit         = new Label();

    //Amount of cars in pass queue.
    private Label lblPassQueue          = new Label("Auto's in pashouder-gereserveerde rij");
    private Label lblPassQueueAmount    = new Label();
    private Label lblPassLimit          = new Label();

    //Amount of bad parked cars.
    private Label lblDoubleParked   = new Label("Dubbel geparkeerde auto's");
    private Label lblDoubles        = new Label();

    //Amount of free spots for ad-hoc cars.
    private Label lblFreeSpots  = new Label("vrij ad-hoc plekken");
    private Label lblFreeAmount = new Label();

    //Amount of ad-hoc cars lost due to queue.
    private Label lblAdhocMiss          = new Label("ad-hoc misgelopen");
    private Label lblAdhocMissAmount    = new Label();

    //Amount of pass cars lost due to queue.
    private Label lblPassMiss          = new Label("pass misgelopen");
    private Label lblPassMissAmount    = new Label();

    //Amount of profit made.
    private Label lblProfit         = new Label("Winst");
    private Label lblProfitValue    = new Label();

    public  TextView(SimulatorController controller, Simulator model) {
        super(controller, model);

        GridPane grid       = new GridPane();

        grid.getColumnConstraints().add(new ColumnConstraints(250));
        grid.getRowConstraints().add(new RowConstraints(50));

        grid.add(lblTotalAdhoc,1,5);
        grid.add(lblAdhoc,2,5);

        grid.add(lblTotalSubs,1,6);
        grid.add(lblSubs,2,6);

        grid.add(lblTotalReservations,1,7);
        grid.add(lblReservations,2,7);

        grid.add(lblDoubleParked,1,8);
        grid.add(lblDoubles,2,8);

        grid.add(lblFreeSpots,1,10);
        grid.add(lblFreeAmount,2,10);

        grid.add(lblAdhocQueue,1,11);
        grid.add(lblAdhocQueueAmount,2,11);
        grid.add(lblAdhocLimit, 4, 11);

        grid.add(lblPassQueue,1,12);
        grid.add(lblPassQueueAmount,2,12);
        grid.add(lblPassLimit, 4, 12);

        grid.add(lblAdhocMiss, 1, 13);
        grid.add(lblAdhocMissAmount,2,13);

        grid.add(lblPassMiss, 1, 14);
        grid.add(lblPassMissAmount,2,14);

        grid.add(lblProfit,1,15);
        grid.add(lblProfitValue, 2, 15);

        HBox container = new HBox(grid);
        this.getChildren().add(container);
        model.addView(this);
    }


    @Override
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //Set adhoc label text.
                lblAdhoc.setText(
                        ""+model.getAdhocReservationSection().getFilledspots()
                );

                //Set subscription text.
                lblSubs.setText(
                        ""+model.getPassSection().getFilledspots()
                );

                lblReservations.setText(
                        ""+model.getAdhocReservationSection().getReservedCars()
                );

                lblFreeAmount.setText(
                        ""+model.getAdhocReservationSection().getFreeSpots()
                );

                lblProfitValue.setText(
                        "€ " + model.getTicketMachine().getProfit()
                );

                lblAdhocQueueAmount.setText(
                        ""+ Garage.getNumberCarsInAdhocQueue()
                );

                lblPassQueueAmount.setText(
                        ""+ Garage.getNumberCarsInPassQueue()
                );

                lblAdhocMissAmount.setText(
                        "" + model.getAdhocReservationsPassed()
                );

                lblPassMissAmount.setText(
                        "" + model.getPassPassed()
                );

                lblDoubles.setText(
                        "" + model.getAdhocReservationSection().getDoubleParked()
                );

                if (Garage.getNumberCarsInAdhocQueue() >= SettingHandler.getMaxQueueSize()) {
                    lblAdhocLimit.setText("AD-HOC QUEUE ZIT VOL!");
                } else {
                    lblAdhocLimit.setText("");
                }


                if (Garage.getNumberCarsInPassQueue() >= SettingHandler.getMaxQueueSize()) {
                    lblPassLimit.setText("PASSHOUDER QUEUE ZIT VOL!");
                } else {
                    lblPassLimit.setText("");
                }
            }
        });
    }
}
