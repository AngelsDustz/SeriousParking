package nl.SeriousParking.Parkeersimulator.model;

import javafx.beans.property.SimpleStringProperty;


public class Reservation extends Model {

    public final SimpleStringProperty StartTime;
    public final SimpleStringProperty Name;
    public final SimpleStringProperty EndTime;


    public Reservation(String sTime, String lName, String EndTime) {
        this.StartTime  = new SimpleStringProperty(sTime);
        this.Name       = new SimpleStringProperty(lName);
        this.EndTime    = new SimpleStringProperty(EndTime);
    }


    public String getStartTime() {
        return StartTime.get();
    }

    public void setStartTime(String sTime) {
        StartTime.set(sTime);
    }

    public String getName() { return Name.get(); }

    public void setName(String sTime) {
        Name.set(sTime);
    }

    public String getEndTime() {
        return EndTime.get();
    }

    public void setEndTime(String sTime) {
        EndTime.set(sTime);
    }

}