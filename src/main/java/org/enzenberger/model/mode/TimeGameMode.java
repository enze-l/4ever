package org.enzenberger.model.mode;

import org.enzenberger.exceptions.ColumnOverflowException;

public class TimeGameMode extends GameMode implements TimeListener {
    private StopWatch stopWatch;

    public void setTimeLimit(int time) {
        this.stopWatch = new StopWatch();
        this.stopWatch.setTimeListener(this);
        this.stopWatch.setTime(5);
    }

    @Override
    protected void onPlayerChangeHook() {
        this.stopWatch.start();
    }

    @Override
    protected void resetPlayerMove() {
        this.stopWatch.pauseTimer();
        this.stopWatch.resetTimer();
    }

    @Override
    protected void pausePlayerMove() {
        this.stopWatch.pauseTimer();
    }

    @Override
    public void schedulePlayerMove() {
        this.stopWatch.resumeTimer();
    }

    @Override
    public void notifyTimeOver() {
        boolean turnDone= false;
        while (!turnDone) {
            try {
                this.boardController.dropStone(this.game.getCurrentPlayer(), (int) (Math.random() * 7));
                turnDone = true;
                changeActivePlayer();
            } catch (ColumnOverflowException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void notifyTime(double time) {
        //todo
    }
}
