package org.enzenberger.view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import org.enzenberger.BoardController;
import org.enzenberger.Player;
import org.enzenberger.model.Board;
import org.enzenberger.model.Game;

import java.util.LinkedList;
import java.util.List;

public class BoardView {
    private static BoardView instance;

    private BoardController boardController;
    private Game game;

    private Group boardView;
    private Scene scene;

    private double gridDistance;
    private double boardStartX;
    private double boardStartY;

    private Circle referenceStone;

    private List<Rectangle> columnButtons;

    private BoardView() {
        this.boardController = new BoardController();
        this.boardView = new Group();
        this.referenceStone = new Circle();
        this.columnButtons = new LinkedList<>();
        for (int width = 0; width < Board.columnCount; width++) {
            this.columnButtons.add(new Rectangle());
        }
    }

    public static BoardView getInstance() {
        if (instance == null) {
            instance = new BoardView();
        }
        return instance;
    }

    public void setGame(Game game) {
        this.game = game;
        this.boardController.setBoard(this.game.getBoard());
        for (int width=0; width<Board.columnCount; width++){
            for (int height=0; height<Board.rowCount; height++){
                int xPosition = width;
                int yPosition = height;
                this.game.getBoard().getFieldProperty(width, height).addListener(
                        (observableValue, player, t1) -> animateStoneDrop(xPosition, yPosition, player)
                );
            }
        }
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        this.scene.heightProperty().addListener(observable -> displayCurrentBoard());
        this.scene.widthProperty().addListener(observable -> displayCurrentBoard());
    }

    private void displayCurrentBoard() {
        double sceneWidth = this.scene.getWidth();
        double sceneHeight = this.scene.getHeight();
        setReferenceValues(sceneWidth, sceneHeight);
        this.boardView.getChildren().clear();
        this.boardView.getChildren().addAll(getStones());
        this.boardView.getChildren().add(getWallWithHoles(new Rectangle(0, 0, sceneWidth, sceneHeight)));
        this.boardView.getChildren().add(getColumnButtons());
    }

    private void setReferenceValues(double sceneWidth, double sceneHeight) {
        if (sceneWidth / Board.columnCount > sceneHeight / Board.rowCount) {
            gridDistance = sceneHeight / (Board.rowCount + Board.boarderDistance);
            boardStartX = (sceneWidth - gridDistance * (Board.columnCount + Board.boarderDistance)) / 2 + gridDistance * Board.boarderDistance / 2;
            boardStartY = gridDistance * Board.boarderDistance / 2;
        } else {
            gridDistance = sceneWidth / (Board.columnCount + Board.boarderDistance);
            boardStartY = (sceneHeight - gridDistance * (Board.rowCount + Board.boarderDistance)) / 2 + gridDistance * Board.boarderDistance / 2;
            boardStartX = gridDistance * Board.boarderDistance / 2;
        }
        this.referenceStone.setRadius(gridDistance * Board.columnCount / 16);
    }

    private List<Circle> getStones() {
        List<Circle> list = new LinkedList<>();
        for (int width = 0; width < Board.columnCount; width++) {
            for (int height = 0; height < Board.rowCount; height++) {
                Player player = this.game.getBoard().getField(width, height);
                if (player != null) {
                    list.add(new Circle(boardStartX + gridDistance * width + gridDistance / 2,
                            boardStartY + gridDistance * height + gridDistance / 2,
                            referenceStone.getRadius(), player.getColor()));
                }
            }
        }
        return list;
    }

    private Shape getWallWithHoles(Shape wall) {
        for (double width = boardStartX; width < boardStartX + gridDistance * Board.columnCount - 1; width = width + gridDistance) {
            this.referenceStone.setCenterX(width + gridDistance / 2);
            for (double height = boardStartY; height < boardStartY + gridDistance * Board.rowCount - 1; height = height + gridDistance) {
                this.referenceStone.setCenterY(height + gridDistance / 2);
                wall = Shape.subtract(wall, this.referenceStone);
            }
        }
        wall.setFill(Color.LIGHTGREY);
        return wall;
    }

    private Group getColumnButtons() {
        Group buttons = new Group();
        for (int buttonIndex = 0; buttonIndex < columnButtons.size(); buttonIndex++) {
            Rectangle button = columnButtons.get(buttonIndex);
            button.setX(boardStartX + gridDistance * buttonIndex);
            button.setY(boardStartY);
            button.setWidth(gridDistance);
            button.setHeight(gridDistance * Board.rowCount);
            button.setFill(Color.TRANSPARENT);
            buttons.getChildren().add(button);
        }
        return buttons;
    }

    public Node getBoardGroup() {
        displayCurrentBoard();
        return boardView;
    }

    private void animateStoneDrop(int xPosition, int yPosition, Player player) {
        Circle stone = new Circle(boardStartX + xPosition * gridDistance + gridDistance / 2,
                boardStartY, this.referenceStone.getRadius(), player.getColor());
        this.boardView.getChildren().add(0, stone);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(stone.translateXProperty(), 0),
                        new KeyValue(stone.translateYProperty(), 0)),
                new KeyFrame(new Duration(50 * (yPosition + 1)),
                        new KeyValue(stone.translateXProperty(), 0),
                        new KeyValue(stone.translateYProperty(), yPosition * gridDistance + gridDistance / 2))
        );
        timeline.play();
    }
}
