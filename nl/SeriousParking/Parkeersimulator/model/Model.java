package nl.SeriousParking.Parkeersimulator.model;

import nl.SeriousParking.Parkeersimulator.view.View;

import java.util.ArrayList;

public abstract class Model {
    private ArrayList<View> views = new ArrayList<View>();;


    public void addView(View view) {
        views.add(view);
    }

    public void notifyViews() {
        for (View v: views) {
            v.update();
        }
    }
}

