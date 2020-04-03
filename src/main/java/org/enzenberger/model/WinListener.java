package org.enzenberger.model;

import org.enzenberger.model.player.Player;

public interface WinListener {
    void notifyWin(CombOrientation orientation, int xCoordinate, int yCoordinate, Player player);
}
