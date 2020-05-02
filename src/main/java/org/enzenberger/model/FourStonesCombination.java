package org.enzenberger.model;

import javafx.beans.property.Property;
import org.enzenberger.model.player.Player;

import java.util.List;

public class FourStonesCombination {
    CombOrientation combOrientation;
    int xCoordinate;
    int yCoordinate;
    private List<Property<Stone>> fields;
    private WinListener winListener;

    public FourStonesCombination(CombOrientation combOrientation, int xCoordinate,
                                 int yCoordinate, List<Property<Stone>> fields) {
        this.combOrientation = combOrientation;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.fields = fields;
        setChangeListener();
    }

    private void setChangeListener() {
        for (Property<Stone> player : fields) {
            player.addListener(event -> checkForWin());
        }
    }

    private void checkForWin() {
        try {
            Player player = fields.get(0).getValue().getPlayer();
            if (player != null
                    && player == fields.get(1).getValue().getPlayer()
                    && player == fields.get(2).getValue().getPlayer()
                    && player == fields.get(3).getValue().getPlayer()) {
                notifyWinListener(player);
            }
        } catch (NullPointerException ignored){}
    }

    private void notifyWinListener(Player player) {
        this.winListener.notifyWin(combOrientation, xCoordinate, yCoordinate, player);
    }

    public void setWinListener(WinListener winListener) {
        this.winListener = winListener;
    }
}
