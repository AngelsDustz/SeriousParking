package nl.SeriousParking.Parkeersimulator.controller;

import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class SimulatorController extends Controller<Simulator> {


    public SimulatorController(Simulator model) {
        super(model);
    }

    public void startSimulator() {
        model.startStop();

    }

    public void resetSimulator(){
        model.ResetSim();
    }

    public void tick(){
        model.singleTick();
    }
}
