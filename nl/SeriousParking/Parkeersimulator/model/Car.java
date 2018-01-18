package nl.SeriousParking.Parkeersimulator.model;


import java.util.Random;

public class Car extends Model{
    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;
    private boolean hasPass;

    /**
     * Constructor for objects of class Car
     */
    public void Car(boolean hasPass) {
        this.hasPass=hasPass;
        Random random = new Random();
        if (this.hasPass) this.setHasToPay(false);
        else this.setHasToPay(true);
        int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    public void tick() {
        minutesLeft--;
    }

      public void sethasPass(boolean hasPass)
        {this.hasPass= hasPass;}

    public boolean gethasPass()
    {return this.hasPass;}

}