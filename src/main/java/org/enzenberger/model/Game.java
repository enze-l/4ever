package org.enzenberger.model;

import org.enzenberger.model.player.Player;

public class Game {
    private Board board;

    private Player player1;
    private Player player2;

    public Game(){
        this.board = new Board();
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Board getBoard() {
        return this.board;
    }
}
