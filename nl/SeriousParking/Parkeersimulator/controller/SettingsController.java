package nl.SeriousParking.Parkeersimulator.controller;


import nl.SeriousParking.Parkeersimulator.model.SettingHandler;


import static java.lang.Integer.parseInt;

public class SettingsController extends Controller<SettingHandler> {

    public SettingsController(SettingHandler model) {
        super(model);
    }
    public void setDefault(){
        model.defaultValue();
    }

    public void doubleEntrance(boolean bool){
        model.setDoubleEntrance(bool);

    }

    public void Save(String[] array){
        try {
            model.setTickPause(parseInt(array[0]));
            model.setEnterSpeed(parseInt(array[1]));
            model.setExitSpeed(parseInt(array[2]));
            model.setPaymentSpeed(parseInt(array[3]));
            model.setWeekDayArrivals(parseInt(array[4]));
            model.setWeekendArrivals(parseInt(array[5]));
            model.setWeekDayPassArrivals(parseInt(array[6]));
            model.setWeekendPassArrivals(parseInt(array[7]));
            model.setChance(parseInt(array[8]));
            model.setGarageFloors(parseInt(array[9]));
            model.setGarageRows(parseInt(array[10]));
            model.setGaragePlaces(parseInt(array[11]));

            model.nofifier();
        }

        catch (NumberFormatException e){
            model.nofifier();
        }
    }
}
