package nl.SeriousParking.Parkeersimulator.controller;

import nl.SeriousParking.Parkeersimulator.model.SimEvent;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class EventController extends Controller<Simulator> {
    public EventController(Simulator model) {
        super(model);
    }

    //Way to add events?

    public void addEvent(SimEvent event) {
        model.addEvent(event);
    }

    public void doEvent(SimEvent event) {
        model.handleEntrance();
    }
}
