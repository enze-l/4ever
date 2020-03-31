package org.enzenberger.control;

import javafx.fxml.FXML;
import org.enzenberger.MainApp;
import org.enzenberger.model.Game;
import org.enzenberger.model.mode.ClassicGameMode;
import org.enzenberger.model.mode.ForeverGameMode;
import org.enzenberger.model.mode.TimeGameMode;

public class ModeSelectionController implements SelectionWindow{
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
        this.game.setGameMode(new ClassicGameMode());
        this.mainApp.hideSelectionWindow();
    }

    @FXML
    private void setGameModeTime(){
        this.game.setGameMode(new TimeGameMode());
        this.mainApp.hideSelectionWindow();
    }

    @FXML
    private void setGameModeForever(){
        this.game.setGameMode(new ForeverGameMode());
        this.mainApp.hideSelectionWindow();
    }

    @FXML
    private void goBack(){
        this.mainApp.showPlayerSelection();
    }
}
