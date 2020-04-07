package org.enzenberger.model.player;


import javafx.scene.paint.Color;

public class LocalPlayer extends Player {

    public LocalPlayer(Color color) {
        super(color);
    }

    @Override
    public boolean isLocalPlayer() {
        return true;
    }

    @Override
    public void notifyTurn() {

    }
}
