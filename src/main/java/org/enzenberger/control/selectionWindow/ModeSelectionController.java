package org.enzenberger.control.selectionWindow;

import javafx.fxml.FXML;
import org.enzenberger.model.mode.ClassicGameMode;
import org.enzenberger.model.mode.ForeverGameMode;
import org.enzenberger.model.mode.TimeGameMode;

public class ModeSelectionController extends SelectionWindow{
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
