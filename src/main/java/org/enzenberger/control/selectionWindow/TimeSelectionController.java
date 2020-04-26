package org.enzenberger.control.selectionWindow;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import org.enzenberger.model.mode.TimeGameMode;

public class TimeSelectionController extends SelectionWindowController {
    @FXML private Slider timeSlider;
    @FXML private Label timeDisplay;

    @FXML
    private void initialize(){
        timeDisplay.textProperty().bind(Bindings.format("%.0f seconds", timeSlider.valueProperty()));
    }

    @FXML
    private void startGame() {
        TimeGameMode timeGameMode = (TimeGameMode) this.game.getGameMode();
        timeGameMode.setTimeLimit(timeSlider.getValue());
        this.mainApp.startGame();
    }
}
