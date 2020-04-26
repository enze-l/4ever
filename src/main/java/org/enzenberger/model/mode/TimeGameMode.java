package org.enzenberger.model.mode;

import org.enzenberger.exceptions.ColumnOverflowException;
import org.enzenberger.model.GameState;

public class TimeGameMode extends GameMode implements TimeListener {
    private StopWatch stopWatch;

    public void setTimeLimit(double time) {
        this.stopWatch = new StopWatch();
        this.stopWatch.setTimeListener(this);
        this.stopWatch.setTime(time);
        this.stopWatch.start();
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
        boolean turnDone = false;
        while (!turnDone && this.game.getGameState() == GameState.PLAYING) {
            try {
                this.boardController.dropStone(this.game.getCurrentPlayer(), (int) (Math.random() * 7));
                turnDone = true;
                changeActivePlayer();
            } catch (ColumnOverflowException ignored) {
            }
        }
    }

    @Override
    public void notifyTime(double time) {
        //todo
    }
}
