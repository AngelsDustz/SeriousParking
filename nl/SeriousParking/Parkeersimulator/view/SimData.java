package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
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

        abbo.setFont(new Font("Arial", 20));
        adhoc.setFont(new Font("Arial", 20));
        dubbel.setFont(new Font("Arial", 20));
        gereserveerd.setFont(new Font("Arial", 20));
        vrij.setFont(new Font("Arial", 20));

        Circle abboC = new Circle(10,DARKOLIVEGREEN);
        Circle adhocC = new Circle(10,DARKBLUE);
        Circle dubbelC = new Circle(10,RED);
        Circle reserC = new Circle(10,ORANGE);
        Circle vrijC = new Circle(10,ANTIQUEWHITE);

        data.add(abbo,2,0);
        data.add(adhoc,2,1);
        data.add(dubbel,2,2);
        data.add(gereserveerd,2,3);
        data.add(vrij,2,4);

        data.add(abboC,0,0);
        data.add(adhocC,0,1);
        data.add(dubbelC,0,2);
        data.add(reserC,0,3);
        data.add(vrijC,0,4);

        data.getColumnConstraints().add(new ColumnConstraints(40));

        VBox data1 = new VBox(data);
        data1.setAlignment(Pos.CENTER);
        this.getChildren().add(data1);


    }

    @Override
    public void update() {


    }
}
