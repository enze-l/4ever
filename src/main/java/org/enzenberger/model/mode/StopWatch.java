package org.enzenberger.model.mode;

public class StopWatch implements Runnable {
    double timeLimit;
    double currentTime;
    private TimeListener timeListener;
    private boolean timeRunning;

    @SuppressWarnings("InfiniteLoopStatement")
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
