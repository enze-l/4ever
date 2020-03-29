package org.enzenberger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.enzenberger.model.Game;

import java.io.IOException;

public class MainApp extends Application {


    private Stage primaryStage;
    private AnchorPane rootLayout;

    private final Game game;

    public static void main(String[] args) {
        launch(args);
    }

    public MainApp(){
        this.game = new Game();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("4ever");

        initRootLayout();
        initBoard();
        showSetup();
    }

    private void initRootLayout() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RootLayout.fxml"));
            rootLayout = (AnchorPane)loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSetup() {
        //todo
    }

    private void initBoard() {
        //todo
    }
}
