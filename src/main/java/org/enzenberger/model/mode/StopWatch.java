package org.enzenberger.model.mode;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class StopWatch {
    double timeLimit;
    double currentTime;
    private TimeListener timeListener;
    private boolean timeRunning;
    private Service<Void> backGroundThread;

    public void start() {
        this.backGroundThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        currentTime = timeLimit;
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

    /*@SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        this.timeRunning = false;
        try {
            synchronized (this) {
                while (true) {
                    while (currentTime >= 0 && timeRunning) {
                        this.wait(100);
                        this.currentTime = this.currentTime - 0.1;
                        this.timeListener.notifyTime(currentTime);
                        System.out.println(currentTime);
                    }
                    this.timeListener.notifyTimeOver();
                    this.wait();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("fatal StopWatch error");
        }
    }
*/
    public void setTime(int time) {
        this.timeLimit = time;
    }

    public void pauseTimer() {
        this.timeRunning = false;
    }

    public void resumeTimer() {
        this.timeRunning = true;
        synchronized (this) {
            this.notify();
        }
    }

    public void resetTimer() {
        this.currentTime = timeLimit;
        synchronized (this) {
            this.notify();
        }
    }

    public void setTimeListener(TimeListener timeOverListener) {
        this.timeListener = timeOverListener;
    }
}
