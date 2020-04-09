package org.enzenberger.model.mode;

public class ClassicGameMode extends GameMode {

    @Override
    protected void onPlayerChangeHook() {

    }

    @Override
    protected void resetPlayerMove() {

    }

    @Override
    protected void pausePlayerMove() {

    }

    @Override
    public void schedulePlayerMove() {
        this.game.getCurrentPlayer().notifyTurn();
    }
}
