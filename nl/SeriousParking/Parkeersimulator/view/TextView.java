package nl.SeriousParking.Parkeersimulator.view;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Date_time;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class TextView extends View<SimulatorController, Simulator>  {
    Label timeLbl    = new Label();
    Label total1     = new Label();
    Label passcar1   = new Label();
    Label adhoccar1  = new Label();
    Label queue1     = new Label();
    Label dubbel1    = new Label();
    Label reser1     = new Label();
    Label free1      = new Label();
    Label rev1       = new Label();

    public  TextView(SimulatorController controller, Simulator model) {
        super(controller, model);

        GridPane grid = new GridPane();


        Label total     = new Label("totaal aantal auto's");
        Label passcar   = new Label("Abbonoment's houders");
        Label adhoccar  = new Label("Gast auto's");
        Label queue     = new Label("Auto's in queue");
        Label dubbel    = new Label("dubbel geparkeerde auto's");
        Label reser     = new Label("gereserveerde plekken");
        Label free      = new Label("vrij plekken");
        Label rev       = new Label("Winst");

        grid.getColumnConstraints().add(new ColumnConstraints(250));
        grid.getRowConstraints().add(new RowConstraints(50));

        grid.add(total,1,5);
        grid.add(passcar,1,6);
        grid.add(adhoccar,1,7);
        grid.add(dubbel,1,8);
        grid.add(reser,1,9);
        grid.add(free,1,10);
        grid.add(queue,1,11);
        grid.add(rev,1,12);

        grid.add(timeLbl,2,2);
        grid.add(total1,2,5);
        grid.add(passcar1,2,6);
        grid.add(adhoccar1,2,7);
        grid.add(dubbel1,2,8);
        grid.add(reser1,2,9);
        grid.add(free1,2,10);
        grid.add(queue1,2,11);
        grid.add(rev1,2,12);

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
                total1.setText(""+(+model.getNumberOfPasscarsinPark()+model.getNumberOfAddhoccarsinPark()));
                passcar1.setText(""+model.getNumberOfPasscarsinPark());
                adhoccar1.setText(""+model.getNumberOfAddhoccarsinPark());
                dubbel1.setText(""+model.getNumberOfCarsParkedDouble());
                reser1.setText("");
                free1.setText(""+model.getNumberOfOpenSpots());
                queue1.setText(""+model.getNumberOfCarsinQueue());
                rev1.setText("€ " + model.getProfit());
            }
        });


    }
}
