package org.enzenberger.control;

import org.enzenberger.exceptions.ColumnOverflowException;
import org.enzenberger.model.Game;

public class GameController implements BoardClickListener{
    private Game game;
    private BoardController boardController;

    public GameController(){}

    public void setGame(Game game){
        this.game = game;
        this.boardController = new BoardController();
        this.boardController.setBoard(this.game.getBoard());
    }

    public void onColumnClicked(int column) {
        try {
            boardController.dropStone(this.game.getCurrentPlayer(), column);
            changeActivePlayer();
        } catch (ColumnOverflowException e) {
            e.printStackTrace();
        }
    }

    private void changeActivePlayer(){
        this.game.setCurrentPlayer(this.game.getOpponent(this.game.getCurrentPlayer()));
    }
}
