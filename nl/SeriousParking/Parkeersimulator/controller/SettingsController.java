package nl.SeriousParking.Parkeersimulator.controller;


import nl.SeriousParking.Parkeersimulator.model.Model;
import nl.SeriousParking.Parkeersimulator.model.SettingHandler;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

import static java.lang.Integer.parseInt;

public class SettingsController extends Controller<SettingHandler> {

    public SettingsController(SettingHandler model) {
        super(model);
    }


    public void Save(String[] array){

        model.setTickPause(parseInt(array[0]));
        model.setEnterSpeed(parseInt(array[1]));
        model.setExitSpeed(parseInt(array[2]));
        model.setPaymentSpeed(parseInt(array[3]));
        model.nofifier();
    }
}
