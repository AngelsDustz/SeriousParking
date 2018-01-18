package SeriousParking;

import nl.SeriousParking.Parkeersimulator.model.Car;

import java.util.Random;

public class SeriousParking {

    public static void main(String[] args) {
        Parkeersimulator.Simulator sim = new Parkeersimulator.Simulator();
        sim.run();

    }

    public static class AdHocCar extends Car {
        private static final Color COLOR=Color.red;

        public AdHocCar() {
            Random random = new Random();
            int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
            this.setMinutesLeft(stayMinutes);
            this.setHasToPay(true);
        }

        public Color getColor(){
            return COLOR;
        }
    }
}