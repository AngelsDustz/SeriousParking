package nl.SeriousParking.Parkeersimulator.model;

import javafx.application.Platform;


public class Runtime extends Model implements Runnable {
   // private Date now = new Date();
    private Long startTime;
    private Long runTime;
    private boolean run;

    public Runtime() {
        startTime   = System.currentTimeMillis();
        runTime     = 0L;

        new Thread(this).start();
        run = true;
    }

    public Long getRunTime() {
        return runTime;
    }

    @Override
    public void run() {
        while (run) {
            runTime = System.currentTimeMillis()-startTime;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    notifyViews();
                }
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
