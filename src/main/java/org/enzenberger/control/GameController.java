package org.enzenberger.control;

import org.enzenberger.model.CombOrientation;
import org.enzenberger.model.Game;
import org.enzenberger.model.GameState;
import org.enzenberger.model.WinListener;
import org.enzenberger.model.player.Player;

public class GameController implements BoardClickListener, WinListener {
    private Game game;

    public GameController() {
    }

    public void setGame(Game game) {
        this.game = game;
        this.game.getBoard().setWinListener(this);
    }

    @Override
    public void notifyWin(CombOrientation orientation, int xCoordinate, int yCoordinate, Player player) {
        this.game.setGameState(GameState.OVER);
    }

    public void startGame() {
        if (this.game.getGameState() == GameState.INITIALIZING) {
            this.game.getGameMode().setGame(this.game);
            this.game.resetBoard();
            this.game.setGameState(GameState.PLAYING);
        }
    }

    public void stopGame() {
        if (this.game.getGameState() != GameState.OVER) {
            this.game.setGameState(GameState.STOPPED);
        }
    }

    public void pauseGame() {
        if (this.game.getGameState() == GameState.PLAYING) {
            this.game.setGameState(GameState.PAUSED);
        }
    }

    public void resumeGame(){
        if (this.game.getGameState()== GameState.PAUSED){
            this.game.setGameState(GameState.PLAYING);
        }
    }

    public void restartGame() {
        this.game.resetBoard();
        this.game.setGameState(GameState.PLAYING);
    }

    @Override
    public void onColumnClicked(int column) {
        this.game.getGameMode().reactToUserInput(column);
    }
}
