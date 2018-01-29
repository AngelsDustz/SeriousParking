package nl.SeriousParking.Parkeersimulator.model;


import java.util.Random;

public class GarageSection extends Garage{
    private int floors;
    private int rows;
    private int places;
    private int freeSpots;
    private int totalspots;
    Car[][][] section;

    public GarageSection(int floors,int rows,int places) {

        this.floors = floors;
        this.rows = rows;
        this.places = places;
        section =new Car[floors][rows][places];
        totalspots = this.floors*this.rows*this.places;
        freeSpots = totalspots;
    }




    public void clear(){
        for (int floor = 0; floor < floors;floor++) {

            for (int row = 0; row < rows; row++) {

                for (int place = 0; place < places; place++) {
                    section[floor][row][place] = null;
                }
            }
        }
        freeSpots=totalspots;
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


    private boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }

        Car oldCar = getCarAt(location);
        if (oldCar == null) {

           section[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            freeSpots--;

            return true;
        }

        return false;
    }

    public void carLeavesSpot(Car car){

        removeCarAt(car.getLocation());

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
        int i=0;
        //TODO make a max amount of cars driving out setting;
        while (car!=null&& i<SettingHandler.driveTroughSpeed &&drivingToExit.carsInQueue()<15) {


                carLeavesSpot(car);

                drivingToExit.addCar(car);

            car = getFirstLeavingCar();
            i++;
        }
    }

    private Car getFirstLeavingCar() {
        for (int floor = 0; floor < floors; floor++) {

            for (int row = 0; row < rows; row++) {

                for (int place = 0; place < places; place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);

                    if (car != null && car.getMinutesLeft() <= 0) {
                        return car;
                    }
                }
            }
        }

        return null;
    }
    protected void Tick(){
        for (int floor = 0; floor < floors; floor++) {
            for (int row = 0; row < rows; row++) {
                for (int place = 0; place < places; place++) {
                    Car car = section[floor][row][place];
                    if (car!=null) {
                        car.tick();
                    }
                }
            }
        }
    }

    protected void carsEntering(Queue queue) {
        int i = 0;

        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue() > 0 && freeSpots > 0 && i < SettingHandler.enterSpeed) {
            Car car = queue.removeCar();

            if (!car.isParkedDouble()) {
                Location freeLocation = getFirstFreeLocation();
                setCarAt(freeLocation, car);
                //Todo Rework ParkedDouble
            }   /*else {

          Location[] freeLocation = getFirstFreeDoubleLocation();


                if (freeLocation != null) {
                    Location loc1   = freeLocation[0];
                    Location loc2   = freeLocation[1];
                    Car car2        = new Car();
                    car2            = car2.copy(car);

                    setCarAt(loc1, car);
                    setCarAt(loc2, car2);

                } else {

                    queue.addCar(car);
                }
            } */
                i++;

        }
    }

        public void carsLeaving () {
            // Let cars leave.
            int exitSpeed = SettingHandler.exitSpeed;
            int i = 0;
            while (exitCarQueue.carsInQueue() > 0 && i < exitSpeed) {
                   exitCarQueue.removeCar();

                i++;
            }
        }
        //todo  reimplement

    private void carsBackEntering(Queue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue()>0 && freeSpots>0 && i<SettingHandler.enterSpeed) {

            Car car = queue.removeCar();

            if (!car.isParkedDouble()){
                Location freeLocation = getLastFreeLocation();
                setCarAt(freeLocation, car);

                //Todo reimplement parkedDouble
            }/* else {
                Location[] freeLocation = getLastFreeDoubleLocation();


                if (freeLocation != null) {
                    Location loc1   = freeLocation[0];
                    Location loc2   = freeLocation[1];
                    Car car2        = new Car();
                    car2            = car2.copy(car);

                    setCarAt(loc1, car);
                    setCarAt(loc2, car2);

                }
            }*/
            i++;
        }
   }

    protected int carsPassingBy(Queue queue){
        int carsPassed=0;
        if(freeSpots!=0){
            while (queue.carsInQueue()>SettingHandler.maxQueueSize){
                queue.removeCar();
                carsPassed++;
            }
        } else {
            while(queue.carsInQueue()>0){
                queue.removeCar();
                carsPassed++;
            }


        }
        return carsPassed;
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
}
