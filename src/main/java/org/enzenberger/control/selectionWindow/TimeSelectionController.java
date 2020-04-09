package org.enzenberger.control.selectionWindow;

import javafx.fxml.FXML;

public class TimeSelectionController extends SelectionWindowController {
    @FXML
    private void startGame() {
        System.out.println("mode chosen");
        this.mainApp.startGame();
    }
}
