package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.layout.HBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class TextView extends View<SimulatorController, Simulator>  {


    public  TextView(SimulatorController controller, Simulator model) {
        super(controller, model);





        HBox container = new HBox();
        this.getChildren().add(container);


    }


    @Override
    public void update() {

    }
}
