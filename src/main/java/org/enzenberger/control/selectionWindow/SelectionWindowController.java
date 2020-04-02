package org.enzenberger.control.selectionWindow;

import javafx.fxml.FXML;
import org.enzenberger.MainApp;
import org.enzenberger.model.Game;

public abstract class SelectionWindowController {
    Game game;
    MainApp mainApp;

    public void setGame(Game game){
        this.game = game;
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    public void goBack(){
        this.mainApp.showPreviousSelection();
    }
}
