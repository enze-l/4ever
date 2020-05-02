package org.enzenberger.model;

import org.enzenberger.model.player.Player;

public class Stone {
    private final Player player;
    private int lastXPosition;
    private int lastYPosition;

    public Stone(Player player, int lastXPosition, int lastYPosition) {
        this.player = player;
        this.lastXPosition = lastXPosition;
        this.lastYPosition = lastYPosition;
    }

    public int getLastXPosition() {
        return lastXPosition;
    }

    public int getLastYPosition() {
        return lastYPosition;
    }

    public void setLastPosition(int lastXPosition, int lastYPosition){
        this.lastXPosition = lastXPosition;
        this.lastYPosition = lastYPosition;
    }

    public Player getPlayer() {
        return player;
    }
}
