package org.enzenberger.model.mode;

import org.enzenberger.model.player.Player;

import java.util.List;

public class ForeverGameMode extends GameMode {

    @Override
    protected void onPlayerChangeHook() {
        List<Player> upperBound = this.game.getBoard().getRow(2);
        boolean allFieldsOccupied = true;
        for (Player field:upperBound){
            if (field == null){
                allFieldsOccupied= false;
                break;
            }
        }
        if (allFieldsOccupied) this.game.getBoard().removeLastRow();
    }

    @Override
    protected void resetPlayerMove() {

    }

    @Override
    protected void pausePlayerMove() {

    }

    @Override
    public void schedulePlayerMove() {

    }
}
