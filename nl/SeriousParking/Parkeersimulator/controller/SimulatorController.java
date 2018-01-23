package nl.SeriousParking.Parkeersimulator.controller;


import nl.SeriousParking.Parkeersimulator.model.Simulator;
import static java.lang.Integer.parseInt;


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

    public void Save(String[] array){
          //  model.setNumberofFloors(parseInt(array[0]));
          //  model.setNumberofRows(parseInt(array[1]));
            //model.setnumberofPlaces(parseInt(array[2]));
        }
    }

