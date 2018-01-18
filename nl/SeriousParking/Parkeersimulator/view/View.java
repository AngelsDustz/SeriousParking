package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.layout.VBox;
import nl.SeriousParking.Parkeersimulator.controller.Controller;
import nl.SeriousParking.Parkeersimulator.model.Model;

abstract public class View<C extends Controller, M extends Model> extends VBox {

    protected C controller;
    protected M model;

    abstract public void update();

    public View(C controller, M model) {
        this.controller = controller;
        this.model = model;
    }
}
