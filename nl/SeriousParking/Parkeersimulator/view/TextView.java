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
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class TextView extends View<SimulatorController, Simulator>  {
    Label timeLbl    = new Label();
    Label adHoc1 = new Label();
    Label passcar1   = new Label();
    Label reservation1 = new Label();
    Label queue1     = new Label();
    Label queue2     = new Label();

    Label dubbel1    = new Label();
    Label reser1     = new Label();
    Label free1      = new Label();
    Label rev1       = new Label();
    Label lAdhocPass    = new Label();

    public  TextView(SimulatorController controller, Simulator model) {
        super(controller, model);

        GridPane grid = new GridPane();


        Label adhoc         = new Label("aantal adhoc auto's");
        Label passcar       = new Label("Abbonoment's houders");
        Label reservation   = new Label("reserveringen");
        Label queue         = new Label("Auto's Ad-hoc rij");
        Label backqueue     = new Label("Auto's in pashouder-gereserveerde rij");
        Label dubbel        = new Label("dubbel geparkeerde auto's");
        Label reser         = new Label("gereserveerde plekken");
        Label free          = new Label("vrij ad-hoc plekken");
        Label adhocPass     = new Label("ad-hoc misgelopen.");
        Label rev           = new Label("Winst");

        grid.getColumnConstraints().add(new ColumnConstraints(250));
        grid.getRowConstraints().add(new RowConstraints(50));

        grid.add(adhoc,1,5);
        grid.add(passcar,1,6);
        grid.add(reservation,1,7);
        grid.add(dubbel,1,8);
        grid.add(reser,1,9);
        grid.add(free,1,10);
        grid.add(queue,1,11);
        grid.add(backqueue,1,12);
        grid.add(rev,1,13);
        grid.add(adhocPass, 1, 14);

        grid.add(timeLbl,2,2);
        grid.add(adHoc1,2,5);
        grid.add(passcar1,2,6);
        grid.add(reservation1,2,7);
        grid.add(dubbel1,2,8);
        grid.add(reser1,2,9);
        grid.add(free1,2,10);
        grid.add(queue1,2,11);
        grid.add(queue2,2,12);
        grid.add(rev1,2,13);
        grid.add(lAdhocPass, 2, 14);

        HBox container = new HBox(grid);
        this.getChildren().add(container);
        model.addView(this);
    }


    @Override
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                timeLbl.setText("Year :  "+ Date_time.getYears()+"  week :  "+Date_time.getWeeks()+"  day :  "+Date_time.getDays()+"  time :  "+Date_time.getHours()+" : "+Date_time.getMinutes()+" : 00");
                adHoc1.setText(""+(+model.getAdhocReservationSection().getFilledspots()));
                passcar1.setText(""+model.getPassSection().getFilledspots());

                //TODO fix NUMBERS NOW PART OF SAME SECTION
                // reservation1.setText(""+model.getReservationSection().getFilledspots());
                //dubbel1.setText(""+model.getNumberOfCarsParkedDouble());
                //TODO FIX NUMBERS NOW PART OF SAME SECTION
               // reser1.setText(""+model.getReservationSection().getFreeSpots());
                //TODO uitsplitsen per section aanroepen als model.get....Section.getFreeSpots()
                free1.setText(""+model.getAdhocReservationSection().getFreeSpots());
                lAdhocPass.setText("" + model.getNumberOfAdhocPassing());


                queue1.setText(""+ Garage.getNumberCarsInAdhocQueue());
                queue2.setText(""+ (Garage.getNumberCarsInPassQueue()));
                rev1.setText("€ " + model.getProfit());
            }
        });


    }
}
