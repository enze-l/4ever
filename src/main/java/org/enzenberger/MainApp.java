package org.enzenberger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.enzenberger.control.ModeSelectionController;
import org.enzenberger.control.PlayerSelectionController;
import org.enzenberger.model.Game;
import org.enzenberger.view.BoardView;

import java.io.IOException;

public class MainApp extends Application {


    private Stage primaryStage;
    private AnchorPane rootLayout;

    private final Game game;

    public static void main(String[] args) {
        launch(args);
    }

    public MainApp() {
        this.game = new Game();
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
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showBoard() {
        BoardView boardView = BoardView.getInstance();
        boardView.setScene(this.primaryStage.getScene());
        boardView.setGame(this.game);
        this.rootLayout.getChildren().add(boardView.getBoardGroup());
    }

    private void showPlayerSelection() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PlayerSelection.fxml"));
            StackPane playerSelectionWindow = loader.load();
            PlayerSelectionController playerSelectionController = loader.getController();
            playerSelectionController.setGame(this.game);
            playerSelectionController.setMainApp(this);

            playerSelectionWindow.setAlignment(Pos.CENTER);
            playerSelectionWindow.setPrefHeight(rootLayout.getHeight());
            playerSelectionWindow.setPrefWidth(rootLayout.getWidth());
            rootLayout.widthProperty().addListener(observable -> playerSelectionWindow.setPrefWidth(rootLayout.getWidth()));
            rootLayout.heightProperty().addListener(observable -> playerSelectionWindow.setPrefHeight(rootLayout.getHeight()));

            this.rootLayout.getChildren().add(playerSelectionWindow);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showModeSelection() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ModeSelection.fxml"));
            StackPane modeSelectionWindow = loader.load();
            ModeSelectionController modeSelectionController = loader.getController();
            modeSelectionController.setGame(this.game);
            modeSelectionController.setMainApp(this);

            modeSelectionWindow.setAlignment(Pos.CENTER);
            modeSelectionWindow.setPrefHeight(rootLayout.getHeight());
            modeSelectionWindow.setPrefWidth(rootLayout.getWidth());
            rootLayout.widthProperty().addListener(observable -> modeSelectionWindow.setPrefWidth(rootLayout.getWidth()));
            rootLayout.heightProperty().addListener(observable -> modeSelectionWindow.setPrefHeight(rootLayout.getHeight()));

            this.rootLayout.getChildren().add(modeSelectionWindow);


        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showConnectionPanel() {
        //todo
    }
}