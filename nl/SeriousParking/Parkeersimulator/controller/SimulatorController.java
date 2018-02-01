package nl.SeriousParking.Parkeersimulator.controller;


import nl.SeriousParking.Parkeersimulator.model.Simulator;
import static java.lang.Integer.parseInt;


public class SimulatorController extends Controller<Simulator> {


    public SimulatorController(Simulator model) {
        super(model);
    }

    public void startSimulator() {
        if (model.isGarageIsSet()) {
            model.startStop();
        }
    }

    public void resetSimulator() {
        if (model.isGarageIsSet()) {
            model.ResetSim();
        }
    }

    public void tick() {
        if (model.isGarageIsSet()) {
            model.singleTick();
        }
    }

    /**
     * @param times The amount of times it has to tick.
     */
    public void tickMany(int times) {
        if (model.isGarageIsSet()) {
            model.tickMany(times);
        }
    }
}

