/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.game.result;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import tictactoe.resources.styles.Styles;
import tictactoe.router.RouteViewController;
import tictactoe.utils.UIHelper;

/**
 * FXML Controller class
 *
 * @author ITI
 */
public class WinnerPlayerController extends RouteViewController{

    @FXML
    private MediaView videoView;
    @FXML
    private Button backButton;
    @FXML
    private Region background;
    
    private File file;
    private Media media ;
    private MediaPlayer mediaPlayer;
    

    @Override
    public URL getViewUri() {
        return getClass().getResource("WinnerPlayer.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scene().getStylesheets().add(resourcesLoader().getCss(Styles.BASE_STYLE_STRING).toString());
        background.setEffect(UIHelper.createBlurEffect());
         file = new File("");
         media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        videoView.setMediaPlayer(mediaPlayer);
        backButton.setOnAction((e) -> {
            router().pop();
        });
    }    
    
}
