package nl.SeriousParking.Parkeersimulator.model;

public class SimEvent {
    private  Boolean active;
    private  String title;
    private  int week;
    private  int day;
    private  int passCarModifier;
    private  int reservationCarModifier;
    private  int adhocCarModifier;

    public SimEvent() {
        active=false;
        title       = "No Title";
        week        = 0;
        day         = 0;
        reservationCarModifier=0;
        adhocCarModifier=0;
        passCarModifier=0;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getPassCarModifier() {
        return passCarModifier;
    }

    public void setPassCarModifier(int passCarModifier) {
        this.passCarModifier = passCarModifier;
    }

    public int getReservationCarModifier() {
        return reservationCarModifier;
    }

    public void setReservationCarModifier(int reservationCarModifier) {
        this.reservationCarModifier = reservationCarModifier;
    }

    public int getAdhocCarModifier() {
        return adhocCarModifier;
    }

    public void setAdhocCarModifier(int adhocCarModifier) {
        this.adhocCarModifier = adhocCarModifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDay(){return day;}

    public void setDay(int day){this.day = day;}

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    /**
     * Checks if the current event is active or not.
     *
     * @return Weither the event is active or not.
     */
    public boolean isActive() {
        if (Date_time.getWeeks() == week && Date_time.getDays() == day) {
            return true;
        }
        else if(Date_time.getDays() == day && week == 0){
            return true;
        }
        else if(Date_time.getWeeks() == week && day == 0){
            return true;
        }
        else {
            return false;
        }
    }
}
