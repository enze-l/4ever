package org.enzenberger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.enzenberger.control.GameController;
import org.enzenberger.control.selectionWindow.SelectionWindowController;
import org.enzenberger.model.Board;
import org.enzenberger.model.Game;
import org.enzenberger.model.GameState;
import org.enzenberger.view.BoardView;

import java.io.IOException;
import java.util.Stack;

/**
 * Class to start game and bring objects into relation with each other
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private Scene scene;
    private AnchorPane rootLayout;

    private StackPane selectionWindow;
    private Stack<String> selectionWindowOrder;

    private final Game game;
    private final GameController gameController;

    /**
     * Starting the app is done trough this method
     *
     * @param args parameters passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Constructor used for creating all Objects that are de facto final
     */
    public MainApp() {
        this.game = new Game();
        this.gameController = new GameController();
        this.gameController.setGame(this.game);
        this.selectionWindowOrder = new Stack<>();
    }

    /**
     * JavaFX GUI is initialized
     *
     * @param primaryStage the window in which content can be displayed
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("4ever");

        initRootLayout();
        initKeyPressedListeners();
        initBoard();
        showPlayerSelection();
        primaryStage.show();
    }

    /**
     * Registering Scene as KeyPressedListener
     */
    private void initKeyPressedListeners() {
        this.scene.setOnKeyReleased(keyEvent -> handleKeyEvent(keyEvent.getCode()));
        this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    }

    /**
     * Handling Key Events
     * @param code the code of the key being pressed
     */
    private void handleKeyEvent(KeyCode code) {
        switch (code) {
            case ESCAPE:
                handleGoBackAction();
                break;
            case DIGIT1:
                this.gameController.onColumnClicked(0);
                break;
            case DIGIT2:
                this.gameController.onColumnClicked(1);
                break;
            case DIGIT3:
                this.gameController.onColumnClicked(2);
                break;
            case DIGIT4:
                this.gameController.onColumnClicked(3);
                break;
            case DIGIT5:
                this.gameController.onColumnClicked(4);
                break;
            case DIGIT6:
                this.gameController.onColumnClicked(5);
                break;
            case DIGIT7:
                this.gameController.onColumnClicked(6);
                break;
            case F11:
                toggleFullscreen();

        }
    }

    private void toggleFullscreen(){
        if (this.primaryStage.isFullScreen())this.primaryStage.setFullScreen(false);
        else this.primaryStage.setFullScreen(true);
    }

    /**
     * Loading and the root-layout
     */
    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("RootLayout.fxml"));
            rootLayout = loader.load();

            this.scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(Board.columnCount * 50);
            primaryStage.setMinHeight(Board.rowCount * 50);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing the Board View and Controller
     */
    private void initBoard() {
        BoardView boardView = BoardView.getInstance();
        boardView.setScene(this.primaryStage.getScene());
        boardView.setGame(this.game);
        boardView.setBoardClickListener(this.gameController);
        this.rootLayout.getChildren().add(boardView.getBoardGroup());
    }

    /**
     * showing a menu to select the player configuration
     */
    public void showPlayerSelection() {
        showWindowMenu("PlayerSelection.fxml");
    }

    /**
     * showing a menu to select the gaming mode
     */
    public void showModeSelection() {
        showWindowMenu("ModeSelection.fxml");
    }

    /**
     * showing a panel to manage online-connections
     */
    public void showConnectionPanel() {
        showWindowMenu("ConnectionPanel.fxml");
    }

    /**
     * showing a panel to chose the difficulty of the virtual opponent
     */
    public void showVirtualPlayerSelection() {
        showWindowMenu("VirtualPlayerSelection.fxml");
    }

    /**
     * Handling a goBack action is done here. If the board is in focus it shows the menu.
     * Inside a menu it shows the hierarchical previous menu.
     * If the last menu is displayed, it closes the App
     */
    public void handleGoBackAction() {
        if (this.selectionWindow.isVisible()) {
            this.selectionWindowOrder.pop();
            if (this.selectionWindowOrder.empty()) {
                this.exit();
            } else {
                showWindowMenu(this.selectionWindowOrder.pop());
            }
        } else {
            this.selectionWindow.setVisible(true);
        }
    }

    /**
     * Enables to show a menu by using it's fxml layout name
     *
     * @param resourceName the name of the fxml resource file
     */
    private void showWindowMenu(String resourceName) {
        if (this.selectionWindow == null) {
            loadWindowResource(resourceName);
            this.rootLayout.getChildren().add(selectionWindow);
        } else {
            loadWindowResource(resourceName);
            this.rootLayout.getChildren().set(this.rootLayout.getChildren().size() - 1, this.selectionWindow);
        }
        this.selectionWindow.visibleProperty().set(true);
        this.selectionWindowOrder.push(resourceName);
    }

    /**
     * loading a fxml resource and configuring it to be correctly displayed
     *
     * @param resourceName the name of the fxml-file
     */
    private void loadWindowResource(String resourceName) {
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

    /**
     * CLosing the App and handling relevant clean up
     */
    public void exit() {
        this.primaryStage.close();
    }

    /**
     * hiding the current selectionWindow is done here
     */
    public void hideSelectionWindow() {
        selectionWindow.visibleProperty().set(false);
    }

    /**
     * Starting a game
     */
    public void startGame() {
        hideSelectionWindow();
        this.game.setCurrentPlayer(this.game.getPlayer1());
        this.gameController.startGame();
    }
}