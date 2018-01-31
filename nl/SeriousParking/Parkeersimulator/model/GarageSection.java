package nl.SeriousParking.Parkeersimulator.model;


import java.util.ArrayList;
import java.util.Random;

public class GarageSection extends Garage {
    private int floors;
    private int rows;
    private int places;
    private int freeSpots;
    private int totalspots;
    private int reservedCars;
    private int carsParked;
    private int doubleParked;
    private  Queue sectionQueue;
    Car[][][] section;

    public GarageSection(int floors,int rows,int places) {
        sectionQueue    = new Queue();
        this.floors     = floors;
        this.rows       = rows;
        this.places     = places;
        section         = new Car[floors][rows][places];
        totalspots      = this.floors*this.rows*this.places;
        freeSpots       = totalspots;
        carsParked      = 0;
        reservedCars    = 0;
        doubleParked    = 0;
    }

    public void clear(){
        for (int floor = 0; floor < floors;floor++) {

            for (int row = 0; row < rows; row++) {

                for (int place = 0; place < places; place++) {
                    section[floor][row][place] = null;
                }
            }
        }

        freeSpots       = totalspots;
        carsParked      = 0;
        reservedCars    = 0;
        doubleParked    = 0;
    }


    private boolean locationIsValid(Location location) {
        if (location == null) {
            return false;
        }

        int floor   = location.getFloor();
        int row     = location.getRow();
        int place   = location.getPlace();

        if (floor < 0 || floor >= floors) {
            return false;
        }

        if (row < 0 || row > rows) {
            return false;
        }

        if (place < 0 || place > places) {
            return false;
        }

        return true;
    }

    public int getDoubleParked() {
        return doubleParked;
    }

