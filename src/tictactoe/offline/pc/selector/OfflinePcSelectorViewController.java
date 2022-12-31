/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package tictactoe.offline.pc.selector;

import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import tictactoe.game.GameViewController;
import tictactoe.resources.styles.Styles;
import tictactoe.router.RouteViewController;
import tictactoe.utils.UIHelper;

public class OfflinePcSelectorViewController extends RouteViewController {

    @FXML
    private Region background;
    @FXML
    private Label gameTitle;
    @FXML
    private Button backButton;
    @FXML
    private Button easyButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button hardButton;

    @Override
    public URL getViewUri() {
        return getClass().getResource("OfflinePcSelectorView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scene().getStylesheets().add(resourcesLoader().getCss(Styles.OFFLINE_PC_SELECTOR_STYLE_STRING).toString());
        background.setEffect(UIHelper.createBlurEffect());
        backButton.setOnAction(router()::pop);
        easyButton.setOnAction(this::goToGame);
        mediumButton.setOnAction(this::goToGame);
        hardButton.setOnAction(this::goToGame);
    }
    
    private void goToGame(EventObject e) {
        router().push(new GameViewController());
    }
}
