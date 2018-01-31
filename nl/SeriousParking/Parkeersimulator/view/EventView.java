package nl.SeriousParking.Parkeersimulator.view;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import nl.SeriousParking.Parkeersimulator.controller.EventController;
import nl.SeriousParking.Parkeersimulator.model.SimEvent;
import nl.SeriousParking.Parkeersimulator.model.Simulator;

public class EventView extends View<EventController, Simulator> {
    ListView listView = new ListView();

    public EventView(EventController controller, Simulator model) {
        super(controller, model);

        this.getChildren().add(listView);
        model.addView(this);
    }

    @Override
    public void update() {
        listView.getItems().clear();

        for (SimEvent e : model.getEvents()) {
            if (e.isActive()) {
                controller.doEvent(e);
                listView.getItems().add(e.getTitle()+" IS ACTIVE!");
            } else {
                listView.getItems().add(e.getTitle()+" not active.");
            }
        }
    }
}
