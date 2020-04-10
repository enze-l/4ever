package org.enzenberger.model.mode;

import org.enzenberger.control.BoardController;
import org.enzenberger.exceptions.ColumnOverflowException;
import org.enzenberger.model.Game;
import org.enzenberger.model.GameState;
import org.enzenberger.model.GameStateListener;

public abstract class GameMode implements GameStateListener {

    protected Game game;
    protected BoardController boardController;

    public void setGame(Game game) {
        this.game = game;
        this.boardController = new BoardController();
        this.boardController.setBoard(this.game.getBoard());
        this.game.getGameStateProperty().addListener(observable -> notifyGameStateChange(this.game.getGameState()));
    }

    public void reactToUserInput(int column){
        if (this.game.getGameState() == GameState.PLAYING && this.game.getCurrentPlayer().isLocalPlayer()) {
            try {
                this.boardController.dropStone(this.game.getCurrentPlayer(), column);
                changeActivePlayer();
            } catch (ColumnOverflowException e) {
                e.printStackTrace();
            }
        }
    }

    protected void changeActivePlayer(){
        onPlayerChangeHook();
        this.game.setCurrentPlayer(this.game.getOpponent(this.game.getCurrentPlayer()));
        this.game.getCurrentPlayer().notifyTurn();
    }

    protected abstract void onPlayerChangeHook();

    @Override
    public void notifyGameStateChange(GameState gameState){
         switch (gameState){
             case PLAYING:
                 schedulePlayerMove();
                 break;
             case PAUSED:
                 pausePlayerMove();
                 break;
             default:
                 resetPlayerMove();
         }
    }

    protected abstract void resetPlayerMove();

    protected abstract void pausePlayerMove();

    public abstract void schedulePlayerMove();
}
