package nl.SeriousParking.Parkeersimulator.controller;

import nl.SeriousParking.Parkeersimulator.model.Reservation;
import static javax.xml.bind.DatatypeConverter.parseString;


public class ReservationController extends Controller<Reservation> {

    public ReservationController(Reservation model) {
        super(model);
    }

    public void add(String[] array) {
        try {
            model.setStartTime(parseString(array[0]));
            model.setName(parseString(array[1]));
            model.setEndTime(parseString(array[2]));
        }
        catch (NumberFormatException e) {
            model.notifyViews();
        }
    }

}
