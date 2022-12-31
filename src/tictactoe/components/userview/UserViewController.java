/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package tictactoe.components.userview;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tictactoe.authentication.AuthenticationViewController;
import tictactoe.base.ViewController;
import tictactoe.resources.images.Images;
import tictactoe.user.UserAccountViewController;

public class UserViewController extends ViewController {

    @FXML
    private ImageView userImage;
    @FXML
    private Label username;
    @FXML
    private Button authButton;

    @Override
    public URL getViewUri() {
        return getClass().getResource("UserView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userImage.setImage(new Image(resourcesLoader().getImageAsStream(Images.USER_IC_STRING)));
        username.setText("Local user");
        authButton.setText("Sign in");
        authButton.setOnAction((e) -> {
            router().push(new AuthenticationViewController());
        });
        userImage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            router().push(new UserAccountViewController());
            event.consume();
        });
    }
}
