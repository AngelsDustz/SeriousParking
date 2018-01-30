package nl.SeriousParking.Parkeersimulator.model;
import java.util.LinkedList;

public class Queue {
    private java.util.Queue<Car> queue = new LinkedList<>();

    public boolean addCar(Car car) {
        return queue.add(car);
    }

    public Car removeCar() {
        return queue.poll();
    }

    public int carsInQueue(){
    	return queue.size();
    }

    public void reset() {
        queue.clear();
    }
}
