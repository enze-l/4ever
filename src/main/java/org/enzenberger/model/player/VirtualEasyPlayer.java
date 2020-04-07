package org.enzenberger.model.player;

import javafx.scene.paint.Color;

public class VirtualEasyPlayer extends Player {
    public VirtualEasyPlayer(Color color) {
        super(color);
    }

    @Override
    public boolean isLocalPlayer() {
        return false;
    }

    @Override
    public void notifyTurn() {

    }
}
