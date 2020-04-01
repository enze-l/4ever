package org.enzenberger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.enzenberger.control.GameController;
import org.enzenberger.control.selectionWindow.SelectionWindowController;
import org.enzenberger.model.Board;
import org.enzenberger.model.Game;
import org.enzenberger.view.BoardView;

import java.io.IOException;

public class MainApp extends Application {


    private Stage primaryStage;
    private AnchorPane rootLayout;
    private StackPane selectionWindow;
    private final Game game;
    private GameController gameController;

    public static void main(String[] args) {
        launch(args);
    }

    public MainApp() {
        this.game = new Game();
        this.gameController = new GameController();
        this.gameController.setGame(this.game);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("4ever");

        initRootLayout();
        showBoard();
        showPlayerSelection();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(Board.columnCount * 50);
            primaryStage.setMinHeight(Board.rowCount * 50);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showBoard() {
        BoardView boardView = BoardView.getInstance();
        boardView.setScene(this.primaryStage.getScene());
        boardView.setGame(this.game);
        boardView.setBoardClickListener(this.gameController);
        this.rootLayout.getChildren().add(boardView.getBoardGroup());
    }

    public void showPlayerSelection() {
        showWindow("PlayerSelection.fxml");
    }

    public void showModeSelection() {
        showWindow("ModeSelection.fxml");
    }

    public void showConnectionPanel() {
        showWindow("ConnectionPanel.fxml");
    }

    public void showVirtualPlayerSelection() {
        showWindow("VirtualPlayerSelection.fxml");
    }

    private void showWindow(String resourceName) {
        if (this.selectionWindow==null) {
            loadWindowResource(resourceName);
            this.rootLayout.getChildren().add(selectionWindow);
        } else {
            loadWindowResource(resourceName);
            this.rootLayout.getChildren().set(this.rootLayout.getChildren().size()-1, this.selectionWindow);
        }
        this.selectionWindow.visibleProperty().set(true);
    }

    private void loadWindowResource(String resourceName){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(resourceName));
            selectionWindow = loader.load();
            SelectionWindowController selectionWindowController = loader.getController();
            selectionWindowController.setGame(this.game);
            selectionWindowController.setMainApp(this);

            selectionWindow.setAlignment(Pos.CENTER);
            selectionWindow.setPrefHeight(rootLayout.getHeight());
            selectionWindow.setPrefWidth(rootLayout.getWidth());
            rootLayout.widthProperty().addListener(observable -> selectionWindow.setPrefWidth(rootLayout.getWidth()));
            rootLayout.heightProperty().addListener(observable -> selectionWindow.setPrefHeight(rootLayout.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        this.primaryStage.close();
    }

    public void hideSelectionWindow() {
        selectionWindow.visibleProperty().set(false);
    }

    public void startGame() {
        hideSelectionWindow();
        //todo
    }
}