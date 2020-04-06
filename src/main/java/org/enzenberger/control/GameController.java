package org.enzenberger.control;

import org.enzenberger.exceptions.ColumnOverflowException;
import org.enzenberger.model.*;
import org.enzenberger.model.player.Player;

public class GameController implements BoardClickListener, WinListener {
    private Game game;
    private BoardController boardController;

    public GameController() {
    }

    public void setGame(Game game) {
        this.game = game;
        this.boardController = new BoardController();
        this.boardController.setBoard(this.game.getBoard());
        this.game.getBoard().setWinListener(this);
    }

    @Override
    public void onColumnClicked(int column) {
        if (this.game.getGameState() == GameState.PLAYING) {
            try {
                boardController.dropStone(this.game.getCurrentPlayer(), column);
                changeActivePlayer();
            } catch (ColumnOverflowException ignored) {
            }
        }
    }

    private void changeActivePlayer() {
        this.game.setCurrentPlayer(this.game.getOpponent(this.game.getCurrentPlayer()));
    }

    @Override
    public void notifyWin(CombOrientation orientation, int xCoordinate, int yCoordinate, Player player) {
        this.game.setGameState(GameState.OVER);
    }

    public void startGame() {
        if (this.game.getGameState() == GameState.PAUSED)
            this.game.setGameState(GameState.PLAYING);
    }

    public void stopGame() {
        this.game.setGameState(GameState.STOPPED);
    }

    public void pauseGame() {
        this.game.setGameState(GameState.PAUSED);
    }

    public void restartGame(){
        this.game.resetBoard();
        this.game.setGameState(GameState.PLAYING);
    }
}
