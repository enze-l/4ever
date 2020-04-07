package org.enzenberger.model.player;

import javafx.scene.paint.Color;

public class VirtualMediumPlayer extends Player {
    public VirtualMediumPlayer(Color color) {
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
