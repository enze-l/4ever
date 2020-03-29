package org.enzenberger;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * JavaFX App
 */
public class BoardViewOld extends Application {

    public static final int columnCount = 7;
    public static final int rowCount = 6;
    public static final double boarderDistance = 0.125;

    private Stage stage;
    private Scene scene;
    private Group root;

    private double gridDistance;
    private double boardStartX;
    private double boardStartY;

    private Circle referenceStone;

    private List<List<Player>> board;
    private List<Rectangle> columnButtons;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        //this.stage.setFullScreen(true);
        this.root = new Group();
        this.scene = new Scene(root, columnCount * 100, rowCount * 100);
        this.stage.setScene(this.scene);
        createBoard();
        this.stage.setTitle("4ever");
        displayBoard();
    }

    private void setupListeners() {
        this.scene.heightProperty().addListener(observable -> displayBoard());
        this.scene.widthProperty().addListener(observable -> displayBoard());
        for(int buttonIndex = 0; buttonIndex<columnButtons.size(); buttonIndex++){
            int index=buttonIndex;
            //todo
            //columnButtons.get(buttonIndex).setOnMouseClicked(mouseEvent -> animateStoneDrop(index, rowCount-1, testPlayer));
        }
    }

    private void animateStoneDrop(int xPosition, int yPosition, Player player) {
        Circle stone = new Circle(boardStartX + xPosition * gridDistance + gridDistance / 2,
                boardStartY, this.referenceStone.getRadius(), player.getColor());
        this.root.getChildren().add(0, stone);
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
        this.stage.show();
        this.board.get(xPosition).set(yPosition, player);
    }

    private void createBoard() {
        this.referenceStone = new Circle();
        this.board = new ArrayList<>();
        this.columnButtons = new LinkedList<>();
        for (int width = 0; width < columnCount; width++) {
            this.board.add(new ArrayList<>());
            this.columnButtons.add(new Rectangle());
            for (int height = 0; height < rowCount; height++) {
                this.board.get(width).add(null);
            }
        }
        setupListeners();
    }

    private void displayBoard() {
        double sceneWidth = this.scene.getWidth();
        double sceneHeight = this.scene.getHeight();
        setReferenceValues(sceneWidth, sceneHeight);
        this.root.getChildren().clear();
        this.root.getChildren().addAll(getStones());
        this.root.getChildren().add(getWallWithHoles(new Rectangle(0, 0, sceneWidth, sceneHeight)));
        this.root.getChildren().add(getColumnButtons());
        this.stage.show();
    }

    //todo
    private Group getColumnButtons() {
        Group buttons = new Group();
        for (int buttonIndex = 0; buttonIndex < columnButtons.size(); buttonIndex++){
            Rectangle button = columnButtons.get(buttonIndex);
            button.setX(boardStartX + gridDistance*buttonIndex);
            button.setY(boardStartY);
            button.setWidth(gridDistance);
            button.setHeight(gridDistance*rowCount);
            button.setFill(Color.TRANSPARENT);
            buttons.getChildren().add(button);
        }
        return buttons;
    }

    private List<Circle> getStones() {
        List<Circle> list = new LinkedList<>();
        for (int width = 0; width < board.size(); width++) {
            for (int height = 0; height < board.get(0).size(); height++) {
                Player player = board.get(width).get(height);
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
        for (double width = boardStartX; width < boardStartX + gridDistance * columnCount - 1; width = width + gridDistance) {
            this.referenceStone.setCenterX(width + gridDistance / 2);
            for (double height = boardStartY; height < boardStartY + gridDistance * rowCount - 1; height = height + gridDistance) {
                this.referenceStone.setCenterY(height + gridDistance / 2);
                wall = Shape.subtract(wall, this.referenceStone);
            }
        }
        wall.setFill(Color.LIGHTGREY);
        return wall;
    }

    private void setReferenceValues(double sceneWidth, double sceneHeight){
        if (sceneWidth / columnCount > sceneHeight / rowCount) {
            gridDistance = sceneHeight / (rowCount + boarderDistance);
            boardStartX = (sceneWidth - gridDistance * (columnCount + boarderDistance)) / 2 + gridDistance * boarderDistance/2;
            boardStartY = gridDistance * boarderDistance/2;
        } else {
            gridDistance = sceneWidth / (columnCount + boarderDistance);
            boardStartY = (sceneHeight - gridDistance * (rowCount + boarderDistance)) / 2 + gridDistance * boarderDistance/2;
            boardStartX = gridDistance * boarderDistance/2;
        }
        this.referenceStone.setRadius(gridDistance * columnCount / 16);
    }
}