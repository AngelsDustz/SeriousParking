package nl.SeriousParking.Parkeersimulator.controller;

import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class SimulatorController extends Controller<Simulator> {


    public SimulatorController(Simulator model) {
        super(model);
    }

    public void startSimulator() {
        if (!model.getRun()){

            model.setRun(true);
            model.startSimulator();
        }
        else {
            model.setRun(false);
        }

    }

    public void resetSimulator(){
        model.ResetSim();

    }
}
