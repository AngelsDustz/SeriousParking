package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import javafx.scene.control.Label;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

import static javafx.scene.paint.Color.*;

public class SimData extends View<SimulatorController, Simulator> {


    public SimData(SimulatorController controller, Simulator model) {
        super(controller, model);

        GridPane data = new GridPane();



        Label abbo = new Label("abbo");
        Label adhoc = new Label("adhoc");
        Label dubbel = new Label("dubbel");
        Label gereserveerd = new Label("gereserveerd");
        Label vrij = new Label("vrij");

        Circle abboC = new Circle(5,DARKOLIVEGREEN);
        Circle adhocC = new Circle(5,DARKBLUE);
        Circle dubbelC = new Circle(5,RED);
        Circle reserC = new Circle(5,ORANGE);
        Circle vrijC = new Circle(5,ANTIQUEWHITE);


        data.add(abbo,1,0);
        data.add(adhoc,1,1);
        data.add(dubbel,1,2);
        data.add(gereserveerd,1,3);
        data.add(vrij,1,4);

        data.add(abboC,0,0);
        data.add(adhocC,0,1);
        data.add(dubbelC,0,2);
        data.add(reserC,0,3);
        data.add(vrijC,0,4);

        VBox data1 = new VBox(data);
        data1.setAlignment(Pos.CENTER);
        this.getChildren().add(data1);


    }

    @Override
    public void update() {


    }
}
