package nl.SeriousParking.Parkeersimulator.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.SeriousParking.Parkeersimulator.canEvent;

import java.util.ArrayList;
import java.util.HashMap;


public class ReservationContainer extends Model {
    public ObservableList<Reservation> data =
            FXCollections.observableArrayList(
                    new Reservation("12:30", "Smith", "13:30"),
                    new Reservation("12:30", "Johnson", "13:30")
            );

    public void AddReservation(String s1, String s2, String s3){
            data.add( new Reservation(s1, s2,s3));
        }
}

