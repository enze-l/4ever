package org.enzenberger.control;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import org.enzenberger.model.Game;
import org.enzenberger.model.player.LocalPlayer;
import org.enzenberger.model.player.RemotePlayer;
import org.enzenberger.model.player.VirtualPlayer;

public class SelectionController {

    private Game game;

    public SelectionController(){}

    @FXML
    private void setGameModeSinglePlayer(){
        this.game.setPlayer1(new LocalPlayer(Color.BLUE));
        this.game.setPlayer2(new VirtualPlayer(Color.RED));
    }

    @FXML
    private void setGameModeDualPlayer(){
        this.game.setPlayer1(new LocalPlayer(Color.BLUE));
        this.game.setPlayer2(new LocalPlayer(Color.RED));
    }

    @FXML
    private void setGameModeOnline(){
        this.game.setPlayer1(new LocalPlayer(Color.BLUE));
        this.game.setPlayer2(new RemotePlayer(Color.RED));
    }

    public void setGame(Game game){
        this.game = game;
    }
}
