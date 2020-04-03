package org.enzenberger.model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import org.enzenberger.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int columnCount = 7;
    public static final int rowCount = 6;
    public static final double boarderDistance = 0.125;

    List<List<Property<Player>>> fields;
    List<List<Player>> groundTruth;

    List<FourInARow> rows;

    public Board() {
        fields = new ArrayList<>();
        groundTruth = new ArrayList<>();
        for (int row = 0; row < rowCount; row++) {
            fields.add(new ArrayList<>());
            groundTruth.add(new ArrayList<>());
            for (int column = 0; column < columnCount; column++) {
                fields.get(row).add(new SimpleObjectProperty<>(null));
                groundTruth.get(row).add(null);
            }
        }
    }

    public Player getField(int width, int height) {
        return fields.get(height).get(width).getValue();
    }

    public void setField(Player player, int width, int height) {
        fields.get(height).get(width).setValue(player);
        groundTruth.get(height).set(width, player);
    }

    public Property<Player> getFieldProperty(int width, int height) {
        return fields.get(height).get(width);
    }
}
