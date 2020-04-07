package org.enzenberger.model.player;

import javafx.scene.paint.Color;

public class VirtualHardPlayer extends Player {
    public VirtualHardPlayer(Color color) {
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
