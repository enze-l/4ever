package org.enzenberger.control;

import javafx.fxml.FXML;
import org.enzenberger.MainApp;
import org.enzenberger.model.Game;

public class ModeSelectionController {
    private Game game;
    private MainApp mainApp;

    public ModeSelectionController(){}

    public void setGame(Game game) {
        this.game = game;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void setGameModeClassic(){
        //todo
    }

    @FXML
    private void setGameModeTime(){
        //todo
    }

    @FXML
    private void setGameModeForever(){
        //todo
    }
}