    private Location getFirstFreeLocation() {
        for (int floor = 0; floor < floors; floor++) {

            for (int row = 0; row < rows; row++) {

                for (int place = 0; place < places; place++) {
                    Location location = new Location(floor, row, place);

                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }


    private Location getLastFreeLocation() {
        for (int floor = floors-1; floor >=0; floor--) {

            for (int row = rows-1; row >=0; row--) {

                for (int place = places-1; place >=0; place--) {
                    Location location = new Location(floor, row, place);

                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }

        return null;
    }


    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }

        return section[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public int getReservedCars() {
        return reservedCars;
    }


    private boolean setCarAt(Location location, Car car) {
        Car oldCar = getCarAt(location);

        if (!locationIsValid(location)) {
            return false;
        }

        if (oldCar == null) {
            section[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            freeSpots--;

            if (car instanceof ReservationCar) {
                reservedCars++;
            }

            if (car.primary) {
                carsParked++;
            } else {
                doubleParked++;
            }

            return true;
        }

        return false;
    }

    public void carLeavesSpot(Car car){
        removeCarAt(car.getLocation());

        if (car.primary) {
            carsParked--;
        } else {
            doubleParked--;
        }

        if (car instanceof ReservationCar) {
            reservedCars--;
        }

        exitCarQueue.addCar(car);
    }

    private Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }

        Car car = getCarAt(location);

        if (car == null) {
            return null;
        }

        section[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        freeSpots++;

        return section[location.getFloor()][location.getRow()][location.getPlace()] = null;
    }

    //todo reimplement
    private Location[] getFirstFreeDoubleLocation() {
        Location[] locations = new Location[2];

        for (int floor = 0; floor < floors; floor++) {

            for (int row = 0; row < rows; row++) {

                for (int place = 0; place < places; place++) {
                    Location location = new Location(floor, row, place);

                    if (getCarAt(location) == null) {

                        if (place<places-1){
                            Location secondLocation = new Location(floor, row, place + 1);

                            if (getCarAt(secondLocation) == null) {
                                locations[0] = location;
                                locations[1] = secondLocation;
                                return locations;
                            }
                        }
                    }
                }
            }

        }

        return null;
    }


    //todo  reimplement
    private Location[] getLastFreeDoubleLocation() {
        Location[] locations = new Location[2];

        for (int floor = floors-1; floor >=0; floor--) {

            for (int row = rows-1; row >=0; row--) {

                for (int place = places-1; place >=0; place--) {
                    Location location = new Location(floor, row, place);

                    if (getCarAt(location) == null) {

                        if (place<places-1){
                            Location secondLocation = new Location(floor, row, place - 1);

                            if (getCarAt(secondLocation) == null) {
                                locations[0] = location;
                                locations[1] = secondLocation;
                                return locations;
                            }
                        }
                    }
                }
            }
        }

        return null;
    }


    protected void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();
        int i   = 0;

        while (car != null && i<SettingHandler.driveTroughSpeed) {
            carLeavesSpot(car);
            drivingToExit.addCar(car);
            car = getFirstLeavingCar();
            if (car!=null && car.primary ){i++;}
        }
    }

    private Car getFirstLeavingCar() {
        for (int floor = 0; floor < floors; floor++) {

            for (int row = 0; row < rows; row++) {

                for (int place = 0; place < places; place++) {
                    Location location   = new Location(floor, row, place);
                    Car car             = getCarAt(location);

                    if (car != null && car.getMinutesLeft() <= 0) {
                        return car;
                    }
                }
            }
        }

        return null;
    }

    protected void tick(){
        for (int floor = 0; floor < floors; floor++) {

            for (int row = 0; row < rows; row++) {

                for (int place = 0; place < places; place++) {
                    Car car = section[floor][row][place];

                    if (car!=null) {
                        car.tick();

                        if (car instanceof ReservationCar) {
                            ((ReservationCar) car).carShows();
                        }
                    }
                }
            }
        }
    }


    protected void carsEntering() {
        // Remove car from the front of the queue and assign to a parking space.
        ArrayList<Car> addMe = new ArrayList<>();

        while (sectionQueue.carsInQueue() > 0 && freeSpots > 0) {
            Car car = sectionQueue.removeCar();

            if (!car.isParkedDouble()) {
                Location freeSpot   = getFirstFreeLocation();
                setCarAt(freeSpot, car);
            } else {
                if (car instanceof ReservationCar) {
                    //Store car for later then add back after the while loop.
                    addMe.add(car);
                } else {
                    //Double parked cars.
                    Car nextCar;


                    Location[] freeSpots    = getFirstFreeDoubleLocation();

                    if (freeSpots != null) {
                        nextCar =car.copy(car);
                        setCarAt(freeSpots[0], car);
                        setCarAt(freeSpots[1], nextCar);
                    }
                }
            }
        }

        for (Car c : addMe) {
            sectionQueue.addCar(c);
        }
    }

    public void carsLeaving () {
        // Let cars leave.
        int exitSpeed   = SettingHandler.exitSpeed;
        int i           = 0;

        while (exitCarQueue.carsInQueue() > 0 && i < exitSpeed) {
            exitCarQueue.removeCar();
            i++;
        }
    }

    //todo  reimplement
    protected void carsBackEntering(){
        int i=0;

        // Remove car from the front of the queue and assign to a parking space.
        ArrayList<Car> addMe = new ArrayList<>();

        while (sectionQueue.carsInQueue() > 0 && freeSpots > 0) {
            Car car = sectionQueue.removeCar();

            if (!car.isParkedDouble()) {
                Location freeSpot   = getLastFreeLocation();
                setCarAt(freeSpot, car);
            } else {

                if (car instanceof ReservationCar) {
                    //Store car for later then add back after the while loop.
                    addMe.add(car);
                } else {
                    //Double parked cars.
                    Car nextCar;


                    Location[] freeSpots    = getLastFreeDoubleLocation();

                    if (freeSpots != null) {
                        nextCar =car.copy(car);
                        setCarAt(freeSpots[0], car);
                        setCarAt(freeSpots[1], nextCar);
                    }
                }
            }
        }

        for (Car c : addMe) {
            sectionQueue.addCar(c);
        }

    }



    public double getFreeSpots() {
        return freeSpots;
    }

    public int getTotalspots() {
        return totalspots;
    }

    public int getFilledspots() {
        return totalspots-freeSpots;
    }

    public Queue getSectionQueue() {
        return sectionQueue;
    }

    public int getFloors() {
        return floors;
    }

    public int getRows() {
        return rows;
    }

    public int getPlaces() {
        return places;
    }
}
