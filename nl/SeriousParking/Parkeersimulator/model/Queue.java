package nl.SeriousParking.Parkeersimulator.model;
import java.util.LinkedList;

public class Queue {
    private java.util.Queue<Car> queue = new LinkedList<>();

    /**
     * Adds a car to the queue.
     *
     * @param car The car to add.
     * @return What was added.
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     * Removes a car from the queue.
     *
     * @return What was removed.
     */
    public Car removeCar() {
        return queue.poll();
    }

    /**
     * Gives the amount of cars in the queue.
     *
     * @return The amount of cars in the queue.
     */
    public int carsInQueue(){
    	return queue.size();
    }

    /**
     * Resets the queue.
     */
    public void reset() {
        queue.clear();
    }
}
