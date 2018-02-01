package nl.SeriousParking.Parkeersimulator.model;

public class TicketMachine {
    private double profit;
    private double reservationCost  = 2.00;
    private double pricePerHour     = 1.50;

    public TicketMachine() {
        this.profit = 0.0;
    }

    /**
     * Handles the payment of cars.
     */
    public void handlePayment(){
        carsPaying();
    }

    /**
     * Resets the ticketmachine.
     */
    protected void reset(){
        this.profit = 0.0;
    }


    /**
     * Makes all the cars pay.
     */
    private void carsPaying(){
        // Let cars pay.
        int i=0;

        while (Garage.paymentCarQueue.carsInQueue()>0 && i < SettingHandler.paymentSpeed){
            Car car  = Garage.paymentCarQueue.removeCar();
            profit  += car.PaymentMethod(reservationCost,pricePerHour);

            if (car.areAllTransactionsComplete()) {
                Garage.exitCarQueue.addCar(Garage.paymentCarQueue.removeCar());
            }

            i++;
        }
    }

    public double getProfit() {
        return profit;
    }
}
