package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import javafx.scene.control.Label;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class SimData extends View<SimulatorController, Simulator> {


    public SimData(SimulatorController controller, Simulator model) {
        super(controller, model);

        GridPane data = new GridPane();

        Label abbo = new Label();
        Label adhoc = new Label();
        Label dubbel = new Label();
        Label gereserveerd = new Label();
        Label vrij = new Label();


        data.add(abbo,0,0);
        data.add(adhoc,1,0);
        data.add(dubbel,2,0);
        data.add(gereserveerd,3,0);
        data.add(vrij,4,0);

        VBox data1 = new VBox(data);

        this.getChildren().add(data1);


    }

    @Override
    public void update() {


    }
}
