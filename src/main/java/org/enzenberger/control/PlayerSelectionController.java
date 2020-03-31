package org.enzenberger.control;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import org.enzenberger.MainApp;
import org.enzenberger.model.Game;
import org.enzenberger.model.player.LocalPlayer;
import org.enzenberger.model.player.RemotePlayer;

public class PlayerSelectionController implements SelectionWindow{

    private Game game;
    private MainApp mainApp;

    public PlayerSelectionController(){}

    public void setGame(Game game){
        this.game = game;
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    private void setSinglePlayerMode(){
        this.game.setPlayer1(new LocalPlayer(Color.BLUE));
        this.mainApp.showVirtualPlayerSelection();
    }

    @FXML
    private void setDualPlayerMode(){
        this.game.setPlayer1(new LocalPlayer(Color.BLUE));
        this.game.setPlayer2(new LocalPlayer(Color.RED));
        this.mainApp.showModeSelection();
    }

    @FXML
    private void setOnlinePlayerMode(){
        this.game.setPlayer1(new LocalPlayer(Color.BLUE));
        this.game.setPlayer2(new RemotePlayer(Color.RED));
        this.mainApp.showConnectionPanel();
    }

    @FXML
    private void exitGame(){
        this.mainApp.exit();
    }
}
