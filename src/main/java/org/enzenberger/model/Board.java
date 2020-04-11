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

    List<FourStonesCombination> rows;

    public Board() {
        fields = new ArrayList<>();
        groundTruth = new ArrayList<>();
        this.rows = new LinkedList<>();
        initFields();
        initFourInARowDetectors();
    }

    public void setWinListener(WinListener winListener) {
        for (FourStonesCombination combination : rows) {
            combination.setWinListener(winListener);
        }
    }

    public void clearBoard() {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                fields.get(row).get(column).setValue(null);
                groundTruth.get(row).set(column, null);
            }
        }
    }

    private void initFields() {
        for (int row = 0; row < rowCount; row++) {
            fields.add(new ArrayList<>());
            groundTruth.add(new ArrayList<>());
            for (int column = 0; column < columnCount; column++) {
                fields.get(row).add(new SimpleObjectProperty<>(null));
                groundTruth.get(row).add(null);
            }
        }
    }

    private void initFourInARowDetectors() {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                addFourInARowDetectorsForField(column, row);
            }
        }
    }

    private void addFourInARowDetectorsForField(int column, int row) {
        addFourUp(column, row);
        addFourDown(column, row);
        addFourHorizontal(column, row);
        addFourVertical(column, row);
    }

    private void addFourVertical(int column, int row) {
        if (row < rowCount - 3) {
            List<Property<Player>> fourRowFields = new LinkedList<>();
            for (int field = 0; field < 4; field++) {
                fourRowFields.add(this.fields.get(row + field).get(column));
            }
            this.rows.add(new FourStonesCombination(CombOrientation.VERTICAL, column, row, fourRowFields));
        }
    }

    private void addFourHorizontal(int column, int row) {
        if (column < columnCount - 3) {
            List<Property<Player>> fourRowFields = new LinkedList<>();
            for (int field = 0; field < 4; field++) {
                fourRowFields.add(this.fields.get(row).get(column + field));
            }
            this.rows.add(new FourStonesCombination(CombOrientation.HORIZONTAL, column, row, fourRowFields));
        }
    }

    private void addFourDown(int column, int row) {
        if (column < columnCount - 3 && row < rowCount - 3) {
            List<Property<Player>> fourRowFields = new LinkedList<>();
            for (int field = 0; field < 4; field++) {
                fourRowFields.add(this.fields.get(row + field).get(column + field));
            }
            this.rows.add(new FourStonesCombination(CombOrientation.RIGHT_DOWN, column, row, fourRowFields));
        }
    }

    private void addFourUp(int column, int row) {
        if (column < columnCount - 3 && row > 3) {
            List<Property<Player>> fourRowFields = new LinkedList<>();
            for (int field = 0; field < 4; field++) {
                fourRowFields.add(this.fields.get(row - field).get(column + field));
            }
            this.rows.add(new FourStonesCombination(CombOrientation.RIGHT_UP, column, row, fourRowFields));
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

    public List<Player> getRow(int rowNumber) {
        return groundTruth.get(rowNumber);
    }

    public void removeLastRow() {
        this.groundTruth.remove(groundTruth.size() - 1);
        LinkedList<Player> newRow = new LinkedList<>();
        for (int column = 0; column < 7; column++) {
            newRow.add(null);
        }
        this.groundTruth.add(0, newRow);
        mirrorTruth();
    }

    private void mirrorTruth() {
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                this.fields.get(row).get(column).setValue(this.groundTruth.get(row).get(column));
            }
        }
    }
}
