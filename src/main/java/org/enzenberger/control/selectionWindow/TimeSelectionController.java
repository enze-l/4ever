package org.enzenberger.control.selectionWindow;

import javafx.fxml.FXML;
import org.enzenberger.model.mode.TimeGameMode;

public class TimeSelectionController extends SelectionWindowController {
    @FXML
    private void startGame() {
        TimeGameMode timeGameMode = (TimeGameMode) this.game.getGameMode();
        timeGameMode.setTimeLimit(3);
        this.mainApp.startGame();
    }
}
