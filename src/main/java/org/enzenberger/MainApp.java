package org.enzenberger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.enzenberger.control.SelectionWindow;
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

    private void showWindow(String resourceName){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(resourceName));
            StackPane selectionWindow = loader.load();
            SelectionWindow selectionWindowController = loader.getController();
            selectionWindowController.setGame(this.game);
            selectionWindowController.setMainApp(this);

            selectionWindow.setAlignment(Pos.CENTER);
            selectionWindow.setPrefHeight(rootLayout.getHeight());
            selectionWindow.setPrefWidth(rootLayout.getWidth());
            rootLayout.widthProperty().addListener(observable -> selectionWindow.setPrefWidth(rootLayout.getWidth()));
            rootLayout.heightProperty().addListener(observable -> selectionWindow.setPrefHeight(rootLayout.getHeight()));

            this.rootLayout.getChildren().add(selectionWindow);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void exit(){
        this.primaryStage.close();
    }

    public void hideSelectionWindow() {
        //todo
    }
}