package org.enzenberger.model.mode;

import org.enzenberger.control.BoardController;
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
    }

    public abstract void requestPlayerMove(int column);

    public abstract void startPlayerMove();

    protected void changeActivePlayer(){
        this.game.setCurrentPlayer(this.game.getOpponent(this.game.getCurrentPlayer()));
    }

    @Override
    public void notifyGameStateChange(GameState gameState){

    }
}
