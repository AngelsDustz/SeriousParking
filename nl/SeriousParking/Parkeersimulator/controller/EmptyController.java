package nl.SeriousParking.Parkeersimulator.controller;

import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class EmptyController extends Controller<Simulator> {

    private boolean clicked=false;
    public EmptyController(Simulator model) {
        super(model);
    }

    public void startSimulator(){
      if (clicked==false){
            model.setRun(true);
            model.run();
            clicked=true;
        }
        else model.setRun(false);
        clicked=false;

    }
}
