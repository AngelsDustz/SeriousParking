package nl.SeriousParking.Parkeersimulator.model;

import nl.SeriousParking.Parkeersimulator.canEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class TicketMachine {

    private ArrayList<Model> listners = new ArrayList<>();
    double profit;
    double reservationCost;
    double pricePerHour;
    public TicketMachine() {
    }

    private void sendEvent(HashMap data) {
        for (Model m : listners) {
            if (m instanceof canEvent) {
                ((canEvent) m).doEvent(data);
            }
        }
    }

    public void handlePayment(){

        carsPaying();


        HashMap<String, Object> data = new HashMap<>();
        data.put("time_passed", Date_time.getTickSinceStart());
        data.put("profit", profit);
        sendEvent(data);


    }
    protected void reset(){
     sendEvent(null);}


    private void carsPaying(){
        // Let cars pay.
        int i=0;

        while (Garage.paymentCarQueue.carsInQueue()>0 && i < SettingHandler.paymentSpeed){
            HashMap<String, Object> data = new HashMap<>();
            Car car = Garage.paymentCarQueue.removeCar();

            car.PaymentMethod(reservationCost,pricePerHour);




            data.put("profit", profit);
            data.put("time_passed", Date_time.getTickSinceStart());
            data.put("minutes", Date_time.getMinutes());
            //data.put("cars", numberOfAdhocPassing+numberOfPassCarsPassing);
           // data.put("doubled", car.getisParkedDouble());
            sendEvent(data);



            car.getSection().carLeavesSpot(car);
            i++;
        }
    }
}
