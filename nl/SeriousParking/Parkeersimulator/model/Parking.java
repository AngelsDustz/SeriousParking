package nl.SeriousParking.Parkeersimulator.model;

public class Parking extends Model {

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private Car[][][] cars;


    public Parking(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors     = numberOfFloors;
        this.numberOfRows       = numberOfRows;
        this.numberOfPlaces     = numberOfPlaces;
        this.numberOfOpenSpots  = numberOfFloors * numberOfRows * numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
    }


    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }


    public int getNumberOfOpenSpots() {
        return numberOfOpenSpots;
    }

    private boolean locationIsValid(Location location) {
        int floor   = location.getFloor();
        int row     = location.getRow();
        int place   = location.getPlace();

        if (floor < 0 || floor >= numberOfFloors) {
            return false;
        }

        if (row < 0 || row > numberOfRows) {
            return false;
        }

        if (place < 0 || place > numberOfPlaces) {
            return false;
        }

        return true;
    }

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }

        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }

        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }

        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }

        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {

            for (int row = 0; row < getNumberOfRows(); row++) {

                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);

                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }

        return null;
    }

    public Car getFirstLeavingCar() {

        for (int floor = 0; floor < getNumberOfFloors(); floor++) {

            for (int row = 0; row < getNumberOfRows(); row++) {

                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);

                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {

            for (int row = 0; row < getNumberOfRows(); row++) {

                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location   = new Location(floor, row, place);
                    Car car             = getCarAt(location);

                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }
}


