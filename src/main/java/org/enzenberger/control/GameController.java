package org.enzenberger.control;

import org.enzenberger.exceptions.ColumnOverflowException;
import org.enzenberger.model.CombOrientation;
import org.enzenberger.model.Game;
import org.enzenberger.model.GameState;
import org.enzenberger.model.WinListener;
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
}
