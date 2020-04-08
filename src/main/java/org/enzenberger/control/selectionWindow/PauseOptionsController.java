package org.enzenberger.control.selectionWindow;

import javafx.fxml.FXML;

public class PauseOptionsController extends SelectionWindowController {
    @FXML
    private void onRestartGameClicked() {
        this.gameController.restartGame();
        this.mainApp.handleEscapeAction();
    }

    @FXML
    private void onContinueGameClicked() {
        this.gameController.resumeGame();
        this.mainApp.handleEscapeAction();
    }

    @FXML
    private void onExitGameClicked() {
        this.gameController.stopGame();
        this.mainApp.setGameActive(false);
        this.mainApp.showPlayerSelection();
    }
}
