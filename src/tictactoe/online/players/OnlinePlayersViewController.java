/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package tictactoe.online.players;

import TicTacToeCommon.models.UserModel;
import TicTacToeCommon.models.requests.OnlinePlayersRequest;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import tictactoe.base.ViewModelListener;
import tictactoe.components.onlineplayercell.OnlinePlayerCell;
import tictactoe.resources.styles.Styles;
import tictactoe.router.RouteViewController;
import tictactoe.utils.UIHelper;

public class OnlinePlayersViewController extends RouteViewController implements ViewModelListener<Boolean> {

    @FXML
    private Region background;
    @FXML
    private Label gameTitle;
    @FXML
    private Button backButton;
    @FXML
    private Button startButton;
    @FXML
    private ListView<UserModel> playersList;

    private OnlinePlayersViewModel viewModel;

    @Override
    public URL getViewUri() {
        return getClass().getResource("OnlinePlayersView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewModel = new OnlinePlayersViewModel(handle().socketHandler());
        scene().getStylesheets().add(resourcesLoader().getCss(Styles.ONLINE_PLAYERS_STYLE_STRING).toString());
        background.setEffect(UIHelper.createBlurEffect());
        backButton.setOnAction(router()::pop);
        playersList.setItems(viewModel.getData());
        playersList.setCellFactory((e) -> {
            return new OnlinePlayerCell(this);
        });
        viewModel.bind(this);
        viewModel.fetch();
        uIAlert().showLoadingDialog("loading", "getting data");
    }

    @Override
    public void didUpdateState(Boolean newState) {
        if (newState == false) {
            uIAlert().showErrorDialog("Error", "Error loading online players");
            router().pop(false);
        } else if (newState == true) {
            uIAlert().close();
        }
    }

    @Override
    public void onClosed() {
        viewModel.unbind(this);
    }
}
