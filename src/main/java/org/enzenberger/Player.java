package org.enzenberger;


import javafx.scene.paint.Color;

public abstract class Player {
    private Color color;

    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
