package nl.SeriousParking.Parkeersimulator.controller;

import nl.SeriousParking.Parkeersimulator.model.SettingHandler;
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
        int numberOfCars;

        //Ad-hoc cars.
        numberOfCars = model.getNumberOfCars(event.getAdhocCarModifier(), event.getAdhocCarModifier());
        model.addArrivingCars((numberOfCars), model.ADHOC);

        //Subscriber cars.
        numberOfCars = model.getNumberOfCars(event.getPassCarModifier(),event.getPassCarModifier());
        model.addArrivingCars((numberOfCars), model.PASS);

        //Reservation cars.
        numberOfCars = model.getNumberOfCars(event.getReservationCarModifier(), event.getReservationCarModifier());
        model.addArrivingCars((numberOfCars), model.RES);
    }


}
