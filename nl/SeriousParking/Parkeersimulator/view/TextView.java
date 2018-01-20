package nl.SeriousParking.Parkeersimulator.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class TextView extends View<SimulatorController, Simulator>  {
    private HBox container = new HBox();

    public  TextView(SimulatorController controller, Simulator model) {
        super(controller, model);

        BorderPane borderPane   = new BorderPane();

        borderPane.setCenter(container);



        this.getChildren().add(borderPane);
        model.addView(this);

    }


    @Override
    public void update() {

    }
}
