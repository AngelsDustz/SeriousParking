package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import nl.SeriousParking.Parkeersimulator.controller.RuntimeController;
import nl.SeriousParking.Parkeersimulator.model.Date_time;
import nl.SeriousParking.Parkeersimulator.model.Runtime;

public class RuntimeView extends View<RuntimeController, Runtime> {
    Label output;
    Label output2;
    public FlowPane flow;

    public RuntimeView(RuntimeController controller, Runtime model) {
        super(controller, model);

        flow    = new FlowPane();
        output  = new Label("   Runtime: " + model.getRunTime() + "ms");
        output2 = new Label();

        flow.getChildren().addAll(output2, output);
        this.getChildren().add(flow);
        model.addView(this);
    }

    @Override
    public void update() {
        Long runTime        = model.getRunTime();
        int secondPassed    = Math.toIntExact(runTime / 1000);
        int minutesPassed   = secondPassed / 60;

        output.setText("  Runtime: " + minutesPassed + ":" + (secondPassed % 60));
        output2.setText("    Year :  " + Date_time.getYears() + "  week :  " + Date_time.getWeeks() + "  day :  " + Date_time.getDays() + "  time :  " + Date_time.getHours() + " : " + Date_time.getMinutes() + " : 00      ");
    }
}