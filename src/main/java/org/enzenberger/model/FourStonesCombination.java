package org.enzenberger.model;

import javafx.beans.property.Property;
import org.enzenberger.model.player.Player;

import java.util.List;

public class FourStonesCombination {
    CombOrientation combOrientation;
    int xCoordinate;
    int yCoordinate;
    private List<Property<Player>> fields;
    private WinListener winListener;

    public FourStonesCombination(CombOrientation combOrientation, int xCoordinate,
                                 int yCoordinate, List<Property<Player>> fields) {
        this.combOrientation = combOrientation;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.fields = fields;
        setChangeListener();
    }

    private void setChangeListener() {
        for (Property<Player> player : fields) {
            player.addListener(event -> checkForWin());
        }
    }

    private void checkForWin() {
        Player player = fields.get(0).getValue();
        if (player != null
                && player == fields.get(1).getValue()
                && player == fields.get(2).getValue()
                && player == fields.get(3).getValue()) {
            notifyWinListener(player);
        }
    }

    private void notifyWinListener(Player player) {
        this.winListener.notifyWin(combOrientation, xCoordinate, yCoordinate, player);
    }

    public void setWinListener(WinListener winListener) {
        this.winListener = winListener;
    }
}
