package org.enzenberger.control.selectionWindow;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import org.enzenberger.model.player.Player;
import org.enzenberger.model.player.VirtualHardPlayer;
import org.enzenberger.model.player.VirtualMediumPlayer;

public class VirtualPlayerSelectionController extends SelectionWindowController {
    @FXML
    private void setVirtualPlayerEasy() {
        setVirtualPlayer(new VirtualHardPlayer(Color.RED));
    }

    @FXML
    private void setVirtualPlayerMedium() {
        setVirtualPlayer(new VirtualMediumPlayer(Color.RED));
    }

    @FXML
    private void setVirtualPlayerHard() {
        setVirtualPlayer(new VirtualHardPlayer(Color.RED));
    }

    private void setVirtualPlayer(Player virtualPlayer) {
        this.game.setPlayer2(virtualPlayer);
        this.mainApp.showModeSelection();
    }

    @FXML
    private void goBack() {
        this.mainApp.showPlayerSelection();
    }
}
