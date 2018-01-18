package nl.SeriousParking.Parkeersimulator.model;

import nl.SeriousParking.Parkeersimulator.view.View;

import java.util.ArrayList;

public abstract class Model {
    private ArrayList<View> views;

    public Model() {
        this.views = new ArrayList<View>();
    }

    public void addView(View v) {
        views.add(v);
    }

    public void updateViews() {
        for (View v : views) {
            v.update();

        }
    }
}
