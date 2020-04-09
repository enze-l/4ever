package org.enzenberger.control.selectionWindow;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import org.enzenberger.model.GameState;
import org.enzenberger.model.player.LocalPlayer;
import org.enzenberger.model.player.RemotePlayer;

public class PlayerSelectionController extends SelectionWindowController {
    @FXML
    private void setSinglePlayerMode(){
        this.game.setPlayer1(new LocalPlayer(Color.BLUE));
        this.game.setGameState(GameState.INITIALIZING);
        this.mainApp.showVirtualPlayerSelection();
    }

    @FXML
    private void setDualPlayerMode(){
        this.game.setPlayer1(new LocalPlayer(Color.BLUE));
        this.game.setPlayer2(new LocalPlayer(Color.RED));
        this.game.setGameState(GameState.INITIALIZING);
        this.mainApp.showModeSelection();
    }

    @FXML
    private void setOnlinePlayerMode(){
        this.game.setPlayer1(new LocalPlayer(Color.BLUE));
        this.game.setPlayer2(new RemotePlayer(Color.RED));
        this.game.setGameState(GameState.INITIALIZING);
        this.mainApp.showConnectionPanel();
    }
}
