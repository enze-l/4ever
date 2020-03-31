package org.enzenberger.control.selectionWindow;

import org.enzenberger.MainApp;
import org.enzenberger.model.Game;

public abstract class SelectionWindow {
    Game game;
    MainApp mainApp;

    public void setGame(Game game){
        this.game = game;
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
}
