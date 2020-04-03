package org.enzenberger.model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import org.enzenberger.model.player.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Board {
    public static final int columnCount = 7;
    public static final int rowCount = 6;
    public static final double boarderDistance = 0.125;

    List<List<Property<Player>>> fields;
    List<List<Player>> groundTruth;

    List<FourInARowDetector> rows;

    public Board() {
        fields = new ArrayList<>();
        groundTruth = new ArrayList<>();
        for (int row = 0; row < rowCount; row++) {
            fields.add(new ArrayList<>());
            groundTruth.add(new ArrayList<>());
            for (int column = 0; column < columnCount; column++) {
                fields.get(row).add(new SimpleObjectProperty<>(null));
                groundTruth.get(row).add(null);
                addFourInARowDetectors(column, row);
            }
        }
    }

    private void addFourInARowDetectors(int column, int row) {
        addFourUp(column, row);
        addFourDown(column, row);
        addFourHorizontal(column, row);
        addFourVertical(column, row);
    }

    private void addFourVertical(int column, int row) {
        if (row<rowCount-3){
            List<Property<Player>> fourRowFields = new LinkedList<>();
            for (int field = 0; field<4; field++){
                fourRowFields.add(this.fields.get(row+field).get(column));
            }
            this.rows.add(new FourInARowDetector(column, row, fourRowFields));
        }
    }

    private void addFourHorizontal(int column, int row) {
        if (column<columnCount-3){
            List<Property<Player>> fourRowFields = new LinkedList<>();
            for (int field = 0; field<4; field++){
                fourRowFields.add(this.fields.get(row).get(column+field));
            }
            this.rows.add(new FourInARowDetector(column, row, fourRowFields));
        }
    }

    private void addFourDown(int column, int row) {
        if (column<columnCount-3 && row<rowCount-3){
            List<Property<Player>> fourRowFields = new LinkedList<>();
            for (int field = 0; field<4; field++){
                fourRowFields.add(this.fields.get(row+field).get(column+field));
            }
            this.rows.add(new FourInARowDetector(column, row, fourRowFields));
        }
    }

    private void addFourUp(int column, int row) {
        if (column<columnCount-3 && row>3){
            List<Property<Player>> fourRowFields = new LinkedList<>();
            for (int field=0; field<4; field++){
                fourRowFields.add(this.fields.get(row-field).get(column+field));
            }
            this.rows.add(new FourInARowDetector(column, row, fourRowFields));
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
