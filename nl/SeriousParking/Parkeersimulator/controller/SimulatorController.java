package nl.SeriousParking.Parkeersimulator.controller;


import nl.SeriousParking.Parkeersimulator.model.Simulator;
import static java.lang.Integer.parseInt;


public class SimulatorController extends Controller<Simulator> {


    public SimulatorController(Simulator model) {
        super(model);
    }

    public void startSimulator() {
        if(model.isGarageIsSet()){
            model.startStop();
        }
    }

    public void resetSimulator(){
        if(model.isGarageIsSet()) {
            model.ResetSim();
        }
    }

    public void tick(){
        if(model.isGarageIsSet()) {
            model.singleTick();
        }
    }

    public void tickMany(int times) {
        if( model.isGarageIsSet()){
            model.tickMany(times);
        }
    }

    public void SaveSettings(String[] array){
        model.setNumberOfFloors(parseInt(array[0]));
        model.setNumberOfRows(parseInt(array[1]));
        model.setNumberOfPlaces(parseInt(array[2]));
        model.setGarageIsSet(true);
    }

    public void defaultValues(){
        model.setGarageIsSet(true);

    }
}

