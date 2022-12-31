/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package tictactoe.game;

import java.net.URL;
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

    @Override
    public URL getViewUri() {
        return getClass().getResource("GameView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scene().getStylesheets().add(resourcesLoader().getCss(Styles.GAME_STYLE_STRING).toString());
        background.setEffect(UIHelper.createBlurEffect());
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                ImageView image = new ImageView();
                Node node = new StackPane(image);
                StackPane.setAlignment(image, Pos.CENTER);
                gameGrid.add(node, x, y);
                node.setOnMouseClicked(this::handleOnGridClicked);
            }
        }
        backButton.setOnAction(router()::pop);
        for (Rectangle finishLine: getFinishLines()) {
            finishLine.setVisible(false);
        }
    }
    
    private Rectangle[] getFinishLines() {
        return new Rectangle[]{
            finishLineLeft, finishLineMiddleV, finishLineRight, 
            finishLineTop, finishLineMiddleH, finishLineBottom, 
            finishLineTopLeft, finishLineTopRight
        };
    }
    
    private void handleOnGridClicked(MouseEvent event) {
        Pane source = (Pane)event.getSource();
        source.setOnMouseClicked(null);
        source.getChildren().get(0).getStyleClass().add("XImage");
        int colIndex = ObjectUtils.getOrElse(GridPane.getColumnIndex(source), 0);
        int colRow = ObjectUtils.getOrElse(GridPane.getRowIndex(source), 0);
        logger.log(Level.INFO, "Pressed at {0} {1}", new Object[]{colIndex, colRow});
    }
}
