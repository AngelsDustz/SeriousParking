package nl.SeriousParking.Parkeersimulator.controller;

import nl.SeriousParking.Parkeersimulator.model.SimEvent;
import nl.SeriousParking.Parkeersimulator.model.Simulator;


public class EventController extends Controller<Simulator> {
    public EventController(Simulator model) {
        super(model);
    }

    /**
     * @param event The event to add.
     */
    public void addEvent(SimEvent event) {
        model.addEvent(event);
    }

    /**
     * @param event The event to execute.
     */
    public void doEvent(SimEvent event) {
        model.handleEntrance();
    }
}
