package org.enzenberger.model;

import javafx.beans.property.SimpleBooleanProperty;
import org.enzenberger.model.mode.GameMode;
import org.enzenberger.model.player.Player;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameMode gameMode;
    private SimpleBooleanProperty gameActive;

    public Game() {
        this.board = new Board();
        this.gameActive = new SimpleBooleanProperty(false);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getOpponent(Player player) {
        if (player.equals(player1)) return player2;
        else {
            return player1;
        }
    }

    public void resetBoard() {
        this.board = new Board();
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public void setGameActive(boolean active) {
        this.gameActive.setValue(active);
    }

    public boolean gameActive() {
        return gameActive.get();
    }

    public SimpleBooleanProperty gameActiveProperty() {
        return gameActive;
    }
}
