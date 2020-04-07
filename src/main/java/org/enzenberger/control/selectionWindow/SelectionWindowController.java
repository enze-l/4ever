package org.enzenberger.control.selectionWindow;

import javafx.fxml.FXML;
import org.enzenberger.MainApp;
import org.enzenberger.control.GameController;
import org.enzenberger.model.Game;

public abstract class SelectionWindowController {
    Game game;
    MainApp mainApp;
    GameController gameController;

    public void setGame(Game game){
        this.game = game;
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setGameController(GameController gameController){
        this.gameController = gameController;
    }

    @FXML
    public void goBack(){
        this.mainApp.handleGoBackAction();
    }
}
