package nl.SeriousParking.Parkeersimulator.controller;


import nl.SeriousParking.Parkeersimulator.model.ReservationContainer;


public class ReservationController extends Controller<ReservationContainer> {

    public ReservationController(ReservationContainer model) {
        super(model);
    }

    public void add(String[] array) {
        try {
            model.AddReservation(array[0],array[1],array[2]);
        }
        catch (NumberFormatException e) {

        }
    }

}
