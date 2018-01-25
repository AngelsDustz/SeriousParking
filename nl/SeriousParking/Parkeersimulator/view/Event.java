package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nl.SeriousParking.Parkeersimulator.controller.SimulatorController;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class Event extends View<SimulatorController, Simulator>{


    private Button Clear    =new Button("Clear");
    private Button Add      =new Button("Add");

    private TextField passB     = new TextField(""+0);
    private TextField adhocB    = new TextField(""+0);
    private TextField dubbelPB  = new TextField(""+0);
    private TextField dubbelAB  = new TextField(""+0);


    private Label pass      = new Label("passhouders");
    private Label adhoc     = new Label("Gasparkeerders");
    private Label dubbelP   = new Label("dubbelparkeerders Pass");
    private Label dubbelA   = new Label("Dubbelparkeerders Gast");


    public Event(SimulatorController controller, Simulator model)  {
        super(controller, model);

        GridPane container  = new GridPane();

        container.add(pass,0,0);
        container.add(adhoc,0,1);
        container.add(dubbelP,0,2);
        container.add(dubbelA,0,3);

        container.add(passB,1,0);
        container.add(adhocB,1,1);
        container.add(dubbelPB,1,2);
        container.add(dubbelAB,1,3);

        container.add(Clear,0,4);
        container.add(Add,1,4);

        this.getChildren().add(container);
    }

    @Override
    public void update(){

    }
}
