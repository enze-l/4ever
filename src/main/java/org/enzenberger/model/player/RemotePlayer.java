package org.enzenberger.model.player;

import javafx.scene.paint.Color;

public class RemotePlayer extends Player {
    public RemotePlayer(Color color) {
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
