package nl.SeriousParking.Parkeersimulator.view;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.SettingsController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class SimSettings extends View<SettingsController, Simulator> {

    private Button[] btns;
    private Label[] label;
    private VBox container = new VBox();

    public SimSettings(SettingsController settingscontroller, Simulator model) {
        super(settingscontroller, model);
        btns = new Button[5];
        label = new Label[5];

        btns[0] = new Button("Save");
        btns[1] = new Button("Default");
        label[0] = new Label("Car's");


        container.getChildren().addAll(btns[0],btns[1],label[0]);


        this.getChildren().add(container);

    }

    @Override
    public void update() {

    }

}