package org.enzenberger.control;

import javafx.scene.paint.Color;
import org.enzenberger.exceptions.ColumnOverflowException;
import org.enzenberger.model.Game;
import org.enzenberger.model.LocalPlayer;

public class GameController {
    private Game game;

    public GameController(){}

    public void setGame(Game game){
        this.game = game;
    }

    public void onColumnClicked(int column, BoardController boardController) {
        try {
            boardController.dropStone(new LocalPlayer(Color.BLUE), column);
        } catch (ColumnOverflowException e) {
            e.printStackTrace();
        }
    }
}