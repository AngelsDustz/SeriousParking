package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.control.Label;
import nl.SeriousParking.Parkeersimulator.controller.RuntimeController;
import nl.SeriousParking.Parkeersimulator.model.Runtime;


public class RuntimeView extends View<RuntimeController, Runtime> {
    Label output;

    @Override
    public void update() {
        Long runTime        = model.getRunTime();
        int secondPassed    = Math.toIntExact(runTime/1000);
        int minutesPassed   = secondPassed/60;

        output.setText("Runtime: " + minutesPassed + ":" + (secondPassed%60));
    }

    public RuntimeView(RuntimeController controller, Runtime model) {
        super(controller, model);

        output = new Label("Runtime: " + model.getRunTime() + "ms");
        this.getChildren().add(output);
        model.addView(this);
    }
}

