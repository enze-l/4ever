package org.enzenberger.model.mode;

import org.enzenberger.exceptions.ColumnOverflowException;
import org.enzenberger.model.GameState;

public class ClassicGameMode extends GameMode {
    @Override
    public void requestPlayerMove(int column) {
        if (this.game.getGameState() == GameState.PLAYING && this.game.getCurrentPlayer().isLocalPlayer()) {
            try {
                this.boardController.dropStone(this.game.getCurrentPlayer(), column);
                changeActivePlayer();
            } catch (ColumnOverflowException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void startPlayerMove() {
        this.game.getCurrentPlayer().notifyTurn();

    }
}
