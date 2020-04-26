package org.enzenberger.model.mode;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class StopWatch {
    double timeLimit;
    double currentTime;
    private TimeListener timeListener;
    private Service<Void> backGroundThread;

    public void start() {
        this.currentTime = timeLimit;
        runTimer();
    }

    public void runTimer(){
        if (this.backGroundThread!=null)this.backGroundThread.cancel();
        this.backGroundThread = new Service<>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<>() {
                    @Override
                    protected Void call() throws Exception {
                        while (currentTime >= 0) {
                            Thread.sleep(100);
                            currentTime = currentTime - 0.1;
                            timeListener.notifyTime(currentTime);
                        }
                        return null;
                    }
                };
            }
        };
        this.backGroundThread.setOnSucceeded(event -> timeListener.notifyTimeOver());
        backGroundThread.restart();
    }

    public void setTime(double time) {
        this.timeLimit = time;
    }

    public void pauseTimer() {
        this.backGroundThread.cancel();
    }

    public void resumeTimer() {
        this.backGroundThread.restart();
    }

    public void resetTimer() {
        this.currentTime = timeLimit;
    }

    public void setTimeListener(TimeListener timeOverListener) {
        this.timeListener = timeOverListener;
    }
}
