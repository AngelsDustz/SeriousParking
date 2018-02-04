package nl.SeriousParking.Parkeersimulator.controller;


import nl.SeriousParking.Parkeersimulator.model.SettingHandler;


import static java.lang.Integer.parseInt;

public class SettingsController extends Controller<SettingHandler> {

    public SettingsController(SettingHandler model) {
        super(model);
    }

    /**
     * @param status The new state of double entrances.
     */
    public void updateDoubleEntrance(boolean status) {
        model.setDoubleEntrance(status);
    }

    /**
     * @param tickrate The new tickrate of the simulator.
     */
    public void updateTickPause(int tickrate) {
        model.setTickPause(tickrate);
    }

    /**
     * @param speed The new entry speed of vehicles.
     */
    public void updateEnterSpeed(int speed) {
        model.setEnterSpeed(speed);
    }

    /**
     * @param speed The new exit speed of vehicles.
     */
    public void updateExitSpeed(int speed) {
        model.setExitSpeed(speed);
    }

    /**
     * @param speed The new payment speed of vehicles.
     */
    public void updatePaymentSpeed(int speed) {
        model.setPaymentSpeed(speed);
    }

    /**
     * @param cars The new arrival value of weekday adhoc cars.
     */
    public void updateWeekdayAdhocCars(int cars) {
        model.setWeekDayArrivals(cars);
    }

    /**
     * @param cars The new arrival value of weekday pass cars.
     */
    public void updateWeekdayPassCars(int cars) {
        model.setWeekDayPassArrivals(cars);
    }

    /**
     * @param cars The new arrival value of weekday reservations
     */
    public void updateWeekdayReservationCars(int cars) {
        model.setweekDayReservations(cars);
    }

    /**
     * @param cars The new arrival value of weekend adhoc cars.
     */
    public void updateWeekendAdhocCars(int cars) {
        model.setWeekendArrivals(cars);
    }

    /**
     * @param cars The new arrival value of weekend pass cars.
     */
    public void updateWeekendPassCars(int cars) {
        model.setWeekendPassArrivals(cars);
    }

    /**
     * @param cars The new arrival value of weekend reservations.
     */
    public void updateWeekendReservationCars(int cars) {
        model.setweekendReservations(cars);
    }

    /**
     * @param cars The new chance a reservation has to show up in %.
     */
    public void updateReservationShowChance(int cars) { model.setReservationShowchance(cars);}

    /**
     * @param cars The new chance a car has to double park in %.
     */
    public void updateDoubleParkingChance(int cars) {model.setChanseToParkDouble(cars);}

    /**
     * @param queuesize The new maximum queue size.
     */
    public void updateGarageQueue(int queuesize) { model.setMaxQueueSize(queuesize); }

    /**
     * @param speed The new time it takes for a car to go through the garage.
     */
    public void updateGarageThroughSpeed(int speed) { model.setDriveTroughSpeed(speed); }

    /**
     * @param passRows The amount of rows that are reserved for pass holders.
     */
    public void setPassRows(int passRows){model.setPassRows(passRows);}

    /**
     * @param adhocReservationRows The amount of rows that are available for reservations.
     */
    public void setAdhocRows(int adhocReservationRows){model.setAdhocReservationRows(adhocReservationRows);}

    /**
     * @param floorsadhoc The amount of floors available for adhoc cars.
     */
    public void setAdhocFloors(int floorsadhoc){model.setAdhocReservationFloors(floorsadhoc);}

    /**
     * @param floorspass The amount of floor available for pass cars.
     */
    public void setPassFloors(int floorspass){model.setPassFloors(floorspass);}

    /**
     * @param passpl The amount of places available for passholders.
     */
    public void setPassPlaces(int passpl){model.setPassplaces(passpl);}

    /**
     * @param adhocpl The amount of places available for adhoc cars.
     */
    public void setadhocplaces(int adhocpl){model.setAdhocReservationplaces(adhocpl);}

    public void defaultValues() {
        model.defaultValue();
    }

}
