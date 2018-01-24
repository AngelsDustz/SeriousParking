package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.layout.FlowPane;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import javafx.scene.control.Label;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class SimData extends View<SimulatorController, Simulator> {


    public SimData(SimulatorController controller, Simulator model) {
        super(controller, model);

        FlowPane flow = new FlowPane();

        Label abbo = new Label("abbo");
        Label adhoc = new Label("adhoc");
        Label dubbel = new Label("dubbel");
        Label gereserveerd = new Label("gereserveerd");
        Label vrij = new Label("vrij");

        abbo.setId("abbo");
        adhoc.setId("adhoc");
        dubbel.setId("dubbel");
        gereserveerd.setId("reserv");
        vrij.setId("vrij");

        flow.setVgap(20);
        flow.setHgap(5);
        flow.getChildren().addAll(abbo,adhoc,dubbel,gereserveerd,vrij);
        this.getChildren().add(flow);


    }

    @Override
    public void update() {


    }
}
