package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.layout.GridPane;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class Event extends View<SimulatorController, Simulator>{

    public Event(SimulatorController controller, Simulator model)  {
        super(controller, model);

        GridPane container      = new GridPane();



        this.getChildren().add(container);
    }

    @Override
    public void update(){

    }
}
