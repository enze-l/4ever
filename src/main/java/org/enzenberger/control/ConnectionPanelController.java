package org.enzenberger.control;

import org.enzenberger.MainApp;
import org.enzenberger.model.Game;

public class ConnectionPanelController implements SelectionWindow {
    private Game game;
    private MainApp mainApp;

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
