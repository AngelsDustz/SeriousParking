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
        Button start            = new Button("Start/Stop");
        Button reset            = new Button("Reset");
        ToolBar toolBar         = new ToolBar();

        container.setPadding(new Insets(15, 12, 15, 12));
        container.setSpacing(40);

        start.setOnAction(e -> {
            controller.startSimulator();
        });

        reset.setOnAction(e -> {
            controller.resetSimulator();
        });


        toolBar.getItems().addAll(start,reset);

        borderPane.setCenter(container);

        this.getChildren().add(borderPane);
        model.addView(this);

    }


    @Override
    public void update() {

    }
}
