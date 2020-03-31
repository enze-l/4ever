package org.enzenberger.control.selectionWindow;

import javafx.fxml.FXML;

public class VirtualPlayerSelectionController extends SelectionWindow {
     @FXML
    private void goBack(){
        this.mainApp.showPlayerSelection();
     }
}
