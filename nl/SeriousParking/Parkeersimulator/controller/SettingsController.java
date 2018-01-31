package nl.SeriousParking.Parkeersimulator.controller;


import nl.SeriousParking.Parkeersimulator.model.SettingHandler;


import static java.lang.Integer.parseInt;

public class SettingsController extends Controller<SettingHandler> {

    public SettingsController(SettingHandler model) {
        super(model);
    }

    public void updateDoubleEntrance(boolean status) {
        model.setDoubleEntrance(status);
    }

    public void updateTickPause(int tickrate) {
        model.setTickPause(tickrate);
    }

    public void updateEnterSpeed(int speed) {
        model.setEnterSpeed(speed);
    }

    public void updateExitSpeed(int speed) {
        model.setEnterSpeed(speed);
    }

    public void updatePaymentSpeed(int speed) {
        model.setPaymentSpeed(speed);
    }

    public void updateWeekdayAdhocCars(int cars) {
        model.setWeekDayArrivals(cars);
    }

    public void updateWeekdayPassCars(int cars) {
        model.setWeekDayPassArrivals(cars);
    }

    public void updateWeekdayReservationCars(int cars) {
        model.setweekDayReservations(cars);
    }

    public void updateWeekendAdhocCars(int cars) {
        model.setWeekendArrivals(cars);
    }

    public void updateWeekendPassCars(int cars) {
        model.setWeekendPassArrivals(cars);
    }

    public void updateWeekendReservationCars(int cars) {
        model.setweekendReservations(cars);
    }

    public void updateReservationShowChance(int cars) { model.setReservationShowchance(cars);}

    public void updateDoubleParkingChance(int cars) {model.setChanseToParkDouble(cars);}

    public void updateGarageQueue(int queuesize) { model.setMaxQueueSize(queuesize); }

    public void updateGarageThroughSpeed(int speed) { model.setDriveTroughSpeed(speed); }

    public void setPassRows(int passRows){model.setPassRows(passRows);}

    public void setAdhocRows(int adhocReservationRows){model.setAdhocReservationRows(adhocReservationRows);}

    public void setAdhocFloors(int floorsadhoc){model.setAdhocReservationFloors(floorsadhoc);}

    public void setPassFloors(int floorspass){model.setPassFloors(floorspass);}

    public void setPassPlaces(int passpl){model.setPassplaces(passpl);}

    public void setadhocplaces(int adhocpl){model.setAdhocReservationplaces(adhocpl);}

    public void defaultValues() {
        model.defaultValue();
    }

}
