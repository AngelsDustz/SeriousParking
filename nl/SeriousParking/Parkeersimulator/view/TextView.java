package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class TextView extends View<SimulatorController, Simulator>  {

    Label timeLbl      = new Label();
    Label total1     = new Label();
    Label passcar1   = new Label();
    Label adhoccar1  = new Label();
    Label dubbel1    = new Label();
    Label reser1     = new Label();
    Label free1      = new Label();
    Label rev1       = new Label();

    public  TextView(SimulatorController controller, Simulator model) {
        super(controller, model);

        GridPane grid = new GridPane();

        //timeLbl.setText("Dag"+model.getDay()+"  Tijd :  "+model.getHour()+" : "+model.getMinute()+" : 00");

        Label total     = new Label("totaal aantal auto's");
        Label passcar   = new Label("Abbonoment's houders");
        Label adhoccar  = new Label("Gast auto's");
        Label dubbel    = new Label("dubbel geparkeerde auto's");
        Label reser     = new Label("gereserveerde plekken");
        Label free      = new Label("vrij plekken");
        Label rev       = new Label("omzet");

        grid.getColumnConstraints().add(new ColumnConstraints(200));

        grid.add(total,0,2);
        grid.add(passcar,0,3);
        grid.add(adhoccar,0,4);
        grid.add(dubbel,0,5);
        grid.add(reser,0,6);
        grid.add(free,0,7);
        grid.add(rev,0,8);

        grid.add(timeLbl,1,1);
        grid.add(total1,3,2);
        grid.add(passcar1,3,3);
        grid.add(adhoccar1,3,4);
        grid.add(dubbel1,3,5);
        grid.add(reser1,3,6);
        grid.add(free1,3,7);
        grid.add(rev1,3,8);

        HBox container = new HBox(grid);
        this.getChildren().add(container);
        model.addView(this);


    }


    @Override
    public void update() {
       /*
        timeLbl.setText(""+model.getDay()+"  time :  "+model.getHour()+" : "+model.getMinute()+" : 00");
        total1.setText(""+model.getNumberOfPasscarsinPark()+model.getNumberOfAddhoccarsinPark());
        passcar1.setText(""+model.getNumberOfPasscarsinPark());
        adhoccar1.setText(""+model.getNumberOfAddhoccarsinPark());
        dubbel1.setText(""+model.getNumberOfCarsParkedDouble());
        reser1.setText("");
        free1.setText(""+model.getNumberOfOpenSpots());
        rev1.setText("");
*/


    }
}
