package org.enzenberger.model;

import javafx.beans.property.Property;
import org.enzenberger.model.player.Player;

import java.util.List;

public class FourInARowDetector {
    int xCoordinate;
    int yCoordinate;
    private List<Property<Player>> fields;

    public FourInARowDetector(int xCoordinate, int yCoordinate, List<Property<Player>> fields){
        this.xCoordinate = xCoordinate;
        this.yCoordinate= yCoordinate;
        this.fields = fields;
    }


}