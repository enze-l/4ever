package org.enzenberger.control;

import javafx.scene.paint.Color;
import org.enzenberger.exceptions.ColumnOverflowException;
import org.enzenberger.model.Game;
import org.enzenberger.model.player.LocalPlayer;

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
            boardController.dropStone(new LocalPlayer(Color.BLUE), column);
        } catch (ColumnOverflowException e) {
            e.printStackTrace();
        }
    }
}
