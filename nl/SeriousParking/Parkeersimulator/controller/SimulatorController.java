package nl.SeriousParking.Parkeersimulator.controller;

import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class SimulatorController extends Controller<Simulator> {
    private boolean clicked = false;

    public SimulatorController(Simulator model) {
        super(model);
    }

    public void startSimulator() {

        if (!clicked) {
            model.setRun(true);
            model.startSimulator();
            clicked=true;
        } else {
            model.setRun(false);
            clicked=false;
        }

    }
}
