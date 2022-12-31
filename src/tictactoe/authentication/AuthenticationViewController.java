/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package tictactoe.authentication;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import tictactoe.authentication.login.LoginViewController;
import tictactoe.authentication.signup.SignupViewController;
import tictactoe.components.tabview.TabView;
import tictactoe.resources.styles.Styles;
import tictactoe.router.RouteViewController;
import tictactoe.utils.UIHelper;

public class AuthenticationViewController extends RouteViewController {

    @FXML
    private Region background;
    @FXML
    private Label gameTitle;
    @FXML
    private Button backButton;
    @FXML
    private BorderPane cardView;

    @Override
    public URL getViewUri() {
        return getClass().getResource("AuthenticationView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scene().getStylesheets().add(resourcesLoader().getCss(Styles.AUTHENTICATION_STYLE_STRING).toString());
        background.setEffect(UIHelper.createBlurEffect());
        backButton.setOnAction((e) -> router().pop());
        
        List<TabView.Tab> tabs = new LinkedList<>();
        tabs.add(new TabView.Tab("Login", attachController(new LoginViewController())));
        tabs.add(new TabView.Tab("Sign up", attachController(new SignupViewController())));
        
        TabView tabView = new TabView(tabs);
        
        cardView.setCenter(tabView);
    }
}