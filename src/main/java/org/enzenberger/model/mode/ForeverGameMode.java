package org.enzenberger.model.mode;

import org.enzenberger.model.GameState;
import org.enzenberger.model.Stone;

import java.util.List;

public class ForeverGameMode extends GameMode {

    @Override
    protected void onPlayerChangeHook() {
        if(this.game.getGameState()== GameState.PLAYING) {
            List<Stone> upperBound = this.game.getBoard().getRow(2);
            boolean allFieldsOccupied = true;
            for (Stone field : upperBound) {
                if (field == null) {
                    allFieldsOccupied = false;
                    break;
                }
            }
            if (allFieldsOccupied) this.game.getBoard().removeLastRow();
        }
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
