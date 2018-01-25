package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.control.Label;
import nl.SeriousParking.Parkeersimulator.controller.RuntimeController;
import nl.SeriousParking.Parkeersimulator.model.Runtime;


public class RuntimeView extends View<RuntimeController, Runtime> {
    Label output;

    @Override
    public void update() {
        output.setText("Runtime: " + model.getRunTime() + "ms");
    }

    public RuntimeView(RuntimeController controller, Runtime model) {
        super(controller, model);

        output = new Label("Runtime: " + model.getRunTime() + "ms");
        this.getChildren().add(output);
        model.addView(this);
    }
}

