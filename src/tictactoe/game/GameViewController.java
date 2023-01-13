/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package tictactoe.game;

import TicTacToeCommon.models.MoveModel;
import TicTacToeCommon.models.events.GameEvent;
import TicTacToeCommon.services.engine.piece.League;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import tictactoe.game.providers.GameProvider;
import tictactoe.resources.styles.Styles;
import tictactoe.router.RouteViewController;
import tictactoe.utils.ObjectUtils;
import tictactoe.utils.UIHelper;

public class GameViewController extends RouteViewController {

    @FXML
    private Region background;
    @FXML
    private Label gameTitle;
    @FXML
    private GridPane gameGrid;
    @FXML
    private Button backButton;
    @FXML
    private Label player1Username;
    @FXML
    private Label player2Username;
    @FXML
    private Label timer;
    @FXML
    private Button recordButton;
    @FXML
    private Rectangle finishLineLeft;
    @FXML
    private Rectangle finishLineMiddleV;
    @FXML
    private Rectangle finishLineRight;
    @FXML
    private Rectangle finishLineTop;
    @FXML
    private Rectangle finishLineMiddleH;
    @FXML
    private Rectangle finishLineBottom;
    @FXML
    private Rectangle finishLineTopLeft;
    @FXML
    private Rectangle finishLineTopRight;

    private final List<Pane> squares = new LinkedList<>();

    private final GameProvider gameProvider;

    public GameViewController(GameProvider gameProvider) {
        this.gameProvider = gameProvider;
    }

    @Override
    public URL getViewUri() {
        return getClass().getResource("GameView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scene().getStylesheets().add(resourcesLoader().getCss(Styles.GAME_STYLE_STRING).toString());
        background.setEffect(UIHelper.createBlurEffect());
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                ImageView image = new ImageView();
                Pane node = new StackPane(image);
                StackPane.setAlignment(image, Pos.CENTER);
                gameGrid.add(node, x, y);
                node.setOnMouseClicked(this::handleOnGridClicked);
                squares.add(node);
            }
        }
        backButton.setOnAction((e) -> gameProvider.withdraw());

        for (Rectangle finishLine : getFinishLines()) {
            finishLine.setVisible(false);
        }

        gameProvider.getEvents().addListener((event) -> {
            if (event instanceof GameEvent.Moved) {
                handleMove(((GameEvent.Moved) event).getMove());
            } else if (event instanceof GameEvent.Ended) {
                router().pop(false);
            } else if (event instanceof GameEvent.Withdraw) {
                // handle opponent withdraw
            } else if (event instanceof GameEvent.Won) {
                // handle winning
            } else if (event instanceof GameEvent.Lost) {
                // handle losing
            }
        });
        gameProvider.getLastMoveResult().addListener((isValid) -> {
            if (isValid == false) {
                uIAlert().showErrorDialog("Invalid move", "Invalid move made");
            }
        });

        gameProvider.getCanInput().addListener((isValid) -> {
            if (isValid == false) {
                uIAlert().showErrorDialog("Invalid move", "Invalid move made");
            }
        });
    }

    @Override
    public void onClosed() {
        gameProvider.close();
    }

    private Rectangle[] getFinishLines() {
        return new Rectangle[]{
            finishLineLeft, finishLineMiddleV, finishLineRight,
            finishLineTop, finishLineMiddleH, finishLineBottom,
            finishLineTopLeft, finishLineTopRight
        };
    }

    private void handleOnGridClicked(MouseEvent event) {
        if (gameProvider.getCanInput().getValue()) {
            Pane source = (Pane) event.getSource();
            int colIndex = ObjectUtils.getOrElse(GridPane.getColumnIndex(source), 0);
            int colRow = ObjectUtils.getOrElse(GridPane.getRowIndex(source), 0);
            int index = colRow * 3 + colIndex;
            gameProvider.makeMove((byte) index);
            logger.log(Level.INFO, "Pressed at {0} {1}", new Object[]{colIndex, colRow});
        }
    }

    private void handleMove(MoveModel move) {
        Pane pane = squares.get(move.getSpacePosition());
        pane.setOnMouseClicked(null);
        League league;
        if (move.getPlayerId().equals(gameProvider.getPlayer1().getId())) {
            league = gameProvider.getPlayer1League();
        } else {
            league = gameProvider.getPlayer2League();
        }
        if (league.equals(league.Cross)) {
            pane.getChildren().get(0).getStyleClass().add("XImage");
        } else {
            pane.getChildren().get(0).getStyleClass().add("OImage");
        }
    }
}
