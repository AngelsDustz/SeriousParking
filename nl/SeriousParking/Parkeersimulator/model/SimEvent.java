package nl.SeriousParking.Parkeersimulator.model;

public class SimEvent {
    private String title;
    private int week;

    public SimEvent() {
        title       = "No Title";
        week        = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public boolean isActive() {
        if (Date_time.getWeeks() != week) {
            return false;
        }

        return true;
    }
}
