package org.enzenberger.control;

import org.enzenberger.exceptions.ColumnOverflowException;
import org.enzenberger.model.Board;
import org.enzenberger.model.player.Player;

public class BoardController {
    Board board;

    public BoardController() {

    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void dropStone(Player player, int column) throws ColumnOverflowException {
        if (!dropPossible(column)) throw new ColumnOverflowException();
        int row = 0;
        while (row + 1 < Board.rowCount&&board.getField(column, row + 1) == null) {
            row++;
        }
        board.setField(player, column, row);
    }

    private boolean dropPossible(int column) {
        return this.board.getField(column, 0) == null;
    }
}